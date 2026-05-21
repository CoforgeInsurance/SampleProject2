
package com.avivacanada.services.fuse.notificationcallback.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnsubscribeRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnsubscribeRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="destinationNo" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}PhoneType"/&gt;
 *         &lt;element name="claimInfo" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}ClaimInfoType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ccTransactionID" type="{http://www.avivacanada.com/services/fuse/notificationcallback/v1.0}TransactionIDType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsubscribeRequestType", propOrder = {
    "destinationNo",
    "claimInfo",
    "ccTransactionID"
})
public class UnsubscribeRequestType {

    @XmlElement(required = true)
    protected String destinationNo;
    @XmlElement(required = true)
    protected List<ClaimInfoType> claimInfo;
    @XmlElement(required = true)
    protected String ccTransactionID;

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

    public void Unsubscribe()
    {    	
    }
}
