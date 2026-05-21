
package com.avivacanada.avivaapi.claims.v1.twilio.sms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for errorCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="errorCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NOD5001"/&gt;
 *     &lt;enumeration value="NOD5002"/&gt;
 *     &lt;enumeration value="NOD5003"/&gt;
 *     &lt;enumeration value="NOD5004"/&gt;
 *     &lt;enumeration value="MDB7001"/&gt;
 *     &lt;enumeration value="MDB7002"/&gt;
 *     &lt;enumeration value="MDB7003"/&gt;
 *     &lt;enumeration value="MDB7004"/&gt;
 *     &lt;enumeration value="MDB7005"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "errorCodeType")
@XmlEnum
public enum ErrorCodeType {

    @XmlEnumValue("NOD5001")
    NOD5001("NOD5001"),
    @XmlEnumValue("NOD5002")
    NOD5002("NOD5002"),
    @XmlEnumValue("NOD5003")
    NOD5003("NOD5003"),
    @XmlEnumValue("NOD5004")
    NOD5004("NOD5004"),
    @XmlEnumValue("MDB7001")
    MDB7001("MDB7001"),
    @XmlEnumValue("MDB7002")
    MDB7002("MDB7002"),
    @XmlEnumValue("MDB7003")
    MDB7003("MDB7003"),
    @XmlEnumValue("MDB7004")
    MDB7004("MDB7004"),
    @XmlEnumValue("MDB7005")
    MDB7005("MDB7005");
    private final String value;

    ErrorCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorCodeType fromValue(String v) {
        for (ErrorCodeType c: ErrorCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
