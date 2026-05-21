
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

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
 *         &lt;element name="ClientInfo" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}ClientInfoType"/&gt;
 *         &lt;element name="UnsubscribeInfo" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeInfoType"/&gt;
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
    "clientInfo",
    "unsubscribeInfo"
})
@XmlRootElement(name = "UnsubscribeRq")
public class UnsubscribeRq {

    @XmlElement(name = "ClientInfo", required = true)
    protected ClientInfoType clientInfo;
    @XmlElement(name = "UnsubscribeInfo", required = true)
    protected UnsubscribeInfoType unsubscribeInfo;

    /**
     * Gets the value of the clientInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ClientInfoType }
     *     
     */
    public ClientInfoType getClientInfo() {
        return clientInfo;
    }

    /**
     * Sets the value of the clientInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientInfoType }
     *     
     */
    public void setClientInfo(ClientInfoType value) {
        this.clientInfo = value;
    }

    /**
     * Gets the value of the unsubscribeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link UnsubscribeInfoType }
     *     
     */
    public UnsubscribeInfoType getUnsubscribeInfo() {
        return unsubscribeInfo;
    }

    /**
     * Sets the value of the unsubscribeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnsubscribeInfoType }
     *     
     */
    public void setUnsubscribeInfo(UnsubscribeInfoType value) {
        this.unsubscribeInfo = value;
    }

}
