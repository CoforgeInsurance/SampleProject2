package com.aviva.ca.esb.api.apollo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.api.apollo.exception.ApolloServiceException;
import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;

public class ApolloResponseMapper implements Processor {

	private final Logger LOGGER = LoggerFactory
			.getLogger(ApolloResponseMapper.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			LOGGER.info("ApolloService :: Inside ApolloResponseMapper ; ClaimNumber : "
					+ exchange
							.getProperty(TransactionLogConstants.MSG_IDENTIFIER)
					+ " ; REQUEST_CORRELATION_ID : "
					+ exchange
							.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
			String response = exchange.getProperty(
					ApolloServiceConstants.EVENT_REQUEST_STRING,
					String.class);
			if(exchange
			.getProperty(ApolloServiceConstants.IS_ERROR,Boolean.class)){
				exchange.setProperty(TransactionLogConstants.STATUS, "ERROR");
			}else{
				exchange.setProperty(TransactionLogConstants.STATUS, "SUCCESS");
			}
		
			exchange.setProperty(TransactionLogConstants.INBOUND_RESPONSE,
					response);
			exchange.getOut().setBody(
					response);

			LOGGER.info("ApolloService :: Exiting ApolloResponseMapper ; ClaimNumber : "
					+ exchange
							.getProperty(TransactionLogConstants.MSG_IDENTIFIER)
					+ " ; REQUEST_CORRELATION_ID : "
					+ exchange
							.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
		} catch (Exception e) {
			throw new ApolloServiceException("REJECT",
					"FUS001 :: Internal Error");
		}
	}

}
