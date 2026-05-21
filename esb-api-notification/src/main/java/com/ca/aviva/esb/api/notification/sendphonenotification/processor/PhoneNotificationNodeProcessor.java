package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.client.api.PropertiesLoaderService;
import com.aviva.ca.esb.core.conf.constant.ConfConstants;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.PropertiesLoader;

public abstract class PhoneNotificationNodeProcessor {
	
	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeProcessor.class);
	private static String endpointURL = null;
	private static String timeoutProperty = null;
	private static String authorizationHeader = null;
	private static String totalRetries = null;
	private static String username = null;
	private static String shortretrytimer = null;
	
	public static String getEndpointURL() {
		return endpointURL;
	}
	public static void setEndpointURL(String endpointURL) {
		PhoneNotificationNodeProcessor.endpointURL = endpointURL;
	}
	public static String getTimeoutProperty() {
		return timeoutProperty;
	}
	public static void setTimeoutProperty(String timeoutProperty) {
		PhoneNotificationNodeProcessor.timeoutProperty = timeoutProperty;
	}
	public static String getAuthorizationHeader() {
		return authorizationHeader;
	}
	public static void setAuthorizationHeader(String authorizationHeader) {
		PhoneNotificationNodeProcessor.authorizationHeader = authorizationHeader;
	}
	public static String getTotalRetries() {
		return totalRetries;
	}
	public static void setTotalRetries(String totalRetries) {
		PhoneNotificationNodeProcessor.totalRetries = totalRetries;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		PhoneNotificationNodeProcessor.username = username;
	}
	public static String getShortretrytimer() {
		return shortretrytimer;
	}
	public static void setShortretrytimer(String shortretrytimer) {
		PhoneNotificationNodeProcessor.shortretrytimer = shortretrytimer;
	}

	private void loadConfiguration(Exchange exchange) throws SOAPServiceException {
		// load properties from application_config.properties for attributes about Claim Center Service call
		log.info(NotificationConstants.LOGTAG + "To load the properties of node js service for one time");
		PropertiesLoader propertiesLoaderBean = exchange.getProperty(ConfConstants.LOAD_PROPERTIES,
				PropertiesLoader.class);

		Map<String, String> serviceConfigProperties = null;
		if (null != propertiesLoaderBean) {
			PropertiesLoaderService propertiesLoaderService = propertiesLoaderBean.getPropertiesLoaderService();
			if (null != propertiesLoaderService) {
				serviceConfigProperties = propertiesLoaderService.getCustomerMDMConfiguration();
			}
		}

		// properties not loaded
		if (null == serviceConfigProperties) {
			throw NotificationUtils.generateServiceException(exchange, 
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_PROPERTIES_LOADING_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_PROPERTIES_LOADING_FAILURE);
		}

		// retrieve the properties
		/*JasyptPropertiesParser jasp = new JasyptPropertiesParser();
		jasp.setAlgorithm("PBEWithMD5AndDES");
		jasp.setPassword("MasterPassword");*/
		
		try {
			endpointURL = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_ENDPOINT_URL+"}}");
			timeoutProperty = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_TIMEOUT+"}}");		
			username = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_USERNAME+"}}");		
			/*String password = jasp.parseProperty(null,
					exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_PASSWORD+"}}"), null);*/
			//String password = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_PASSWORD+"}}");
			String password = exchange.getProperty("copClaimsNotificationPwd").toString();
			shortretrytimer = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_RETRYTIMER+"}}");
			totalRetries = exchange.getContext().resolvePropertyPlaceholders("{{"+NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES+"}}");		
			totalRetries = String.valueOf(Integer.parseInt(totalRetries)+1);
			authorizationHeader = "Basic "
					+ org.apache.cxf.common.util.Base64Utility.encode((username + ":" + password).getBytes());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw NotificationUtils.generateServiceException(exchange, 
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE + e.getMessage());
		}		
	};

	public int getLeftRetry(Exchange exchange) 
	{
		String leftRetry = NotificationUtils.getHeaderString(exchange,
				NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES);
		return Integer.parseInt(leftRetry);
	}
	
	public void initializeProperty(Exchange exchange) throws Exception
	{
		if(getEndpointURL()==null)
			loadConfiguration(exchange);
		//The header COP_CLAIMNOTIFICATION_RETRIES is set, this helps in case exception happened on input processor, in this case only 1 as default, otherwise, it might become unlimited 
		//it will be overwritten by the logging processor
		exchange.getIn().setHeader(NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES, "1");
	}
}
