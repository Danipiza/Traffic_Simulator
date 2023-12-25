package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class Junction extends SimulatedObject{

	private List<Road> listaCarreterasEntrantes;
	
	private Map<Junction,Road> mapaCarreterasSalientes;
	
	private List<List<Vehicle>> listaColas;
	
	// NUEVO
	private Map<Road, List<Vehicle>> mapaCarreteraCola;
	
	private int indiceVerdeSemaforo;
	
	private int ultimoPasoCambioSemaforo;
	
	private LightSwitchingStrategy estrategiaCambioSemaforo;
	
	private DequeuingStrategy estrategiaExtraeElementosCola;
	
	private int coordX, coordY;
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy
			dqStrategy, int xCoor, int yCoor) {
			super(id);			
			
			// LANZA EXCEPCIONES
			if (lsStrategy == null || dqStrategy == null) {
				throw new IllegalArgumentException("Las estrategias no pueden ser nulas");
			}
			else if (xCoor < 0 || yCoor < 0) {
				throw new IllegalArgumentException("Las coordenadas tienen que ser positivas");
			}
			else if(id == null || id == "") throw new IllegalArgumentException("La id no puede ser nula"); 	
			
			
			
			coordX = xCoor;
			coordY = yCoor;
			estrategiaCambioSemaforo = lsStrategy;
			estrategiaExtraeElementosCola = dqStrategy;
			indiceVerdeSemaforo = -1; // 0
			
			listaCarreterasEntrantes = new ArrayList<Road>();
			mapaCarreterasSalientes = new HashMap<Junction, Road>();
			listaColas = new ArrayList<List<Vehicle>>();
			mapaCarreteraCola = new HashMap<Road,List<Vehicle>>();						
	}
	
	// GETTERS ----------------------------------------------------------
	// ------------------------------------------------------------------

	// DEVUELVE EL VALOR DE LA COORDENADA X
	public int getX() {
		return coordX;
	}
	
	// DEVUELVE EL VALOR DE LA COORDENADA Y
	public int getY() {
		return coordY;
	}
	
	//
	public void addIncomingRoad(Road r) {
		// LANZA EXCEPCION SI LA CARRETERA NO ES ENTRANTE 
		if (this != r.getDest()) new IllegalArgumentException("La carretera no es entrante");
		
		listaCarreterasEntrantes.add(r); // AÑADE r AL FINAL DE LA LISTA
		
		List<Vehicle> auxList = new LinkedList<Vehicle>();		
		
		listaColas.add(auxList); // AÑADE UNA LISTA CON LOS VALORES DE r A LA listaColas
		mapaCarreteraCola.put(r, auxList);
	}
	
	//
	public void addOutgoingRoad(Road r) {					
		if(roadTo(r.getDest()) != null) {
			throw new IllegalArgumentException("Hay mas carreteras que conectan este cruce");
		}
		else if(this != r.getSrc()) {
			throw new IllegalArgumentException("Este cruce no coincide con el cruce destino");
		}
		
		mapaCarreterasSalientes.put(r.getDest(),r);
		
		
		/*Junction j  = r.getDest();
		if (j == this) new RuntimeException("No puede haber 2 vehiculos con el mismo destino al mismo tiempo"); 
		// (HAY QUE MIRARLO)
		if (r.getSrc() != r.getDest()) new RuntimeException("La carretera no es saliente"); 
		
		mapaCarreterasSalientes.put(j,r);	*/	
	}
	
	// AÑADE v A LA COLA DE LA CARRETERA r
	public void enter(Vehicle v) {
		listaColas.get(listaCarreterasEntrantes.indexOf(v.getRoad())).add(v);
	}
	
	// BUSCAR EN EL MAPA DE CARRETERAS SALIENTES
	Road roadTo(Junction j) {				
		return mapaCarreterasSalientes.get(j);
	}
	
	//
	@Override
	void advance(int time) {
		
		if(indiceVerdeSemaforo != -1) {
			List<Vehicle> aux = new ArrayList<Vehicle>();
			aux = estrategiaExtraeElementosCola.dequeue(mapaCarreteraCola.get(listaCarreterasEntrantes.get(indiceVerdeSemaforo)));
			
			for (Vehicle vehicle : aux) {
				vehicle.moveToNextRoad();
			}
			mapaCarreteraCola.get(listaCarreterasEntrantes.get(indiceVerdeSemaforo)).removeAll(aux);
		}
		
		int aux = 0; 
		aux = indiceVerdeSemaforo;
		
		indiceVerdeSemaforo = estrategiaCambioSemaforo.chooseNextGreen(listaCarreterasEntrantes, listaColas, 
				indiceVerdeSemaforo, ultimoPasoCambioSemaforo, time);
		
		if(indiceVerdeSemaforo != aux) ultimoPasoCambioSemaforo = time;
		
	}

	//
	@Override
	public JSONObject report() {
		JSONArray jA = new JSONArray();
		//int x = listaColas.size();
		//int y = listaCarreterasEntrantes.size();
		
		for(int i = 0; i < listaColas.size(); ++i) {
			JSONArray jV = new JSONArray();
			for(Vehicle v: listaColas.get(i)) {
				jV.put(v.getId());
			}
			
			JSONObject jO2 = new JSONObject();
			
			jO2.put("road", listaCarreterasEntrantes.get(i).getId());
			jO2.put("vehicles", jV);
			
			jA.put(jO2);
		}
		
		JSONObject jO1 = new JSONObject();
		
		jO1.put("id", getId());
		
		if(indiceVerdeSemaforo == -1) {
			jO1.put("green", "none");		
		} else {
			jO1.put("green", listaCarreterasEntrantes.get(indiceVerdeSemaforo).getId()); //
		}
		jO1.put("queues", jA);
		
		/* JSON
		"id" : "j3",
		"green" : "r1",
		"queues" : [Q1,Q2,....]
		}*/
		
		return jO1;
	}
	
	public int getGreenLightIndex() {
		return indiceVerdeSemaforo;
	}
	
	public String getGreenLightId() {
		String ret = "";
		
		if(indiceVerdeSemaforo == -1) {
			ret = "NONE";
		} else {
			ret = listaCarreterasEntrantes.get(indiceVerdeSemaforo).getId();
		}
		
		return ret;
	}
	
	// private List<Road> listaCarreterasEntrantes;
	public List<Road> getInRoads(){
		return listaCarreterasEntrantes;
	}

}
