
package org.openestate.io.is24_xml.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnergieausweistypTyp.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnergieausweistypTyp"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="KeineAngabe"/&gt;
 *     &lt;enumeration value="Endenergiebedarf"/&gt;
 *     &lt;enumeration value="Energieverbrauchskennwert"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EnergieausweistypTyp")
@XmlEnum
public enum EnergieausweistypTyp {

    @XmlEnumValue("KeineAngabe")
    KEINE_ANGABE("KeineAngabe"),
    @XmlEnumValue("Endenergiebedarf")
    ENDENERGIEBEDARF("Endenergiebedarf"),
    @XmlEnumValue("Energieverbrauchskennwert")
    ENERGIEVERBRAUCHSKENNWERT("Energieverbrauchskennwert");
    private final String value;

    EnergieausweistypTyp(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnergieausweistypTyp fromValue(String v) {
        for (EnergieausweistypTyp c: EnergieausweistypTyp.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
