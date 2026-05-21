package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notification.v1.IdentificationCategoryType;
import com.avivacanada.services.fuse.notification.v1.NotificationIdentifierType;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;

public class PhoneNotificationFuseRqLogging implements Processor {
	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationFuseRqLogging.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + "in processor of PhoneNotificationFuseRqLogging");

		Object[] args = exchange.getIn().getBody(Object[].class);
		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest inSendPhoneNotificationRequest = (com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest) args[0];
		NotificationIdentifierType notificationIdentifier = inSendPhoneNotificationRequest.getIdentifier();
		String notificationCategory = inSendPhoneNotificationRequest.getNotificationCategory();
		
		IdentificationCategoryType identificationCategoryEnum = null;
		if (notificationIdentifier != null)
		 identificationCategoryEnum = notificationIdentifier.getCategory();
		String identificationCategory = "";
		String claimNumber = "";
		
		if (identificationCategoryEnum != null)
			identificationCategory = identificationCategoryEnum.value();
		if (notificationIdentifier != null)
			claimNumber = notificationIdentifier.getNumber();
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER, claimNumber);

		if (claimNumber == null || "".equalsIgnoreCase(claimNumber.trim())) {
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_CLAIM_NUMBER, 
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_CLAIM_NUMBER);
		}

		if (identificationCategory == null || "".equalsIgnoreCase(identificationCategory.trim())) {
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_IDENTIFICATION_TYPE,
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_IDENTIFICATION_TYPE);
		}
		
		if ("Real".equals(notificationCategory))
			exchange.getIn().setHeader(NotificationConstants.COP_NOTIFICATION_QUEUE, "COP_CLAIMNOTIFICATION_REAL_QUEUE");
		else if ("Batch".equals(notificationCategory)) 
			exchange.getIn().setHeader(NotificationConstants.COP_NOTIFICATION_QUEUE, "COP_CLAIMNOTIFICATION_BATCH_QUEUE");			
		else
		{
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_INCORRECT_NOTIFICATION_CATEGORY,
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_INCORRECT_NOTIFICATION_CATEGORY);
		}
	
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER_TYPE, identificationCategory);
		exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER, claimNumber);
		
		log.info(NotificationConstants.LOGTAG + "end processor of PhoneNotificationFuseRqLogging");
	}
}
