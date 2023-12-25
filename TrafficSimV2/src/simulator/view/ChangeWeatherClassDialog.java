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

import simulator.model.Road;
import simulator.model.Weather;


public class ChangeWeatherClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int estado; // 0 o 1
	
	private JComboBox<Road> roads;
	private DefaultComboBoxModel<Road> roadsModel;
	private JComboBox<Weather> weather;
	private DefaultComboBoxModel<Weather> weatherModel;
	
	private JSpinner _ticksSpinner;
	
	
	public ChangeWeatherClassDialog() {
		super();
		initGUI();		
	}
	
	public ChangeWeatherClassDialog(Frame parent) {
		super(parent, true);
		initGUI();		
	}
	
	private void initGUI() {

		estado = 0;
		
		
		
		setTitle("Change in Weather");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

		JLabel label = new JLabel("Select road:                 Select Weather:                Select number of ticks: ");
		label.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(label);

		
		
		
		//mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
				

		//mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		roadsModel = new DefaultComboBoxModel<>();
		roads = new JComboBox<>(roadsModel);
		
		weatherModel = new DefaultComboBoxModel<>();
		weather = new JComboBox<>(weatherModel);
		
		_ticksSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		_ticksSpinner.setToolTipText("Simulation tick to run: 1-1000"); // LOS TICKS QUE SE EJECUTAN
		_ticksSpinner.setMaximumSize(new Dimension(80, 40)); // DIMENSINA MAXIMA
		_ticksSpinner.setMinimumSize(new Dimension(80, 40)); // DIMENSION MINIMA
		_ticksSpinner.setPreferredSize(new Dimension(80, 40)); // DIMESION PREFERIDA
		
		viewsPanel.add(roads);
		viewsPanel.add(new JLabel("                     "));
		viewsPanel.add(weather);
		viewsPanel.add(new JLabel("                     "));
		viewsPanel.add(_ticksSpinner);
		
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				ChangeWeatherClassDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (roadsModel.getSelectedItem() != null) {
					estado = 1;
					ChangeWeatherClassDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(420, 200));
		pack();
		setResizable(false);
		setVisible(false);
		
	}
	
	public int open(List<Road> road, List<Weather> weather) {

		// update the comboxBox model -- if you always use the same no
		// need to update it, you can initialize it in the constructor.
		//
		roadsModel.removeAllElements();
		for (Road r : road) {
			roadsModel.addElement(r);
		}
		
		weatherModel.removeAllElements();
		for (Weather w : weather) {
			weatherModel.addElement(w);
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
	
	public Road getRoad() {
		return (Road) roads.getSelectedItem();
	}
	
	public Weather getWeather() {
		return (Weather) weather.getSelectedItem();
	} 
	

	
}
