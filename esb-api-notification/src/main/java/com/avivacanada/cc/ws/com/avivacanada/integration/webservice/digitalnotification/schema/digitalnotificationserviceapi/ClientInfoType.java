
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ClientInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ClientID" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}ClientIDType"/&gt;
 *         &lt;element name="ClientName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="RequestReference" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransactionTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientInfoType", propOrder = {
    "clientID",
    "clientName",
    "requestReference",
    "transactionTime"
})
public class ClientInfoType {

    @XmlElement(name = "ClientID", required = true)
    @XmlSchemaType(name = "string")
    protected ClientIDType clientID;
    @XmlElement(name = "ClientName", required = true)
    protected String clientName;
    @XmlElement(name = "RequestReference", required = true)
    protected String requestReference;
    @XmlElement(name = "TransactionTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar transactionTime;

    /**
     * Gets the value of the clientID property.
     * 
     * @return
     *     possible object is
     *     {@link ClientIDType }
     *     
     */
    public ClientIDType getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientIDType }
     *     
     */
    public void setClientID(ClientIDType value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the requestReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestReference() {
        return requestReference;
    }

    /**
     * Sets the value of the requestReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestReference(String value) {
        this.requestReference = value;
    }

    /**
     * Gets the value of the transactionTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionTime() {
        return transactionTime;
    }

    /**
     * Sets the value of the transactionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionTime(XMLGregorianCalendar value) {
        this.transactionTime = value;
    }

}
