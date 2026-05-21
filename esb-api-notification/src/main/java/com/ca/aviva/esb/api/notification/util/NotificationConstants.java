package com.ca.aviva.esb.api.notification.util;

public class NotificationConstants {
	public static final String ORIGINALEXCEPTION = "ORIGINALEXCEPTION";
	public static final String EXCEPTIONOBJECT = "EXCEPTIONOBJECT";
	public static final String FAULTMESSAGE_NOTIFICATION_INBOUND_REQUEST_INVALID_ERROR = "notification inbond request message is not valid";
	public static final String FAULTMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_INVALID_ERROR = "unsubscribe inbond request message is not valid";
	public static final String FAULTMESSAGE_STATUSUPDATE_INBOUND_REQUEST_INVALID_ERROR = "status update inbond request message is not valid";
	public static final String FAULTMESSAGE_SYSTEM_ERROR = "internal system error";
	public static final String FAULTMESSAGE_APPLICATION_ERROR = "internal application error";
	
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE = "schema validation failure for the inbond request message with the details: ";	
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_APPLICATION_ID = "application_id is missing in the request message";
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_UNMARSHAL_FAILURE = "Failed to serialize message to text";
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_CLAIM_NUMBER = "claim number is not valid which can not be missing or empty";
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_MISSING_IDENTIFICATION_TYPE = "notification identifier type is not valid which must be either ClaimNumber or PolicyNumber";
	public static final String ERRORMESSAGE_NOTIFICATION_INBOUND_REQUEST_INCORRECT_NOTIFICATION_CATEGORY = "notification category type is not valid which must be either Real or Batch";

	public static final String ERRORMESSAGE_SYSTEM_PROPERTIES_LOADING_FAILURE = "properties can not be loaded properly";	
	public static final String ERRORMESSAGE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE = "required property can not be loaded from property file";
	public static final String ERRORMESSAGE_SYSTEM_FETCH_TIMESTAMP_FAILURE = "failed to fetch current timestamp";
	public static final String ERRORMESSAGE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE = "authentication failed while calling claim center service with details: ";
	public static final String ERRORMESSAGE_SYSTEM_CCSERVICE_SOAPFAULT = "soap fault occurred while calling claim center service with details: ";
	public static final String ERRORMESSAGE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION = "webservice exception occurred while calling claim center service with details: ";
	public static final String ERRORMESSAGE_NOTIFICATION_TWILIO_TECHNICAL_ERROR = "technical Error";
	public static final String ERRORMESSAGE_SYSTEM_INTERNAL_EXCEPTION = "system internal error with details: ";
	
	public static final String ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE = "transaction id of claim center in the request which can not be missing or empty";
	public static final String ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_DESTINATION_NO = "destination no of user in the request which can not be missing or empty";
	public static final String ERRORMESSAGE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_APPLICATION_ID = "application_id is missing in the request message";
	public static final String ERRORMESSAGE_NOTIFICATION_APPLICATION_ERROR = "negative acknowledgement response received with details: ";
	
	public static final String ERRORMESSAGE_STATUSUPDATE_INBOUND_REQUEST_MISSING_CLAIM_NUMBER = "claim number is not valid which can not be missing or empty";
	public static final String ERRORMESSAGE_STATUSUPDATE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE = "transaction id of claim center in the request which can not be missing or empty";
	public static final String ERRORMESSAGE_STATUSUPDATE_INBOUND_REQUEST_MISSING_APPLICATION_ID = "application_id is missing in the request message";
	
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_SCHEMA_VALIDATION_FAILURE = "FS1001";
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_APPLICATION_ID = "FS1002";
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_UNMARSHAL_FAILURE = "FS2003";
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_CLAIM_NUMBER = "FS1004";
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_MISSING_IDENTIFICATION_TYPE = "FS1005";
	public static final String ERRORCODE_NOTIFICATION_INBOUND_REQUEST_INCORRECT_NOTIFICATION_CATEGORY = "FS1006";
	
	public static final String ERRORCODE_SYSTEM_PROPERTIES_LOADING_FAILURE = "FS2001";
	public static final String ERRORCODE_SYSTEM_REQUIRED_PROPERTY_LOADING_FAILURE = "FS2002";
	public static final String ERRORCODE_SYSTEM_FETCH_TIMESTAMP_FAILURE = "FS2003";
	public static final String ERRORCODE_SYSTEM_CCSERVICE_AUTHENTICATION_FAILURE = "FS2004";
	public static final String ERRORCODE_SYSTEM_CCSERVICE_SOAPFAULT = "FS2005";
	public static final String ERRORCODE_SYSTEM_CCSERVICE_WEBSERVICEEXCEPTION = "FS2006";
	public static final String ERRORCODE_NOTIFICATION_TWILIO_TECHNICAL_ERROR = "FS2007";
	public static final String ERRORCODE_SYSTEM_INTERNAL_EXCEPTION = "FS2008";
	public static final String ERRORCODE_NOTIFICATION_APPLICATION_ERROR = "FS2009"; //This error will not send back to claim center and it is internal
	
	public static final String ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE = "FS3001";
	public static final String ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_DESTINATION_NO = "FS3002";
	public static final String ERRORCODE_UNSUBSCRIBE_INBOUND_REQUEST_MISSING_APPLICATION_ID = "FS3003";
	
	public static final String ERRORCODE_STATUSUPDATE_INBOUND_REQUEST_MISSING_REQUEST_REFERENCE = "FS3101";
	public static final String ERRORCODE_STATUSUPDATE_INBOUND_REQUEST_MISSING_CLAIM_NUMBER = "FS3102";
	public static final String ERRORCODE_STATUSUPDATE_INBOUND_REQUEST_MISSING_APPLICATION_ID = "FS3103";
	
	public static final String COP_CLAIMNOTIFICATION_USERNAME = "COP_CLAIMNOTIFICATION_USERNAME";
	public static final String COP_CLAIMNOTIFICATION_PASSWORD = "COP_CLAIMNOTIFICATION_PASSWORD";
	public static final String COP_CLAIMNOTIFICATION_ENDPOINT_URL = "COP_CLAIMNOTIFICATION_ENDPOINT_URL";
	public static final String COP_CLAIMNOTIFICATION_TIMEOUT = "COP_CLAIMNOTIFICATION_TIMEOUT";
	public static final String COP_CLAIMNOTIFICATION_RETRIES = "COP_CLAIMNOTIFICATION_RETRIES";
	public static final String COP_CLAIMNOTIFICATION_RETRYTIMER = "COP_CLAIMNOTIFICATION_RETRYTIMER";
	
	public static final String COP_NOTIFICATION_QUEUE = "COP_NOTIFICATION_QUEUE";
	
	public static final String COP_DIGITALNOTIFICATIONSERVICE_USERNAME = "CC_CREATECLAIM_USERNAME";
	public static final String COP_DIGITALNOTIFICATIONSERVICE_PASSWORD = "CC_CREATECLAIM_PASSWORD";
	public static final String COP_DIGITALNOTIFICATIONSERVICE_ENDPOINT_URL = "COP_DIGITALNOTIFICATIONSERVICE_ENDPOINT_URL";
	public static final String COP_DIGITALNOTIFICATIONSERVICE_TIMEOUT = "CC_CREATECLAIM_TIMEOUT";
	public static final String COP_DIGITALNOTIFICATIONSERVICE_STATUSUPDATE = "StatusUpdateClaimCenterService";
	public static final String COP_DIGITALNOTIFICATIONSERVICE_UNSUBSCRIBE = "UnsubscribeClaimCenterService";
	public static final int COP_NODE_RETURNCODE_RETRIABLE = 1;
	public static final int COP_NODE_RETURNCODE_NONRETRIABLE = 2;	
	public static final String TEXTMESSAGE = "TextMessage";
	public static final String RETRIABLE = "RETRIABLE";
	public static final String RETRYEXPIRED = "RETRYEXPIRED";
	public static final String RECOVERABLE = "RECOVERABLE";
	public static final String LOGTAG = "lognotify: ";
	public static final String INITIAL_RETRY = "-1";
	public static final String LEFTRETRIES = "LEFTRETRIES";
	public static final String NON_TECHNICAL_ERRORCODE_LIST = "21211,21214,21216,21217,21401,21408,21421,21422,21610,21612,21614,22102,30003,30004,30005,30006,30007";
	
}
