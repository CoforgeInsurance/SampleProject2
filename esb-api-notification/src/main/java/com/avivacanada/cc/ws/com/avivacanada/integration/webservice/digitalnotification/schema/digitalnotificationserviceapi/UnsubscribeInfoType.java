
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnsubscribeInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnsubscribeInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ClaimInfo" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}ClaimInfoType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="UnsubscribeStatus" type="{http://www.avivacanada.com/cc/ws/com/avivacanada/integration/webservice/digitalnotification/schema/DigitalNotificationServiceAPI}UnsubscribeStatusType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsubscribeInfoType", propOrder = {
    "claimInfo",
    "phoneNumber",
    "unsubscribeStatus"
})
public class UnsubscribeInfoType {

    @XmlElement(name = "ClaimInfo", required = true)
    protected List<ClaimInfoType> claimInfo;
    @XmlElement(name = "PhoneNumber", required = true)
    protected String phoneNumber;
    @XmlElement(name = "UnsubscribeStatus", required = true)
    @XmlSchemaType(name = "string")
    protected UnsubscribeStatusType unsubscribeStatus;

    /**
     * Gets the value of the claimInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claimInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaimInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaimInfoType }
     * 
     * 
     */
    public List<ClaimInfoType> getClaimInfo() {
        if (claimInfo == null) {
            claimInfo = new ArrayList<ClaimInfoType>();
        }
        return this.claimInfo;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the unsubscribeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link UnsubscribeStatusType }
     *     
     */
    public UnsubscribeStatusType getUnsubscribeStatus() {
        return unsubscribeStatus;
    }

    /**
     * Sets the value of the unsubscribeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnsubscribeStatusType }
     *     
     */
    public void setUnsubscribeStatus(UnsubscribeStatusType value) {
        this.unsubscribeStatus = value;
    }

}
