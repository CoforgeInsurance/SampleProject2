package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UnsubscribeNotificationResponse.Return;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.ErrorInfoType;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRs;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.YesNoType;

import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class NotificationUnsubscribeRsMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRsMapper.class);

	@Override
	public void process(Exchange exchange) {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationUnsubscribeRsMapper");
		
		Object[] args = exchange.getIn().getBody(Object[].class);

		Return ccReturn = (Return) args[0];
		UnsubscribeRs ccUnsubscribeResponse = ccReturn.getUnsubscribeRs();
		ErrorInfoType ccErrorInfo = ccUnsubscribeResponse.getErrorInfo();
		
		YesNoType ccYesNoType = ccReturn.getUnsubscribeRs().getIsSuccessful();
		
		com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeResponseType nodeUnsubscribeResponse = new com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeResponseType();
		
		if ("Y".equals(ccYesNoType.toString()))
			nodeUnsubscribeResponse.setIsSuccessful(com.avivacanada.services.fuse.notificationcallback.v1.YesNoType.Y);
		else
			nodeUnsubscribeResponse.setIsSuccessful(com.avivacanada.services.fuse.notificationcallback.v1.YesNoType.N);
		
		if (ccErrorInfo != null) {
			nodeUnsubscribeResponse.setErrorCode(ccErrorInfo.getCode());
			nodeUnsubscribeResponse.setErrorDescription(ccErrorInfo.getDescription());
		};
				
		exchange.getIn().setBody(nodeUnsubscribeResponse);

		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationUnsubscribeRsMapper");
	}

}
