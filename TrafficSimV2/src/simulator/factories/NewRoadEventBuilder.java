package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Weather;

public abstract class NewRoadEventBuilder extends Builder<Event>{

	protected int time;
	
	protected String id;
	
	protected String srcJun;
	
	protected String dstJun;
	
	protected int length;
	
	protected int co2Limit;
	
	protected int maxSpeed;
	
	protected Weather weather;
	
	NewRoadEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {		
		Event ret = null;
		
		//Weather weather;
		time = data.getInt("time");
		id = data.getString("id");
		srcJun = data.getString("src");
		dstJun = data.getString("dest");
		length = data.getInt("length");
		co2Limit = data.getInt("co2limit");
		maxSpeed = data.getInt("maxspeed");
		weather = Weather.valueOf(data.getString("weather").toUpperCase());
		
		ret = createTheRoad();		
		
		return ret;
	}
	
	public abstract Event createTheRoad();

}
