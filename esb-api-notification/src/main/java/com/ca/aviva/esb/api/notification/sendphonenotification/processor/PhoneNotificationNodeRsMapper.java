package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.avivaapi.claims.v1.twilio.sms.ErrorCodeType;
import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationResponseType;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

public class PhoneNotificationNodeRsMapper extends PhoneNotificationNodeProcessor implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeRsMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationNodeRsMapper");

		Object[] args = exchange.getIn().getBody(Object[].class);
		//Response response = (Response) args[0];
		Response response = (Response) ((Object[])exchange.getProperty("responseObject"))[0];
		String responseMessage = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		NotificationResponseType notificationResponse = null;
		try {
			notificationResponse = objectMapper.readValue(responseMessage.getBytes(), NotificationResponseType.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange,
					"JsonParseException within PhoneNotificationNodeRsMapper " + e.toString());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange,
					"JsonMappingException within PhoneNotificationNodeRsMapper " + e.toString());
		} catch (IOException e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange,
					"IOException within PhoneNotificationNodeRsMapper " + e.toString());
		}

		if (notificationResponse == null)
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_APPLICATION_ERROR,
					NotificationConstants.ERRORCODE_NOTIFICATION_APPLICATION_ERROR,
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_APPLICATION_ERROR
							+ "due to the nontification response empty");
		BigInteger returnCodeInteger = notificationResponse.getReturnCode();
		if (returnCodeInteger == null)
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_APPLICATION_ERROR,
					NotificationConstants.ERRORCODE_NOTIFICATION_APPLICATION_ERROR,
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_APPLICATION_ERROR + "return code is empty");

		int returnCode = returnCodeInteger.intValue();

		if (returnCode == 0) {
			exchange.getIn().setBody(new Object[] { responseMessage });
			log.info(NotificationConstants.LOGTAG
					+ "At the end of processor of PhoneNotificationNodeRsMapper with successful result");
			return;
		};

		ErrorCodeType errorCodeType = notificationResponse.getErrorCode();
		
		if (errorCodeType == null)
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_APPLICATION_ERROR,
					NotificationConstants.ERRORCODE_NOTIFICATION_APPLICATION_ERROR,
					NotificationConstants.ERRORMESSAGE_NOTIFICATION_APPLICATION_ERROR
							+ "return code is not success but error code is empty");

		boolean retriable = true;

		if (errorCodeType == ErrorCodeType.NOD5001 || errorCodeType == ErrorCodeType.NOD5002
				|| errorCodeType == ErrorCodeType.NOD5003 || errorCodeType == ErrorCodeType.MDB7001||returnCode == NotificationConstants.COP_NODE_RETURNCODE_NONRETRIABLE) {
			retriable = false;
		}
		
		if (retriable)
			exchange.getIn().setHeader(NotificationConstants.RETRIABLE, true);
		else
			exchange.getIn().setHeader(NotificationConstants.RETRIABLE, false);

		
		log.info(NotificationConstants.LOGTAG
				+ "At the end of processor of PhoneNotificationNodeRsMapper with application error");
		throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_APPLICATION_ERROR,
				NotificationConstants.ERRORCODE_NOTIFICATION_APPLICATION_ERROR,
				NotificationConstants.ERRORMESSAGE_NOTIFICATION_APPLICATION_ERROR + "with return code " + returnCode
						+ ", error code " + errorCodeType.toString());
	}
}
