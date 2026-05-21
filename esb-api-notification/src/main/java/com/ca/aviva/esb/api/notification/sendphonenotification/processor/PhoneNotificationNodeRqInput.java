package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.ByteArrayInputStream;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.component.jms.JmsMessage;

public class PhoneNotificationNodeRqInput extends PhoneNotificationNodeProcessor implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeRqInput.class);
	private String messageBody = null;

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationNodeRqInput");
		initializeProperty(exchange);
		JmsMessage jmsMessage = exchange.getIn().getBody(JmsMessage.class);

		TextMessage textMessage = null;
		byte[] msgBuffer = null;

		ActiveMQMessage amqMessage = (ActiveMQMessage) jmsMessage.getJmsMessage();

		if (amqMessage != null) {
			textMessage = (TextMessage) amqMessage;
		};

		if (textMessage == null)
			return;

		try {
			String message = textMessage.getText();
			if (message != null)
			{
				exchange.getIn().setHeader(NotificationConstants.TEXTMESSAGE, message);
				message = getMessageWithoutMessageBody(message);
				msgBuffer = message.getBytes();
			};
		} catch (JMSException e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange, "JMSException within PhoneNotificationNodeRqInput "+e.toString());
		}

		SendPhoneNotificationRequest ccRequestMessage = null;
		try {
			ccRequestMessage = getCCRequestMessage(msgBuffer);
			log.info("Successfully cast to SendPhoneNotificationRequest from byte stream");
		} catch (JAXBException e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange, "JAXBException within PhoneNotificationNodeRqInput "+e.toString());
		}
		ccRequestMessage.setMessage(messageBody);
		exchange.getIn().setBody(ccRequestMessage);
		log.info(NotificationConstants.LOGTAG + " at the end of processor PhoneNotificationNodeRqInput");
	}

	private SendPhoneNotificationRequest getCCRequestMessage(byte[] msgBuffer) throws JAXBException {
		if (msgBuffer == null)
			return null;
		SendPhoneNotificationRequest ccRequestMessage = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(msgBuffer);
		log.info("Casting message to SendPhoneNotificationRequest Type");

		JAXBContext jaxbContext = JAXBContext.newInstance(SendPhoneNotificationRequest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ccRequestMessage = (SendPhoneNotificationRequest) jaxbUnmarshaller.unmarshal(bis);

		return ccRequestMessage;
	}
	
	private String getMessageWithoutMessageBody(String message)
	{
		String startStr = "<message>";
		int location = message.indexOf(startStr) + startStr.length();
		String endStr = message.substring(location);
		message = message.substring(0, location);
		location = endStr.indexOf("</message>");
		messageBody = endStr.substring(0, location);
		endStr= endStr.substring(location);
		return message+endStr;
	}
}
