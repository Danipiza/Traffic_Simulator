package simulator.model;

import java.util.List;
			 
public class MostCrowdedStrategy implements LightSwitchingStrategy {

	private int timeSlot;
	
	public MostCrowdedStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int ret = 0;
		
		if(roads == null) {
			ret = -1;
		}
		else if(currGreen == -1){
			int max1 = 0;
			int indice1 = 0;
			
			for(int i = 0; i < roads.size(); ++i) {
				if(max1 < qs.get(i).size()) {
					max1 = qs.get(i).size();
					indice1 = i;
				}
			}
			
			ret = indice1;
		}
		else if(currTime-lastSwitchingTime < timeSlot) {
			ret = currGreen;
		}
		else {
			int max2 = 0;
			int indice2 = currGreen;
			
			for(int i = currGreen + 1; i < i + roads.size(); ++i) {
				if(max2 < qs.get(i%roads.size()).size()) {
					max2 = qs.get(i%roads.size()).size();
					indice2 = i%roads.size();
				}
			}
			
			ret = indice2;
		}
		
		return ret;
	}
}
