package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.services.fuse.notification.v1.DetailException;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;

public class NotificationUnsubscribeRsErrorMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRsErrorMapper.class);

	@Override
	public void process(Exchange exchange) {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationUnsubscribeRsErrorMapper");

		SOAPServiceException serviceException = NotificationUtils.getHeaderSOAPException(exchange,
				NotificationConstants.EXCEPTIONOBJECT);

		com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeResponseType nodeUnsubscribeResponse = new com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeResponseType();
		nodeUnsubscribeResponse.setIsSuccessful(com.avivacanada.services.fuse.notificationcallback.v1.YesNoType.N);
		if (serviceException != null) {
			DetailException detailException = serviceException.getFaultInfo();
			if (detailException != null) {
				nodeUnsubscribeResponse.setErrorCode(detailException.getErrorCode());
				nodeUnsubscribeResponse.setErrorDescription(detailException.getErrorMessage());
				exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, detailException.getErrorMessage());
			} else {
				nodeUnsubscribeResponse.setErrorCode(NotificationConstants.ERRORCODE_SYSTEM_INTERNAL_EXCEPTION);
				nodeUnsubscribeResponse.setErrorDescription(NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR);
				exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR);
			};
		} else {
			Exception exception = (Exception) exchange.getProperty("CamelExceptionCaught");
			exchange.getIn().setHeader(NotificationConstants.ORIGINALEXCEPTION, exception.toString());
			nodeUnsubscribeResponse.setErrorCode(NotificationConstants.ERRORCODE_SYSTEM_INTERNAL_EXCEPTION);
			nodeUnsubscribeResponse.setErrorDescription(
					NotificationConstants.ERRORMESSAGE_SYSTEM_INTERNAL_EXCEPTION + exception.toString());
		}

		exchange.getIn().setBody(nodeUnsubscribeResponse);

		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationUnsubscribeRsErrorMapper");
	}

}
