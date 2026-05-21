package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi;

import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.client.api.PropertiesLoaderService;
import com.aviva.ca.esb.core.conf.constant.ConfConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UnsubscribeNotification.UnsubscribeRq;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatus.NotificationStatusUpdateRq;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.ca.aviva.esb.api.notification.util.PropertiesLoader;

public class ClaimCenterNotificationServiceInvoker implements Processor {

	public static final Logger log = LoggerFactory.getLogger(ClaimCenterNotificationServiceInvoker.class);
	private static String endpointURL = null;
	private static String timeoutProperty = null;
	private static String username = null;
	private static String ccPass = null;
	private static int shortretrytimer;
	private static int totalRetries;

	private DigitalNotificationServiceAPIPortType digitalNotificationService;

	public DigitalNotificationServiceAPIPortType getDigitalNotificationService() {
		return digitalNotificationService;
	}

	public void setDigitalNotificationService(DigitalNotificationServiceAPIPortType digitalNotificationService) {
		this.digitalNotificationService = digitalNotificationService;
	}

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + "in the processor of ClaimCenterNotificationServiceInvoker");
		initializeBindingProvider(exchange);

		log.info(NotificationConstants.LOGTAG + "Set OUBOUND_USER to be " + username);
		// set the outbound user for logging framework
		exchange.setProperty("OUBOUND_USER", username);
		String service = (String) exchange.getProperty("SERVICE");
		handleServiceCall(exchange, service);

		log.info(NotificationConstants.LOGTAG + "at the end of ClaimCenterNotificationServiceInvoker");
	};

	private void handleServiceCall(Exchange exchange, String service) throws SOAPServiceException {
		boolean succeed = false;
		SOAPServiceException serviceException = null;
		for (int i = 0; i < totalRetries && !succeed; i++) {
			try {
				if (NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_STATUSUPDATE.equals(service))
					callStatusupdateService(exchange);
				else if (NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_UNSUBSCRIBE.equals(service))
					callUnsubscribeService(exchange);
				succeed = true;
			} catch (SOAPServiceException e) {
				//////////////
				ProducerTemplate exceptionLogger = exchange.getContext().createProducerTemplate();
				exchange.setProperty("CamelExceptionCaught",new Exception(e.getMessage()));
				exchange.setProperty("OUT_REQ_MSG",exchange.getIn().getBody());
				exchange.setProperty("REQUEST_MSG_TYPE","text/xml");
				exchange.getIn().setHeader("REQUEST_MSG_TYPE","text/xml");
				exchange.setProperty("SERVICE","NotificationService");
				//exceptionLogger.asyncSend("seda:insertOutboundErrorImpl",exchange);
				exceptionLogger.send("direct:insertOutboundErrorImpl",exchange);
				log.info("Logging CC invoke failure in TXN database");
				//////////////
				serviceException = e;
				try {
					Thread.sleep(1000 * shortretrytimer);
				} catch (InterruptedException e1) {
				}
			}
		}
		if (!succeed)
		{
			exchange.getIn().setHeader(NotificationConstants.EXCEPTIONOBJECT, serviceException);
			throw serviceException;
		};
	}

	private void callUnsubscribeService(Exchange exchange) throws SOAPServiceException {
		Object[] args = exchange.getIn().getBody(Object[].class);
		UnsubscribeRq unsubscribeRq = (UnsubscribeRq) args[0];
		String transactionId = unsubscribeRq.getUnsubscribeRq().getClientInfo().getRequestReference();
		/*exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, transactionId);
		exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE, "RequestReference")*/;

		//exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, unsubscribeRq.getUnsubscribeRq().getClientInfo());
		try {
			exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, exchange.getIn().getHeader(TransactionLogConstants.MSG_IDENTIFIER));
		}catch (NullPointerException npe)
		{
			exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, "NOT FOUND");
		}
		exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE, "DestinationNo");

		log.info(NotificationConstants.LOGTAG + "to invoke the claim center service");
		com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UnsubscribeNotificationResponse.Return ccReturn;
		try {
			ccReturn = digitalNotificationService.unsubscribeNotification(unsubscribeRq);
			exchange.getIn().setBody(new Object[] { ccReturn });
		} catch (WsiAuthenticationException_Exception e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with WsiAuthenticationException_Exception");
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE + NotificationUtils.CCAuthenticationException(e));
		} catch (SOAPFaultException e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with SOAPFaultException");
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_SOAPFAULT,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_SOAPFAULT + NotificationUtils.CCSOAPFaultMessage(e));
		} catch (WebServiceException e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with WebServiceException");
			throw NotificationUtils.generateServiceException(exchange,
					e.getMessage()+NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION + NotificationUtils.CCWebServiceExceptionMessage(e));
		}
		log.info(NotificationConstants.LOGTAG + "response from claim center service returned");
	}

	private void callStatusupdateService(Exchange exchange) throws SOAPServiceException {
		Object[] args = exchange.getIn().getBody(Object[].class);
		NotificationStatusUpdateRq notificationStatusUpdateRq = (NotificationStatusUpdateRq) args[0];
		String callNumber = notificationStatusUpdateRq.getNotificationStatusUpdateRq().getNotificationStatusInfo()
				.getClaimNumber();
		exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, callNumber);
		//exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE, "CallNumber");
		exchange.getIn().setHeader(TransactionLogConstants.POLICY_OR_QUOTE, "ClaimNumber");

		log.info(NotificationConstants.LOGTAG + "to invoke the claim center service");
		com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatusResponse.Return ccReturn;
		try {
			ccReturn = digitalNotificationService.updateNotificationStatus(notificationStatusUpdateRq);
			exchange.getIn().setBody(new Object[] { ccReturn });
		} catch (WsiAuthenticationException_Exception e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with WsiAuthenticationException_Exception");
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE + NotificationUtils.CCAuthenticationException(e));
		} catch (SOAPFaultException e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with SOAPFaultException");
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_SOAPFAULT,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_SOAPFAULT + NotificationUtils.CCSOAPFaultMessage(e));
		} catch (WebServiceException e) {
			log.error(NotificationConstants.LOGTAG
					+ "end processor of ClaimCenterNotificationServiceInvoker with WebServiceException");
			throw NotificationUtils.generateServiceException(exchange,
					e.getMessage()+NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION,
					NotificationConstants.ERRORMESSAGE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION + NotificationUtils.CCWebServiceExceptionMessage(e));
		}
		log.info(NotificationConstants.LOGTAG + "response from claim center service returned");
	}

	private void initializeBindingProvider(Exchange exchange) throws SOAPServiceException {
		if (endpointURL == null)
			loadConfiguration(exchange);

		BindingProvider bp = (BindingProvider) digitalNotificationService;
		Map<String, Object> requestContext = bp.getRequestContext();

		// Timeout logic
		if (null != timeoutProperty) {
			// Set timeout until a connection is established
			requestContext.put(ConfConstants.TIME_OUT_PROPERTIES, timeoutProperty);

			// Set timeout until the response is received
			requestContext.put(ConfConstants.RECIEVE_TIME_OUT_PROPERTIES, timeoutProperty);
		}
		// Specify taraget URL
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		// Provide username/password to be available in CCHeaderHandler
		requestContext.put(NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_USERNAME, username);
		requestContext.put(NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_PASSWORD, ccPass);
	}

	private void loadConfiguration(Exchange exchange) throws SOAPServiceException {
		// load properties from application_config.properties for attributes about Claim
		// Center Service call
		log.info(NotificationConstants.LOGTAG + "To load the properties of claim center service for one time");
		PropertiesLoader propertiesLoaderBean = exchange.getProperty(ConfConstants.LOAD_PROPERTIES,
				PropertiesLoader.class);

		Map<String, String> mdmConfigProperties = null;
		if (null != propertiesLoaderBean) {
			PropertiesLoaderService propertiesLoaderService = propertiesLoaderBean.getPropertiesLoaderService();
			if (null != propertiesLoaderService) {
				mdmConfigProperties = propertiesLoaderService.getCustomerMDMConfiguration();
			}
		}

		// properties not loaded
		if (null == mdmConfigProperties) {
			log.error(NotificationConstants.LOGTAG + "Error for loading the properties ");
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
			timeoutProperty = exchange.getContext().resolvePropertyPlaceholders(
					"{{" + NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_TIMEOUT + "}}");
			username = exchange.getContext().resolvePropertyPlaceholders(
					"{{" + NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_USERNAME + "}}");
			//ccPass = exchange.getContext().resolvePropertyPlaceholders("{{" + NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_PASSWORD + "}}");
			ccPass = exchange.getProperty("ccCreateClaimPwd").toString();
			/*ccPass = jasp.parseProperty(null, exchange.getContext().resolvePropertyPlaceholders(
					"{{" + NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_PASSWORD + "}}"), null);*/
			endpointURL = exchange.getContext().resolvePropertyPlaceholders(
					"{{" + NotificationConstants.COP_DIGITALNOTIFICATIONSERVICE_ENDPOINT_URL + "}}");
			shortretrytimer = Integer.parseInt(exchange.getContext()
					.resolvePropertyPlaceholders("{{" + NotificationConstants.COP_CLAIMNOTIFICATION_RETRYTIMER + "}}"));
			totalRetries = Integer.parseInt(exchange.getContext()
					.resolvePropertyPlaceholders("{{" + NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES + "}}"));
		} catch (Exception e) {
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE + e.getMessage());
		}
	}
}
