package com.aviva.ca.esb.api.apollo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.mitchell.schemas.mitchellclaimevent.MitchellClaimEvent;

public class InitialQueuePreProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		//MitchellClaimEvent request = exchange.getIn().getBody(MitchellClaimEvent.class);
		exchange.getIn().setBody(
				exchange.getProperty(TransactionLogConstants.INBOUND_REQUEST));
	}

}
