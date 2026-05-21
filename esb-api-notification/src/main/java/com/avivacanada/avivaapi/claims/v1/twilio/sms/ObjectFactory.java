
package com.avivacanada.avivaapi.claims.v1.twilio.sms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avivacanada.avivaapi.claims.v1.twilio.sms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Request_QNAME = new QName("http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms", "Request");
    private final static QName _Response_QNAME = new QName("http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms", "Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avivacanada.avivaapi.claims.v1.twilio.sms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotificationRequestType }
     * 
     */
    public NotificationRequestType createNotificationRequestType() {
        return new NotificationRequestType();
    }

    /**
     * Create an instance of {@link NotificationResponseType }
     * 
     */
    public NotificationResponseType createNotificationResponseType() {
        return new NotificationResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificationRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms", name = "Request")
    public JAXBElement<NotificationRequestType> createRequest(NotificationRequestType value) {
        return new JAXBElement<NotificationRequestType>(_Request_QNAME, NotificationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificationResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms", name = "Response")
    public JAXBElement<NotificationResponseType> createResponse(NotificationResponseType value) {
        return new JAXBElement<NotificationResponseType>(_Response_QNAME, NotificationResponseType.class, null, value);
    }

}
