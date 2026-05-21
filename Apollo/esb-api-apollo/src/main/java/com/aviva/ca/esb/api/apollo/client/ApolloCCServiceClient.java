package com.aviva.ca.esb.api.apollo.client;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.org.openuri.FileReceiverServiceSoap;

public class ApolloCCServiceClient {

	private final Logger LOGGER = LoggerFactory
			.getLogger(ApolloCCServiceClient.class);
	private FileReceiverServiceSoap proxy;
	private String url;
	private String timeout;
	private String userName;
	private String key;

	public ApolloCCServiceClient(FileReceiverServiceSoap proxy, String url,
			String timeout, String userName, String key) {
		super();
		this.proxy = proxy;
		this.url = url;
		this.timeout = timeout;
		this.userName = userName;
		this.key = key;
	}

	public void receiveFile(Exchange exchange) throws Exception {
		try {
			exchange.setProperty(TransactionLogConstants.REQUEST_MSG, exchange
					.getProperty(ApolloServiceConstants.EVENT_REQUEST_STRING,
							String.class));
			initializeBindingProvider(exchange);
			String response = proxy.receiveFile("");
			exchange.setProperty(TransactionLogConstants.OUTBOUND_RES_MSG,
					response);
			exchange.setProperty(ApolloServiceConstants.EVENT_REQUEST_STRING,
					response);

			LOGGER.info("Response from CC : " + response);
		} catch (Exception e) {
			LOGGER.error("Error in ApolloCCServiceClient :", e);
			throw e;
		}

	}

	private void initializeBindingProvider(Exchange exchange) {

		BindingProvider bp = (BindingProvider) proxy;
		Map<String, Object> requestContext = bp.getRequestContext();
		if (null != timeout) {
			requestContext.put(ApolloServiceConstants.CONNECTION_TIME_OUT,
					timeout);
			requestContext
					.put(ApolloServiceConstants.RECIEVE_TIME_OUT, timeout);
		}
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		requestContext.put(ApolloServiceConstants.CC_USERNAME,userName);
		requestContext.put(ApolloServiceConstants.CC_KEY,key);
		requestContext.put(ApolloServiceConstants.EVENT_REQUEST_STRING,
				exchange.getProperty(
						ApolloServiceConstants.EVENT_REQUEST_STRING,
						String.class));

	}
}
