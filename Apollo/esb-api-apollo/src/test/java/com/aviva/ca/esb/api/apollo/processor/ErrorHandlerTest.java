package com.aviva.ca.esb.api.apollo.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.api.apollo.exception.ApolloServiceException;
import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchell.schemas.mitchellclaimevent.MitchellClaimEvent;

;

public class ErrorHandlerTest {
	

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
		WebServiceException webServiceException=new WebServiceException();
		PowerMockito
				.when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
						Exception.class))
				.thenReturn(webServiceException);

		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		

		
		ErrorHandler errorHandler = new ErrorHandler();
		try {
			errorHandler.process(exchange);
		} catch (Exception e) {
			
		}
		}
	
	@Test
	public void testErrorHandlerEstimates() throws Exception {
		ApolloServiceException apolloServiceException=new ApolloServiceException("","");
		PowerMockito
				.when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
						Exception.class))
				.thenReturn(apolloServiceException);

		PowerMockito.when(exchange.getIn()).thenReturn(message);
		PowerMockito.when(exchange.getOut()).thenReturn(message);
		

		
		ErrorHandler errorHandler = new ErrorHandler();
		try {
			errorHandler.process(exchange);
		} catch (Exception e) {
			
		}
		}
	
	
	
	

}