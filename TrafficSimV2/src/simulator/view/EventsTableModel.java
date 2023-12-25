package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;	
	
	private List<Event> events;
	
	private String[] colNames = { "Time", "Desc." };
	
	Controller ctrl;

	public EventsTableModel() {
		events = null;
	}
	
	// FALTA LA CONSTRUCTORA PARA INICIALIZAR CON UN EVENTO
	public EventsTableModel(Controller _ctrl) {
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
		return events == null ? 0 : events.size();
	}

	@Override
	// EL ÍNDICE DEL ARRAYLIST ES EL NÚMERO DE FILA PQ EN ESTE EJEMPLO QUIERO ENUMERARLOS.
	// returns the value of a particular cell 
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = "";
		Event e = events.get(rowIndex);

		
		switch ( columnIndex ) {
		case 0:
			s = "" + e.getTime();			
			break;
		case 1:
			s = e.toString();			
			break;
		default:
			assert(false);
		}
		
		return s;
	}

	
	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		//this.events = events;
		//fireTableDataChanged();	
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		this.events = events;
		fireTableDataChanged();		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		this.events = events;
		fireTableDataChanged();				
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.events = events;
		fireTableDataChanged();				
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		this.events = events;
		fireTableDataChanged();				
	}

	@Override
	public void onError(String msg) {
		
	}
}
