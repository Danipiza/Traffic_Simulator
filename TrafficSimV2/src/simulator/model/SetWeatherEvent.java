package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{
	
	private List<Pair<String,Weather>> ws;
	
	// NUEVO ATRIBUTO PARA EL toString
	int i = 0;
	
	
	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
		super(time);
		if (ws == null) throw new IllegalArgumentException();
		this.ws = ws;
		
	}

	@Override
	void execute(RoadMap map) {
		for (Pair<String,Weather> w: ws) {
			if(map.getRoad(w.getFirst()) != null) {
				map.getRoad(w.getFirst()).setWeather(w.getSecond());	
			}
			else {
				throw new IllegalArgumentException("No puede ser nulo");
			}
			
		}
		
	}
	
	@Override
	public String toString() {
		String ret = "Change Weather";
		
		
		return ret;
	}
	



}