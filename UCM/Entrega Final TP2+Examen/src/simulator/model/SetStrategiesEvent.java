package simulator.model;

public class SetStrategiesEvent extends Event{

	private String id;
	
	private LightSwitchingStrategy lsStrategy;
	
	private DequeuingStrategy dqStrategy;
	
	
	public SetStrategiesEvent(int time, String id, LightSwitchingStrategy
			lsStrategy, DequeuingStrategy dqStrategy) {
			super(time);
			this.id = id;
			this.lsStrategy = lsStrategy;
			this.dqStrategy = dqStrategy;						
	}


	@Override
	void execute(RoadMap map) {
		map.getJunction(id).set_dqs(dqStrategy);
		map.getJunction(id).set_lss(lsStrategy);		
	}

	
	
}
