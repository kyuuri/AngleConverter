package ku.angle.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.ws.WebServiceException;

import ku.angle.controller.AngleController;

import net.webservicex.Angles;

/**
 * User Interface class for receiving input and showing the output
 * of the converted angle unit.
 * 
 * @author Sarathit Sangtaweep 5510546182
 */
public class AngleConverterView extends JFrame{
	
	/**attributes*/
	private JTextField inputField;
	private JTextField outputField;
	
	private JComboBox fromUnitBox;
	private JComboBox toUnitBox;
	private JPanel panel;
	private JPanel centerPanel;
	private JPanel innerPanelN;
	private JPanel innerPanelS;
	private JPanel panelLower;
	private JPanel lowerUpper;
	private JPanel lowerLower;
	private JButton btnConvert;
	
	private JLabel status;
	
	private AngleController controller;
	private Timer timer;
	
	public void setController(AngleController controller) {
		this.controller = controller;
		this.enableUI();
	}

	private AngleWorker worker;
	
	/**
	 * Constructor of the class.
	 * @param ac angle unit controller 
	 */
	public AngleConverterView(){
		super("AngleConverter");	
		initComponent();
	}
	
	/**
	 * Initialize UI component.
	 */
	private void initComponent(){
		panel = new JPanel();
		BorderLayout bd = new BorderLayout();
		panel.setLayout(bd);
		getContentPane().add(panel, BorderLayout.CENTER);		
		
		fromUnitBox = new JComboBox(Angles.values());
		toUnitBox = new JComboBox(Angles.values());
		
		centerPanel = new JPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("AngleUnit Converter");
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, BorderLayout.NORTH);
		
		GridLayout grid1 = new GridLayout(3,1);
		grid1.setVgap(5);
		innerPanelN = new JPanel(grid1);
		centerPanel.add(innerPanelN, BorderLayout.NORTH);
		
		innerPanelS = new JPanel(grid1);
		centerPanel.add(innerPanelS, BorderLayout.SOUTH);
		
		JLabel labelFrom = new JLabel("From");
		labelFrom.setFont(new Font("Arial", Font.BOLD, 14));
		labelFrom.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelTo = new JLabel("To");
		labelTo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTo.setFont(new Font("Arial", Font.BOLD, 14));
		
		inputField = new JTextField();
		inputField.setColumns(10);
		innerPanelN.add(labelFrom);
		innerPanelN.add(inputField);
		innerPanelN.add(fromUnitBox);
		
		
		outputField = new JTextField();
		outputField.setColumns(10);
		outputField.setEditable(false);
		innerPanelS.add(labelTo);
		innerPanelS.add(outputField);
		innerPanelS.add(toUnitBox);
		
		status = new JLabel("Connecting");
		status.setFont(new Font("Arial", Font.BOLD, 14));
		
		panelLower = new JPanel(new BorderLayout());
		panel.add(panelLower, BorderLayout.SOUTH);
		
		btnConvert = new JButton("Convert");
		lowerUpper = new JPanel();
		lowerLower = new JPanel();
		
		lowerUpper.add(status);
		lowerLower.add(btnConvert);
		panelLower.add(lowerUpper, BorderLayout.NORTH);
		panelLower.add(lowerLower, BorderLayout.SOUTH);
		
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Angles fromAngleUnit = (Angles)(fromUnitBox.getSelectedItem());
				Angles toAngleUnit = (Angles)(toUnitBox.getSelectedItem());
				double value;
				if(checkInput()){
					value = Double.parseDouble(inputField.getText());
					
					if (worker != null && !worker.isDone()){
						worker.cancel(true);
					}	
					else {
						worker = new AngleWorker(value, fromAngleUnit, toAngleUnit);
						worker.execute();
					}
				}
			}
		});
	}
	
	/**
	 * Check the validation of an input String.
	 * @return true if the String is valid; otherwise false
	 */
	public boolean checkInput(){
		try{
			Double.parseDouble(inputField.getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
				    "Invalid input. Please try again.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Run the user interface.
	 */
	public void run(){
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(455, 225);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.disableUI();
	}
	
	/**
	 * Disable all the components
	 */
	private void disableUI(){
		inputField.setEnabled(false);
		outputField.setEnabled(false);
		fromUnitBox.setEnabled(false);
		toUnitBox.setEnabled(false);
		btnConvert.setEnabled(false);
		status.setText("Connecting");
	}
	
	/**
	 * Enable all the components
	 */
	private void enableUI(){
		inputField.setEnabled(true);
		outputField.setEnabled(true);
		fromUnitBox.setEnabled(true);
		toUnitBox.setEnabled(true);
		btnConvert.setEnabled(true);
		status.setText("Connected");
	}
	
	/**
	 * AngleWorker class works as thread for using the controller,
	 * prevent the program from hanging.
	 * 
	 * @author Sarathit Sangtaweep 5510546182
	 */
	class AngleWorker extends SwingWorker<Double, Void>{	
		private double value;
		private Angles fromAngleUnit;
		private Angles toAngleUnit;
		
		/**
		 * Contructor.
		 * @param value value to be converted
		 * @param fromAngleUnit from angle unit
		 * @param toAngleUnit to angle unit
		 */
		public AngleWorker(double value, Angles fromAngleUnit, Angles toAngleUnit){
			this.value = value;
			this.fromAngleUnit = fromAngleUnit;
			this.toAngleUnit = toAngleUnit;
		}

		@Override
		protected Double doInBackground() throws Exception {
			return convert();
		}

		@Override
		protected void done() {
			try {
				if (!this.isCancelled()){
					double result = get();
					outputField.setText(result+"");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Convert the value of the angle.
		 * Handle timeout and network connection problem.
		 * @return converted value.
		 */
		private Double convert(){
			
			timer = new Timer(100, new ActionListener() {
				
				int count = 0;
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(count%6 == 0){
						status.setText(".Converting.");
					}
					else if(count%6 == 2){
						status.setText("..Converting..");
					}
					else if(count%6 == 4){
						status.setText("...Converting...");
					}
					
					if(count == 102){
						Object[] options = { "RETRY", "EXIT" };
						int c = JOptionPane.showOptionDialog(null, "Connection Timeout.", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
						
						if(c == 0){
							status.setText("Resuming");
							count = 0;
							worker.cancel(true);
							convert();
						}
						else{
							System.exit(0);
						}
					}
					count++;
				}
			});
			timer.start();
			try{
				double result = controller.convert(value, fromAngleUnit, toAngleUnit);
				timer.stop();
				status.setText("Converted");
				return result;
			}catch(WebServiceException we){
				timer.stop();
				status.setText("No connection");
				Object[] options = { "RETRY", "EXIT" };
				int c = JOptionPane.showOptionDialog(null, "No internet connection", "Warning",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(c == 0){
					setController(new AngleController());
				}
				else{
					System.exit(0);
				}
			}
			return convert();
		}
		
		
	}
	
}
