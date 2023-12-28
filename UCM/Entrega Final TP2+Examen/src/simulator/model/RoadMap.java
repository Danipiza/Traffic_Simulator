package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	
	private List<Junction> listaCruces;
	
	private List<Road> listaCarreteras; 
	
	private List<Vehicle> listaVehiculos;
	
	private Map<String,Junction> mapaCruces;
	
	private Map<String,Road> mapaCarreteras;
	
	private Map<String,Vehicle> mapaVehiculos;
	
	// INICIALIZAR LOS ATRIBUTOS A SUS VALORES POR DEFECTO (NO SE SI ESTA BIEN)
	public RoadMap(){
		listaCruces = new ArrayList<Junction>();
		listaCarreteras = new ArrayList<Road>();
		listaVehiculos = new ArrayList<Vehicle>();
		mapaCruces = new HashMap<String, Junction>();
		mapaCarreteras = new HashMap<String, Road>();
		mapaVehiculos = new HashMap<String, Vehicle>();
		
	}
	
	// AÑADE UN CRUCE AL FINAL DE LA LISTA 
	// LANZA EXCEPCION SI SE REPITE CRUCE CON EL MISMO id
	public void addJunction(Junction j) {			
				
		if(getJunction(j.getId()) == null) {
			listaCruces.add(j);
			mapaCruces.put(j.getId(), j);
		}
		else {
			throw new IllegalArgumentException("No pueden se iguales");
		}
		
		
		// MODIFICA EL MAPA CORRESPONDIENTE
		//mapaCruces.put(listaCruces.get(i).getId(), j);
	}
	
	// AÑADE UNA CARRETERA AL FINAL DE LA LISTA
	public void addRoad(Road r) {
		if(getJunction(r.getId()) == null) {
			listaCarreteras.add(r);
			mapaCarreteras.put(r.getId(), r);
		}
		else {
			throw new IllegalArgumentException("No pueden se iguales");
		}
		
		
	}
	
	// AÑADE UN VEHICULO AL FINAL DE LA LISTA
	public void addVehicle(Vehicle v) {
		boolean aux = false;		
		
		// COMPREBA QUE EL VEHICULO QUE SE AÑADE ESTA EN UNA CARRETERA CORRECTA
		for (Road road: listaCarreteras) {
			if((road.getSrc() == v.getItinerary().get(0)) && road.getDest() == v.getItinerary().get(1)) aux = true;
		}
		// SI NO ESTÁ EN UNA CARRETERA VALIDA LANZA EXCEPCION
		if(!aux) throw new IllegalArgumentException("El coche no esta en ninguna carretera");
		
		// COMPRUEBA QUE NO HALLA DOS VEHICULOS CON EL MISMO ID EN UN MISMO CRUCE
		if(getJunction(v.getId()) == null) {
			listaVehiculos.add(v);
			mapaVehiculos.put(v.getId(), v);
		}
		else {
			throw new IllegalArgumentException("No pueden se iguales");
		}	
		
	}	
	
	// DEVUELVE EL CRUCE CON IDENTIFICADOR id, null SI NO EXISTE
	public Junction getJunction(String id) {
		Junction ret = null;
		
		for (Junction j : listaCruces) {
			if(j.getId().equals(id)) {
				ret =j;
			}						
		}
		
		
		return ret;		
	}
	
	// DEVUELVE LA CARRETERA CON IDENTIFICADOR id, null SI NO EXISTE
	public Road getRoad(String id) {
		Road ret = null;
		
		
		for (Road r : listaCarreteras) {
			if(r.getId().equals(id)) {
				ret = r;
			}						
		}
		
		return ret;			
	}
	
	// DEVUELVE EL VEHICULO CON IDENTIFICADOR id, null SI NO EXISTE
	public Vehicle getVehicle(String id) {
		Vehicle ret = null;
		
		for (Vehicle v : listaVehiculos) {
			if(v.getId().equals(id)) {
				ret = v;
			}						
		}
		
		return ret;			
	}
	
	// DEVUELVE UNA VERSION DE SOLO LECTURA
	public List<Junction>getJunctions(){
		return listaCruces;		
	}
	
	// DEVUELVE UNA VERSION DE SOLO LECTURA
	public List<Road> getRoads(){
		return listaCarreteras;		
	}
	
	// DEVUELVE UNA VERSION DE SOLO LECTURA
	public List<Vehicle> getVehicle(){
		return listaVehiculos;		
	} 
	
	// LIMPIA TODAS LAS LISTAS Y MAPAS
	void reset() {
		listaCruces.clear();
		listaCarreteras.clear();
		listaVehiculos.clear();
		mapaCruces.clear();
		mapaCarreteras.clear();
		mapaVehiculos.clear();			
	}
	
	public JSONObject report() {
		JSONObject json = new JSONObject();	
		JSONArray jv = new JSONArray();
		JSONArray jj = new JSONArray();
		JSONArray jr = new JSONArray();
		
		//json.put("junctions", ((RoadMap) listaCruces).report()); // IDENTIFICADOR DE LA CARRETERA		
		//json.put("road", ((RoadMap) listaCarreteras).report());
		//json.put("vehicles", ((RoadMap) listaVehiculos).report());			
		
		for (Road r: listaCarreteras) {
			jr.put(r.report());			
		}
		
		
		
		for (Vehicle v: listaVehiculos) {
			jv.put(v.report());		
		}
		
		
		for (Junction j: listaCruces) {
			jj.put(j.report());			
		}
		
		
		
		json.put("roads", jr);
		json.put("vehicles", jv);
		json.put("junctions", jj);
		
		
		/*		
		{
			"junctions" : [J1Report,J2Report,...],
			"road" : [R1Report,R2Report,...],
			"vehicles" : [V1Report,V2Report,...],
		}	
		*/
		return json;	
	}
	
	
	
	
	
	
}
