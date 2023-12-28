package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;
import simulator.model.SetStrategiesEvent;

public class SetStrategiesEventBuilder extends Builder<Event>{
			
	private int time;
	
	private String id;
	
	private Factory<LightSwitchingStrategy> lss;
	private Factory<DequeuingStrategy> dqs;
		
	private LightSwitchingStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;
	
	public SetStrategiesEventBuilder(Factory<LightSwitchingStrategy>lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super("set_strategies");
		lss = lssFactory;
		dqs = dqsFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) {		
		time = data.getInt("time");
		id = data.getString("id");		
		JSONObject ls = data.getJSONObject("ls_strategy");
		JSONObject dq = data.getJSONObject("dq_strategy");
		lsStrategy = lss.createInstance(ls);
		dqStrategy = dqs.createInstance(dq);
		
		return new SetStrategiesEvent(time, id, lsStrategy, dqStrategy);
	}

}
