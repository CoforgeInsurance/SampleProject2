
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
 *         &lt;element name="unsubscribeRq" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeRq"/&gt;
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
    "unsubscribeRq"
})
@XmlRootElement(name = "unsubscribeNotification")
public class UnsubscribeNotification {

    protected UnsubscribeNotification.UnsubscribeRq unsubscribeRq;

    /**
     * Gets the value of the unsubscribeRq property.
     * 
     * @return
     *     possible object is
     *     {@link UnsubscribeNotification.UnsubscribeRq }
     *     
     */
    public UnsubscribeNotification.UnsubscribeRq getUnsubscribeRq() {
        return unsubscribeRq;
    }

    /**
     * Sets the value of the unsubscribeRq property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnsubscribeNotification.UnsubscribeRq }
     *     
     */
    public void setUnsubscribeRq(UnsubscribeNotification.UnsubscribeRq value) {
        this.unsubscribeRq = value;
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
     *         &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeRq"/&gt;
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
        "unsubscribeRq"
    })
    public static class UnsubscribeRq {

        @XmlElement(name = "UnsubscribeRq", namespace = "http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI", required = true)
        protected com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRq unsubscribeRq;

        /**
         * Gets the value of the unsubscribeRq property.
         * 
         * @return
         *     possible object is
         *     {@link com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRq }
         *     
         */
        public com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRq getUnsubscribeRq() {
            return unsubscribeRq;
        }

        /**
         * Sets the value of the unsubscribeRq property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRq }
         *     
         */
        public void setUnsubscribeRq(com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRq value) {
            this.unsubscribeRq = value;
        }

    }

}
