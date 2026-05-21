
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi.UnsubscribeRs;


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
 *         &lt;element name="return" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeRs"/&gt;
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
    "_return"
})
@XmlRootElement(name = "unsubscribeNotificationResponse")
public class UnsubscribeNotificationResponse {

    @XmlElement(name = "return")
    protected UnsubscribeNotificationResponse.Return _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link UnsubscribeNotificationResponse.Return }
     *     
     */
    public UnsubscribeNotificationResponse.Return getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnsubscribeNotificationResponse.Return }
     *     
     */
    public void setReturn(UnsubscribeNotificationResponse.Return value) {
        this._return = value;
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
     *         &lt;element ref="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeRs"/&gt;
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
        "unsubscribeRs"
    })
    public static class Return {

        @XmlElement(name = "UnsubscribeRs", namespace = "http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI", required = true)
        protected UnsubscribeRs unsubscribeRs;

        /**
         * Gets the value of the unsubscribeRs property.
         * 
         * @return
         *     possible object is
         *     {@link UnsubscribeRs }
         *     
         */
        public UnsubscribeRs getUnsubscribeRs() {
            return unsubscribeRs;
        }

        /**
         * Sets the value of the unsubscribeRs property.
         * 
         * @param value
         *     allowed object is
         *     {@link UnsubscribeRs }
         *     
         */
        public void setUnsubscribeRs(UnsubscribeRs value) {
            this.unsubscribeRs = value;
        }

    }

}
