package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;
import simulator.model.Weather;

public class MapByRoadComponent  extends JPanel implements TrafficSimObserver {
	
	private static final long serialVersionUID = 1L;
	
	private static final int JRADIUS = 10;
	
	private static final Color ROAD_COLOR = Color.BLACK; 	// COLOR DE LA LINEA DE CARRETERA (NEGRO)
	private static final Color BG_COLOR = Color.WHITE;		
	private static final Color JUNCTION_COLOR = Color.BLUE;
	private static final Color JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color RED_LIGHT_COLOR = Color.RED;

	private RoadMap map;
	
	private Image car;	
	
	private Image weather[];	
	
	private Image cont[];

	MapByRoadComponent(Controller ctrl) {		
		initGUI();
		ctrl.addObserver(this);
	}	
	
	private void initGUI() {
		cont = new Image[6];
		weather = new Image[5];
		
		car = loadImage("car.png");				
		
		cont[0] = loadImage("cont_0.png");
		cont[1] = loadImage("cont_1.png");
		cont[2] = loadImage("cont_2.png");
		cont[3] = loadImage("cont_3.png");
		cont[4] = loadImage("cont_4.png");
		cont[5] = loadImage("cont_5.png");		
		
		weather[0] = loadImage("sun.png"); 
		weather[1] = loadImage("cloud.png");		
		weather[2] = loadImage("rain.png");	
		weather[3] = loadImage("wind.png");		
		weather[4] = loadImage("storm.png");	
		
		setPreferredSize(new Dimension (300, 200));
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (map == null || map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			//updatePrefferedSize();
			drawMap(g);
		}
	}

	private void drawMap(Graphics g) {
		int y = 50;
		int x1 = 50;
		int x2 = getWidth() - 100;
		
		for (Road r : map.getRoads()) {			
			
			// ROADS
			g.setColor(ROAD_COLOR);
			g.drawString(r.getId(), x1-30, y+3);					
			g.drawLine(x1, y, x2, y);
			
			// JUNCTIONS
			g.setColor(JUNCTION_COLOR);
			g.fillOval(x1 - JRADIUS / 2, y - JRADIUS / 2, JRADIUS, JRADIUS);

			g.setColor(JUNCTION_LABEL_COLOR);				
			g.drawString(r.getSrc().getId(), x1-4, y-8);
			g.drawString(r.getDest().getId(), x2-4, y-8);	
			
			int idx = r.getDest().getGreenLightIndex();
			if (idx != -1 && r.equals(r.getDest().getInRoads().get(idx))) {
				g.setColor(GREEN_LIGHT_COLOR); 
			} else {
				g.setColor(RED_LIGHT_COLOR); 
			}			
			g.fillOval(x2 - JRADIUS / 2, y - JRADIUS / 2, JRADIUS, JRADIUS);
			
			
			// IMAGENES DE WEATHER Y CONTAMINATION
			g.drawImage(weather[getWeatherImage(r.getWeather())], x2+11, y-16, 32, 32, this);			
			int C = (int) Math.floor(Math.min((double) r.getTotalCO2()/(1.0 + (double) r.getContLimit()),1.0) / 0.19);
			g.drawImage(cont[C], x2+51, y-16, 32, 32, this);
				
			
			
			// VEHICLES
			for (Vehicle v: r.getVehicles()) {
				if (v.getStatus() != VehicleStatus.ARRIVED) {
					int x = x1 + (int) ((x2 - x1) * ((double) v.getLocation() / (double) v.getRoad().getLength()));
			
					int vLabelColor = (int) (25.0 * (10.0 - (double) v.getContClass()));

					g.drawImage(car, x, y -9, 16, 16, this);
					
					g.setColor(new Color(0, vLabelColor, 0));
					g.drawString(v.getId(), x, y - 10);	
					
					
				}
			}
			y += 60;
		}	
		
		//drawRoads(g, y, x1, x2);
		
	}
	
	/*private void drawRoads(Graphics g, int y, int x1, int x2) {
		for (Road r : map.getRoads()) {
			
			
			g.setColor(ROAD_COLOR);
			g.drawString(r.getId(), x1-30, y+3);
			
			//int roadColorValue = 200 - (int) (200.0 * Math.min(1.0, (double) r.getTotalCO2() / (1.0 + (double) r.getContLimit())));
			//Color roadColor = new Color(roadColorValue, roadColorValue, roadColorValue);			
			g.drawLine(x1, y, x2, y);
			
			g.setColor(JUNCTION_COLOR);
			g.fillOval(x1 - JRADIUS / 2, y - JRADIUS / 2, JRADIUS, JRADIUS);
			
			int idx = r.getDest().getGreenLightIndex();
			if (idx != -1 && r.equals(r.getDest().getInRoads().get(idx))) {
				g.setColor(GREEN_LIGHT_COLOR); 
			} else {
				g.setColor(RED_LIGHT_COLOR); 
			}
			
			g.fillOval(x2 - JRADIUS / 2, y - JRADIUS / 2, JRADIUS, JRADIUS);
			
			//g.drawImage(weather[r.getWeather().getImage()], x2+11, y-16, 32, 32, this);
			g.drawImage(weather[getWeatherImage(r.getWeather())], x2+11, y-16, 32, 32, this);
			
			int C = (int) Math.floor(Math.min((double) r.getTotalCO2()/(1.0 + (double) r.getContLimit()),1.0) / 0.19);
			g.drawImage(cont[C], x2+51, y-16, 32, 32, this);
			
			g.setColor(JUNCTION_LABEL_COLOR);	
			
			g.drawString(r.getSrc().getId(), x1-4, y-8);
			g.drawString(r.getDest().getId(), x2-4, y-8);
			
			
			
			//y += 60;
			for (Vehicle v: r.getVehicles()) {
				if (v.getStatus() != VehicleStatus.ARRIVED) {
					int x = x1 + (int) ((x2 - x1) * ((double) v.getLocation() / (double) v.getRoad().getLength()));
			
					int vLabelColor = (int) (25.0 * (10.0 - (double) v.getContClass()));

					g.drawImage(car, x, y -9, 16, 16, this);
					
					g.setColor(new Color(0, vLabelColor, 0));
					g.drawString(v.getId(), x, y - 10);	
					
					
				}
			}
			y += 60;
		}		

	}	*/
	
	public int getWeatherImage(Weather w) {
		int ret = 0;
	
		switch (w) {
		case SUNNY:
			ret = 0;
			break;
		case CLOUDY:
			ret = 1;
			break;
		case RAINY:
			ret = 2;
			break;
		case WINDY:
			ret = 3;
			break;
		case STORM:
			ret = 4;
			break;
		
		}
		
		
		return ret;
	}

	
	
	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}	
	
	public void update(RoadMap map) {
		this.map = map;
		repaint();
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		update(map);
	}

	@Override
	public void onError(String err) {
	}

}
