package simulator.model;

public class NewJunctionEvent extends Event{

	private String id;
	
	private LightSwitchingStrategy lsStrategy;
	
	private DequeuingStrategy dqStrategy;
	
	private int coordX, coordY;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy
			lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
			super(time);
			this.id = id;
			this.lsStrategy = lsStrategy;
			this.dqStrategy = dqStrategy;
			coordX = xCoor;
			coordY = yCoor;
			
	}


	@Override
	void execute(RoadMap map) {
		//Junction j = new Junction(id, lsStrategy, dqStrategy, coordX, coordY);
			
		map.addJunction(new Junction(id, lsStrategy, dqStrategy, coordX, coordY));
	}

	@Override
	public String toString() {
		return "New Junction '"+ id +"'";
	}
	
}
