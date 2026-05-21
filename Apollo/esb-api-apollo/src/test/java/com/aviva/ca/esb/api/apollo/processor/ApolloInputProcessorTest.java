package com.aviva.ca.esb.api.apollo.processor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.aviva.ca.esb.api.apollo.exception.ApolloServiceException;
import com.aviva.ca.esb.core.conf.exception.ErrorResponse;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchell.schemas.mitchellclaimevent.MitchellClaimEvent;

public class ApolloInputProcessorTest {
	private Exchange exchange;
	private Message message;
	ApolloInputProcessor apolloInputProcessor;
	MitchellClaimEvent mitchellClaimEvent;
	ObjectMapper objectMapper;

	@Before
	public void setUpMocks() {
		exchange = Mockito.mock(Exchange.class);
		message = Mockito.mock(Message.class);
	}

	@Test
	public void testApolloInputProcessorEstimates() throws Exception {

		PowerMockito
				.when(exchange
						.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID))
				.thenReturn("ReqCorrelationID");

		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		
		
		MessageContentsList messageContentsList=new MessageContentsList();
		MitchellClaimEvent mitchellClaimEvent = new MitchellClaimEvent();
		mitchellClaimEvent.setClaimNumber("123");
		mitchellClaimEvent.setEventCode("AE");
		messageContentsList.add(mitchellClaimEvent);
		PowerMockito.when(exchange.getIn().getBody(Object.class))
		.thenReturn(messageContentsList);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		PowerMockito.when(exchange.getProperties()).thenReturn(properties);
		ApolloInputProcessor apolloInputProcessor = new ApolloInputProcessor();
		try {
			apolloInputProcessor.process(exchange);
		} catch (Exception e) {
			assertTrue(e instanceof ApolloServiceException);
		}
		}
	
	@Test
	public void testApolloInputStringProcessorEstimates() throws Exception {

		PowerMockito
				.when(exchange
						.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID))
				.thenReturn("ReqCorrelationID");

		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		
		
		MessageContentsList messageContentsList=new MessageContentsList();
		String requestString= "Auto";
		messageContentsList.add(requestString);
		PowerMockito.when(exchange.getIn().getBody(Object.class))
		.thenReturn(messageContentsList);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		PowerMockito.when(exchange.getProperties()).thenReturn(properties);
		ApolloInputProcessor apolloInputProcessor = new ApolloInputProcessor();
		try {
			apolloInputProcessor.process(exchange);
		} catch (Exception e) {
			assertTrue(e instanceof ApolloServiceException);
		}
		}
	
	@Test
	public void testApolloElseProcessorEstimates() throws Exception {

		PowerMockito
				.when(exchange
						.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID))
				.thenReturn("ReqCorrelationID");

		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		
		
		MessageContentsList messageContentsList=new MessageContentsList();
		Date date= new Date();
		messageContentsList.add(date);
		PowerMockito.when(exchange.getIn().getBody(Object.class))
		.thenReturn(messageContentsList);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		PowerMockito.when(exchange.getProperties()).thenReturn(properties);
		ApolloInputProcessor apolloInputProcessor = new ApolloInputProcessor();
		try {
			apolloInputProcessor.process(exchange);
		} catch (Exception e) {
			assertTrue(e instanceof ApolloServiceException);
		}
		}
		
		
	}

