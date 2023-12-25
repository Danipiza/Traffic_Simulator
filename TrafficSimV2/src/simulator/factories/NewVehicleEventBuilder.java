package simulator.factories;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event>{

	public NewVehicleEventBuilder() {
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		JSONArray it = data.getJSONArray("itinerary");
		List<String> list = new ArrayList<String>();
	
		for (int i = 0; i < it.length(); ++i) {
			list.add(it.getString(i));
		}
		
		
		
		// int time, String id, int maxSpeed, int contClass, List<String> itinerary
		return new NewVehicleEvent(data.getInt("time"), data.getString("id"), data.getInt("maxspeed"), data.getInt("class"), list);
	}

}
