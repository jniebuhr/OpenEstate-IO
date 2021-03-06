
package org.openestate.io.openimmo.xml;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy2;
import org.jvnet.jaxb2_commons.lang.CopyTo2;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Java class for &lt;aktion&gt; element.
 *
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "aktion")
public class Aktion implements Serializable, Cloneable, CopyTo2, Equals2, ToString2
{

    @XmlAttribute(name = "aktionart")
    protected Aktion.AktionArt aktionart;

    /**
     * Gets the value of the aktionart property.
     *
     * @return
     *     possible object is
     *     {@link Aktion.AktionArt }
     *
     */
    public Aktion.AktionArt getAktionart() {
        return aktionart;
    }

    /**
     * Sets the value of the aktionart property.
     *
     * @param value
     *     allowed object is
     *     {@link Aktion.AktionArt }
     *
     */
    public void setAktionart(Aktion.AktionArt value) {
        this.aktionart = value;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            Aktion.AktionArt theAktionart;
            theAktionart = this.getAktionart();
            strategy.appendField(locator, this, "aktionart", buffer, theAktionart, (this.aktionart!= null));
        }
        return buffer;
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy2 strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy2 strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof Aktion) {
            final Aktion copy = ((Aktion) draftCopy);
            {
                Boolean aktionartShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, (this.aktionart!= null));
                if (aktionartShouldBeCopiedAndSet == Boolean.TRUE) {
                    Aktion.AktionArt sourceAktionart;
                    sourceAktionart = this.getAktionart();
                    Aktion.AktionArt copyAktionart = ((Aktion.AktionArt) strategy.copy(LocatorUtils.property(locator, "aktionart", sourceAktionart), sourceAktionart, (this.aktionart!= null)));
                    copy.setAktionart(copyAktionart);
                } else {
                    if (aktionartShouldBeCopiedAndSet == Boolean.FALSE) {
                        copy.aktionart = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Aktion();
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Aktion that = ((Aktion) object);
        {
            Aktion.AktionArt lhsAktionart;
            lhsAktionart = this.getAktionart();
            Aktion.AktionArt rhsAktionart;
            rhsAktionart = that.getAktionart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aktionart", lhsAktionart), LocatorUtils.property(thatLocator, "aktionart", rhsAktionart), lhsAktionart, rhsAktionart, (this.aktionart!= null), (that.aktionart!= null))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * Java enum for <em>aktionart</em> attribute in &lt;aktion&gt; elements.

     */
    @XmlType(name = "")
    @XmlEnum
    public enum AktionArt {

        CHANGE,
        DELETE,
        REFERENZ;

        public String value() {
            return name();
        }

        public static Aktion.AktionArt fromValue(String v) {
            return valueOf(v);
        }

    }

}