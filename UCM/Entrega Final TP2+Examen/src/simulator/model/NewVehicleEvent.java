package simulator.model;

import java.util.ArrayList;
import java.util.List;


public class NewVehicleEvent extends Event{

	private String id;
	
	private int velocidadMax;
	
	private int gradoContaminacion;
	
	private List<String> itinerario;	
	
	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		super(time);
		
		this.id = id;
		velocidadMax = maxSpeed;
		gradoContaminacion = contClass;
		itinerario = itinerary;			
	}


	@Override
	void execute(RoadMap map) {
		// CREA UNA LISTA PARA AÑADIRLA POSTERIORMENTE EN Vehicle v
		 List <Junction> jcitinerary = new ArrayList<Junction>(); 
		
		for (String auxitinerary: itinerario) {
			jcitinerary.add(map.getJunction(auxitinerary));
		}
		
		Vehicle v = new Vehicle(id, velocidadMax, gradoContaminacion, jcitinerary);
		map.addVehicle(v);
		v.moveToNextRoad();
	}

	@Override
	public String toString() {
		return "New Vehicle '"+ id +"'";
	}
	
}
