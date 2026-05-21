
package com.avivacanada.services.fuse.notification.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhoneCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PhoneCategoryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MOBILE"/&gt;
 *     &lt;enumeration value="LANDLINE"/&gt;
 *     &lt;enumeration value="FAX"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PhoneCategoryType")
@XmlEnum
public enum PhoneCategoryType {

    MOBILE,
    LANDLINE,
    FAX;

    public String value() {
        return name();
    }

    public static PhoneCategoryType fromValue(String v) {
        return valueOf(v);
    }

}
