
package com.avivacanada.services.fuse.notificationcallback.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avivacanada.services.fuse.notificationcallback.v1 package. 
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

    private final static QName _StatusUpdateRequest_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "statusUpdateRequest");
    private final static QName _StatusUpdateResponse_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "statusUpdateResponse");
    private final static QName _UnsubscribeRequest_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "unsubscribeRequest");
    private final static QName _UnsubscribeResponse_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "unsubscribeResponse");
    private final static QName _CallbackBaseResponseTypeErrorDescription_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "errorDescription");
    private final static QName _StatusupdateRequestTypeSmsErrorCode_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "smsErrorCode");
    private final static QName _StatusupdateRequestTypeSmsErrorDescription_QNAME = new QName("http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", "smsErrorDescription");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avivacanada.services.fuse.notificationcallback.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StatusupdateRequestType }
     * 
     */
    public StatusupdateRequestType createStatusupdateRequestType() {
        return new StatusupdateRequestType();
    }

    /**
     * Create an instance of {@link StatusupdateResponseType }
     * 
     */
    public StatusupdateResponseType createStatusupdateResponseType() {
        return new StatusupdateResponseType();
    }

    /**
     * Create an instance of {@link UnsubscribeRequestType }
     * 
     */
    public UnsubscribeRequestType createUnsubscribeRequestType() {
        return new UnsubscribeRequestType();
    }

    /**
     * Create an instance of {@link UnsubscribeResponseType }
     * 
     */
    public UnsubscribeResponseType createUnsubscribeResponseType() {
        return new UnsubscribeResponseType();
    }

    /**
     * Create an instance of {@link ClaimInfoType }
     * 
     */
    public ClaimInfoType createClaimInfoType() {
        return new ClaimInfoType();
    }

    /**
     * Create an instance of {@link CallbackBaseResponseType }
     * 
     */
    public CallbackBaseResponseType createCallbackBaseResponseType() {
        return new CallbackBaseResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusupdateRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "statusUpdateRequest")
    public JAXBElement<StatusupdateRequestType> createStatusUpdateRequest(StatusupdateRequestType value) {
        return new JAXBElement<StatusupdateRequestType>(_StatusUpdateRequest_QNAME, StatusupdateRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusupdateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "statusUpdateResponse")
    public JAXBElement<StatusupdateResponseType> createStatusUpdateResponse(StatusupdateResponseType value) {
        return new JAXBElement<StatusupdateResponseType>(_StatusUpdateResponse_QNAME, StatusupdateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "unsubscribeRequest")
    public JAXBElement<UnsubscribeRequestType> createUnsubscribeRequest(UnsubscribeRequestType value) {
        return new JAXBElement<UnsubscribeRequestType>(_UnsubscribeRequest_QNAME, UnsubscribeRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsubscribeResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "unsubscribeResponse")
    public JAXBElement<UnsubscribeResponseType> createUnsubscribeResponse(UnsubscribeResponseType value) {
        return new JAXBElement<UnsubscribeResponseType>(_UnsubscribeResponse_QNAME, UnsubscribeResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "errorDescription", scope = CallbackBaseResponseType.class)
    public JAXBElement<String> createCallbackBaseResponseTypeErrorDescription(String value) {
        return new JAXBElement<String>(_CallbackBaseResponseTypeErrorDescription_QNAME, String.class, CallbackBaseResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "smsErrorCode", scope = StatusupdateRequestType.class)
    public JAXBElement<String> createStatusupdateRequestTypeSmsErrorCode(String value) {
        return new JAXBElement<String>(_StatusupdateRequestTypeSmsErrorCode_QNAME, String.class, StatusupdateRequestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.avivacanada.com/services/fuse/notificationcallback/v1.0", name = "smsErrorDescription", scope = StatusupdateRequestType.class)
    public JAXBElement<String> createStatusupdateRequestTypeSmsErrorDescription(String value) {
        return new JAXBElement<String>(_StatusupdateRequestTypeSmsErrorDescription_QNAME, String.class, StatusupdateRequestType.class, value);
    }

}
