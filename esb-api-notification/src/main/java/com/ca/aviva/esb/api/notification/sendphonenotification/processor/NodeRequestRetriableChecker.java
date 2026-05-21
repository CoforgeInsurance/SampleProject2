package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import com.avivacanada.avivaapi.claims.v1.twilio.sms.ErrorCodeType;
import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationResponseType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class NodeRequestRetriableChecker implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        NotificationResponseType notificationResponseType = exchange.getIn().getBody(NotificationResponseType.class);
        ErrorCodeType errorCodeType = notificationResponseType.getErrorCode();
        boolean retriable=true;
        int returnCode = notificationResponseType.getReturnCode().intValue();

        if (errorCodeType == ErrorCodeType.NOD5001 || errorCodeType == ErrorCodeType.NOD5002
                || errorCodeType == ErrorCodeType.NOD5003 || errorCodeType == ErrorCodeType.MDB7001||returnCode == NotificationConstants.COP_NODE_RETURNCODE_NONRETRIABLE) {
            retriable = false;
        }
        if (retriable)
            exchange.getIn().setHeader(NotificationConstants.RETRIABLE, true);
        else
            exchange.getIn().setHeader(NotificationConstants.RETRIABLE, false);
    }
}
