package logic;

/* ANTES ESTABA ESTO
import es.ucm.tp1.supercars.view.GamePrinter;
import java.util.Random;
import es.ucm.tp1.supercars.control.*;
import es.ucm.tp1.supercars.logic.gameobjects.*; */

import java.util.Random;
import control.Level;
import control.exceptions.InvalidPositionException;
import logic.gameobjects.*;
import logic.actions.InstantAction;
import logic.GameObjectGenerator;


public class Game { // HERENCIA?

	private GameObjectContainer gameObjectContainer;	
	
	private Random rand;	
	
	@SuppressWarnings("unused")
	private long seed;
	
	public Level level;	
	
	public Player player;
	
	private boolean doExit;	
	
	private String FINISH_LINE_SYMBOL = "¦";
	
	private long initTime;
	
	private long record;
	
	private boolean testMode;
	
	//private boolean isNewRecord;

	
	public Game(Long seed, Level level) { // SI		
		this.level = level;
		this.seed = seed;
		this.doExit = false;
			
		reset(level, seed);				
	}
	
	public void reset(Level level, Long seed) { // SI	
		this.level = level; 
		this.seed = seed;		
		this.initTime = System.currentTimeMillis();
		//this.testMode = false;
		
		this.rand = new Random(seed);
		this.gameObjectContainer = new GameObjectContainer();
		this.player = new Player(this, 0, level.getWidth()/2);
		
		
		GameObjectGenerator.generateGameObjects(this, this.level);		
	}
	
	public void clear() { // SI
		gameObjectContainer.clear();		
	}
	
	public void update() {  // SI		
		player.update();
		player.goFoward();
		player.update();
		gameObjectContainer.update();
		//player.goFoward();
		GameObjectGenerator.generateRuntimeObjects(this);			
	}	
	
	public void updateRecord() { // TODO
		
		
	}
	
	public int getRoadWidth() {	 // SI
		return level.getWidth(); 
	}
	
	public int getRoadLength() { // SI	
		return level.getLength(); 
	}
	
	public int getVisibility() { // SI	
		return level.getVisibility();  
	}
	
	public boolean isUserExit() { // SI				
		return doExit;
	}	
	
	// TERMINA EL JUEGO CUANDO SE CHOCA, LLEGA A LA META O USA EL COMANDO EXIT
	public boolean isFinished() { // SI
		return player.toString() == "@" || player.hasArrived() || doExit;
	}		
	
	public String positionToString(int x, int y) {  // SI 		
		String ret = ""; 		
		int rx = x + player.getX();
		
		if (player.isInPosition(rx, y)) ret = player.toString() + " ";		
		if (!gameObjectContainer.isPositionEmpty(rx,  y)) ret += gameObjectContainer.getObjectInPosition(rx, y);
		if (level.getLength() == rx) ret += FINISH_LINE_SYMBOL; 	 		
		
		
		return ret;		
	}
	
	public long elapsedTime() {  // SI		
		return System.currentTimeMillis() - initTime;
	}
	
	public int getObstacleCount() {
		return gameObjectContainer.getObstacleCount();
	}
	
	// ESTA BIEN PERO ES MUY GUARRO
	// return gameObjectContainer.getCoinsCount() - playerCoins() + 5; (ANTES ESTABA ESTO)
	public int getCoinsCount() {
		int ret;
		boolean colectedSuperCoin = false;
		
		ret = playerCoins();
		
		if (ret > 1000 || colectedSuperCoin) {
			ret = gameObjectContainer.getCoinsCount() - playerCoins() + 5 +1000;
			colectedSuperCoin = true;
		}
		else ret = gameObjectContainer.getCoinsCount() - playerCoins() + 5;
		
		return ret;
	}
	
	public void add(GameObject gameObject) {  // SI
		gameObjectContainer.add(gameObject);
		
	}
	
	public void goUp() {  // SI		 
		player.update();
		if(player.getY() > 0) player.goUp();
		player.goFoward();
		player.update();

		gameObjectContainer.update();
		GameObjectGenerator.generateRuntimeObjects(this);	
	}
	
	public void goDown() {  // SI				
		player.update();
		if(player.getY() < level.getWidth() - 1) player.goDown();
		player.goFoward();		
		player.update();
		
		gameObjectContainer.update();
		GameObjectGenerator.generateRuntimeObjects(this);	
	}	
	
	public void setUserExit() {		 // ANTES ERA setUserExit
		doExit = true;
	}
	
	public boolean execute(InstantAction Action) {  // ANTES ERA VOID  // SI
		Action.execute(this);
		return true;
	}
	
	public int getColliderInPosition() {	
		
		return 1;
		//return level.getColliderInPosition();  // HAY QUE CAMBIARLO
	}
	
	public boolean isPositionEmpty(int x, int y) {	 // SI	
		return gameObjectContainer.getObjectInPosition(x, y) == null;
	}
	
	public void hasCoins() {
		
		
	}
	
	public void subCoins(int x) {	 // SI				
		player.setCoins(player.getCoins() - x);
		
	}
	
	public int getRandomLane() {  // SI		
		return (int)(rand.nextDouble()*(level.getWidth()));
	}
	
	public int getRandomVisibleColumn() {  // SI			
		return (int)(rand.nextDouble()*(level.getVisibility()));
	}
	
	public void addCoins() {   // SI
		//player.addCoins(numCoins);
		
	}
	
	public void inVisibleLimits() { 
		
	}
	
	public boolean isEmptyVisiblePosition() {   // BOOL ???
		
		return false;
	}
	
	public int getPlayerX() {	 // SI		
		return player.getX();								
	}
	
	public int getPlayerY() {	 // SI		
		return player.getY();							
	}
	
	
	public void tryToAddObject(GameObject gameObject, double f) {	 // SI		
		if (rand.nextDouble() < f) {    
			if(gameObjectContainer.isPositionEmpty(gameObject.getX(), gameObject.getY())) {
				// SIRVE PARA VER TODAS LOS OBSTACULOS Y MONEDAS AL PRINCIPIO
				// System.out.println(gameObject.getX() + " " + gameObject.getY() + " " + gameObject.toString()); 
				gameObjectContainer.add(gameObject);
			}
		}			
	}	
	
	
	public void forceAddObject(GameObject gameObject) { // SI
		if(gameObjectContainer.isPositionEmpty(gameObject.getX(), gameObject.getY())) {
			gameObjectContainer.add(gameObject);
		}
		
	}
	
	public void toggleTest(){	 // SI	
		this.testMode = true;
	}	
	
	public boolean isTestMode() {	 // SI		
		return testMode;
	}
	
	public void clearColumn(int column) {  // SI
		gameObjectContainer.clearColumn(column);		
	}
	
	public long getRecord() {		
		return record;
	}
	
	public int getCycle() {	 // SI
		return player.getX(); // cycle
	}
	
	
	public int playerCoins() {  // SI
		return player.getCoins();
	}
	
	public int distanceToGoal() {	 // SI	
		return level.getLength() - player.getX();
	}
	
	public boolean hasArrived() {  // SI
		return player.hasArrived();
	}
	
	public boolean isNewRecord() {
		
		return false;
	}
	
	public void serializePosition() {
		
		
		
	}
	
	public Level getLevel() { // SI
		return this.level;
	}
	
	public Player getPlayer() {
		return this.player;
	}	
	
		
	
	public Collider getObjectInPosition(int x, int y) { // SI
		return gameObjectContainer.getObjectInPosition(x, y);
	}

	public void removeDead(GameObject gameobject) {  // SI
		gameObjectContainer.removeDead(gameobject);
		
	}

	// NUEVOS PRACTICA FINAL
	/*
	public String serialize() {
		StringBuilder buffer = new StringBuilder();
		GameObject GO;
		for (int i = 0; i < getRoadLength(); ++i) {
			for (int j = 0; j < getRoadLength(); ++j) {
				if (player.getX() == i && player.getY() == j) {
					buffer.append(player.serializePosition(i, j));
				} 
				if (GO != null) {
					buffer.append(gamObjectContainer.serializePosition(i, j));
				}
			}
			
		}
		
		return buffer.toString();
	}*/
	
	public void forceAddGrenade(GameObject GO) throws InvalidPositionException { // SI
		int x = GO.getX();
		int y = GO.getY();
		
		if (gameObjectContainer.isPositionEmpty(x,  y) && 
		   (x > 0 && x < getVisibility()) && (y >= 0 && y < getRoadWidth())) {
			gameObjectContainer.add(GO);
		} else {
			throw new InvalidPositionException("Invalid position");
		}
		
	}
	
	
	
	
}

