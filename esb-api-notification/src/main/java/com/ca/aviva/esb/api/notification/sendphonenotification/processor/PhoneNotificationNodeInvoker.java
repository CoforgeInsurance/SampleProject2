package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.avivacanada.avivaapi.claims.v1.twilio.sms.NotificationResponseType;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationNodeInvoker extends PhoneNotificationNodeProcessor implements Processor {
	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeInvoker.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {

		try {
			log.info(NotificationConstants.LOGTAG + "in the processor of PhoneNotificationNodeInvoker");
			Object[] args = exchange.getIn().getBody(Object[].class);

			com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest request = (com.avivacanada.services.fuse.notification.v1.SendPhoneNotificationRequest) args[0];
			exchange.setProperty("nodeRequestObject",request);
			Form form=null;
			if (request == null)
				throw NotificationUtils.wrapInternalSystemException(exchange, "Exception while pulling message from processor in PhoneNotificationNodeInvoker");

			try {
				if (request.getContactId().length() > 0) {
					 form = new Form().param("destinationNo", request.getPhone().getNumber())
							.param("claimNo", request.getIdentifier().getNumber())
							.param("contactID", request.getContactId())
							.param("smsLanguage", request.getLanguage().value())
							.param("smsMessage", request.getMessage())
							.param("transactionRefID", request.getClientInfo().getRequestReference());
				}
			}catch (NullPointerException npe)
			{
				 form = new Form().param("destinationNo", request.getPhone().getNumber())
						.param("claimNo", request.getIdentifier().getNumber())
						.param("smsLanguage", request.getLanguage().value())
						.param("smsMessage", request.getMessage())
						.param("transactionRefID", request.getClientInfo().getRequestReference());
			}
			if (form == null)
				throw NotificationUtils.wrapInternalSystemException(exchange, "Exception while generating request message in PhoneNotificationNodeInvoker");
			Client client = ClientBuilder.newClient();
			if (client == null)
				throw NotificationUtils.wrapInternalSystemException(exchange, "Exception while initializing the client in PhoneNotificationNodeInvoker");
			client.register(com.aviva.ca.esb.core.conf.cxf.features.OutboundFeature.class);
			client.property("http.receive.timeout", getTimeoutProperty());
			WebTarget target = client.target(getEndpointURL());
			if (target == null)
				throw NotificationUtils.wrapInternalSystemException(exchange, "Exception while initializing the target in PhoneNotificationNodeInvoker");

			Invocation.Builder invoker = target.request().accept("application/json").acceptLanguage("en-US")
					.acceptEncoding("gzip");
			if (invoker == null)
				throw NotificationUtils.wrapInternalSystemException(exchange, "Exception while initializing the invoker in PhoneNotificationNodeInvoker");
			invoker.header("Authorization", getAuthorizationHeader());
			Response response = invoker.post(Entity.form(form)); response.getStatusInfo().getStatusCode();
			log.info("Http Status="+response.getStatusInfo().getStatusCode());
			exchange.setProperty("CamelHttpResponseCode",response.getStatusInfo().getStatusCode());
			exchange.getIn().setBody(new Object[] { response });
			exchange.setProperty("responseObject",new Object[] { response });
			log.info("Response entity = "+response.readEntity(String.class));
			exchange.getIn().setBody(response.readEntity(String.class));
			log.info(NotificationConstants.LOGTAG + "at the end of processor of PhoneNotificationNodeInvoker");
		} catch (SOAPServiceException e) {
			log.error(e.getMessage());
			throw e;
		} catch(Exception e) {
			log.error(e.getMessage());
			throw NotificationUtils.wrapInternalSystemException(exchange, "Exception within PhoneNotificationNodeInvoker " + e.toString());
		}
		finally {
			NotificationUtils.consolidateLogging(exchange, getUsername(), getEndpointURL());
		}
	}
}
