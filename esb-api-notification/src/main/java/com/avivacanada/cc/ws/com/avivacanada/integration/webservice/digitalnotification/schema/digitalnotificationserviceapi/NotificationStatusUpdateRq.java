
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
 *         &lt;element name="NotificationStatusInfo" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}NotificationStatusInfoType"/&gt;
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
    "notificationStatusInfo"
})
@XmlRootElement(name = "NotificationStatusUpdateRq")
public class NotificationStatusUpdateRq {

    @XmlElement(name = "ClientInfo", required = true)
    protected ClientInfoType clientInfo;
    @XmlElement(name = "NotificationStatusInfo", required = true)
    protected NotificationStatusInfoType notificationStatusInfo;

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
     * Gets the value of the notificationStatusInfo property.
     * 
     * @return
     *     possible object is
     *     {@link NotificationStatusInfoType }
     *     
     */
    public NotificationStatusInfoType getNotificationStatusInfo() {
        return notificationStatusInfo;
    }

    /**
     * Sets the value of the notificationStatusInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationStatusInfoType }
     *     
     */
    public void setNotificationStatusInfo(NotificationStatusInfoType value) {
        this.notificationStatusInfo = value;
    }

}
