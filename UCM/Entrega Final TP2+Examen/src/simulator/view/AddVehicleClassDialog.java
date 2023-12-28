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
import javax.swing.JTextField;




public class AddVehicleClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int estado; // 0 o 1
	
	private JComboBox<Integer> jcCO2;
	private DefaultComboBoxModel<Integer> CO2Model;
	
	private JTextField vehicles2;
	private JTextField itinerary2;
	
	private JSpinner _ticksSpinner;
	private JSpinner speedSpinner;
	
	
	public AddVehicleClassDialog(Frame parent) {
		super(parent, true);
		initGUI();		
	}
	
	public AddVehicleClassDialog() {		
		super();
		initGUI();		
	}
	
	private void initGUI() {
		estado = 0;		
		
		setTitle("Add a new vehicle");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		JLabel label = new JLabel("Fill in the vehicle's information, anb click Add button");
		label.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(label);
			
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

		// TICKS PANEL (PARA QUE QUEDE BIEN COMO EN LA FOTO) ---------------------------------
		JPanel ticksPanel = new JPanel();
		ticksPanel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(ticksPanel);
		
		JLabel ticks = new JLabel("Ticks from now:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		ticksPanel.add(ticks);
		
		JLabel ticksSpace = new JLabel("                   ");
		
		ticksPanel.add(ticksSpace);
		_ticksSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		_ticksSpinner.setToolTipText("Simulation tick to run: 1-1000"); // LOS TICKS QUE SE EJECUTAN
		_ticksSpinner.setMaximumSize(new Dimension(80, 20)); // DIMENSINA MAXIMA
		_ticksSpinner.setMinimumSize(new Dimension(80, 20)); // DIMENSION MINIMA
		_ticksSpinner.setPreferredSize(new Dimension(80, 20)); // DIMESION PREFERIDA
		
		ticksPanel.add(_ticksSpinner);
		
		// ---------------------------------------------------------------------------------------
		
		// IDENTIFIER PANEL (PARA QUE QUEDE BIEN COMO EN LA FOTO) --------------------------------
		JPanel identifierPanel = new JPanel();
		identifierPanel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(identifierPanel);
		
		
		JLabel identifier = new JLabel("Identifier:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		identifierPanel.add(identifier);
		
		vehicles2 = new JTextField(10);
		
//		vehiclesModel = new DefaultComboBoxModel<>();
//		vehicles = new JComboBox<>(vehiclesModel);
		
		JLabel identifierSpace = new JLabel("                 ");
		
		identifierPanel.add(identifierSpace);
		
		identifierPanel.add(vehicles2);
		
		// ---------------------------------------------------------------------------------------
		
		// ITINERARY PANEL (PARA QUE QUEDE BIEN COMO EN LA FOTO) ---------------------------------
		JPanel itineraryPanel = new JPanel();
		itineraryPanel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(itineraryPanel);
		
		
		JLabel itinerary = new JLabel("Itinerary:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		itineraryPanel.add(itinerary);
		
		itinerary2 = new JTextField(10);
		
		JLabel itineraySpace = new JLabel("                 ");
		
		itineraryPanel.add(itineraySpace);
		
		itineraryPanel.add(itinerary2);
		
		// ---------------------------------------------------------------------------------------
		
		// CO2 PANEL (PARA QUE QUEDE BIEN COMO EN LA FOTO) ---------------------------------------
		JPanel CO2Panel = new JPanel();
		CO2Panel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(CO2Panel);
		
		
		JLabel CO2 = new JLabel("CO2 Class:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		CO2Panel.add(CO2);
		

		CO2Model = new DefaultComboBoxModel<>();
		jcCO2 = new JComboBox<>(CO2Model);
		
		
		JLabel CO2Space = new JLabel("                       ");
		
		CO2Panel.add(CO2Space);
		
		CO2Panel.add(jcCO2);
			
		// ---------------------------------------------------------------------------------------
		
		// SPEED PANEL (PARA QUE QUEDE BIEN COMO EN LA FOTO) -------------------------------------
		
		JPanel speedPanel = new JPanel();
		speedPanel.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(speedPanel);
		
		JLabel speedticks = new JLabel("Maximum Speed:");
		label.setAlignmentX(LEFT_ALIGNMENT);
		speedPanel.add(speedticks);
		
		JLabel speedSpace = new JLabel("                   ");
		
		speedPanel.add(speedSpace);
		speedSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1));
		speedSpinner.setToolTipText("Simulation tick to run: 1-1000"); // LOS TICKS QUE SE EJECUTAN
		speedSpinner.setMaximumSize(new Dimension(80, 20)); // DIMENSINA MAXIMA
		speedSpinner.setMinimumSize(new Dimension(80, 20)); // DIMENSION MINIMA
		speedSpinner.setPreferredSize(new Dimension(80, 20)); // DIMESION PREFERIDA
		
		speedPanel.add(speedSpinner);
		
		// ---------------------------------------------------------------------------------------
		
		
		
		
		// BOTONES -------------------------------------------------------------------------------
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel);		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				AddVehicleClassDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("Add");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				if (vehiclesModel.getSelectedItem() != null) {
					estado = 1;
					AddVehicleClassDialog.this.setVisible(false);
//				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(420, 400));
		pack();
		setResizable(false);
		setVisible(false);
		
	}
	
	public int open(List<Integer> CO2) {

		// update the comboxBox model -- if you always use the same no
		// need to update it, you can initialize it in the constructor.
		//

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
	
	public String getId() {
		return (String) vehicles2.getText();
	}
	
	public String getItinerary() {
		return (String) itinerary2.getText();
	}	

	public int getCO2() {
		return (Integer) jcCO2.getSelectedItem();
	} 
	
	public int getMaxSpeed() {
		return (Integer) speedSpinner.getValue();
	}
	

}
