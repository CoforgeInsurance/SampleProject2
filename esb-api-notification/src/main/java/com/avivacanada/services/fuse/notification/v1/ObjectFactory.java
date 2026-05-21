
package com.avivacanada.services.fuse.notification.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avivacanada.services.fuse.notification.v1 package. 
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

    private final static QName _SendPhoneNotificationRq_QNAME = new QName("http://www.avivacanada.com/services/fuse/notification/v1", "sendPhoneNotificationRq");
    private final static QName _SendPhoneNotificationRs_QNAME = new QName("http://www.avivacanada.com/services/fuse/notification/v1", "sendPhoneNotificationRs");
    private final static QName _SOAPServiceException_QNAME = new QName("http://www.avivacanada.com/services/fuse/notification/v1", "SOAPServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avivacanada.services.fuse.notification.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendPhoneNotificationRequest }
     * 
     */
    public SendPhoneNotificationRequest createSendPhoneNotificationRequest() {
        return new SendPhoneNotificationRequest();
    }

    /**
     * Create an instance of {@link SendPhoneNotificationResponse }
     * 
     */
    public SendPhoneNotificationResponse createSendPhoneNotificationResponse() {
        return new SendPhoneNotificationResponse();
    }

    /**
     * Create an instance of {@link DetailException }
     * 
     */
    public DetailException createDetailException() {
        return new DetailException();
    }

    /**
     * Create an instance of {@link ClientInfoType }
     * 
     */
    public ClientInfoType createClientInfoType() {
        return new ClientInfoType();
    }

    /**
     * Create an instance of {@link PhoneType }
     * 
     */
    public PhoneType createPhoneType() {
        return new PhoneType();
    }

    /**
     * Create an instance of {@link NotificationIdentifierType }
     * 
     */
    public NotificationIdentifierType createNotificationIdentifierType() {
        return new NotificationIdentifierType();
    }

    /**
     * Create an instance of {@link BaseRequest }
     * 
     */
    public BaseRequest createBaseRequest() {
        return new BaseRequest();
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPhoneNotificationRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notification/v1", name = "sendPhoneNotificationRq")
    public JAXBElement<SendPhoneNotificationRequest> createSendPhoneNotificationRq(SendPhoneNotificationRequest value) {
        return new JAXBElement<SendPhoneNotificationRequest>(_SendPhoneNotificationRq_QNAME, SendPhoneNotificationRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPhoneNotificationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notification/v1", name = "sendPhoneNotificationRs")
    public JAXBElement<SendPhoneNotificationResponse> createSendPhoneNotificationRs(SendPhoneNotificationResponse value) {
        return new JAXBElement<SendPhoneNotificationResponse>(_SendPhoneNotificationRs_QNAME, SendPhoneNotificationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notification/v1", name = "SOAPServiceException")
    public JAXBElement<DetailException> createSOAPServiceException(DetailException value) {
        return new JAXBElement<DetailException>(_SOAPServiceException_QNAME, DetailException.class, null, value);
    }

}
