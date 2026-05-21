package com.ca.aviva.esb.api.notification.util;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.camel.Exchange;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi.WsiAuthenticationException_Exception;
import com.avivacanada.services.fuse.notification.v1.DetailException;
import com.avivacanada.services.fuse.notification.v1.SOAPServiceException;

public class NotificationUtils {
	public static XMLGregorianCalendar getCurrentTimestamp() throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		XMLGregorianCalendar timestamp = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		return timestamp;
	}

	public static SOAPServiceException generateServiceException(Exchange exchange, String faultMessage, String errorCode,
			String errorMessage) {
		DetailException detailException = new DetailException();
		detailException.setErrorCode(errorCode);
		detailException.setErrorMessage(errorMessage);
		SOAPServiceException serviceException = new SOAPServiceException(faultMessage, detailException);
		exchange.getIn().setHeader(NotificationConstants.EXCEPTIONOBJECT, serviceException);
		return serviceException;
	};
	
	public static void consolidateLogging(Exchange exchange, String username, String endURL)
	{
		exchange.setProperty(TransactionLogConstants.OUBOUND_USER, username);
		exchange.setProperty(TransactionLogConstants.URL, endURL);
	};

	public static Timestamp getCurrentTimeStamp() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}
	
	public static String getHeaderString(Exchange exchange, String headerName) {
		Object header = exchange.getIn().getHeader(headerName);
		if (header!=null)
			return (String) header;
		else
			return null;
	}

	public static SOAPServiceException getHeaderSOAPException(Exchange exchange, String headerName) {
		Object header = exchange.getIn().getHeader(headerName);
		if (header!=null)
			return (SOAPServiceException) header;
		else
			return null;
	}

	public static String CCAuthenticationException(WsiAuthenticationException_Exception exception) {
		return "error message : "+ exception.getLocalizedMessage()+ ", title: WebServiceException";
	}

	public static String CCSOAPFaultMessage(SOAPFaultException exception) {
		return "error message : "+ exception.getLocalizedMessage()+ ", title: ClaimCenter SOAPFaultException";
	}

	public static String CCWebServiceExceptionMessage(WebServiceException exception) {
		Throwable cause = exception.getCause(); 
		if(cause instanceof java.net.ConnectException)
		{
			java.net.ConnectException connectException = (java.net.ConnectException) cause;
			return "error message : "+ connectException.getMessage()+ ", title: java.net.ConnectException";
		}
		return "error message : "+ exception.getLocalizedMessage()+ ", title: WebServiceException";
	}	
	
	public static SOAPServiceException wrapInternalSystemException(Exchange exchange, String details)
	{
		SOAPServiceException serviceException = generateServiceException(exchange,NotificationConstants.FAULTMESSAGE_SYSTEM_ERROR, 
				NotificationConstants.ERRORCODE_SYSTEM_INTERNAL_EXCEPTION, NotificationConstants.ERRORMESSAGE_SYSTEM_INTERNAL_EXCEPTION + details);
		return serviceException;
	}
}
