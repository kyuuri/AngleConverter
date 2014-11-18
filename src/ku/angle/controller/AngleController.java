package ku.angle.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.xml.ws.WebServiceException;

import net.webservicex.AngleUnit;
import net.webservicex.AngleUnitSoap;
import net.webservicex.Angles;

/**
 * AngleController use to connect to the angle unit converter service, retrieve
 * the result and send back to UI.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class AngleController {

	/** attributes */
	private AngleUnit factory;
	private AngleUnitSoap proxy;
	private Timer timer;

	/** constructor */
	public AngleController() {
		createController();
	}

	/**
	 * Connect to the service and return the result.
	 * 
	 * @param value
	 *            value to convert
	 * @param fromAngleUnit
	 *            from angle unit
	 * @param toAngleUnit
	 *            to angle unit
	 * @return the converted value
	 */
	public double convert(double value, Angles fromAngleUnit, Angles toAngleUnit) {
		double result = 0;
		try {
			result = proxy.changeAngleUnit(value, fromAngleUnit, toAngleUnit);
		} catch (WebServiceException we) {
			throw we;
		}
		return result;
	}

	/**
	 * Create the controller and handle an Internet connection problem.
	 */
	public void createController() {
		try {
			factory = new AngleUnit();
			proxy = factory.getAngleUnitSoap();
		} catch (WebServiceException we) {
				Object[] options = { "RETRY", "EXIT" };
				int c = JOptionPane.showOptionDialog(null,
						"No internet connection", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
				if (c == 0) {

					createController();
				} else {
					System.exit(0);
				}		
		}
	}
}
