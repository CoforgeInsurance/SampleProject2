
package com.avivacanada.services.fuse.notification.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificationCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdentificationCategoryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PolicyNumber"/&gt;
 *     &lt;enumeration value="ClaimNumber"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "IdentificationCategoryType")
@XmlEnum
public enum IdentificationCategoryType {

    @XmlEnumValue("PolicyNumber")
    POLICY_NUMBER("PolicyNumber"),
    @XmlEnumValue("ClaimNumber")
    CLAIM_NUMBER("ClaimNumber");
    private final String value;

    IdentificationCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentificationCategoryType fromValue(String v) {
        for (IdentificationCategoryType c: IdentificationCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
