
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationStatusUpdateRq" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}NotificationStatusUpdateRq"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "notificationStatusUpdateRq"
})
@XmlRootElement(name = "updateNotificationStatus")
public class UpdateNotificationStatus {

    protected UpdateNotificationStatus.NotificationStatusUpdateRq notificationStatusUpdateRq;

    /**
     * Gets the value of the notificationStatusUpdateRq property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateNotificationStatus.NotificationStatusUpdateRq }
     *     
     */
    public UpdateNotificationStatus.NotificationStatusUpdateRq getNotificationStatusUpdateRq() {
        return notificationStatusUpdateRq;
    }

    /**
     * Sets the value of the notificationStatusUpdateRq property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateNotificationStatus.NotificationStatusUpdateRq }
     *     
     */
    public void setNotificationStatusUpdateRq(UpdateNotificationStatus.NotificationStatusUpdateRq value) {
        this.notificationStatusUpdateRq = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}NotificationStatusUpdateRq"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "notificationStatusUpdateRq"
    })
    @XmlRootElement(name = "notificationStatusUpdateRq")
    public static class NotificationStatusUpdateRq {

        @XmlElement(name = "NotificationStatusUpdateRq", namespace = "http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI", required = true)
        protected com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRq notificationStatusUpdateRq;

        /**
         * Gets the value of the notificationStatusUpdateRq property.
         * 
         * @return
         *     possible object is
         *     {@link com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRq }
         *     
         */
        public com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRq getNotificationStatusUpdateRq() {
            return notificationStatusUpdateRq;
        }

        /**
         * Sets the value of the notificationStatusUpdateRq property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRq }
         *     
         */
        public void setNotificationStatusUpdateRq(com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.NotificationStatusUpdateRq value) {
            this.notificationStatusUpdateRq = value;
        }

    }

}
