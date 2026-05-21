package com.ca.aviva.esb.api.notification.notificationcallback.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UpdateNotificationStatusResponse.Return;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.ErrorInfoType;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRs;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.YesNoType;

import com.ca.aviva.esb.api.notification.util.NotificationConstants;

public class NotificationStatusUpdateRsMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationStatusUpdateRsMapper.class);

	@Override
	public void process(Exchange exchange) {
		log.info(NotificationConstants.LOGTAG + " in the processor of NotificationStatusUpdateRsMapper");

		Object[] args = exchange.getIn().getBody(Object[].class);

		Return ccReturn = (Return) args[0];
		NotificationStatusUpdateRs ccStatusUpdateRs = ccReturn.getNotificationStatusUpdateRs();
		ErrorInfoType ccErrorInfo = ccStatusUpdateRs.getErrorInfo();
		YesNoType ccYesNoType = ccStatusUpdateRs.getIsSuccessful();

		com.avivacanada.services.fuse.notificationcallback.v1.StatusupdateResponseType nodeStatusupdateResponse = new com.avivacanada.services.fuse.notificationcallback.v1.StatusupdateResponseType();

		if ("Y".equals(ccYesNoType.toString()))
			nodeStatusupdateResponse.setIsSuccessful(com.avivacanada.services.fuse.notificationcallback.v1.YesNoType.Y);
		else
			nodeStatusupdateResponse.setIsSuccessful(com.avivacanada.services.fuse.notificationcallback.v1.YesNoType.N);

		if (ccErrorInfo != null) {
			nodeStatusupdateResponse.setErrorCode(ccErrorInfo.getCode());
			nodeStatusupdateResponse.setErrorDescription(ccErrorInfo.getDescription());
		};
		
		exchange.getIn().setBody(nodeStatusupdateResponse);

		log.info(NotificationConstants.LOGTAG + " At the end of processor of NotificationStatusUpdateRsMapper");
	}
}
