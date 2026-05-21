package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.ClientIDType;
import com.avivacanada.services.fuse.notification.v1.ClientInfoType;
import com.avivacanada.services.fuse.notification.v1.ClientNameType;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notification.v1.YesNo;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationFuseRsMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationFuseRsMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationFuseRsMapper");

		Object header = exchange.getIn().getHeader("ClientInfo");
		ClientInfoType clientInfo = null;
		if (header != null)
			clientInfo = (ClientInfoType) header;

		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationResponse outSendPhoneNotificationResponse = new com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationResponse();
		if (clientInfo != null) {
			try {
				clientInfo.setTransactionTime(NotificationUtils.getCurrentTimestamp());
			} catch (DatatypeConfigurationException e) {
				throw NotificationUtils.generateServiceException(exchange,
						NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
						NotificationConstants.ERRORCODE_SYSTEM_FETCH_TIMESTAMP_FAILURE,
						NotificationConstants.ERRORMESSAGE_SYSTEM_FETCH_TIMESTAMP_FAILURE
								+ " with details: " + e.toString());
			}
			clientInfo.setClientID(ClientIDType.CC);
			clientInfo.setClientName(ClientNameType.CLAIM_CENTER);
			outSendPhoneNotificationResponse.setClientInfo(clientInfo);
		};
		
		outSendPhoneNotificationResponse.setIsSuccessful(YesNo.Y);
		exchange.getIn().setBody(outSendPhoneNotificationResponse);

		log.info(NotificationConstants.LOGTAG + "At the end of processor of PhoneNotificationFuseRsMapper");
	}
}
