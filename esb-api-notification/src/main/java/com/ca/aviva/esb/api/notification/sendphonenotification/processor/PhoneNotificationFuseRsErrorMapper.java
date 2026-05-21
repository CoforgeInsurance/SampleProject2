package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.ClientIDType;
import com.avivacanada.services.fuse.notification.v1.ClientInfoType;
import com.avivacanada.services.fuse.notification.v1.ClientNameType;
import com.avivacanada.services.fuse.notification.v1.DetailException;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notification.v1.YesNo;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationFuseRsErrorMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationFuseRsErrorMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationFuseRsErrorMapper");
		SOAPServiceException serviceException = NotificationUtils.getHeaderSOAPException(exchange,
				NotificationConstants.EXCEPTIONOBJECT);
		Object header = exchange.getIn().getHeader("ClientInfo");
		ClientInfoType clientInfo = null;
		if (header != null)
			clientInfo = (ClientInfoType) header;

		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationResponse outSendPhoneNotificationResponse = new com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationResponse();
		if (clientInfo != null) {
			try {
				clientInfo.setTransactionTime(NotificationUtils.getCurrentTimestamp());
			} catch (DatatypeConfigurationException e) {
				log.error(e.getMessage());
				throw NotificationUtils.generateServiceException(exchange,
						NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
						NotificationConstants.ERRORCODE_SYSTEM_FETCH_TIMESTAMP_FAILURE,
						NotificationConstants.ERRORMESSAGE_SYSTEM_FETCH_TIMESTAMP_FAILURE + " with details: " + e.toString());
			}
			clientInfo.setClientID(ClientIDType.CC);
			clientInfo.setClientName(ClientNameType.CLAIM_CENTER);
			outSendPhoneNotificationResponse.setClientInfo(clientInfo);
		};

		outSendPhoneNotificationResponse.setIsSuccessful(YesNo.N);
		String errorMessage = NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR;
		if (serviceException != null) {
			DetailException detailException = serviceException.getFaultInfo();
			if (detailException != null)
				errorMessage = detailException.getErrorMessage();
		} else {
			Exception exception = (Exception) exchange.getProperty("CamelExceptionCaught");
			if(exception !=null)
			{
				exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, exception.toString());
				serviceException = NotificationUtils.wrapInternalSystemException(exchange, "CamelExceptionCaught with " + exception.toString());
			};
		}
		exchange.getIn().setHeader("RESPONSE_MSG_TYPE", "text/xml;charset=UTF-8");
		exchange.getIn().setBody(outSendPhoneNotificationResponse);

		log.info(NotificationConstants.LOGTAG + "At the end of processor of PhoneNotificationFuseRsErrorMapper");
		if (serviceException != null)
		{
			exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, errorMessage);
			throw serviceException;
		}
	}
}
