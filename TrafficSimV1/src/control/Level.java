package control;

import logic.gameobjects.*;

public enum Level {

	// NUEVO NIVEL ADVANCED Y NUEVA VARIABLE INTRODUCIDAS EN LOS NIVELES
	TEST(10, 3, 8, 0.5, 0, 0), EASY(30, 3, 8, 0.5, 0.5, 0), HARD(100, 5, 6, 0.7, 0.3, 0), ADVANCED(100, 3, 8, 0.3, 0.3, 0.1);   // Niveles

	private int length;

	private int width;

	private int visibility;

	private double coinFrequency;

	private double obstacleFrequency;
	
	// NUEVA VARIABLE INTRODUCIDA 
	private double advObjFreq;
	
	// NUEVA COONSTRUCTORA DEL LEVEL, CON LA NUEVA VARIABLE 
	private Level(int length, int width, int visibility, double obstacleFrequency, double coinFrequency, double advObjFreq) {		//Inicializa el level
		this.length = length;
		this.width = width;
		this.visibility = visibility;
		this.obstacleFrequency = obstacleFrequency;
		this.coinFrequency = coinFrequency;	
		this.advObjFreq = advObjFreq;
		
		
	}
	
	public static Level valueOfIgnoreCase(String inputString) {
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) {
				return level;
			}
		}
		return null;
	}

	public static String all(String separator) {
		StringBuilder buffer = new StringBuilder();
		int levelCount = 0;
		for (Level level : Level.values()) {
			if (levelCount > 0) {
				buffer.append(separator);
			}
			buffer.append(level.name());
			levelCount++;
		}
		return buffer.toString();
	}
	
	public int getLength() {		
		return length;			
	}
	
	public void setLength(int lengthAux) {		
		this.length = lengthAux;		
	}
	
	public int getWidth() {		
		return width;			
	}
	
	public void setWidth(int widthAux) {		
		this.width = widthAux;		
	}
	
	public int getVisibility() {		
		return visibility;			
	}
	
	public void seVisibility(int visibilityAux) {								
		this.visibility = visibilityAux;		
	}
	
	public double getCoinFrequency() {											
		return coinFrequency;			
	}
	
	public void setcoinFrequency(double coinFrequencyAux) {						
		this.coinFrequency = coinFrequencyAux;		
	}
	
	public double getObstacleFrequency() {										
		return obstacleFrequency;			
	}
	
	public void setObstacleFrequency(double obstacleFrequencyAux) {				
		this.obstacleFrequency = obstacleFrequencyAux;		
	}
	
	// FUNCIONES CAMBIADAS
	
	// ANTES ERA getAdvancedObjectFrequency
	public double advancedObjectsFrequency() {										
		return advObjFreq;			
	}
	
	public void setAdvObsFreq(double advObjFreqAux) {				
		this.advObjFreq = advObjFreqAux;		
	}
	
	public boolean hasAdvancedObjects() {
		boolean ret = false;
		
		if(this.advObjFreq != 0) {
			ret = true;
		}
		
		return ret;
	}
	
	 
	
	
	
}
