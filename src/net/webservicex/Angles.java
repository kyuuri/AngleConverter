
package net.webservicex;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Angles.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Angles">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="radians"/>
 *     &lt;enumeration value="mils"/>
 *     &lt;enumeration value="gradients"/>
 *     &lt;enumeration value="degrees"/>
 *     &lt;enumeration value="minutes"/>
 *     &lt;enumeration value="seconds"/>
 *     &lt;enumeration value="points"/>
 *     &lt;enumeration value="OneBySixtienCircle"/>
 *     &lt;enumeration value="OneByTenCircle"/>
 *     &lt;enumeration value="OneByEightCircle"/>
 *     &lt;enumeration value="OneBySixCircle"/>
 *     &lt;enumeration value="OneByFourCircle"/>
 *     &lt;enumeration value="OneByTwoCircle"/>
 *     &lt;enumeration value="fullCircle"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Angles")
@XmlEnum
public enum Angles {

    @XmlEnumValue("radians")
    RADIANS("radians"),
    @XmlEnumValue("mils")
    MILS("mils"),
    @XmlEnumValue("gradients")
    GRADIENTS("gradients"),
    @XmlEnumValue("degrees")
    DEGREES("degrees"),
    @XmlEnumValue("minutes")
    MINUTES("minutes"),
    @XmlEnumValue("seconds")
    SECONDS("seconds"),
    @XmlEnumValue("points")
    POINTS("points"),
    @XmlEnumValue("OneBySixtienCircle")
    ONE_BY_SIXTIEN_CIRCLE("OneBySixtienCircle"),
    @XmlEnumValue("OneByTenCircle")
    ONE_BY_TEN_CIRCLE("OneByTenCircle"),
    @XmlEnumValue("OneByEightCircle")
    ONE_BY_EIGHT_CIRCLE("OneByEightCircle"),
    @XmlEnumValue("OneBySixCircle")
    ONE_BY_SIX_CIRCLE("OneBySixCircle"),
    @XmlEnumValue("OneByFourCircle")
    ONE_BY_FOUR_CIRCLE("OneByFourCircle"),
    @XmlEnumValue("OneByTwoCircle")
    ONE_BY_TWO_CIRCLE("OneByTwoCircle"),
    @XmlEnumValue("fullCircle")
    FULL_CIRCLE("fullCircle");
    private final String value;

    Angles(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Angles fromValue(String v) {
        for (Angles c: Angles.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
