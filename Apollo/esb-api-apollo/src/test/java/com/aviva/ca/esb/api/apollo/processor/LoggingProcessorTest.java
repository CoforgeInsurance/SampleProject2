package com.aviva.ca.esb.api.apollo.processor;



import java.util.HashMap;
import java.util.Map;


import org.apache.camel.Exchange;
import org.apache.camel.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;



public class LoggingProcessorTest {
	
	private Exchange exchange;
	private Message message;
	
	@Before
	public void setUpMocks() {
	exchange = Mockito.mock(Exchange.class);
	message = Mockito.mock(Message.class);
	}
	
	@Test
	public void testLoggingProcessorEstimates() throws Exception{
		

	PowerMockito.when(exchange.getIn()).thenReturn(message);
	Map<String,Object> properties=new HashMap<String,Object>();
	PowerMockito.when(exchange.getProperties()).thenReturn(properties);
	properties.put("INBOUND_APPLICATION_ID", "appID");
	PowerMockito.when(exchange.getProperty("INBOUND_APPLICATION_ID")).thenReturn("appID");
	LoggingProcessor loggingProcessor =new LoggingProcessor();
	loggingProcessor.setCommonLoggingProperties(exchange);
	loggingProcessor.setErrorDetails(exchange);
	loggingProcessor.setSuccessDetails(exchange);
	loggingProcessor.setOutboundDetails(exchange);
	loggingProcessor.setInboundDetails(exchange);
}
	
	
}
