
package net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AngleValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fromAngleUnit" type="{http://www.webserviceX.NET/}Angles"/>
 *         &lt;element name="toAngleUnit" type="{http://www.webserviceX.NET/}Angles"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "angleValue",
    "fromAngleUnit",
    "toAngleUnit"
})
@XmlRootElement(name = "ChangeAngleUnit")
public class ChangeAngleUnit {

    @XmlElement(name = "AngleValue")
    protected double angleValue;
    @XmlElement(required = true)
    protected Angles fromAngleUnit;
    @XmlElement(required = true)
    protected Angles toAngleUnit;

    /**
     * Gets the value of the angleValue property.
     * 
     */
    public double getAngleValue() {
        return angleValue;
    }

    /**
     * Sets the value of the angleValue property.
     * 
     */
    public void setAngleValue(double value) {
        this.angleValue = value;
    }

    /**
     * Gets the value of the fromAngleUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Angles }
     *     
     */
    public Angles getFromAngleUnit() {
        return fromAngleUnit;
    }

    /**
     * Sets the value of the fromAngleUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Angles }
     *     
     */
    public void setFromAngleUnit(Angles value) {
        this.fromAngleUnit = value;
    }

    /**
     * Gets the value of the toAngleUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Angles }
     *     
     */
    public Angles getToAngleUnit() {
        return toAngleUnit;
    }

    /**
     * Sets the value of the toAngleUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Angles }
     *     
     */
    public void setToAngleUnit(Angles value) {
        this.toAngleUnit = value;
    }

}
