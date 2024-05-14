package logic.gameobjects;

import logic.Game;


/**
 * @author DannyP39
 
 * ENG: Class for the object Truck
 * ESP: Clase para el objeto Camion
 */
public class Truck extends GameObject{	

	private static final String SYMBOL = "←"; 	
	public static final Object INFO = "Hits the car and moves to the left";
	
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Truck
	 * ESP: Constructor de la clase Camion
	 */
	public Truck(Game game, int x, int y) {
		super(game, x, y);		
	}
	
	
	/**
	 *  ENG: toString function to print Truck
	 *  ESP: funcion toString para imprimir Camion
	 */
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	
	public void goFoward() { // Go foward (backwards seen by the player)
		this.x--;	
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
	public boolean receiveCollision(Player player) { // When the Truck collides with the player, the player crash
		player.setCrashed(); 
		return false;
	} 

	@Override
	public void onEnter() {	}

	@Override
	public void update() { // Moves one column to the left
		this.x--;  		
	}
	

	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean receiveShoot() { // Doesnt receives a shoot		
		return false;
	}

	@Override
	public boolean receiveExplosion() { // Removes from the road
		game.removeDead(this);
		return false;
	}

	@Override
	public boolean receiveWave() { // Moves one columna to the right
		this.x++;
		return false;
	}

	@Override
	public boolean receiveThunder() { // Deletes from the road
		game.removeDead(this);
		return false;
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	public int getX() { return x; }	
	public int getY() { return y; }
	
	

}
