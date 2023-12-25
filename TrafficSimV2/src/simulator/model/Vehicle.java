package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject {
	
	private int velocidadActual;	
	
	private int velocidadMax;
	
	private List<Junction> itinerario;
	
	private VehicleStatus estado;
	
	private Road carretera;
	
	private int localizacion;
	
	private int gradoContaminacion;
	
	private int contaminacionTotal;
	
	private int distanciaRecorrida;
	
	private int indice;
	
	private Junction srcJunc = null;
	
	private Junction destJunc = null; 
	
	private List<Junction> copiaItinerario;
		
	
	// CONSTRUCTORA
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		super(id);
		
		copiaItinerario = Collections.unmodifiableList(new ArrayList<>(itinerary));		
		
		if(id == null || id == "") throw new IllegalArgumentException("La id no puede ser nula"); 		
		
		if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser positiva"); 
		velocidadMax = maxSpeed;
		
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException("La clase de contaminacion tiene que ser un numero entre 0 - 10"); 
		gradoContaminacion = contClass;	
		
		if(itinerary.size() < 2) throw new IllegalArgumentException("El itinerario tiene que tener al menos 2 carreteras");
		itinerario = itinerary;
		
		indice = 0;
		velocidadActual = 0;
		localizacion = 0;
		contaminacionTotal = 0;
		distanciaRecorrida = 0;
		
		estado = VehicleStatus.PENDING;
		
		
					
	}
	
	// PONE LA VELOCIDAD ACTUAL AL VALOR MINIMO ENTRE s Y LA VELOCIDAD MAXIMA DEL VEHICULO 
	// LANZA EXCEPCION SI LA VELOCIDAD ES NEGATIVA
	void setSpeed(int s) {
		if(estado == VehicleStatus.TRAVELING) {
			if (s < 0) throw new IllegalArgumentException("La velocidad no puede ser negativa");
			else if (s > getMaxSpeed()) velocidadActual = getMaxSpeed();
			else velocidadActual = s;
		}
	}
	
	
	// PONE EL VALOR DE LA CONTAMINACION DEL VEHICULO
	// LANZA EXCEPCION SI c NO ES UN VALOR ENTRE 0 Y 10
	void setContClass(int c) {
		if (0 <= c && c <= 10) gradoContaminacion = c;		
		else throw new IllegalArgumentException("La contaminacion debe ser un valor entre 0 y 10");
	}	
	
	@Override
	void advance(int time) {

		if(estado == VehicleStatus.TRAVELING) {
			
			int localizacionNueva = localizacion + velocidadActual;
			
			if(localizacionNueva > carretera.getLength()) {
				localizacionNueva = carretera.getLength();
			}
			int c= gradoContaminacion*(localizacionNueva -localizacion);
			distanciaRecorrida += (localizacionNueva - localizacion);
			contaminacionTotal += c;
			carretera.addContamination(c);
			localizacion = localizacionNueva;
			
			if(localizacion >= carretera.getLength()) {
				estado = VehicleStatus.WAITING;
				velocidadActual = 0;
				indice++;
				carretera.getDest().enter(this);
			}
		} else velocidadActual = 0;
		
	
	}
	
	void moveToNextRoad() {		
		if(estado != VehicleStatus.PENDING && estado != VehicleStatus.WAITING) {
			throw new IllegalArgumentException("El estado actual del vehiculo no permite moverse a otra carretera");
		}
		if(carretera != null){
			carretera.exit(this);
		}
		if(indice == itinerario.size() - 1){
			carretera = null;
			estado = VehicleStatus.ARRIVED;
			localizacion = 0;
		} else {
			srcJunc = itinerario.get(indice);
			destJunc = itinerario.get(indice + 1);
			localizacion = 0;
			Road sigCarretera = srcJunc.roadTo(destJunc);
			sigCarretera.enter(this);
			carretera = sigCarretera;
			estado = VehicleStatus.TRAVELING;		
		}
		
	}
	
	@Override
	public JSONObject report() {
		JSONObject json = new JSONObject();
		
		json.put("id", getId()); 
		json.put("speed", velocidadActual);
		json.put("distance", distanciaRecorrida);
		json.put("co2", contaminacionTotal);
		json.put("class", gradoContaminacion); // EQUIQUETA MEDIOAMBIENTAL DEL VEHICULO
		json.put("status", estado.toString()); //.name() 
		if(estado != VehicleStatus.PENDING && estado != VehicleStatus.ARRIVED) { 
			json.put("road", carretera.getId()); 
			json.put("location", localizacion); 
		}
		
		/*JSONObject jsonRet = 		
		{
			"id" : "v1",
			"speed" : 20,
			"distance" : 60,
			"co2": 100,
			"class": 3,
			"status": "TRAVELING",
			"road" : "r4",
			"location" : 30
		}*/
		
		return json;
	}
	
	// GETTERS ----------------------------------------------------------
	// ------------------------------------------------------------------
		
	
	// UNA LISTA DE CRUCES QUE REPRESENTA EL ITINERARIO DEL VEHICULO 
	// LA CLASE JUNCTION REPRESENTA LOS CRUCES Y SE DESCRIBE MAS ADELANTE 5.1.3.3
	public List<Junction> getItinerary() {
		return copiaItinerario;		
	}
	
	// DEVUELVE LA VELOCIDAD MAXIMA DEL VEHICULO
	public int getMaxSpeed() {
		return velocidadMax;
	}
	
	// DEVUELVE LA VELOCIDAD ACTUAL DEL VEHICULO
	public int getSpeed() {
		return velocidadActual;
	}
	
	// EL ESTADO DEL VEHICULO, PUEDE SER:
	// "PENDING", TODAVIA NO HA ENTRADO A LA PRIMERA CARRETERA DE SU ITINERARIO
	//"TRAVELLING", CIRCULANDO POR LA CARRETERA
	// "WAITING", ESPERANDO EN UN CRUCE
	// "ARRIVED", HA COMPLETADO SU ITINERARIO
	public VehicleStatus getStatus() {
		return estado;		
	}

	// LA CARRETERA POR DONDE ESTA CIRCULANDO (NULL SI NO ESTA EN NIGUNA CARRETERA)
	public Road getRoad() {
		return carretera;
	}
	
	// LOCALIZACION DEL VEHICULO SOBRE LA CARRETERA QIE ESTA CIRCULANDO, ES DECIR,
	// LA DISTANCIA DESDE EL COMIENZO DE LA CARRETERA (EL COMIENZO ES 0)
	public int getLocation() {		
		return localizacion;
	}	
	
	// DEVUELVE LA CONTAMINACION ENTRE 0 Y 10 DEL VEHICULO PARA CALCULAR EL CO2
	public int getContClass() {		
		return gradoContaminacion;
	}
	
	// DEVUELVE EL TOTAL DE C02 EMITIDO POR EL VEHICULO DURANTE SU TRAYECTORIA RECORRIDA
	public int getTotalCO2() {		
		return contaminacionTotal;
	}
	
	// DISTANCIA TOTAL RECORRIDA
	public int getTotalDistance() {		
		return distanciaRecorrida;
	}	
	
}
	

