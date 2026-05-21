package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeRequestType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationUnsubscribeRqLogging implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRqLogging.class);
	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationUnsubscribeRqLogging");
		String jsonTextMessage = "";
		
		Object[] args = exchange.getIn().getBody(Object[].class);
		UnsubscribeRequestType notificationUnsubscribeRequest = (UnsubscribeRequestType) args[0];

		String phoneNumber = notificationUnsubscribeRequest.getDestinationNo();
		
		if (phoneNumber == null ||"".equals(phoneNumber.trim()))
		{
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_DESTINATION_NO, 
					NotificationConstants.ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_DESTINATION_NO);
		}
		
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER_TYPE, "DestinationNo");
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER, phoneNumber);

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonTextMessage = mapper.writeValueAsString(notificationUnsubscribeRequest);
		} catch (JsonProcessingException e) {
			throw NotificationUtils.wrapInternalSystemException(exchange, "Unable to convert message to queue with JsonProcessingException " + e.toString());
		}

		exchange.getIn().setHeader(NotificationConstants.TEXTMESSAGE, jsonTextMessage);
		
		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationUnsubscribeRqLogging");
	}

}
