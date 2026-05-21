package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class NotificationUnsubscribeRsErrorQueue implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRsErrorQueue.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the  processor of NotificationUnsubscribeRsErrorQueue");
		
		String jsonTextMessage = (String)exchange.getIn().getHeader(NotificationConstants.TEXTMESSAGE);
		exchange.getIn().setBody(jsonTextMessage);

		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationUnsubscribeRsErrorQueue");
	}
}
