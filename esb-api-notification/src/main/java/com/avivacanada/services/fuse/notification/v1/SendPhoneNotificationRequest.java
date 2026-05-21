
package com.avivacanada.services.fuse.notification.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendPhoneNotificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendPhoneNotificationRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.avivacanada.com/services/fuse/notification/v1}BaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationCategory"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Real"/&gt;
 *               &lt;enumeration value="Batch"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="phone" type="{http://www.avivacanada.com/services/fuse/notification/v1}PhoneType"/&gt;
 *         &lt;element name="identifier" type="{http://www.avivacanada.com/services/fuse/notification/v1}NotificationIdentifierType"/&gt;
 *         &lt;element name="underwritingCompany" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendPhoneNotificationRequest", propOrder = {
    "notificationCategory",
    "phone",
    "identifier",
    "underwritingCompany",
        "contactId",
    "message"
})
@XmlRootElement(name = "sendPhoneNotificationRq")
public class SendPhoneNotificationRequest
    extends BaseRequest
{

    @XmlElement(required = true)
    protected String notificationCategory;
    @XmlElement(required = true)
    protected PhoneType phone;
    @XmlElement(required = true)
    protected NotificationIdentifierType identifier;
    @XmlElement(required = true)
    protected String underwritingCompany;
    @XmlElement(required = true)
    protected String contactId;
    @XmlElement(required = true)
    protected String message;

    /**
     * Gets the value of the notificationCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationCategory() {
        return notificationCategory;
    }

    /**
     * Sets the value of the notificationCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationCategory(String value) {
        this.notificationCategory = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneType }
     *     
     */
    public PhoneType getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneType }
     *     
     */
    public void setPhone(PhoneType value) {
        this.phone = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link NotificationIdentifierType }
     *     
     */
    public NotificationIdentifierType getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificationIdentifierType }
     *     
     */
    public void setIdentifier(NotificationIdentifierType value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the underwritingCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderwritingCompany() {
        return underwritingCompany;
    }

    /**
     * Sets the value of the underwritingCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderwritingCompany(String value) {
        this.underwritingCompany = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void sendPhoneNotification()
    {
    }
}
