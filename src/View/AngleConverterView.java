package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

import net.webservicex.Angles;
import Controller.AngleController;

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
	private JButton btnConvert;
	
	private AngleController controller;
	private AngleWorker worker;
	
	/**
	 * Constructor of the class.
	 * @param ac angle unit controller 
	 */
	public AngleConverterView(AngleController ac){
		super("AngleConverter");	
		controller = ac;
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
		
		panelLower = new JPanel();
		panel.add(panelLower, BorderLayout.SOUTH);
		
		btnConvert = new JButton("Convert");
		panelLower.add(btnConvert, BorderLayout.CENTER);
		
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
					worker = new AngleWorker(value, fromAngleUnit, toAngleUnit);
					worker.execute();
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
		this.setSize(455, 195);
		this.setResizable(false);
		if(!controller.checkInternetConnection()){
			disableUI();
		}	
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
			double result = controller.convert(value, fromAngleUnit, toAngleUnit);
			return result;
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
		
		
	}
	
}
