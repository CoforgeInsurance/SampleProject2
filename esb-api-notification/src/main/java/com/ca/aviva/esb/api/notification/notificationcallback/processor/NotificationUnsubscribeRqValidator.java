package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeRequestType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;

public class NotificationUnsubscribeRqValidator implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRqValidator.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationStatusUpdateRqValidator");

		String applicationId = NotificationUtils.getHeaderString(exchange, TransactionLogConstants.APPLICATION_ID);
		if (null == applicationId || applicationId.length() == 0 || TransactionLogConstants.NOT_FOUND.equalsIgnoreCase(applicationId)) {
			log.error(NotificationConstants.LOGTAG + "APPLICATION_ID is missing from the inbound request");
			throw NotificationUtils.generateServiceException(exchange, 
					NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_APPLICATION_ID, NotificationConstants.ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_APPLICATION_ID);
		}
		
		Object[] args = exchange.getIn().getBody(Object[].class);

		UnsubscribeRequestType notificationUnsubscribeRequest = (UnsubscribeRequestType) args[0];
		String requestReference = notificationUnsubscribeRequest.getCcTransactionID();
		if (requestReference == null || "".equalsIgnoreCase(requestReference.trim())) {
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE, 
					NotificationConstants.ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE);
		}
		
		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationStatusUpdateRqValidator");
	}
}
