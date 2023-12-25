package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject {
	
	private Junction cruceOrigen; 
	
	private Junction curceDestino;
	
	private int longitud;
	
	protected int velocidadMax;
	
	protected int limiteVelocidad;

	protected int contaminacionTotal;
	
	private int alarmaContaminacion;
		
	private Weather weather;
	
	private List<Vehicle> vehiculos;	
	
	private Ordena OrdenarAdvance;
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather){
			super(id);
			
			if(_id == null || _id == "") throw new IllegalArgumentException("La id no puede ser nula");
			
			if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser positiva");
			velocidadMax = maxSpeed;
			
			if(contLimit < 0) throw new IllegalArgumentException("El limite de contaminacion no puede ser negativa");
			alarmaContaminacion = contLimit;
			
			if(length <= 0) throw new IllegalArgumentException("La longitud tiene que ser positiva");
			longitud = length;
			
			if(srcJunc == null || destJunc == null || weather == null) {
				throw new IllegalArgumentException("srcJunc, destJunc y weather no pueden ser nulos");
			}
			
			cruceOrigen = srcJunc;
			curceDestino = destJunc;
			velocidadMax = maxSpeed;
			limiteVelocidad = maxSpeed;
			alarmaContaminacion = contLimit;
			longitud = length;
			
			this.weather = weather;
			
			vehiculos = new ArrayList<Vehicle>();
			
			OrdenarAdvance = new Ordena();
			
			cruceOrigen.addOutgoingRoad(this);
			curceDestino.addIncomingRoad(this);	
	}	
	
	// METODOS -----------------------------------------------------------
	// -------------------------------------------------------------------
	
	// AÑADE, AL FINAL DE LA LISTA DE VEHICULOS DE LA CARRETERA
	void enter(Vehicle v) {		
		if(v.getLocation() != 0) throw new IllegalArgumentException("La localizacion inicial del vehiculo no puede ser 0");
		if (v.getSpeed() != 0) throw new IllegalArgumentException("La velocidad inicial del vehiculo no puede ser 0");
		
		vehiculos.add(v);		
	}
	
	// ELIMINA DE LA LISTA, EL VEHICULO QUE SALE DE LA CARRETERA
	public void exit(Vehicle v) {
		vehiculos.remove(v);
	}
	
	// PONE LAS CONDICIONES ATMOSFERICAS DE LA CARRETERA A w
	public void setWeather(Weather w) {
		if(w == null) throw new IllegalArgumentException("Las condicioines medioambientales no puede ser nula");
		weather = w;
	}
	
	// AÑADE c UNIDADES AL TOTAL DE CONTAMINACION
	// LANZA EXCEPCION SI c ES NEGATIVO
	public void addContamination(int c) {
		if (c < 0) throw new IllegalArgumentException("El valor añadido a la contaminacion debe ser positivo");
		contaminacionTotal += c;
	}
	
	// REDUCE LA CONTAMINACION DE LA CARRETERA
	abstract void reduceTotalContamination();
	// CAMBIA LA VELOCIDAD MAXIMA DE LA CARRETERA 
	abstract void updateSpeedLimit();
	// CALCULA LA VELOCIDAD DEL VEHICULO
	abstract int calculateVehicleSpeed(Vehicle v);
	// TODOS ESTOS METODOS SE IMPLEMENTAN EN interCityRoad y cityRoad
	
	// AVANZA EL ESTADO DE LA CARRETERA DE LA SIGUIENTE FORMA:
	// 1, LLAMA A reduceTotalContamination (DISMINUYE EL CO2 DE LA CARRETERA)
	// 2, LLAMA A updateSpeedLimit
	// 3, PONE LA VELOCIDAD DEL VEHICULO A calculateVehicleSpeed Y LLAMA A advance
	
	@Override
	void advance(int time) {		
		reduceTotalContamination();
		updateSpeedLimit();
		
		for(Vehicle vehicle2 :vehiculos) {
			vehicle2.setSpeed(calculateVehicleSpeed(vehicle2));
			vehicle2.advance(time);
		}		
		
		vehiculos.sort(OrdenarAdvance);	
	}

	// DEVUELVE EL ESTADO DE LA CARRETERA
	@Override
	public JSONObject report() {
		JSONObject json = new JSONObject();
		JSONArray aux = new JSONArray();
		
		for (Vehicle v1 : vehiculos) {
			aux.put(v1.getId());
		}
		
		json.put("id", getId()); // IDENTIFICADOR DE LA CARRETERA		
		json.put("speedlimit", limiteVelocidad);
		json.put("weather", weather.toString());
		json.put("co2", contaminacionTotal); 
		json.put("vehicles", aux); // ARRAY DE VEHICULOS			
		
		
		/*JSONObject jsonRet = 
		
		{
			"id" : "r3",
			"speedLimit" : 100,
			"weather" : "SUNNY",
			"co2": 100,
			"vehicles":  ["v1","v2",...],			
		}		*/
		return json;
	}

	// GETTERS ----------------------------------------------------------
	// ------------------------------------------------------------------
		
	// DEVUELVE EL VALOR DE LA LONGITUD DE LA CARRETERA
	public int getLength() {
		return longitud;	
	}
	
	// LOS CRUCES A LOS CUALES LA CARRETERA ESTA CONECTADA 
	// CIRCULAR POR LA CARRETERA ES IR DESDE EL CRUCE ORIGEN HASTA EL DESTINO
	public Junction getDest() {
		return curceDestino; 	
	}
	
	// LOS CRUCES A LOS CUALES LA CARRETERA ESTA CONECTADA 
	// CIRCULAR POR LA CARRETERA ES IR DESDE EL CRUCE DESTINO HASTA EL ORIGEN 
	public Junction getSrc() {
		return cruceOrigen;
	}
	
	// LAS CONDICIONES ATMOSFERICAS DE LA CARRETERA
	public Weather getWeather() {
		return weather;
	}
	
	// INDICA EL LIMITE DE CONTAMINACION
	// SI SE SUPERA IMPONE RESTRICCIONES AL TRAFICO PARA REDUCIRLO
	public int getContLimit() {
		return alarmaContaminacion;
	}
	
	// DEVUELVE EL VALOR DE LA VELOCIDAD MAXIMA PERMITIDA EN ESA CARRETERA
	public int getMaxSpeed() {
		return velocidadMax;
	}
	
	// CONTAMINACION ACUMULADA DE LA CARRETERA
	// ES DECIR, EL TOTAL DE CO2 DE TODOS LOS COCHES DE LA CARRETERA
	public int getTotalCO2() {
		return contaminacionTotal;
	}
	
	// DEVUELVE EL limiteVelocidad DE LA CARRETERA
	public int getSpeedLimit() {
		return limiteVelocidad;
	}
		
	// LISTA DE LOS VEHICULOS CIRCULANDO POR LA CARRETERA
	// TIENE QUE ESTAR SIEMPRE ORDENADA POR LA LOCALIZACION DE LOS COCHES (DECRECIENTE)
	public  List<Vehicle> getVehicles(){
		return Collections.unmodifiableList(vehiculos);	
	}	
	
	// METODO NUEVO PARA HACER updateSpeedLimit
	public void setSpeedLimit(int c) {
		limiteVelocidad = c;
	}

	public static class Ordena implements Comparator<Vehicle>{

		@Override
		public int compare(Vehicle o1, Vehicle o2) {
			int ret = 0;			
			
			if(o1.getLocation() < o2.getLocation()) {
				ret = 1;
			}
			else if(o1.getLocation() > o2.getLocation()) {
				ret = -1;
			}
			else {
				ret = 0;
			}
			
			return ret;
		}
		
	}
	
}
