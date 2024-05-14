package control;

/**
 * @author DannyP39
 
 * ENG: Class for the Levels
 * ESP: Clase para los Niveles
 */
public enum Level {

	
	TEST(10, 3, 8, 0.5, 0, 0), 
	EASY(30, 3, 8, 0.5, 0.5, 0), 
	HARD(100, 5, 6, 0.7, 0.3, 0), 
	ADVANCED(100, 3, 8, 0.3, 0.3, 0.1);  

	private int length;
	private int width;
	private int visibility;
	private double coinFrequency;
	private double obstacleFrequency;
	
	private double advObjFreq;
	
	/**
	 * @param length
	 * @param width
	 * @param visibility
	 * @param obstacleFrequency
	 * @param coinFrequency
	 * @param advObjFreq
	 *
	 * ENG: Class constructor for Levels
	 * ESP: Constructor de la clase Niveles
	 */
	private Level(int length, int width, int visibility, double obstacleFrequency, double coinFrequency, double advObjFreq) {		//Inicializa el level
		this.length = length;
		this.width = width;
		this.visibility = visibility;
		this.obstacleFrequency = obstacleFrequency;
		this.coinFrequency = coinFrequency;	
		this.advObjFreq = advObjFreq;
		
		
	}
	
	/** 
	 * @param inputString
	 * @return Level class
	 *
	 * ENG: Method for getting the class level of a input string (ignoring capital letters)
	 * ESP: Funcion para devolver el nivel dado un string (ignora las mayusculas)
	 */
	public static Level valueOfIgnoreCase(String inputString) {
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) 
				return level;
		}
		return null;
	}

	/** 
	 * @param separator
	 * @return
	 *
	 * ENG: Method for seeing the names of all the levels
	 * ESP: Funcion para ver todos los nombres de los niveles
	 */
	public static String all(String separator) {
		StringBuilder buffer = new StringBuilder();
		int levelCount=0;
		
		for (Level level : Level.values()) {
			if (levelCount>0) buffer.append(separator);
			
			buffer.append(level.name());
			levelCount++;
		}
		return buffer.toString();
	}
	
	
	
	/** 
	 * @return boolean
	 *
	 * ENG: Method for checking if there are advancedObjects
	 * ESP: Funcion para comprobar si hay objectosAvanzados
	 */
	public boolean hasAdvancedObjects() { return (this.advObjFreq!=0?true:false); }
	
	 
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS & SETTERS ---------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	// Length of the road
	public int getLength() 				 { return this.length; }	
	public void setLength(int lengthAux) { this.length=lengthAux; }

	// Width of the road
	public int getWidth() 				 { return this.width; }	
	public void setWidth(int widthAux)   { this.width=widthAux; }
	
	// Visibility of the road
	public int getVisibility() 			 		{ return this.visibility;	}	
	public void seVisibility(int visibilityAux) { this.visibility=visibilityAux; }
	
	// Coin frequency
	public double getCoinFrequency() 					  { return this.coinFrequency; }	
	public void setcoinFrequency(double coinFrequencyAux) {	this.coinFrequency=coinFrequencyAux; }
	
	// Obstacle frequency
	public double getObstacleFrequency() { return this.obstacleFrequency; }	
	public void setObstacleFrequency(double obstacleFrequencyAux) {	
		this.obstacleFrequency=obstacleFrequencyAux;		
	}
	
	// advancedObjects frequency
	public double advancedObjectsFrequency() 		{ return advObjFreq; }	
	public void setAdvObsFreq(double advObjFreqAux) { this.advObjFreq=advObjFreqAux; }
	
	
	
}
