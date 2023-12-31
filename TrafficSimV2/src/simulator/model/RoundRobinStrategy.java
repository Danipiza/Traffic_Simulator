package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy {
	
	private int timeSlot;
	
	public RoundRobinStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int ret = -2;
		
		if(roads.isEmpty()) {
			ret = -1;
		}
		else if(currGreen == -1){
			ret = 0;
		}
		else if(currTime-lastSwitchingTime < timeSlot) {
			ret = currGreen;
		}
		else {
			ret = (currGreen+1) % roads.size();  
		}
		
		return ret;
	}
}
