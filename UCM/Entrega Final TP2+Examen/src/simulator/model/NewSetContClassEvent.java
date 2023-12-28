package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class NewSetContClassEvent extends Event{

	private List<Pair<String,Integer>> cs;		
	
	// NUEVO ATRIBUTO PARA EL toString
 
	
	public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		if (cs == null) throw new IllegalArgumentException();
		this.cs = cs;
	}	
	
	@Override
	void execute(RoadMap map) {
		for (Pair<String, Integer> c: cs) {
			if(map.getVehicle(c.getFirst()) != null) {
				map.getVehicle(c.getFirst()).setContClass(c.getSecond());	
			}		
			else throw new IllegalArgumentException("No existe el vehiculo");
			
		}
		
	}
	
	@Override
	public String toString() {
		String ret = "Change CO2 class";
		
		
		return ret;
	}
	
	/*@Override
	void execute(RoadMap map) {
		for (Pair<String, Integer> c: cs) {
			map.getRoad(c.getFirst()).setContamination(c.getSecond());
		}
	}*/
	
	

}
