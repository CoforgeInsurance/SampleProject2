package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.*;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notificationcallback.v1.StatusType;
import com.avivacanada.services.fuse.notificationcallback.v1.StatusupdateRequestType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;

public class NotificationStatusUpdateRqMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationStatusUpdateRqMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + "in the processor of NotificationStatusUpdateRqMapper");
		Object[] args = exchange.getIn().getBody(Object[].class);
		StatusupdateRequestType nodeStatusupdateRequest = (StatusupdateRequestType) args[0];
		String ccTransactionId = nodeStatusupdateRequest.getCcTransactionID();
		String claimNo = nodeStatusupdateRequest.getClaimNo();
		String nodeSmsErrorCode = nodeStatusupdateRequest.getSmsErrorCode();
		String nodeSmsErrorDescription = nodeStatusupdateRequest.getSmsErrorDescription();
		StatusType nodeStatusType = nodeStatusupdateRequest.getSmsStatus();

		com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatus.NotificationStatusUpdateRq outCCStatusUpdateRq = new com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatus.NotificationStatusUpdateRq();
		NotificationStatusUpdateRq ccNotificationStatusUpdateRq = new NotificationStatusUpdateRq();
		outCCStatusUpdateRq.setNotificationStatusUpdateRq(ccNotificationStatusUpdateRq);
		ClientInfoType clientInfo = new ClientInfoType();
		ccNotificationStatusUpdateRq.setClientInfo(clientInfo);
		clientInfo.setClientID(ClientIDType.FUSE);
		clientInfo.setClientName("FuseServer");
		clientInfo.setRequestReference(ccTransactionId);
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
		statusInfo.setClaimNumber(claimNo);

		if (nodeSmsErrorCode != null && !"".equals(nodeSmsErrorCode.trim())&&!"0".equals(nodeSmsErrorCode.trim())) {
			ErrorInfoType errorInfo = new ErrorInfoType();
			
			if (NotificationConstants.NON_TECHNICAL_ERRORCODE_LIST.indexOf(nodeSmsErrorCode) <0) {
				errorInfo.setCode(NotificationConstants.ERRORCODE_NOTIFICATION_TWILIO_TECHNICAL_ERROR);
				errorInfo.setDescription(NotificationConstants.ERRORMESSAGE_NOTIFICATION_TWILIO_TECHNICAL_ERROR);
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

		String nodeStatus = nodeStatusType.toString();
		if ("DELIVERED".equals(nodeStatus))
			statusInfo.setDeliveryStatus(DeliveryStatusType.DELIVERED);
		
		exchange.getIn().setBody(outCCStatusUpdateRq);
		log.info(NotificationConstants.LOGTAG + "at the end of NotificationStatusUpdateRqMapper");
	}
}
