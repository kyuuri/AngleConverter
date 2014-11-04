package Controller;

import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import net.webservicex.AngleUnit;
import net.webservicex.AngleUnitSoap;
import net.webservicex.Angles;

/**
 * AngleController use to connect to the angle unit converter service,
 * retrieve the result and send back to UI. 
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class AngleController {
	
	/**attributes*/
	private AngleUnit factory;
	private AngleUnitSoap proxy;
	private boolean internetConnection;
	
	/**constructor*/
	public AngleController(){
		try{
			factory = new AngleUnit();
			proxy = factory.getAngleUnitSoap();
			internetConnection = true;
		}catch(WebServiceException we){
			internetConnection = false;
			JOptionPane.showMessageDialog(null,
				    "No internet connection. Please re-open the program with internet connection.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Connect to the service and return the result.
	 * @param value value to convert
	 * @param fromAngleUnit from angle unit
	 * @param toAngleUnit to angle unit
	 * @return the converted value
	 */
	public double convert(double value, Angles fromAngleUnit, Angles toAngleUnit){
		double result = 0;
		try{
			result = proxy.changeAngleUnit(value, fromAngleUnit, toAngleUnit);
		}catch(WebServiceException we){
			JOptionPane.showMessageDialog(null,
				    "No internet connection. Please try again.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return result;
	}
	
	/**
	 * Return the Internet connection status.
	 * @return Internet connection status.
	 */
	public boolean checkInternetConnection(){
		return internetConnection;
	}
}
