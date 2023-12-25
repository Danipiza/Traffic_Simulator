package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event>{
	
	private Factory<LightSwitchingStrategy> lss;
	private Factory<DequeuingStrategy> dqs;
	
	private int time;
	
	private String id;
	
	private int coorX;
	private int coorY;
	
	private LightSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy>lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super("new_junction");
		lss = lssFactory;
		dqs = dqsFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		time = data.getInt("time");
		id = data.getString("id");
		coorX = data.getJSONArray("coor").getInt(0);
		coorY = data.getJSONArray("coor").getInt(1);
		
		JSONObject ls = data.getJSONObject("ls_strategy");
		JSONObject dq = data.getJSONObject("dq_strategy");
		lsStrategy = lss.createInstance(ls);
		dqStrategy = dqs.createInstance(dq);
		
		return new NewJunctionEvent(time, id, lsStrategy, dqStrategy, coorX, coorY);
	}

}
