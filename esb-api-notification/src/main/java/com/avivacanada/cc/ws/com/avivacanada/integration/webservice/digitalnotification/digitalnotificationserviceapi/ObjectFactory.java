
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateNotificationStatus }
     * 
     */
    public UpdateNotificationStatus createUpdateNotificationStatus() {
        return new UpdateNotificationStatus();
    }

    /**
     * Create an instance of {@link UpdateNotificationStatusResponse }
     * 
     */
    public UpdateNotificationStatusResponse createUpdateNotificationStatusResponse() {
        return new UpdateNotificationStatusResponse();
    }

    /**
     * Create an instance of {@link UnsubscribeNotification }
     * 
     */
    public UnsubscribeNotification createUnsubscribeNotification() {
        return new UnsubscribeNotification();
    }

    /**
     * Create an instance of {@link UnsubscribeNotificationResponse }
     * 
     */
    public UnsubscribeNotificationResponse createUnsubscribeNotificationResponse() {
        return new UnsubscribeNotificationResponse();
    }

    /**
     * Create an instance of {@link UpdateNotificationStatus.NotificationStatusUpdateRq }
     * 
     */
    public UpdateNotificationStatus.NotificationStatusUpdateRq createUpdateNotificationStatusNotificationStatusUpdateRq() {
        return new UpdateNotificationStatus.NotificationStatusUpdateRq();
    }

    /**
     * Create an instance of {@link UpdateNotificationStatusResponse.Return }
     * 
     */
    public UpdateNotificationStatusResponse.Return createUpdateNotificationStatusResponseReturn() {
        return new UpdateNotificationStatusResponse.Return();
    }

    /**
     * Create an instance of {@link WsiAuthenticationException }
     * 
     */
    public WsiAuthenticationException createWsiAuthenticationException() {
        return new WsiAuthenticationException();
    }

    /**
     * Create an instance of {@link UnsubscribeNotification.UnsubscribeRq }
     * 
     */
    public UnsubscribeNotification.UnsubscribeRq createUnsubscribeNotificationUnsubscribeRq() {
        return new UnsubscribeNotification.UnsubscribeRq();
    }

    /**
     * Create an instance of {@link UnsubscribeNotificationResponse.Return }
     * 
     */
    public UnsubscribeNotificationResponse.Return createUnsubscribeNotificationResponseReturn() {
        return new UnsubscribeNotificationResponse.Return();
    }

}
