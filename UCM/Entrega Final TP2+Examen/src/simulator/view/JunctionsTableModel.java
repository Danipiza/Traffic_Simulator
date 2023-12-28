package simulator.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {	
	
	private static final long serialVersionUID = 1L;

	private List<Junction> junctions;
	
	private String[] colNames = { "Id", "Green", "Queues"};
	
	Controller ctrl;	
	
	public JunctionsTableModel() {	
		junctions = null;
	}
	
	// FALTA LA CONSTRUCTORA PARA INICIALIZAR CON UN EVENTO
	public JunctionsTableModel(Controller _ctrl) {
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
		return junctions == null ? 0 : junctions.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = "";					
			
		switch (columnIndex) {
		case 0:
			s = junctions.get(rowIndex).getId();
			break;
		case 1:			
			s = junctions.get(rowIndex).getGreenLightId();
			break;
		case 2:			
			if(junctions.get(rowIndex).getInRoads().isEmpty()) s = "";
			else s = junctions.get(rowIndex).getInRoads();;
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
		junctions = map.getJunctions();
		
		fireTableDataChanged();				
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		junctions = map.getJunctions();
		
		fireTableDataChanged();		
	}

	@Override
	public void onError(String msg) {
		
		
	}

}
