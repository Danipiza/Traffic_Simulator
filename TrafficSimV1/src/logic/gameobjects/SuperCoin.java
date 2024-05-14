package logic.gameobjects;

import logic.Game;


/**
 * @author DannyP39
 
 * ENG: Class for the object SuperCoin
 * ESP: Clase para el objeto SuperMoneda
 */
public class SuperCoin extends GameObject {

	private static final String SYMBOL = "$";	
	public static final String INFO = "The player receive 1000 coins, there is only 1 SuperCoin";		
	private static final int VALUE = 1000;	
		
	private static boolean cont;	
	private Game game;
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for SuperCoin
	 * ESP: Constructor de la clase SuperMoneda
	 */
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		cont = false;
		symbol = SYMBOL;
		this.game = game;
		
	}
	
	/**
	 *  ENG: toString function to print Truck
	 *  ESP: funcion toString para imprimir Camion
	 */
	public String toString() {
		String Symbol = " ";
		
		if(isAlive()) Symbol = SYMBOL;
		
		
		return Symbol;
	}		
	
	

	
	/** 
	 * @return
	 *
	 * ENG: Method for see if there is a SuperCoin
	 * ESP: Funcion para mirar si hay una SuperMoneda
	 */
	public static boolean hasSuperCoin() { return (cont?true:false); }

	
	/** 
	 * 
	 *
	 * ENG: Method for printing if there is a SuperCoin
	 * ESP: Funcion para imprimir si hay una SuperMoneda
	 */
	public void escribeSuperCoin() {		
		System.out.println("Supercoins is present" + "\n");		
	}
	
	/** 
	 * @return
	 *
	 * ENG: Method for getting the value of SuperCoin
	 * ESP: Funcion para saber el valor de la SuperMoneda
	 */
	public int getSuperCoinValue() { 
		return SuperCoin.VALUE;
	}

	/** 
	 * @param b
	 *
	 * ENG: Method for setting to true the SuperCoin existence
	 * ESP: Funcion para marcar como SuperMoneda existente
	 */
	public static void Exists(boolean b) {
		cont = true;		
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean doCollision() {
		return false;
	}

	
	@Override
	public boolean receiveCollision(Player player) { // Deletes from the road, and adds 1000 coinst to the player
		player.addSuperCoin();
		this.x = -1;
		return true;
	}

	@Override
	public void onEnter() {
		cont = true;
	}

	@Override
	public void update() { }

	@Override
	public void onDelete() { }		
	
	
	

	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean receiveShoot() {	// Doesnt receives a shoot	
		return false;
	}

	@Override
	public boolean receiveExplosion() { // Deletes from the road
		game.removeDead(this);
		return false;
	}

	@Override
	public boolean receiveWave() { // Moves one column to the right
		this.x++; 
		return false;
	}

	@Override
	public boolean receiveThunder() { // Doesnt receive a thunder
		return false;
	}
		

}