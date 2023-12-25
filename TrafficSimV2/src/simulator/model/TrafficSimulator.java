package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator implements Observable<TrafficSimObserver>{

	private RoadMap mapaCarreteras;
	
	private List<Event> listaEventos;
	
	private int tiempoSimulacion;
	
	// NUEVO ATRIBUTO
	private List<TrafficSimObserver> listaObs = new ArrayList<>();
	
	// CONSTRUCTORA POR DEFECTO AUNQUE CREO QUE NO HAY QUE PONERLO
	public TrafficSimulator() {		
		listaEventos = new SortedArrayList<>();
		tiempoSimulacion = 0;
		mapaCarreteras = new RoadMap();
	}
	
	// ORDENAR POR EL TIEMPO DEL EVENTO
	// SI DOS TIENEN EL MISMO TIEMPO IRA PRIMERO EL QUE ANTES ENTRO
	public void addEvent(Event e) {
		listaEventos.add(e);
	}
	
	
	public void advance() {
		++tiempoSimulacion;
		
		
		for (TrafficSimObserver obs : listaObs) {
			obs.onAdvanceStart(mapaCarreteras ,listaEventos, tiempoSimulacion);
		}
		
		
		List<Event> aux = new SortedArrayList<Event>();
		
		for (Event event : listaEventos) {
			if(event.getTime() == tiempoSimulacion) {
				event.execute(mapaCarreteras);
				aux.add(event);
			
				for (TrafficSimObserver obs : listaObs) {
					obs.onEventAdded(mapaCarreteras ,listaEventos, event, tiempoSimulacion);
				}
			}
		}
		
		listaEventos.removeAll(aux);
		
		for (Junction junction : mapaCarreteras.getJunctions()) {
			junction.advance(tiempoSimulacion);
		}
		
		for (Road road: mapaCarreteras.getRoads()) {
			road.advance(tiempoSimulacion);
		}
		
		
		for (TrafficSimObserver obs : listaObs) {
			obs.onAdvanceEnd(mapaCarreteras ,listaEventos, tiempoSimulacion);
		}
		
	}
	
	public void reset() {
		mapaCarreteras.reset();
		listaEventos.clear();
		tiempoSimulacion = 0;
		
		
		for (TrafficSimObserver obs : listaObs) {
			obs.onReset(mapaCarreteras ,listaEventos, tiempoSimulacion);
		}
	}
	
	public JSONObject report() {		
		JSONObject jret = new JSONObject();
		
		jret.put("time", tiempoSimulacion);
		jret.put("state", mapaCarreteras.report());
		
		/*JSON:
		{
		"time" : 3,
		"state" : {
					"junctions" : [...],
					"road" : [...],
					"vehicles" : [...]
		}*/
		
		return jret;
	}
	
	
	// NUEVO
	
	public int getTicks() {
		return tiempoSimulacion;
	}
	@Override
	public void addObserver(TrafficSimObserver o) {
		
		if (!listaObs.contains(o)) {
			listaObs.add(o);
		} 
		
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		listaObs.remove(o);
		
	}
	
}
