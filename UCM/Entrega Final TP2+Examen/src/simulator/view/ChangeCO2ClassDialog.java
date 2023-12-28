package simulator.view;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.model.Vehicle;


public class ChangeCO2ClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int estado; // 0 o 1
	
	private JComboBox<Vehicle> vehicles;
	private DefaultComboBoxModel<Vehicle> vehiclesModel;
	private JComboBox<Integer> CO2;
	private DefaultComboBoxModel<Integer> CO2Model;
	
	private JSpinner _ticksSpinner;
	
	
	public ChangeCO2ClassDialog(Frame parent) {
		super(parent, true);
		initGUI();		
	}
	
	public ChangeCO2ClassDialog() {		
		super();
		initGUI();		
	}
	
	private void initGUI() {

		estado = 0;
		
		
		
		setTitle("Change in CO2");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

		JLabel label = new JLabel("Select vehicle:              Select CO2:            Select number of ticks: ");
		label.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(label);

		
		
		
		//mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
				

		//mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		vehiclesModel = new DefaultComboBoxModel<>();
		vehicles = new JComboBox<>(vehiclesModel);
		
		CO2Model = new DefaultComboBoxModel<>();
		CO2 = new JComboBox<>(CO2Model);
		
		_ticksSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		_ticksSpinner.setToolTipText("Simulation tick to run: 1-1000"); // LOS TICKS QUE SE EJECUTAN
		_ticksSpinner.setMaximumSize(new Dimension(80, 40)); // DIMENSINA MAXIMA
		_ticksSpinner.setMinimumSize(new Dimension(80, 40)); // DIMENSION MINIMA
		_ticksSpinner.setPreferredSize(new Dimension(80, 40)); // DIMESION PREFERIDA
		
		viewsPanel.add(vehicles);
		viewsPanel.add(new JLabel("                     "));
		viewsPanel.add(CO2);
		viewsPanel.add(new JLabel("                     "));
		viewsPanel.add(_ticksSpinner);
		
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				ChangeCO2ClassDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vehiclesModel.getSelectedItem() != null) {
					estado = 1;
					ChangeCO2ClassDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(420, 200));
		pack();
		setResizable(false);
		setVisible(false);
		
	}
	
	public int open(List<Vehicle> vehicles, List<Integer> CO2) {

		// update the comboxBox model -- if you always use the same no
		// need to update it, you can initialize it in the constructor.
		//
		vehiclesModel.removeAllElements();
		for (Vehicle v : vehicles) {
			vehiclesModel.addElement(v);
		}
		
		CO2Model.removeAllElements();
		for (Integer c : CO2) {
			CO2Model.addElement(c);
		}

		// You can chenge this to place the dialog in the middle of the parent window.
		// It can be done using uing getParent().getWidth, this.getWidth(),
		// getParent().getHeight, and this.getHeight(), etc.
		//
		setLocation(getParent().getLocation().x + 300, getParent().getLocation().y + 360);

		setVisible(true);
		return estado;
	}
	
	public int getTicks() {
		return (Integer) _ticksSpinner.getValue();
	}
	
	public Vehicle getVehicle() {
		return (Vehicle) vehicles.getSelectedItem();
	}
	
	public int getCO2() {
		return (Integer) CO2.getSelectedItem();
	} 
	

}
