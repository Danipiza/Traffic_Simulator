package simulator.model;

public class InterCityRoad extends Road{
	
	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		//super(id, srcJunc, destJunc, length, contLimit, maxSpeed, weather);
	}


	@Override
	void reduceTotalContamination() {
		int tc = getTotalCO2();
		int x;		
		
		switch (getWeather()) {
		case SUNNY:
			x= 2;
			break;
		case CLOUDY:
			x= 3;
			break;
		case RAINY:
			x= 10;
			break;
		case WINDY:
			x= 15;
			break;
		case STORM:
			x= 20;
			break; 
		default:
			x = 0;
			break;
		}
		
		contaminacionTotal = ((100-x)*tc)/100; 	
		//addContamination(((100-x)*tc)/100);
		
	}

	@Override
	public void updateSpeedLimit() {
		if(getContLimit() < getTotalCO2()) {
			setSpeedLimit(getMaxSpeed()/2); // SE PUEDEM HACER PROTECTED
		} 
		else setSpeedLimit(getMaxSpeed());
		
	}

	@Override
	public int calculateVehicleSpeed(Vehicle v) {
		int ret = 0;
		
		if(getWeather() == Weather.STORM) {
			ret = (getSpeedLimit()*8)/10;
		} else ret = getSpeedLimit();
		
		return ret;
	}

}
