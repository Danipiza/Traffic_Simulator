package simulator.model;

public class CityRoad extends Road{

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);

	}	

	@Override
	void reduceTotalContamination() {
		int x;
		
		if(getWeather() == Weather.STORM || getWeather() == Weather.WINDY) {
			x = 10;
		} else x = 2;
		
		if(contaminacionTotal - x < 0) {
			contaminacionTotal = 0;
		} else {
			contaminacionTotal = contaminacionTotal - x;
		}			
		
		if (x < 0) throw new RuntimeException("El valor reducido no puede ser negativo");
	}

	// NO HAY QUE HACER NADA
	@Override
	void updateSpeedLimit() {		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		int ret =0;
		
		if(v.getStatus() == VehicleStatus.TRAVELING) {
			ret = ((11-v.getContClass())*limiteVelocidad)/11;
		}		
		return ret;
	}

}
