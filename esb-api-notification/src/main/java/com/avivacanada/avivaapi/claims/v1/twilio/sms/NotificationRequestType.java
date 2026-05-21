
package com.avivacanada.avivaapi.claims.v1.twilio.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for notificationRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="notificationRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="destinationNo" type="{http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms}phoneType"/&gt;
 *         &lt;element name="claimNo" type="{http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms}claimNumberType"/&gt;
 *         &lt;element name="smsLanguage" type="{http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms}languageType"/&gt;
 *         &lt;element name="smsMessage" type="{http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms}requestMessageType"/&gt;
 *         &lt;element name="ccTransactionID" type="{http://www.avivacanada.com/avivaapi/claims/v1/twilio/sms}transactionIDType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificationRequestType", propOrder = {
    "destinationNo",
    "claimNo",
    "smsLanguage",
    "smsMessage",
    "transactionRefID"
})
public class NotificationRequestType {

    @XmlElement(required = true)
    protected String destinationNo;
    @XmlElement(required = true)
    protected String claimNo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected LanguageType smsLanguage;
	@XmlElement(required = true)
    protected String smsMessage;
    @XmlElement(required = true)
    protected String transactionRefID;

    /**
     * Gets the value of the destinationNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationNo() {
        return destinationNo;
    }

    /**
     * Sets the value of the destinationNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationNo(String value) {
        this.destinationNo = value;
    }

    /**
     * Gets the value of the claimNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimNo() {
        return claimNo;
    }

    /**
     * Sets the value of the claimNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimNo(String value) {
        this.claimNo = value;
    }

    /**
     * Gets the value of the smsLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link LanguageType }
     *     
     */
    public LanguageType getSmsLanguage() {
        return smsLanguage;
    }

    /**
     * Sets the value of the smsLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageType }
     *     
     */
    public void setSmsLanguage(LanguageType value) {
        this.smsLanguage = value;
    }

    /**
     * Gets the value of the smsMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsMessage() {
        return smsMessage;
    }

    /**
     * Sets the value of the smsMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsMessage(String value) {
        this.smsMessage = value;
    }

    public String getTransactionRefID() {
		return transactionRefID;
	}

	public void setTransactionRefID(String transactionRefID) {
		this.transactionRefID = transactionRefID;
	}

}
