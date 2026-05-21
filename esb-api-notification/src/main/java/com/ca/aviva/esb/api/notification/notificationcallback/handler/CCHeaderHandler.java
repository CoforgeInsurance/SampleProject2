package com.ca.aviva.esb.api.notification.notificationcallback.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class CCHeaderHandler implements SOAPHandler<SOAPMessageContext> {

	private final Logger log = LoggerFactory.getLogger(CCHeaderHandler.class);
	private static final String gw = "gw";

	@Override
	public boolean handleMessage(SOAPMessageContext soapMessageContext) {
		log.info(NotificationConstants.LOGTAG + "To HandleHeader of SOAPMessage");
		Boolean outboundProperty = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty !=null && outboundProperty.booleanValue()) {
			// This happens for the webservice request
			log.info(NotificationConstants.LOGTAG + "HandleHeader in the request");
			SOAPMessage message = soapMessageContext.getMessage();

			try {
				SOAPEnvelope envelope = soapMessageContext.getMessage().getSOAPPart().getEnvelope();
				// To specify namespace symbol gw
				envelope.addNamespaceDeclaration(gw, "http://guidewire.com/ws/soapheaders");
				
				// To add the SOAPHeader

				SOAPHeader header = envelope.getHeader();
				if (header == null)
					header = envelope.addHeader();

				// add locale under SOAPHeader
				SOAPElement locale = header.addChildElement("locale", gw);
				locale.addTextNode("en_US");
				
				// add authentication under SOAPHeader
				SOAPElement authentication = header.addChildElement("authentication", gw);
				
				// add username and password under authentication
				SOAPElement username = authentication.addChildElement("username", gw);
				username.addTextNode((String)soapMessageContext.get(NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_USERNAME));

				SOAPElement ccPass = authentication.addChildElement("password", gw);
				ccPass.addTextNode((String)soapMessageContext.get(NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_PASSWORD));

				// log the request message
				logSOAPMessage(message);
			} catch (SOAPException e) {
				// not necessary to throw SOAPException here since the handler interface not allowed
				log.error(NotificationConstants.LOGTAG + "HandleHeader got SOAPException");
				e.printStackTrace();
				log.error(e.getMessage(), e);
			} catch (IOException e) {
				log.error(NotificationConstants.LOGTAG + "HandleHeader got IOException");
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
		} else {
			// This happens for the claim center webservice response
			log.info(NotificationConstants.LOGTAG + "HandleHeader in the response");
			SOAPMessage message = soapMessageContext.getMessage();
			log.info(message.toString());
		}

		log.info(NotificationConstants.LOGTAG + "End of HandleHeader of SOAPMessage");
		return outboundProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders() Added by Roopesh to
	 * log the soap messages in log file
	 */
	private void logSOAPMessage(SOAPMessage message) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try  
		{
			message.writeTo(bout);
		} catch (SOAPException | IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if(bout!=null)
			bout.close();
		}
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}
}