package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationFuseRqValidator implements Processor {
	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationFuseRqValidator.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + "in processor of PhoneNotificationFuseRqValidator");
		
		String applicationId = NotificationUtils.getHeaderString(exchange, TransactionLogConstants.APPLICATION_ID);
		if (null == applicationId || applicationId.length() == 0 || TransactionLogConstants.NOT_FOUND.equalsIgnoreCase(applicationId)) {
			log.error(NotificationConstants.LOGTAG + "APPLICATION_ID is missing from the inbound request");
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_APPLICATION_ID, NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_APPLICATION_ID);
		}

		URL url = this.getClass().getClassLoader().getResource("xsd/Notification.xsd");

		Object[] args = exchange.getIn().getBody(Object[].class);
		com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest inSendPhoneNotificationRequest = (com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest) args[0];
		
		JAXBSource source = null;
		SchemaFactory sf = null;

		try {
			JAXBContext jc = JAXBContext
					.newInstance(com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest.class);
			source = new JAXBSource(jc, inSendPhoneNotificationRequest);
			sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		} catch (JAXBException e) {
			log.error(e.getMessage());
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE, NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE + e.toString());
		};

		try {
			Schema schema = sf.newSchema(url);
			Validator validator = schema.newValidator();
			validator.validate(source);
		} catch (SAXException e) {
			log.error(e.getMessage());
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE, NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE + e.toString());
		} 
		catch (IOException e) {
			log.error(e.getMessage());			
			throw NotificationUtils.generateServiceException(exchange, NotificationConstants.FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR, 
					NotificationConstants.ERRORCODE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE, NotificationConstants.ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE + e.toString());
		}
		
		log.info(NotificationConstants.LOGTAG + "end processor of PhoneNotificationFuseRqValidator");
	}
}
