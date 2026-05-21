package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notification.v1.IdentificationCategoryType;
import com.avivacanada.services.fuse.notification.v1.NotificationIdentifierType;

public class PhoneNotificationNodeRqLogging extends PhoneNotificationNodeProcessor implements Processor {
	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeRqLogging.class);

	@Override
	public void process(Exchange exchange) {
		log.info(NotificationConstants.LOGTAG + "in processor of PhoneNotificationNodeRqLogging");

		Object[] args = exchange.getIn().getBody(Object[].class);
		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest request = (com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest) args[0];
		NotificationIdentifierType notificationIdentifier = request.getIdentifier();

		IdentificationCategoryType identificationCategoryEnum = null;
		if (notificationIdentifier != null)
			identificationCategoryEnum = notificationIdentifier.getCategory();
		String identificationCategory = "";
		String claimNumber = "";

		if (identificationCategory != null)
			identificationCategory = identificationCategoryEnum.value();
		if (notificationIdentifier != null)
			claimNumber = notificationIdentifier.getNumber();

		String leftRetries = (String)exchange.getIn().getHeader(NotificationConstants.LEFTRETRIES);
		// populate the left retries in the message to the header
		if (!NotificationConstants.INITIAL_RETRY.equals(leftRetries))
			exchange.getIn().setHeader(NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES, leftRetries);
		else
			exchange.getIn().setHeader(NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES, getTotalRetries());

		String correlationId = (String)exchange.getIn().getHeader(TransactionLogConstants.REQUEST_CORRELATION_ID);
		exchange.setProperty(TransactionLogConstants.REQUEST_CORRELATION_ID, correlationId);
		exchange.setProperty(TransactionLogConstants.POLICY_OR_QUOTE,identificationCategory);		
		exchange.setProperty(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, claimNumber);

		log.info(NotificationConstants.LOGTAG + "end processor of PhoneNotificationNodeRqLogging ");
	}
}
