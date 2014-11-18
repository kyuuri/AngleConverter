import ku.angle.controller.AngleController;
import ku.angle.view.AngleConverterView;

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
		AngleConverterView ui = new AngleConverterView();
		ui.run();
		AngleController controller = new AngleController();
		ui.setController(controller);
	}
}
