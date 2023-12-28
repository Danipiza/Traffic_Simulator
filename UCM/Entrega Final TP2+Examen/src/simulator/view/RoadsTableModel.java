package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver {
	
	private static final long serialVersionUID = 1L;
	
	private List<Road> roads;
	
	private String[] colNames = { "Id", "Length", "Weather", "Max. Speed", "Speed Limit", "Total CO2", "CO2 Limit" };
	
	Controller ctrl;
	
	public RoadsTableModel() {
		roads = null;
	}
	
	// FALTA LA CONSTRUCTORA PARA INICIALIZAR CON UN EVENTO
	public RoadsTableModel(Controller _ctrl) {
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
		return colNames[col];
	}

	@Override	
	// this is for the number of columns
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	// the number of row, like those in the events list
	public int getRowCount() {
		return roads == null ? 0 : roads.size();
	}
	
		

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = "";					
		
		switch (columnIndex) {
		case 0:
			s = roads.get(rowIndex).getId();
			break;
		case 1:			
			s = roads.get(rowIndex).getLength();
			break;
		case 2:			
			s = roads.get(rowIndex).getWeather();
			break;
		case 3:
			s = roads.get(rowIndex).getMaxSpeed();
			break;
		case 4:			
			s = roads.get(rowIndex).getSpeedLimit();
			break;
		case 5:			
			s = roads.get(rowIndex).getTotalCO2();
			break;
		case 6:			
			s = roads.get(rowIndex).getContLimit();
			break;
		default:
			assert(false);
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
		
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		//junctions = map.getJunctions();
		
		fireTableDataChanged();				
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		roads = map.getRoads();
		
		fireTableDataChanged();				
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		roads = map.getRoads();
		
		fireTableDataChanged();		
	}

	@Override
	public void onError(String msg) {
		
		
	}

}
