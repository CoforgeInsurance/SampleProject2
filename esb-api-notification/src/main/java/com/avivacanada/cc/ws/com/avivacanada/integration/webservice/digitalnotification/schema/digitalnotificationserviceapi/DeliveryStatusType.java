
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeliveryStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DeliveryStatusType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Delivered"/&gt;
 *     &lt;enumeration value="Failed"/&gt;
 *     &lt;enumeration value="Undelivered"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DeliveryStatusType")
@XmlEnum
public enum DeliveryStatusType {

    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Undelivered")
    UNDELIVERED("Undelivered");
    private final String value;

    DeliveryStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryStatusType fromValue(String v) {
        for (DeliveryStatusType c: DeliveryStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
