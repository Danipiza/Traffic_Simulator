package simulator.model;

public abstract class NewRoadEvent extends Event{
	
	protected String id;
	
	protected String srcJunc, destJunc;
	
	protected int length;
	
	protected int co2Limit;
	
	protected int maxSpeed;
	
	protected Weather weather;
	
	protected Junction src;
	
	protected Junction dest;
	
	
	NewRoadEvent(int time, String id, String srcJun, String
			destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
		super(time);
		this.id = id;
		this.srcJunc = srcJun;
		this.destJunc = destJunc;
		this.length = length;
		this.co2Limit = co2Limit;
		this.maxSpeed = maxSpeed;
		this.weather = weather;
		
	}

	@Override
	void execute(RoadMap map) {
		src = map.getJunction(srcJunc);
		dest = map.getJunction(destJunc);
		
		map.addRoad(createRoadObject());
	}
	
	abstract public Road createRoadObject();
	
	@Override
	public String toString() {
		return "New Road '"+ id +"'";
	}

}
