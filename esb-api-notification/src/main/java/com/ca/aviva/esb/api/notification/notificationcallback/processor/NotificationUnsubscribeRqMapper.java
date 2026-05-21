package com.ca.aviva.esb.api.notification.notificationcallback.processor;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.*;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;
import com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeRequestType;
import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import com.ca.aviva.esb.api.notification.util.NotificationUtils;

public class NotificationUnsubscribeRqMapper implements Processor {

	public static final Logger log = LoggerFactory.getLogger(NotificationUnsubscribeRqMapper.class);

	@Override
	public void process(Exchange exchange) throws SOAPServiceException {
		log.info(NotificationConstants.LOGTAG + "in the processor of NotificationUnsubscribeRqMapper");		
		Object[] args = exchange.getIn().getBody(Object[].class);		
		UnsubscribeRequestType nodeUnsubscribeRequest = (UnsubscribeRequestType) args[0];
		String nodeTransactionId = nodeUnsubscribeRequest.getCcTransactionID();
		String nodePhoneNo = nodeUnsubscribeRequest.getDestinationNo();
		List<com.avivacanada.services.fuse.notificationcallback.v1.ClaimInfoType> nodeClaimInfoList = nodeUnsubscribeRequest.getClaimInfo();
		
		com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UnsubscribeNotification.UnsubscribeRq outCCUnsubscribeRq = 
				new com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.UnsubscribeNotification.UnsubscribeRq();
		UnsubscribeRq ccUnsubscribeRq = new UnsubscribeRq();
		outCCUnsubscribeRq.setUnsubscribeRq(ccUnsubscribeRq);
		ClientInfoType ccClientInfo = new ClientInfoType();
		ccUnsubscribeRq.setClientInfo(ccClientInfo);
		ccClientInfo.setClientID(ClientIDType.FUSE);
		ccClientInfo.setClientName("FuseServer");
		ccClientInfo.setRequestReference(nodeTransactionId);
		try {
			ccClientInfo.setTransactionTime(NotificationUtils.getCurrentTimestamp());
		} catch (DatatypeConfigurationException e) {
			throw NotificationUtils.generateServiceException(exchange,
					NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR,
					NotificationConstants.ERRORCODE_SYSTEM_FETCH_TIMESTAMP_FAILURE,
					NotificationConstants.ERRORMESSAGE_SYSTEM_FETCH_TIMESTAMP_FAILURE
							+ " with details: " + e.toString());
		}
		
		UnsubscribeInfoType ccUnsubscribeInfo = new UnsubscribeInfoType();
		ccUnsubscribeRq.setUnsubscribeInfo(ccUnsubscribeInfo);
		
		ccUnsubscribeInfo.setPhoneNumber(nodePhoneNo);
		ccUnsubscribeInfo.setUnsubscribeStatus(UnsubscribeStatusType.UNSUBSCRIBE);
		List<ClaimInfoType> ccClaimInfo = ccUnsubscribeInfo.getClaimInfo();
		int size = nodeClaimInfoList.size();
		
		for(int index = 0; index < size; index++)
		{	
			com.avivacanada.services.fuse.notificationcallback.v1.ClaimInfoType nodeClaimItem = (com.avivacanada.services.fuse.notificationcallback.v1.ClaimInfoType) nodeClaimInfoList.get(index);
			/*String claimNumber = nodeClaimItem.getClaimNumber();
			String contactId = nodeClaimItem.getContactId();*/
			ClaimInfoType ccClaimInfoItem = new ClaimInfoType();
			ccClaimInfo.add(ccClaimInfoItem);
			try {
				ccClaimInfoItem.setClaimNumber(nodeClaimItem.getClaimNumber());
			}catch (NullPointerException npe){
				ccClaimInfoItem.setClaimNumber(null);
			}
			try {
				ccClaimInfoItem.setContactId(nodeClaimItem.getContactId());
			}catch (NullPointerException npe)
			{
				ccClaimInfoItem.setContactId(null);
			}
		};
		
		exchange.getIn().setBody(outCCUnsubscribeRq);
		log.info(NotificationConstants.LOGTAG + "at the end of NotificationUnsubscribeRqMapper");		
	}
}
