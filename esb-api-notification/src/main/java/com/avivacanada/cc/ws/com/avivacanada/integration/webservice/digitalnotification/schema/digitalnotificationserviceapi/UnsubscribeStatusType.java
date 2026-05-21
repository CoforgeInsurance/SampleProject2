
package com.avivacanada.cc.ws.com.avivacanada.integration.webservice.digitalnotification.schema.digitalnotificationserviceapi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnsubscribeStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnsubscribeStatusType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Unsubscribe"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UnsubscribeStatusType")
@XmlEnum
public enum UnsubscribeStatusType {

    @XmlEnumValue("Unsubscribe")
    UNSUBSCRIBE("Unsubscribe");
    private final String value;

    UnsubscribeStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnsubscribeStatusType fromValue(String v) {
        for (UnsubscribeStatusType c: UnsubscribeStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
