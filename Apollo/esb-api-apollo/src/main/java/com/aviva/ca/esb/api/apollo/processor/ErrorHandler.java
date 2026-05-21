package com.aviva.ca.esb.api.apollo.processor;

import javax.xml.ws.WebServiceException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.aviva.ca.esb.api.apollo.exception.ApolloServiceException;
import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogUtils;
import com.mitchell.schemas.filedelivery.FileReceiverServiceRs;

public class ErrorHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
				Exception.class);
		if (null != cause) {
			exchange.setProperty(ApolloServiceConstants.IS_ERROR, true);
			FileReceiverServiceRs fileReceiverServiceRs = new FileReceiverServiceRs();
			if (cause instanceof WebServiceException) {

				fileReceiverServiceRs.setStatusString("REJECT");
				fileReceiverServiceRs
						.setDescription("FUS003 :: Technical Exception occurred while calling Claim Center service");

			} else if (cause instanceof ApolloServiceException) {

				ApolloServiceException apolloServiceException = (ApolloServiceException) cause;
				fileReceiverServiceRs.setStatusString(apolloServiceException
						.getStatus());
				fileReceiverServiceRs.setDescription(apolloServiceException
						.getDescription());
			} else {
				fileReceiverServiceRs.setStatusString("REJECT");
				fileReceiverServiceRs
						.setDescription("FUS001 :: Internal Error");
			}
			exchange.getIn().setBody(fileReceiverServiceRs);
			String responseString = TransactionLogUtils.getBodyAsXml(exchange);
			exchange.setProperty(ApolloServiceConstants.EVENT_REQUEST_STRING,
					responseString);
			exchange.setProperty(TransactionLogConstants.INBOUND_RESPONSE,
					responseString);
			exchange.getIn().setBody(responseString);
		}
	}

}
