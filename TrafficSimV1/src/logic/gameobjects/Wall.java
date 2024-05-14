package logic.gameobjects;

import logic.Game;


/**
 * @author DannyP39
 
 * ENG: Class for the object Wall
 * ESP: Clase para el objeto Muro
 */
public class Wall extends GameObject {

	public static final Object INFO = "The same as obstacle, but with points of life";   
	
	
	private int life=3;	
	
	/**
	 *  String to represents the life of the wall
	 */
	private static final String wallMax = "█";
	private static final String wallMedium = "▒";	
	private static final String wallLow = "░";
	
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Wall
	 * ESP: Constructor de la clase Muro
	 */
	public Wall(Game game, int x, int y) {	  
		super(game, x, y);	
	}
	
	
	/**
	 * END: toString function to print the wall
	 * ESP: funcion toString para imprimir el muro
	 */
	public String toString() {
		String ret = " ";
		if(isAlive()) {
			if (life == 3) ret = wallMax;
			else if (life == 2) ret = wallMedium;
			else if (life == 1) ret = wallLow;
		}
		
		return ret;
	}	
	
	
	
	// ---------------------------------------------------------------------------------------------------------
	// --- COLLISION -------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------
	
	@Override
	public void onDelete() { this.x = -1; } // Deletes the wall
	
	@Override
	public boolean doCollision() { return false; } // Doesnt removes when the player collides

	@Override
	public boolean receiveCollision(Player player) { // Execute crash when the player collides
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() {	} // noop

	@Override
	public void update() {} // Doesnt moves
	
	// ---------------------------------------------------------------------------------------------------------
	// --- COLLISION -------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------

	@Override
	public boolean receiveShoot() {	// Reduces its life. If the lifes reaches 0, deletes the wall from the road	
		this.life--;
		
		if (this.life == 0) {
			game.removeDead(this); 
			game.getPlayer().addCoins2(5);
		}
		return true;
	}

	@Override
	public boolean receiveExplosion() { // Deletes the wall from the road
		game.removeDead(this);
		return true;
	}

	@Override
	public boolean receiveWave() { // Moves one column to the right
		this.x++; 
		return true;
	}

	@Override
	public boolean receiveThunder() { // Deletes the wall from the road
		game.removeDead(this);
		return true;
	}

	
		
	
}
