/*package com.aviva.ca.esb.api.apollo.processor;

import static org.junit.Assert.assertEquals;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;

import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


import org.apache.camel.Exchange;
import org.apache.camel.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

public class ApolloCCServicePreProcessorTest {
	private Exchange exchange;
	private Message message;
	
	@Before
	public void setUpMocks() {
	exchange = Mockito.mock(Exchange.class);
	message = Mockito.mock(Message.class);
	}
	
	@Test
	public void testApolloCCServiceProcessorEstimates() throws Exception{
		
		PowerMockito.when(exchange.getProperty("EventRequestString", String.class)).thenReturn("Event");
		
		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		ApolloCCServicePreProcessor apolloCCServicePreProcessor=new ApolloCCServicePreProcessor();
		apolloCCServicePreProcessor.process(exchange);
		SOAPMessage soapMsg = exchange.getIn().getBody(SOAPMessage.class);
		SOAPPart part = soapMsg.getSOAPPart();
		SOAPEnvelope envelope = part.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();

		SOAPBodyElement element = (SOAPBodyElement) body.getElementName();
		String data = element.getNodeValue();
		assertEquals(data, "Event");
	}
}
*/