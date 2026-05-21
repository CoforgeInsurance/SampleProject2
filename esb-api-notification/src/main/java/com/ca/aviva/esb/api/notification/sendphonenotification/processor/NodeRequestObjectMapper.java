package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import com.avivacanada.avivaapi.claims.v1.twilio.sms.LanguageType;
import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationRequestType;
import com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class NodeRequestObjectMapper implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        SendPhoneNotificationRequest sendPhoneNotificationRequest = exchange.getIn().getBody(SendPhoneNotificationRequest.class);
        NotificationRequestType notificationRequestType = new NotificationRequestType();
        String language = "";
        notificationRequestType.setClaimNo(sendPhoneNotificationRequest.getIdentifier().getNumber());
        notificationRequestType.setDestinationNo(sendPhoneNotificationRequest.getPhone().getNumber());
        notificationRequestType.setSmsMessage(sendPhoneNotificationRequest.getMessage());
        language = sendPhoneNotificationRequest.getLanguage().value();
        if (language.equalsIgnoreCase("en_US"))
            notificationRequestType.setSmsLanguage(LanguageType.EN_US);
        else if (language.equalsIgnoreCase("fr_CA"))
            notificationRequestType.setSmsLanguage(LanguageType.FR_CA);
        else
        	notificationRequestType.setSmsLanguage(LanguageType.EN_US);
        notificationRequestType.setTransactionRefID(sendPhoneNotificationRequest.getClientInfo().getRequestReference());
        exchange.getIn().setBody(notificationRequestType);
    }
}