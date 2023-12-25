package a.extra.jtable;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;	
	
	private List<EventEx> _events;
	private String[] _colNames = { "Prueba" };
	
	Controller ctrl;

	public EventsTableModel() {
		_events=null;
	}
	
	// FALTA LA CONSTRUCTORA PARA INICIALIZAR CON UN EVENTO
	public EventsTableModel(Controller _ctrl) {
		ctrl = _ctrl;
	}

	public void update() {
		// observar que si no refresco la tabla no se carga
		// La tabla es la represantaci�n visual de una estructura de datos,
		// en este caso de un ArrayList, hay que notificar los cambios.
		
		// We need to notify changes, otherwise the table does not refresh.
		fireTableDataChanged();;		
	}
	
	public void setEventsList(List<EventEx> events) {
		_events = events;
		update();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	//si no pongo esto no coge el nombre de las columnas
	//
	//this is for the column header
	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override
	// m�todo obligatorio, probad a quitarlo, no compila
	//
	// this is for the number of columns
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	// m�todo obligatorio
	//
	// the number of row, like those in the events list
	public int getRowCount() {
		return _events == null ? 0 : _events.size();
	}

	@Override
	// m�todo obligatorio
	// as� es como se va a cargar la tabla desde el ArrayList
	// el �ndice del arrayList es el n�mero de fila pq en este ejemplo
	// quiero enumerarlos.
	//
	// returns the value of a particular cell 
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex;
			break;
		case 1:
			s = _events.get(rowIndex).getTime();
			break;
		case 2:
			s = _events.get(rowIndex).getPriority();
			break;
		}
		return s;
	}

	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {

		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		 
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
	 
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		 
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		 
		
	}

	@Override
	public void onError(String msg) {
	 
		
	}
}
