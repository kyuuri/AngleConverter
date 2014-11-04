import Controller.AngleController;
import View.AngleConverterView;
import net.webservicex.AngleUnit;
import net.webservicex.AngleUnitSoap;
import net.webservicex.Angles;

/**
 * Main class for creating controller and UI
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class Main {
	
	/**
	 * Main method for running and creating UI.
	 * 
	 * @param args command line argument
	 */
	public static void main(String[] args) {
		AngleController controller = new AngleController();
		AngleConverterView ui = new AngleConverterView(controller);
		ui.run();
	}
}
