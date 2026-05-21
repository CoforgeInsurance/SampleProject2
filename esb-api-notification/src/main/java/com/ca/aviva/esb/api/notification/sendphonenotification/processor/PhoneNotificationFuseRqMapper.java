package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.ClientInfoType;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationFuseRqMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationFuseRqMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationFuseRqMapper");

		Object[] args = exchange.getIn().getBody(Object[].class);
		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest request = (com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest) args[0];
		ClientInfoType clientInfo = request.getClientInfo();
		exchange.getIn().setHeader("ClientInfo", clientInfo);
		
		exchange.getIn().setHeader(NotificationConstants.LEFTRETRIES, NotificationConstants.INITIAL_RETRY);
		Object body = exchange.getIn().getBody();
		Object object = ((MessageContentsList) body).get(0);

		String marshalledString = "";

		try {
			log.info("marshalling object to string");
			marshalledString = SerializeToString(object, StandardCharsets.UTF_8);
		} catch (JAXBException e) {
			log.error("Exception occurred trying to marshall object to string.", e.getMessage());
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_UNMARSHAL_FAILURE, NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_UNMARSHAL_FAILURE+" with details: "+e.toString());
		} 

		exchange.getIn().setBody(marshalledString);
		log.info(NotificationConstants.LOGTAG + " At the end of processor of PhoneNotificationFuseRqMapper");
	}

	private <T> String SerializeToString(final T entity, Charset encoding) throws JAXBException {
		String marshalledString = "";
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(SendPhoneNotificationRequest.class);
		final Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding.name());
		marshaller.marshal(entity, sw);
		marshalledString = sw.toString();
		return marshalledString;
	}
}
