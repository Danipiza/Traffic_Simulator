package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent{

	public NewInterCityRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
		super(time, id, srcJun, destJunc, maxSpeed, co2Limit, length, weather);				
	}

	@Override
	public Road createRoadObject() {					
		return new InterCityRoad(id, src, dest, length,co2Limit,maxSpeed,weather);
	}
	
	

}
