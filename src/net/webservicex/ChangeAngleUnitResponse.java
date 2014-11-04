
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
 *         &lt;element name="ChangeAngleUnitResult" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "changeAngleUnitResult"
})
@XmlRootElement(name = "ChangeAngleUnitResponse")
public class ChangeAngleUnitResponse {

    @XmlElement(name = "ChangeAngleUnitResult")
    protected double changeAngleUnitResult;

    /**
     * Gets the value of the changeAngleUnitResult property.
     * 
     */
    public double getChangeAngleUnitResult() {
        return changeAngleUnitResult;
    }

    /**
     * Sets the value of the changeAngleUnitResult property.
     * 
     */
    public void setChangeAngleUnitResult(double value) {
        this.changeAngleUnitResult = value;
    }

}
