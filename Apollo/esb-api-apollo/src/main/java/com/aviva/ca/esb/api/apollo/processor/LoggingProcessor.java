package com.aviva.ca.esb.api.apollo.processor;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.message.Message;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.ApplicationLogConstant;
import com.aviva.ca.esb.core.conf.logging.MDCConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.MessageContentType;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLog;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogUtils;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionProcessConstant;

public class LoggingProcessor {
	private static final String INBOUND_USER = "INBOUND_USER";
	private static final String NOT_FOUND = "NOT FOUND";
	private static final String CLAIM_NUMBER = "ClaimNumber";
	private TransactionLog transactionLog;
	private final Logger LOGGER = LoggerFactory
			.getLogger(LoggingProcessor.class);

	public LoggingProcessor(TransactionLog transactionLog) {
		super();
		this.transactionLog = transactionLog;
	}

	public LoggingProcessor() {
	}

	public static final String LOG_TABLE_ID = "CC";
	public static final String OPERATION_NAME = "operationName";

	public void setCommonLoggingProperties(Exchange exchange) {
		LOGGER.info("LoggingProcessor  :::: setting CommonLoggingProperties");
		try {

			exchange.setProperty(TransactionLogConstants.TRANSACTION_LOG,
					transactionLog);
			exchange.setProperty(TransactionProcessConstant.LogTableOper,
					TransactionProcessConstant.Insert);
			exchange.setProperty(TransactionProcessConstant.LogTableId,
					LOG_TABLE_ID);

			exchange.setProperty(TransactionLogConstants.INBOUND_RQ_TIME,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.SERVICE,
					"ApolloService");
			exchange.setProperty(
					TransactionLogConstants.REQUEST_CORRELATION_ID,
					TransactionLogUtils.getCorrelationId(exchange));
			exchange.setProperty(
					TransactionLogConstants.INBOUND_REQUEST,
					exchange.getProperty(TransactionLogConstants.INBOUND_REQUEST));

			setMessageIdentifierHeaders(exchange);
		} catch (Exception ex) {
			LOGGER.error(
					" LoggingProcessor  :: Error in setCommonLoggingProperties  {}",
					ex);
		}

	}

	public void setErrorDetails(Exchange exchange) {
		// To set exact Exception message came from Vendor.
		exchange.setProperty(
				TransactionLogConstants.RESPONSE_MSG_TYPE,
				exchange.getProperty("EXCEPTION_MSG") != null ? MessageContentType.XML
						.value() : MessageContentType.EXCEPTION.value());
		exchange.setProperty(TransactionLogConstants.STATUS, "ERROR");
	}

	public void setSuccessDetails(Exchange exchange) {
		exchange.setProperty(TransactionLogConstants.RESPONSE_MSG_TYPE,
				MessageContentType.XML.value());
		exchange.setProperty(TransactionLogConstants.STATUS,
				exchange.getProperty(TransactionLogConstants.STATUS));
	}

	public void setOutboundDetails(Exchange exchange) {
		try {
			exchange.setProperty(TransactionLogConstants.TRANSACTION_LOG,
					transactionLog);
			exchange.setProperty(TransactionProcessConstant.LogTableOper,
					TransactionProcessConstant.Insert);
			exchange.setProperty(TransactionProcessConstant.LogTableId,
					LOG_TABLE_ID);
			exchange.setProperty(TransactionLogConstants.REQUEST_TIME,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.CREATED_DATE,
					new Timestamp(System.currentTimeMillis()));
			String isRetry = exchange.getProperty("IS_RETRY", String.class);

			if (!StringUtils.isEmpty(isRetry)
					&& "TRUE".equalsIgnoreCase(isRetry)) {
				exchange.setProperty(TransactionLogConstants.CREATED_BY,
						"FUSE_RETRY_USER");
				exchange.setProperty(TransactionLogConstants.UPDATED_BY,
						"FUSE_RETRY_USER");
			} else {
				exchange.setProperty(TransactionLogConstants.CREATED_BY,
						TransactionLogConstants.FUSE_ADMIN);
				exchange.setProperty(TransactionLogConstants.UPDATED_BY,
						TransactionLogConstants.FUSE_ADMIN);
			}
			exchange.setProperty(TransactionLogConstants.RESPONSE_MSG, exchange
					.getProperty(TransactionLogConstants.OUTBOUND_RES_MSG));
			exchange.setProperty(TransactionLogConstants.RESPONSE_TIME,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.UPDATED_DATE,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(
					TransactionLogConstants.REQUEST_CORRELATION_ID,
					TransactionLogUtils.getCorrelationId(exchange));
			setMessageIdentifierHeaders(exchange);
		} catch (Exception ex) {
			LOGGER.error(
					" LoggingProcessor  :: Error in setOutboundDetails  {}", ex);
		}

	}

	public void setInboundDetails(Exchange exchange) {
		try {
			exchange.setProperty(TransactionLogConstants.TRANSACTION_LOG,
					transactionLog);
			exchange.setProperty(TransactionProcessConstant.LogTableOper,
					TransactionProcessConstant.Insert);
			exchange.setProperty(TransactionProcessConstant.LogTableId,
					LOG_TABLE_ID);
			exchange.setProperty(TransactionLogConstants.REQUEST_MSG_TYPE,
					MessageContentType.XML.value());
			String user = (String) MDC.get(MDCConstants.ESB_USER);
			if (StringUtils.isEmpty(user)) {
				user = "NOT FOUND";
			}
			exchange.setProperty(TransactionLogConstants.CREATED_BY, user);
			exchange.setProperty(TransactionLogConstants.UPDATED_BY, user);
			exchange.setProperty(TransactionLogConstants.REQUEST_MSG, exchange
					.getProperty(TransactionLogConstants.INBOUND_REQUEST));
			exchange.setProperty(TransactionLogConstants.REQUEST_TIME,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.CREATED_DATE,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.URL,
					MDC.get(MDCConstants.ESB_URI));

			exchange.setProperty(TransactionLogConstants.RESPONSE_MSG, exchange
					.getProperty(TransactionLogConstants.INBOUND_RESPONSE));
			exchange.setProperty(TransactionLogConstants.RESPONSE_MSG_TYPE,
					MessageContentType.XML.value());
			exchange.setProperty(TransactionLogConstants.RESPONSE_TIME,
					new Timestamp(System.currentTimeMillis()));
			exchange.setProperty(TransactionLogConstants.UPDATED_DATE,
					new Timestamp(System.currentTimeMillis()));

			setMessageIdentifierHeaders(exchange);
			exchange.setProperty(TransactionLogConstants.OPERATION_NAME,
					"receiveFile");
			exchange.setProperty(TransactionLogConstants.SERVICE,
					"ApolloService");
		} catch (Exception ex) {
			LOGGER.error(
					" LoggingProcessor  :: Error in setInboundDetails  {}", ex);
		}

	}

	private void setMessageIdentifierHeaders(Exchange exchange) {

		String claimNumber = exchange.getProperty(
				TransactionLogConstants.MSG_IDENTIFIER, String.class);
		if (!StringUtils.isEmpty(claimNumber)) {
			exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER,
					claimNumber);
			exchange.setProperty(TransactionLogConstants.MSG_IDENTIFIER,
					claimNumber);
			exchange.setProperty(
					TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, claimNumber);
			exchange.getIn().setHeader(
					TransactionLogConstants.MSG_IDENTIFIER_TYPE, CLAIM_NUMBER);
			exchange.setProperty(TransactionLogConstants.POLICY_OR_QUOTE,
					CLAIM_NUMBER);
			exchange.setProperty(TransactionLogConstants.MSG_IDENTIFIER_TYPE,
					CLAIM_NUMBER);
		} else {
			exchange.getIn().setHeader(TransactionLogConstants.MSG_IDENTIFIER,
					NOT_FOUND);
			exchange.setProperty(TransactionLogConstants.MSG_IDENTIFIER,
					NOT_FOUND);
			exchange.setProperty(
					TransactionLogConstants.POLICY_OR_QUOTE_NUMBER, NOT_FOUND);
			exchange.getIn().setHeader(
					TransactionLogConstants.MSG_IDENTIFIER_TYPE, CLAIM_NUMBER);
			exchange.setProperty(TransactionLogConstants.POLICY_OR_QUOTE,
					CLAIM_NUMBER);
			exchange.setProperty(TransactionLogConstants.MSG_IDENTIFIER_TYPE,
					CLAIM_NUMBER);
		}
	}

	
}
