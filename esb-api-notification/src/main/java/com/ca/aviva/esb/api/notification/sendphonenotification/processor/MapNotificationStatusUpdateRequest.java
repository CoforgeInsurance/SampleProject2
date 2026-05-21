package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationRequestType;
import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationResponseType;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.*;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest;
import com.ca.aviva.esb.api.notification.notificationcallback.processor.NotificationStatusUpdateRqMapper;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;

public class MapNotificationStatusUpdateRequest implements Processor {
    public static final Logger log = LoggerFactory.getLogger(NotificationStatusUpdateRqMapper.class);

    @Override
    public void process(Exchange exchange) throws SOAPServiceException {
        NotificationResponseType notificationResponseType = exchange.getIn().getBody(NotificationResponseType.class);
        //NotificationRequestType nodeOriginalRequest = (NotificationRequestType)exchange.getProperty("nodeRequestObject");
        SendPhoneNotificationRequest sendPhoneNotificationRequest = (SendPhoneNotificationRequest)exchange.getProperty("nodeRequestObject");
        log.info(NotificationConstants.LOGTAG + "in the processor of NotificationStatusUpdateRqMapper");

        String nodeSmsErrorCode="";
        String nodeSmsErrorDescription="";
        try {
            if (exchange.getProperty("isTwilioError").toString().equalsIgnoreCase("true")) {
                nodeSmsErrorCode = exchange.getProperty("errorCode").toString();
                nodeSmsErrorDescription = exchange.getProperty("errorMessage").toString();
            } else {
                nodeSmsErrorCode = notificationResponseType.getErrorCode().value();
                nodeSmsErrorDescription = notificationResponseType.getMessage();
            }
        }catch (NullPointerException npe)
        {
            nodeSmsErrorCode = notificationResponseType.getErrorCode().value();
            nodeSmsErrorDescription = notificationResponseType.getMessage();
        }
        //String nodeReturnCode = notificationResponseType.getReturnCode().toString();
        String nodeStatus = nodeSmsErrorDescription;

        log.info("nodeSmsErrorCode = "+nodeSmsErrorCode);
        log.info("nodeSmsErrorDescription = "+nodeSmsErrorDescription);
        log.info("nodeStatus = "+nodeStatus);

        /*String claimNo = nodeOriginalRequest.getClaimNo();
        String ccTransactionId = nodeOriginalRequest.getTransactionRefID();*/
        String claimNo = sendPhoneNotificationRequest.getIdentifier().getNumber();
        String ccTransactionId = sendPhoneNotificationRequest.getClientInfo().getRequestReference();

        com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatus.NotificationStatusUpdateRq outCCStatusUpdateRq = new com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatus.NotificationStatusUpdateRq();
        NotificationStatusUpdateRq ccNotificationStatusUpdateRq = new NotificationStatusUpdateRq();
        outCCStatusUpdateRq.setNotificationStatusUpdateRq(ccNotificationStatusUpdateRq);
        ClientInfoType clientInfo = new ClientInfoType();
        ccNotificationStatusUpdateRq.setClientInfo(clientInfo);
        clientInfo.setClientID(ClientIDType.FUSE);
        clientInfo.setClientName("FuseServer");
        clientInfo.setRequestReference(ccTransactionId); //Needs mapping
        try {
            clientInfo.setTransactionTime(NotificationUtils.getCurrentTimestamp());
        } catch (DatatypeConfigurationException e) {
            throw NotificationUtils.generateServiceException(exchange,
                    NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
                    NotificationConstants.ERRORCODE_SYSTEM_FETCH_TIMESTAMP_FAILURE,
                    NotificationConstants.ERRORMESSAGE_SYSTEM_FETCH_TIMESTAMP_FAILURE + " with details: "
                            + e.toString());
        }

        NotificationStatusInfoType statusInfo = new NotificationStatusInfoType();
        ccNotificationStatusUpdateRq.setNotificationStatusInfo(statusInfo);
        statusInfo.setClaimNumber(claimNo); // Needs mapping

        if (nodeSmsErrorCode != null && !"".equals(nodeSmsErrorCode.trim())&&!"0".equals(nodeSmsErrorCode.trim())) {
            ErrorInfoType errorInfo = new ErrorInfoType();
            if (NotificationConstants.NON_TECHNICAL_ERRORCODE_LIST.indexOf(nodeSmsErrorCode) <0) {
                errorInfo.setCode(nodeSmsErrorCode);
                errorInfo.setDescription(nodeSmsErrorDescription);
                statusInfo.setDeliveryStatus(DeliveryStatusType.FAILED);
            } else {
                errorInfo.setCode(nodeSmsErrorCode);
                statusInfo.setDeliveryStatus(DeliveryStatusType.UNDELIVERED);
                if (nodeSmsErrorDescription != null && !"".equals(nodeSmsErrorDescription))
                    errorInfo.setDescription(nodeSmsErrorDescription);
                else
                    errorInfo.setDescription(NotificationConstants.ERRORMESSAGE_NOTIFICATION_TWILIO_TECHNICAL_ERROR);
            };
            statusInfo.setErrorInfo(errorInfo);
        };


        if ("DELIVERED".equals(nodeStatus))
            statusInfo.setDeliveryStatus(DeliveryStatusType.DELIVERED);

        exchange.getIn().setBody(outCCStatusUpdateRq);
        log.debug(NotificationConstants.LOGTAG + "at the end of NotificationStatusUpdateRqMapper");
    }
}

