package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notificationcallback.v1.StatusupdateRequestType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationStatusUpdateRqLogging implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationStatusUpdateRqLogging.class);
	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationStatusUpdateRqLogging");
		String jsonTextMessage = "";
		
		Object[] args = exchange.getIn().getBody(Object[].class);
		StatusupdateRequestType notificationStatusupdateRequest = (StatusupdateRequestType) args[0];

		String claimNumber = notificationStatusupdateRequest.getClaimNo();
		if (claimNumber == null || "".equalsIgnoreCase(claimNumber.trim())) {
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_STATUSUPDATE_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_STATUSUPDATE_INBOUND_REQUEST_MISSING_CLAIM_NUMBER, 
					NotificationConstants.ERRORMESSAGE_STATUSUPDATE_INBOUND_REQUEST_MISSING_CLAIM_NUMBER);
		}

		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER_TYPE, "ClaimNumber");
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER, claimNumber);

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonTextMessage = mapper.writeValueAsString(notificationStatusupdateRequest);
		} catch (JsonProcessingException e) {
			throw NotificationUtils.wrapInternalSystemException(exchange, "Unable to convert message to queue with JsonProcessingException " + e.toString());
		}

		exchange.getIn().setHeader(NotificationConstants.TEXTMESSAGE, jsonTextMessage);
		
		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationStatusUpdateRqLogging");
	}

}
