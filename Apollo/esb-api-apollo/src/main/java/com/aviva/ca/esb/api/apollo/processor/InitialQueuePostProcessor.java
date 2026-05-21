package com.aviva.ca.esb.api.apollo.processor;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jms.JmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aviva.ca.esb.core.conf.logging.transactionlog.TransactionLog;
import com.mitchell.schemas.mitchellclaimevent.MitchellClaimEvent;

public class InitialQueuePostProcessor implements Processor {
	private final Logger LOGGER = LoggerFactory
			.getLogger(InitialQueuePostProcessor.class);
	private TransactionLog transactionLog;

	public InitialQueuePostProcessor(TransactionLog transactionLog) {
		super();
		this.transactionLog = transactionLog;
	}

	public InitialQueuePostProcessor() {
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("ApolloService :: InitialQueuePostProcessor : Request came from initial queue");
		MitchellClaimEvent mitchellClaimEvent = null;
		JmsMessage objJmsMessage = exchange.getIn().getBody(JmsMessage.class);
		ActiveMQTextMessage mstext = null;
		ActiveMQBytesMessage ms = null;
		ActiveMQMessage msg = (ActiveMQMessage) objJmsMessage.getJmsMessage();

		byte[] bs = null;

		try {

			if (msg instanceof org.apache.activemq.command.ActiveMQTextMessage) {
				mstext = (ActiveMQTextMessage) msg;
				bs = mstext.getText().getBytes();
				LOGGER.debug("ApolloService :: InitialQueuePostProcessor : Input is text message");
			}
			if (msg instanceof org.apache.activemq.command.ActiveMQBytesMessage) {
				ms = (ActiveMQBytesMessage) msg;
				bs = ms.getContent().getData();
				LOGGER.debug("ApolloService :: InitialQueuePostProcessor : Input is byte message");
			}
			try {
				mitchellClaimEvent = getMitchellClaimEvent(bs);
				LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Successfully cast to CreateDocumentRequest from byte stream");
			} catch (Exception e) {
				LOGGER.error("ApooloService :: InitialQueuePostProcessor :: Error in casting from byte stream to CreateDocumentRequest",e);
				LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Could not cast to CreateDocumentRequest");
				mitchellClaimEvent = getJaxbObjects(bs);
				LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Successfully cast to JAXBElement");
			}
		} catch (Exception e) {
			LOGGER.error("ApolloService :: InitialQueuePostProcessor :: Unable to create CreateDocumentRequest for retry");
			throw e;
		} finally {
			exchange.setProperty("MitchellClaimEvent", mitchellClaimEvent);
		}
	}

	public MitchellClaimEvent getJaxbObjects(byte[] bs) throws Exception {
		MitchellClaimEvent mitchellClaimEvent = null;

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bs)) {
			LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Casting message to JAXBElement<CreateDocumentRequest> Type");
			JAXBContext jaxbContext = transactionLog.getJaxbContexts().get(
					MitchellClaimEvent.class.getName());

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			@SuppressWarnings("unchecked")
			JAXBElement<MitchellClaimEvent> dataFeedMessageJAXB = (JAXBElement<MitchellClaimEvent>) jaxbUnmarshaller
					.unmarshal(bis);

			mitchellClaimEvent = dataFeedMessageJAXB.getValue();

		} catch (Exception e) {
			LOGGER.debug(
					"ApolloService :: InitialQueuePostProcessor :: Cannot cast to JAXBElement<MitchellClaimEvent> Type",
					e);
		}

		return mitchellClaimEvent;
	}

	public MitchellClaimEvent getMitchellClaimEvent(byte[] bs) throws Exception {
		MitchellClaimEvent mitchellClaimEvent = null;

		try (ByteArrayInputStream bis = new ByteArrayInputStream(bs)) {
			LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Casting ActiveMQMessage to MitchellClaimEvent Type");

			JAXBContext jaxbContext = transactionLog.getJaxbContexts().get(
					MitchellClaimEvent.class.getName());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			mitchellClaimEvent = (MitchellClaimEvent) jaxbUnmarshaller
					.unmarshal(bis);

		} catch (Exception e) {
			LOGGER.debug("ApolloService :: InitialQueuePostProcessor :: Cannot cast to MitchellClaimEvent Type");
			throw e;
		}

		return mitchellClaimEvent;
	}
}
