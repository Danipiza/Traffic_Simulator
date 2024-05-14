package logic;


import java.util.Random;
import control.Level;
import control.exceptions.InvalidPositionException;
import logic.gameobjects.*;
import logic.actions.InstantAction;
import logic.GameObjectGenerator;


/**
 * @author DannyP39
 
 * ENG: Class to manage the execution
 * ESP: Clase para manejar la ejecucion
 */
public class Game { 
		
	private String FINISH_LINE_SYMBOL = "¦";
	
		
	@SuppressWarnings("unused")
	private long seed;	
	private Random rand;
	
	public Level level;		
	public Player player;
	private GameObjectContainer gameObjectContainer;
	
		
	private long initTime;	
	private long record;	
	private boolean testMode;
	private boolean doExit;	
	

	
	/**
	 * @param seed
	 * @param level
	 *
	 * ENG: Class constructor
	 * ESP: Constructor de la clase
	 */
	public Game(Long seed, Level level) { 
		this.level = level;
		this.seed = seed;
		this.doExit = false;
			
		reset(level, seed);				
	}
	
	/** 
	 * @param level	
	 * @param seed
	 *
	 * ENG: Method for executing reset command. The game restart.
	 * ESP: Funcion para ejecutar el comando reset. Vuelve a empezar la partida.
	 */
	public void reset(Level level, Long seed) { // SI	
		this.level = level; 
		this.seed = seed;		
		this.initTime = System.currentTimeMillis();
		
		this.rand = new Random(seed);
		this.gameObjectContainer = new GameObjectContainer();
		this.player = new Player(this, 0, level.getWidth()/2);
		
		
		GameObjectGenerator.generateGameObjects(this, this.level);		
	}
	
	/** 
	 * 
	 *
	 * ENG: Method for executing clear command. Clears als the objects in the road
	 * ESP: Funcion para ejecutar el comando clear. Elimina los objetos de la carretera
	 */
	public void clear() { // SI
		gameObjectContainer.clear();		
	}
	
	/** 
	 * ENG: Method for updating. The player goes forward, the objects also have to update int the road
	 * ESP: Funcion para actualizar. Avanza el jugador, los objetos tambien se actualizan en la carretera
	 */
	public void update() { 		
		player.update();
		player.goForward();
		player.update();
		gameObjectContainer.update();
		
		GameObjectGenerator.generateRuntimeObjects(this);			
	}	
	
	
	
			
	
	/** 
	 * @param x int 		(X coord)
	 * @param y	int			(Y coord)
	 * @return 	String	position
	 *
	 * ENG: Method for translating a position into a string
	 * ESP: Funcion para convertir una posicion en una cadena
	 */
	public String positionToString(int x, int y) {  // SI 		
		String ret = ""; 		
		int rx = x + player.getX();
		
		if (player.isInPosition(rx, y)) ret = player.toString() + " ";		
		if (!gameObjectContainer.isPositionEmpty(rx,  y)) ret += gameObjectContainer.getObjectInPosition(rx, y);
		if (level.getLength() == rx) ret += FINISH_LINE_SYMBOL; 	 		
		
		
		return ret;		
	}
	
	
	
	
	

	
	
	
	/** 
	 * @return		(number of coins)
	 *
	 * ENG: Method that gets how many coins the player has
	 * ESP: Funcion para ver cuantas monedas tiene el jugador
	 */
	public int getCoinsCount() {
		int ret;
		boolean colectedSuperCoin = false;
		
		ret=playerCoins();
		
		if (ret > 1000 || colectedSuperCoin) {
			ret = gameObjectContainer.getCoinsCount() - playerCoins() + 5 +1000;
			colectedSuperCoin = true;
		}
		else ret = gameObjectContainer.getCoinsCount() - playerCoins() + 5;
		
		return ret;
	}
	
	/** 
	 * @param gameObject		(object)
	 *
	 * ENG: Method for adding an object
	 * ESP: Funcion para añadir un objeto
	 */
	public void add(GameObject gameObject) { gameObjectContainer.add(gameObject); }
	
	/** 
	 * ENG: Method for go up a lane
	 * ESP: Funcion para subir un carril
	 */
	public void goUp() {  	 
		player.update();
		if(player.getY() > 0) player.goUp();
		player.goForward();
		player.update();

		gameObjectContainer.update();
		GameObjectGenerator.generateRuntimeObjects(this);	
	}
	
	/** 
	 * ENG: Method for go down a lane
	 * ESP: Funcion para bajar un carril
	 */
	public void goDown() {  			
		player.update();
		if(player.getY() < level.getWidth() - 1) player.goDown();
		player.goForward();		
		player.update();
		
		gameObjectContainer.update();
		GameObjectGenerator.generateRuntimeObjects(this);	
	}	
	
	/** 
	 * 
	 *
	 * ENG: Method for exit the execution
	 * ESP: Funcion para terminar la ejecucion
	 */
	public void setUserExit() { doExit = true;}
	
	/** 
	 * @param Action		(action to execute)
	 * @return	true
	 *
	 * ENG: Method for executing an action
	 * ESP: Funcion para ejecutar una accion
	 */
	public boolean execute(InstantAction Action) {  
		Action.execute(this);
		return true;
	}
	
	/** 
	 * @param x		(number of coins)
	 *
	 * ENG: Method for substracting a number "x" of coins from the player. 
	 * ESP: Funcion para quitar un numero "x" de monedas al jugador. 
	 */
	public void subCoins(int x) { player.setCoins(player.getCoins()-x);	}
	
	/** 
	 * @return		(random lane)
	 *
	 * ENG: Method that gets a random lane.
	 * ESP: Funcion que calcula un carril aleatorio.
	 */
	public int getRandomLane() {  return (int)(rand.nextDouble()*(level.getWidth())); }
	
	/** 
	 * @return 		random visible column
	 *
	 * ENG: Method that gets a ramdom visible column
	 * ESP: Funcion que calcula una columna visible aleatoria
	 */
	public int getRandomVisibleColumn() {  return (int)(rand.nextDouble()*(level.getVisibility())); }
	
	
	
	
	
	
	/** 
	 * @param gameObject	(object)
	 * @param f. 			(probability)
	 *
	 * ENG: Method for adding an object, with a probability
	 * ESP: Funcion para añadir un objeto, con uan probabilidad
	 */
	public void tryToAddObject(GameObject gameObject, double f) {	 // SI		
		if (rand.nextDouble() < f) {    
			if(gameObjectContainer.isPositionEmpty(gameObject.getX(), gameObject.getY())) {				
				gameObjectContainer.add(gameObject);
			}
		}			
	}	
	
	
	/** 
	 * @param gameObject	(object)
	 *
	 * ENG: Method for adding an abject. If its empty
	 * ESP: Funcion para añadir un objeto. Si esta vacio
	 */
	public void forceAddObject(GameObject gameObject) { // SI
		if(gameObjectContainer.isPositionEmpty(gameObject.getX(), gameObject.getY())) {
			gameObjectContainer.add(gameObject);
		}
		
	}
	
	/** 
	 * ENG: Method for activationg test mode
	 * ESP: Funcion para activar el modo test
	 */
	public void toggleTest(){ this.testMode = true; }		
	
	
	/** 
	 * @param column
	 *
	 * ENG: Method for clearing a column
	 * ESP: Funcion para vaciar una columna
	 */
	public void clearColumn(int column) { gameObjectContainer.clearColumn(column); }
		
	
	
	public Collider getObjectInPosition(int x, int y) { // SI
		return gameObjectContainer.getObjectInPosition(x, y);
	}

	public void removeDead(GameObject gameobject) {  // SI
		gameObjectContainer.removeDead(gameobject);		
	}

	
	/** 
	 * @param GO
	 * @throws InvalidPositionException
	 *
	 * ENG: Method for throwing a grenade
	 * ESP: Funcion para lanzar una granada
	 */
	public void forceAddGrenade(GameObject GO) throws InvalidPositionException { // SI
		int x = GO.getX();
		int y = GO.getY();
		
		if (gameObjectContainer.isPositionEmpty(x,  y) && 
		   (x > 0 && x < getVisibility()) && (y >= 0 && y < getRoadWidth())) {
			gameObjectContainer.add(GO);
		} else throw new InvalidPositionException("Invalid position");		
	}
	
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	public int getRoadWidth()     { return level.getWidth(); } // Road width	
	public int getRoadLength()    { return level.getLength(); }	// Road length
	public int getVisibility()    { return level.getVisibility(); } // Visibility	
	public boolean isUserExit()   { return doExit; } // User exit?
	public Level getLevel() 	  { return this.level; } // Level
	public Player getPlayer() 	  { return this.player; } // Player object	
	public boolean isTestMode()   { return testMode; } // TestMode activated?
	public long getRecord() 	  { return record; } // Record time			
	public int playerCoins()      { return player.getCoins(); } // Number of coins the player owns	
	public int distanceToGoal()   { return level.getLength()-player.getX(); } // Distance to the finish line	
	public boolean hasArrived()   { return player.hasArrived(); } // Arrived to the finish line?
	public int getPlayerX() 	  { return player.getX();	} // X position	in the road
	public int getPlayerY() 	  { return player.getY();	} // Y position in the road
	public int getCycle() 		  { return player.getX(); } // Cycle, the same as the X position
	public long elapsedTime() 	  { return System.currentTimeMillis() - initTime; } // Elapsed Time
	public int getObstacleCount() { return gameObjectContainer.getObstacleCount(); }
	public boolean isFinished() { 
		return player.toString() == "@" || player.hasArrived() || doExit;
	}
	
	// -------------------------------------------------------------------------------------------------------
	// --- TODO ----------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	// TODO
	public void addCoins() {   // SI
		//player.addCoins(numCoins);
		
	}
	
	public void inVisibleLimits() { 
		
	}
	
	public boolean isEmptyVisiblePosition() {   return false; }
	
	public void serializePosition() {		
		
		
	}
	
	public void hasCoins() {
		
		
	}
	
	public boolean isNewRecord() {		
		return false;
	}
	
	
	public void updateRecord() { // TODO
		
		
	}

	
	public boolean isPositionEmpty(int x, int y) {	 // SI	
		return gameObjectContainer.getObjectInPosition(x, y) == null;
	}
	
	public int getColliderInPosition() {	
		
		return 1;
		//return level.getColliderInPosition();  // HAY QUE CAMBIARLO
	}
}

