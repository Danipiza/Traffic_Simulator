package simulator.factories;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder{

	public NewCityRoadEventBuilder() {
		super("new_city_road");
	}

	@Override
	public Event createTheRoad() {	 	
		return new NewCityRoadEvent(time, id, srcJun, dstJun, length, co2Limit, maxSpeed, weather);
	}

}


