
package com.avivacanada.services.fuse.notification.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientNameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClientNameType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ClaimCenter"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ClientNameType")
@XmlEnum
public enum ClientNameType {

    @XmlEnumValue("ClaimCenter")
    CLAIM_CENTER("ClaimCenter");
    private final String value;

    ClientNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClientNameType fromValue(String v) {
        for (ClientNameType c: ClientNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
