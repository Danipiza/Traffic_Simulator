package logic.gameobjects;

import logic.Game;
import logic.gameobjects.*;

/**
 * @author DannyP39
 
 * ENG: Class for the object Obstacle
 * ESP: Clase para el objecto Obstaculo
 */
public class Obstacle extends GameObject{
	
	// 1 of life
	private static final String SYMBOL = "░";
	public static final Object INFO = "Hits the car";
	
	
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Obstacle
	 * ESP: Constructor de la clase Obstaculo
	 */
	public Obstacle(Game game, int x, int y) {	  
		super(game, x, y);	
	}	
	
	/**
	 *  ENG: toString function to print Obstacle
	 *  ESP: funcion toString para imprimir Obstaculo
	 */
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	
	// ---------------------------------------------------------------------------------------------------------
	// --- COLLISION -------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------- 
	
	// BORRA EL OBSTACLE
	@Override
	public void onDelete() { // Deletes from the road
		this.x = -1;
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	// SE CHOCA Y EL PLAYER MUERE
	@Override
	public boolean receiveCollision(Player player) { // When the player collides with the Obstacle, the player crash
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() { }

	@Override
	public void update() { }
	


	// ---------------------------------------------------------------------------------------------------------
	// --- COLLISION -------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean receiveShoot() { // Removes from the road, and add a coin to the player
		game.removeDead(this);
		game.getPlayer().addCoins();
		return true;
	}

	@Override
	public boolean receiveExplosion() { // Removes from the road, and add a coin to the player
		game.removeDead(this);
		game.getPlayer().addCoins();
		return true; 
	}

	@Override
	public boolean receiveWave() { // Moves one column to the right
		this.x++;
		return true; 
	}

	@Override
	public boolean receiveThunder() { // Removes from the road
		game.removeDead(this);
		return true; 
	}
	
}
