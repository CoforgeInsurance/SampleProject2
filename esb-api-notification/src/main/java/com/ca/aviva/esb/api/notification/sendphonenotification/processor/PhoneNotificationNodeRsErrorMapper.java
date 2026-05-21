package com.ca.aviva.esb.api.notification.sendphonenotification.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.aviva.esb.api.notification.util.NotificationUtils;
import com.avivacanada.services.fuse.notification.v1.DetailException;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class PhoneNotificationNodeRsErrorMapper extends PhoneNotificationNodeProcessor implements Processor {

	public static final Logger log = LoggerFactory.getLogger(PhoneNotificationNodeRsErrorMapper.class);

	@Override
	public void process(Exchange exchange) {
		log.info(NotificationConstants.LOGTAG + " in the processor of PhoneNotificationNodeRsErrorMapper");
		handleOriginalExceptionLogging(exchange);
		String message = NotificationUtils.getHeaderString(exchange, NotificationConstants.TEXTMESSAGE);

		Boolean headerRetriable = (Boolean) exchange.getIn().getHeader(NotificationConstants.RETRIABLE);
		if (headerRetriable != null) {
			if (!headerRetriable.booleanValue()) {
				exchange.getIn().setBody(message);
				log.info(NotificationConstants.LOGTAG
						+ "At the end of processor of PhoneNotificationNodeRsErrorMapper with non retriable result");
				return;
			}
		}

		int leftRetries = getLeftRetry(exchange) - 1;
		exchange.getIn().setHeader(NotificationConstants.COP_CLAIMNOTIFICATION_RETRIES, String.valueOf(leftRetries));
		exchange.getIn().setHeader(NotificationConstants.LEFTRETRIES, String.valueOf(leftRetries));
		
		if (leftRetries < 0)
		{
			exchange.getIn().setHeader(NotificationConstants.RETRIABLE, false);
			exchange.getIn().setBody(message);
			log.info(NotificationConstants.LOGTAG + "At the end of processor of PhoneNotificationNodeRsErrorMapper with retry expired and last retry also failed");
			return;
		}

		if(leftRetries ==0){
			exchange.getIn().setHeader(NotificationConstants.RETRIABLE, true);
			exchange.getIn().setHeader(NotificationConstants.RETRYEXPIRED, true);
			exchange.getIn().setBody(message);
			return;
		}

		exchange.getIn().setHeader(NotificationConstants.RETRIABLE, true);
		exchange.getIn().setHeader(NotificationConstants.RETRYEXPIRED, false);			
		
		String shortretrytimer = getShortretrytimer();
		int waittimer = Integer.parseInt(shortretrytimer);
		try {
			Thread.sleep(1000 * waittimer);
		} catch (InterruptedException e) {
		}

		exchange.getIn().setBody(message);		

		log.info(NotificationConstants.LOGTAG + "At the end of processor of PhoneNotificationNodeRsErrorMapper");
	}

	private void handleOriginalExceptionLogging(Exchange exchange) {
		SOAPServiceException serviceException = NotificationUtils.getHeaderSOAPException(exchange,
				NotificationConstants.EXCEPTIONOBJECT);

		if (serviceException != null) {
			DetailException detailException = serviceException.getFaultInfo();
			if (detailException != null)
				exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, detailException.getErrorMessage());
			else
				exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION,
						NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR);
		} else {
			Exception exception = (Exception) exchange.getProperty("CamelExceptionCaught");
			if (exception != null)
			exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, exception.toString());
		}
	}
}
