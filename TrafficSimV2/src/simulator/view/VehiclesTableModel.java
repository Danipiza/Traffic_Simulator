package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver {

	private static final long serialVersionUID = 1L;
	
	private List<Vehicle> vehicles;
	
	private String[] _colNames = { "Id", "Location", "Itinerary", "CO2Class", "Max. Speed", "Speed", "Total CO2", "Distance" };
	
	Controller ctrl;
	
	public VehiclesTableModel() {
		vehicles = null;
	}
	
	
	// FALTA LA CONSTRUCTORA PARA INICIALIZAR CON UN EVENTO
	public VehiclesTableModel(Controller _ctrl) {
		ctrl = _ctrl;
		ctrl.addObserver(this);
	}
	

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	// SI NO PONGO ESTO NO COGE EL NOMBRE DE LAS COLUMNAS
	//this is for the column header
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override	
	// this is for the number of columns
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	// the number of row, like those in the events list
	public int getRowCount() {
		return vehicles == null ? 0 : vehicles.size();
	}
	
		

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null ;					
		Vehicle v = vehicles.get(rowIndex);
		
		if(v.getStatus()!= VehicleStatus.ARRIVED) {
			switch (columnIndex) {
			case 0:
				s = v.getId();
				break;
			case 1:			
				s = v.getRoad().getId() + ":" + v.getLocation();
				break;
			case 2:			
				s = v.getItinerary();
				break;
			case 3:
				s = v.getContClass();
				break;
			case 4:			
				s = v.getMaxSpeed(); 
				break;
			case 5:			
				s = v.getSpeed();
				break;
			case 6:
				s = v.getTotalCO2();
				break;
			case 7:			
				s = v.getTotalDistance();
				break;		
			default:
				assert(false);
			}
		}
		
		
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {			
		//junctions = map.getJunctions();
		
		fireTableDataChanged();				
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		//junctions = map.getJunctions();
		vehicles = map.getVehicle();
		
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		//junctions = map.getJunctions();
		vehicles = map.getVehicle();
		
		fireTableDataChanged();				
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		vehicles = map.getVehicle();
		
		fireTableDataChanged();				
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		vehicles = map.getVehicle();
		
		fireTableDataChanged();		
	}

	@Override
	public void onError(String msg) {
		
		
	}

}
