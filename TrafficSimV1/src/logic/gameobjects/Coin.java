package logic.gameobjects;

import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for the object Coin
 * ESP: Clase para el objeto Coin
 */
public class Coin extends GameObject{
	
	private static final String SYMBOL = "¢"; 
	public static final Object INFO = "The player receive 1 coin"; 	
	private static final int VALUE = 1;
	
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Coin
	 * ESP: Constructor de la clase Coin
	 */
	public Coin(Game game, int x, int y) {		  
		super(game, x, y);		
	}
	
	
	
	// toString 
	public String toString() {
		String Symbol = " ";
		
		if(isAlive()) Symbol = SYMBOL;
		
		return Symbol;
	}
		
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- GameObject ----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------

	@Override
	public boolean doCollision() {		
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) { // Removes from the road
		player.addCoins(); // Adds a coin to the player
		this.x = -1; 
		return false;
	}

	@Override
	public void onEnter() {}

	@Override
	public void update() {} // Doesnt moves

	@Override
	public void onDelete() {}
		
	public static Object getCoinsCount() {		
		return null;
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	
	@Override
	public boolean receiveShoot() {	return false; } // Doesnt affects 

	@Override
	public boolean receiveExplosion() { // Remove from the road
		game.removeDead(this);
		return true;
	}

	@Override
	public boolean receiveWave() { // Move the coin one column
		this.x++;
		return true;
	}

	@Override
	public boolean receiveThunder() { // Doesnt affects
		return false; 
	}	
	
	
	
}
