package com.aviva.ca.esb.api.apollo.processor;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.api.apollo.exception.ApolloServiceException;
import com.aviva.ca.esb.api.apollo.utils.ApolloServiceConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLog;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogConstants;
import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLogUtils;
import com.mitchell.schemas.mitchellclaimevent.MitchellClaimEvent;

public class ApolloInputProcessor implements Processor {
	private final Logger LOGGER = LoggerFactory
			.getLogger(ApolloInputProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			LOGGER.info("ApolloService :: Inside ApolloInputProcessor ; REQUEST_CORRELATION_ID : "
					+ exchange
							.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
			Object exchangeBody = exchange.getIn().getBody(Object.class);
			LOGGER.info("ApolloService :: exchangeBody type : "
					+ exchangeBody
					+ " ; REQUEST_CORRELATION_ID : "
					+ exchange
							.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
			Object pojo = null;
			exchange.setProperty(TransactionLogConstants.INBOUND_REQUEST,
					"Invalid request or unable to process the request");
			if (exchangeBody instanceof MessageContentsList) {
				pojo = ((MessageContentsList) exchangeBody).get(0);
			}

			if (pojo instanceof MitchellClaimEvent) {
				MitchellClaimEvent mitchellClaimEvent = (MitchellClaimEvent) pojo;

				exchange.setProperty("EventRequestString",
						TransactionLogUtils.getBodyAsXml(exchange));
				processRequest(exchange, mitchellClaimEvent);
			} else if (pojo instanceof String) {
				TransactionLog transactionLog = exchange.getProperty(
						TransactionLogConstants.TRANSACTION_LOG,
						TransactionLog.class);
				String requestString = (String) pojo;
				if (!StringUtils.isEmpty(requestString)) {
					exchange.setProperty(
							TransactionLogConstants.INBOUND_REQUEST,
							requestString);

					exchange.setProperty("EventRequestString", requestString);
					MitchellClaimEvent mitchellClaimEvent = null;
					try (StringReader reader = new StringReader(requestString)) {
						JAXBContext jaxbContext = transactionLog
								.getJaxbContexts().get(
										MitchellClaimEvent.class.getName());

						Unmarshaller jaxbUnmarshaller = jaxbContext
								.createUnmarshaller();
						mitchellClaimEvent = (MitchellClaimEvent) jaxbUnmarshaller
								.unmarshal(reader);

					} catch (Exception e) {
						throw new ApolloServiceException("INVALID_REQUEST",
								"FUS201 :: Invalid Request payload");
					}
					processRequest(exchange, mitchellClaimEvent);
				} else {
					throw new ApolloServiceException("INVALID_REQUEST",
							"FUS201 :: Invalid Request payload");
				}

				LOGGER.info("ApolloService :: MessageContentsList content is String; REQUEST_CORRELATION_ID : "
						+ exchange
								.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
			} else {
				LOGGER.error("ApolloService :: could not get the request body from exchange ; REQUEST_CORRELATION_ID : "
						+ exchange
								.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
				throw new ApolloServiceException("INVALID_REQUEST",
						"FUS201 :: Invalid Request payload");
			}
		} catch (Exception e) {
			LOGGER.error("ApolloService :: Error in request processing :::", e);
			throw new ApolloServiceException("INVALID_REQUEST",
					"FUS201 :: Invalid Request payload");
		} finally {
			LOGGER.info("ApolloService :: Exiting ApolloInputProcessor ; ClaimNumber : "
					+ exchange
							.getProperty(TransactionLogConstants.MSG_IDENTIFIER)
					+ " ; EVENT_CODE : "
					+ exchange.getProperty(ApolloServiceConstants.EVENT_CODE)
					+ " ; REQUEST_CORRELATION_ID : "
					+ exchange
							.getProperty(TransactionLogConstants.REQUEST_CORRELATION_ID));
		}
	}

	private void processRequest(Exchange exchange,
			MitchellClaimEvent mitchellClaimEvent) {
		exchange.setProperty(TransactionLogConstants.INBOUND_REQUEST,
				mitchellClaimEvent);
		exchange.getIn().setBody(mitchellClaimEvent);
		if (!StringUtils.isEmpty(mitchellClaimEvent.getClaimNumber())) {
			exchange.setProperty(TransactionLogConstants.MSG_IDENTIFIER,
					mitchellClaimEvent.getClaimNumber());
		}
		if (!StringUtils.isEmpty(mitchellClaimEvent.getEventCode())) {
			exchange.setProperty(ApolloServiceConstants.EVENT_CODE,
					mitchellClaimEvent.getEventCode());
		}
	}

}
