package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.NewSetContClassEvent;
import simulator.model.NewVehicleEvent;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	private static final long serialVersionUID = 1L;	

	private Controller ctrl;
	
	private JButton eventsButton;	
	private JButton CO2Button;	
	private JButton weatherButton;	
	private JButton runButton;	
	private JButton stopButton;	
	private JSpinner _ticksSpinner;	
	private JButton exitButton;
	
	private boolean stopped;
	
	private JFileChooser fc;
	
	private Weather weathers;
	
	private RoadMap roadmap;
	
	private JButton vehicleButton;
	
	
	public ControlPanel(Controller _ctrl) {
		ctrl = _ctrl;
		_ctrl.addObserver(this);
		initGUI();
		
	}
	
	public void initGUI() {
		setLayout(new BorderLayout());
		JToolBar mainP = new JToolBar();
		this.add(mainP);	
		
		JToolBar buttons = new JToolBar();
		mainP.add(buttons); 
		
		
		fc = new JFileChooser();
		
		eventsButton = new JButton();
		eventsButton.setToolTipText("Events button"); // LOS TICKS QUE SE EJECUTAN
		eventsButton.setIcon(loadImage("resources/icons/open.png"));
		eventsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {										
				loadFile(fc);
			}
		});				
		
		
		CO2Button = new JButton();
		CO2Button.setToolTipText("CO2 button"); // LOS TICKS QUE SE EJECUTAN
		CO2Button.setIcon(loadImage("resources/icons/co2class.png"));
		CO2Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(roadmap != null) {
					changeCO2Class();
				}
				
			}
		});
		
		weatherButton = new JButton();
		weatherButton.setToolTipText("Weather button"); // LOS TICKS QUE SE EJECUTAN
		weatherButton.setIcon(loadImage("resources/icons/weather.png"));
		weatherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(roadmap != null) {
					changeWeatherClass();
				}
			}
		});
		
		
		runButton = new JButton();
		runButton.setToolTipText("Run button"); // LOS TICKS QUE SE EJECUTAN
		runButton.setIcon(loadImage("resources/icons/run.png"));
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();				
			}
		});
		
		stopButton = new JButton();
		stopButton.setToolTipText("Stop button"); 
		stopButton.setIcon(loadImage("resources/icons/stop.png"));
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		
		
		exitButton = new JButton();
		exitButton.setToolTipText("Exit button"); 
		exitButton.setIcon(loadImage("resources/icons/exit.png"));		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] opciones = {"No", "Yes"}; 
				if(JOptionPane.showOptionDialog(null, "Are you sure yo want to quit?", "Exit", 0, 0, null, opciones, opciones[1]) == 1) {
					System.exit(0);
				}
								
			}
		});		
		
		vehicleButton = new JButton();
		vehicleButton.setToolTipText("Vehicle button"); 
		vehicleButton.setIcon(loadImage("resources/icons/car_front.png"));		
		vehicleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addVehicleClass();
								
			}
		});	
		
		
		_ticksSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
		_ticksSpinner.setToolTipText("Simulation tick to run: 1-10000"); // LOS TICKS QUE SE EJECUTAN
		_ticksSpinner.setMaximumSize(new Dimension(80, 40)); // DIMENSINA MAXIMA
		_ticksSpinner.setMinimumSize(new Dimension(80, 40)); // DIMENSION MINIMA
		_ticksSpinner.setPreferredSize(new Dimension(80, 40)); // DIMESION PREFERIDA
		
		
		buttons.add(eventsButton);			
		buttons.add(CO2Button);
		buttons.add(weatherButton);
		buttons.add(runButton);
		buttons.add(stopButton);		
		buttons.add(vehicleButton);
		
		buttons.add(new JLabel("Ticks: "));
		buttons.add(_ticksSpinner);
		
		buttons.add(Box.createGlue());
		buttons.addSeparator();	
		buttons.add(exitButton);
		
	}
	
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
	
	protected void loadFile(JFileChooser fc) {		
		
		int x = fc.showOpenDialog(this); 
		
		
		if (x == JFileChooser.APPROVE_OPTION) { // x == 0
			
			File in = fc.getSelectedFile();
			
			try {
				ctrl.reset();
				ctrl.loadEvents(new FileInputStream(in));
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
						"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
			}		
		} else {
			JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
					"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	
	
	@SuppressWarnings("static-access")
	protected void changeWeatherClass() {
		ChangeWeatherClassDialog WeatherDialog = new ChangeWeatherClassDialog((Frame) this.getTopLevelAncestor());		
				
		List<Road> roads = new ArrayList<Road>();
		for (Road v : roadmap.getRoads()) {
			roads.add(v);
		}
		
		List<Weather> weather = new ArrayList<Weather>();
		
		weather.add(weathers.SUNNY);
		weather.add(weathers.CLOUDY);
		weather.add(weathers.RAINY);
		weather.add(weathers.WINDY);
		weather.add(weathers.STORM);
		
		
		int estado = WeatherDialog.open(roads, weather);
		
		if (estado == 1) {
			List<Pair<String, Weather>> ws = new ArrayList<Pair<String,Weather>>();	
			
			// AÑADIR EL VEHICULO Y LA CONTAMINACION A LA LISTA
			ws.add(new Pair<String, Weather>(WeatherDialog.getRoad().getId(), WeatherDialog.getWeather()));
			
			try {							
				 
				ctrl.addEvent(new SetWeatherEvent(ctrl.getTicks() + WeatherDialog.getTicks(), ws));
			} catch (Exception e) {
				JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
						"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}			
			
		} 
	}
	
	protected void changeCO2Class() {		
		ChangeCO2ClassDialog CO2Dialog = new ChangeCO2ClassDialog(null); // (Frame) this.getTopLevelAncestor()

		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (Vehicle v : roadmap.getVehicle()) {
			vehicles.add(v);
		}
		
		List<Integer> CO2 = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			CO2.add(i);
		}
		
		
		int estado = CO2Dialog.open(vehicles, CO2);
		
		if (estado == 1) {
			List<Pair<String,Integer>> cs = new ArrayList<Pair<String,Integer>>();	
			
			
			
			cs.add(new Pair<String, Integer>(CO2Dialog.getVehicle().getId(), CO2Dialog.getCO2()));
			
			
			
			try {						
				 
				ctrl.addEvent(new NewSetContClassEvent(ctrl.getTicks() + CO2Dialog.getTicks(), cs));				
			} catch (Exception e) {
				JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
						"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}			
			
		} 
	}
	
	public void addVehicleClass() {				
		AddVehicleClassDialog VehicleDialog = new AddVehicleClassDialog((Frame) this.getTopLevelAncestor());
		
		List<Integer> CO2 = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++) {
			CO2.add(i);
		}		
		
		int estado = VehicleDialog.open(CO2);
		
		if (estado == 1) {
			
			String it = VehicleDialog.getItinerary();
			String[] itD = it.split(",");
			
			
			List<String> lItinerario = new ArrayList<String>();
			for (String s : itD) {
				lItinerario.add(s);
			}
		
			
			try {						
				 ctrl.addEvent(new NewVehicleEvent (ctrl.getTicks() + VehicleDialog.getTicks(), VehicleDialog.getId(), 
						 VehicleDialog.getMaxSpeed(), VehicleDialog.getCO2(), lItinerario));
//				 ctrl.addEvent(new NewVehicleEvent (ctrl.getTicks() + VehicleDialog.getTicks(), "v4", 
//						 VehicleDialog.getMaxSpeed(), VehicleDialog.getCO2(), lItinerario));				
			} catch (Exception e) {
				JOptionPane.showMessageDialog((Frame) SwingUtilities.getWindowAncestor(this), 
						"Something went wrong ...",	"ERROR", JOptionPane.ERROR_MESSAGE);
				
			}			
			
		} 
	}
	
	
	
	protected void start() {
		stopped = false;
		
		eventsButton.setEnabled(false);
		CO2Button.setEnabled(false);
		weatherButton.setEnabled(false);
		runButton.setEnabled(false);		
		
		
		Integer time = (Integer) _ticksSpinner.getValue();
		run_sim(time);
	}
	
	
	private void run_sim(int n) {
		if (n > 0 && !stopped) {
			try {
				ctrl.run();
			} catch (Exception e) {				
				stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {			
				@Override
				public void run() {
					run_sim(n - 1);
				}
			});
		} else {
			enableToolBar(true);
			stopped = true;
		}
	}
	
	void enableToolBar(boolean b) {
		eventsButton.setEnabled(b);
		CO2Button.setEnabled(b);
		weatherButton.setEnabled(b);
		runButton.setEnabled(b);
	}
	
	private void stop() {
		stopped = true;
	}

	
	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		this.roadmap = map;	
		
	}	

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.roadmap = map;	
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
	}
	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {				
	}
	
	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {			
	}

	@Override
	public void onError(String msg) {			
	}
}
