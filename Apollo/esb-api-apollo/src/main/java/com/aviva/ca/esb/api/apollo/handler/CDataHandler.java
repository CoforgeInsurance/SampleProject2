package com.aviva.ca.esb.api.apollo.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
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

import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;

public class CDataHandler implements SOAPHandler<SOAPMessageContext> {
	private static final String GW = "gw";
	private final Logger LOGGER = LoggerFactory.getLogger(CDataHandler.class);

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		LOGGER.info("Client : handleMessage()......");

		Boolean isRequest = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// if this is a request, true for outbound messages, false for inbound
		if (isRequest) {
			LOGGER.info("Client : request message in handleMessage()......");
			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				soapEnv.addNamespaceDeclaration(GW, "http://guidewire.com/ws/soapheaders");
				SOAPHeader soapHeader = soapEnv.getHeader();
				
				if (soapHeader == null)
					soapHeader = soapEnv.addHeader();

				// add locale under SOAPHeader
				SOAPElement locale = soapHeader.addChildElement(ApolloServiceConstants.LOCALE, GW);
				locale.addTextNode(ApolloServiceConstants.EN_US);
				
				// add authentication under SOAPHeader
				SOAPElement authentication = soapHeader.addChildElement(ApolloServiceConstants.AUTHENTICATION, GW);
				
				// add username and password under authentication
				SOAPElement username = authentication.addChildElement(ApolloServiceConstants.USERNAME, GW);
				username.addTextNode((String)context.get(ApolloServiceConstants.CC_USERNAME));

				SOAPElement ccPass = authentication.addChildElement(ApolloServiceConstants.KEY_HEADER_NAME, GW);
				ccPass.addTextNode((String)context.get(ApolloServiceConstants.CC_KEY));

				

				SOAPBody body = soapEnv.getBody();
				body.removeContents();
				SOAPBodyElement element = body.addBodyElement(soapEnv
						.createName("receiveFile", "",
								"http://www.openuri.org/"));
				SOAPElement data = element.addChildElement("payload");

				data.appendChild(data
						.getOwnerDocument()
						.createCDATASection(
								(String) context
										.get(ApolloServiceConstants.EVENT_REQUEST_STRING)));

				soapMsg.saveChanges();
				logSOAPMessage(soapMsg);

			 } catch (SOAPException e) {
				// not necessary to throw SOAPException here since the handler interface not allowed
				 LOGGER.error(ApolloServiceConstants.APOLLO_SERVICE + "HandleHeader got SOAPException");
			} catch (IOException e) {
				LOGGER.error(ApolloServiceConstants.APOLLO_SERVICE + "HandleHeader got IOException");
			}

		} else {
			LOGGER.info("Client : response message in handleMessage()......");
		}
		LOGGER.info("Client : exiting handleMessage()......");
		return true;
	}
	private void logSOAPMessage(SOAPMessage message) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try  
		{
			message.writeTo(bout);
		} catch (SOAPException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
				bout.close();
		}
	}
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
