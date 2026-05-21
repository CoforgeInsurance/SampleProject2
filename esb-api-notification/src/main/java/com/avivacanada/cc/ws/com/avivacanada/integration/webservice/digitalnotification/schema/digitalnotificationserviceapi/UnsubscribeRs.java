
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="IsSuccessful" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}YesNoType"/&gt;
 *         &lt;element name="ErrorInfo" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}ErrorInfoType" minOccurs="0"/&gt;
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
    "isSuccessful",
    "errorInfo"
})
@XmlRootElement(name = "UnsubscribeRs")
public class UnsubscribeRs {

    @XmlElement(name = "ClientInfo", required = true)
    protected ClientInfoType clientInfo;
    @XmlElement(name = "IsSuccessful", required = true)
    @XmlSchemaType(name = "string")
    protected YesNoType isSuccessful;
    @XmlElement(name = "ErrorInfo")
    protected ErrorInfoType errorInfo;

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
     * Gets the value of the isSuccessful property.
     * 
     * @return
     *     possible object is
     *     {@link YesNoType }
     *     
     */
    public YesNoType getIsSuccessful() {
        return isSuccessful;
    }

    /**
     * Sets the value of the isSuccessful property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoType }
     *     
     */
    public void setIsSuccessful(YesNoType value) {
        this.isSuccessful = value;
    }

    /**
     * Gets the value of the errorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorInfoType }
     *     
     */
    public ErrorInfoType getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the value of the errorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorInfoType }
     *     
     */
    public void setErrorInfo(ErrorInfoType value) {
        this.errorInfo = value;
    }

}
