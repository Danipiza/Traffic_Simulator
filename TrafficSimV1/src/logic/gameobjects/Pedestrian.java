package logic.gameobjects;

import logic.Game;
import logic.gameobjects.*;
import control.Level;

/**
 * @author DannyP39
 
 * ENG: Class for the object Pedestrian
 * ESP: Clase para el objeto Peaton
 */
public class Pedestrian extends GameObject{

	private static final String SYMBOL = "☺";
	public static final Object INFO = "Hits the car";   
	
	
	boolean up = false;
	boolean down = true;
	
	// CONSTRUCTORA DEL PEDESTRIAN
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Pedestrian
	 * ESP: Constructor de la clase Peaton
	 */
	public Pedestrian(Game game, int x, int y) {	  
		super(game, x, y);	
	}	
	
	/**
	 *  ENG: toString function to print Pedestrian
	 *  ESP: funcion toString para imprimir Peaton
	 */
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public void onDelete() { // Removes from the road
		this.x = -1;		
	}

	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) { // Collides with the player
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() {	}

	@Override
	public void update() { // Moves in one direction until its hits with a roads limit
		
		if (down && y!=game.getLevel().getWidth()) {
			y++;
			if (y==game.getLevel().getWidth()-1) {
				up=true;
				down=false;
			}
		}
		else if (y != 0){ 
			y--;
			up=true;
			down=false;
			
			if (y==0) {
				up=false;
				down=true;
			}
		}
		
		
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------

	@Override
	public boolean receiveShoot() {	// Deletes the from the road, and set the players coins to zero.		
		game.getPlayer().resetCoins(); 
		game.removeDead(this);
		return true; 
	}

	@Override
	public boolean receiveExplosion() { // Deletes the from the road, and set the players coins to zero.	
		game.getPlayer().resetCoins();
		game.removeDead(this);
		return true; 
	}

	@Override
	public boolean receiveWave() { // Moves one column to the right
		this.x++; 
		return true; 
	}

	@Override
	public boolean receiveThunder() { // Deletes the from the road.
		game.removeDead(this);
		return true;
	}
	
	
	
}
