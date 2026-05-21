package com.aviva.ca.esb.api.apollo.processor;

import static org.junit.Assert.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Before;
import org.junit.Test;


import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApolloResponseMapperTest {
	public Exchange exchange;
	ApolloResponseMapper apolloResponseMapper;
	ApolloInputProcessor apolloInputProcessor;
	ObjectMapper objectMapper;
	@Before
	public void setup() {
		CamelContext context = new DefaultCamelContext();
		exchange = new DefaultExchange(context);
		apolloResponseMapper = new ApolloResponseMapper();
		objectMapper = new ObjectMapper();
		apolloInputProcessor = new ApolloInputProcessor();
		
	}
	
	@Test
	public void testProcessIsError() throws Exception {
		//exchange.setProperty("ERROR_RESPONSE",new ErrorResponse("FUS2001", "Error"));
		exchange.setProperty("IS_ERROR","TRUE");
		apolloResponseMapper.process(exchange);
		assertEquals("ERROR", exchange.getProperty(TransactionLogConstants.STATUS,String.class));
	}
	@Test
	public void testProcessIsSuccess() throws Exception {
		exchange.setProperty("IS_ERROR","FALSE");
		apolloResponseMapper.process(exchange);
		assertEquals("SUCCESS", exchange.getProperty(TransactionLogConstants.STATUS,String.class));
	}
	
}
