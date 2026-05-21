
package com.avivacanada.services.fuse.notificationcallback.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusupdateRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusupdateRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="claimNo" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}ClaimNumberType"/&gt;
 *         &lt;element name="ccTransactionID" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}TransactionIDType"/&gt;
 *         &lt;element name="smsStatus" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}StatusType"/&gt;
 *         &lt;element name="smsErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="smsErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contactId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusupdateRequestType", propOrder = {
    "claimNo",
    "ccTransactionID",
    "smsStatus",
    "smsErrorCode",
    "smsErrorDescription",
        "contactId"
})
public class StatusupdateRequestType {

    @XmlElement(required = true)
    protected String claimNo;
    @XmlElement(required = true)
    protected String ccTransactionID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusType smsStatus;
    @XmlElement(required = false)
    protected String smsErrorCode;
    @XmlElement(required = false)
    protected String smsErrorDescription;
    @XmlElement(required = false)
    protected String contactId;

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
     * Gets the value of the ccTransactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcTransactionID() {
        return ccTransactionID;
    }

    /**
     * Sets the value of the ccTransactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcTransactionID(String value) {
        this.ccTransactionID = value;
    }

    /**
     * Gets the value of the smsStatus property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getSmsStatus() {
        return smsStatus;
    }

    /**
     * Sets the value of the smsStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setSmsStatus(StatusType value) {
        this.smsStatus = value;
    }

    /**
     * Gets the value of the smsErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSmsErrorCode() {
        return smsErrorCode;
    }

    /**
     * Sets the value of the smsErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSmsErrorCode(String value) {
        this.smsErrorCode = value;
    }

    /**
     * Gets the value of the smsErrorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSmsErrorDescription() {
        return smsErrorDescription;
    }

    /**
     * Sets the value of the smsErrorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSmsErrorDescription(String value) {
        this.smsErrorDescription = value;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void Statusupdate()
    {
    }
}
