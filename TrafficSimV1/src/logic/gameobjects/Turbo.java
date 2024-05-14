package logic.gameobjects;

import logic.Game;

// TODO revisar
// FALTA HACER QUE CUANDO COGE UN TURBO Y CAE EN UN OBSTACULO 
// SE IMPRIME EL TABLERO CON 2 OBJETOS EN LA MISMA CASILLA Y 
// LUEGO HACE EL DOCOLLISION

/**
 * @author DannyP39
 
 * ENG: Class for the object Turbo
 * ESP: Clase para el objeto Turbo
 */
public class Turbo extends GameObject {	

	private static final String SYMBOL = ">>>"; 	
	public static final Object INFO = "The car jumps 3 positions"; 

	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Turbo
	 * ESP: Constructor de la clase Turbo
	 */
	public Turbo(Game game, int x, int y) {
		super(game, x, y);		
	}
	
	
	/**
	 *  ENG: toString function to print Turbo
	 *  Esp: funcion toString para imprimir Turbo
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
	public void onDelete() { // Deletes from the road
		this.x = -1;
	}
	
	@Override
	public boolean doCollision() { 
		return false;
	}

	
	@Override
	public boolean receiveCollision(Player player) { // When the player collides increase by three the collumns
		player.getTurbo();		
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	
	@Override
	public void update() { // Doesnt moves
		
	}

	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean receiveShoot() { // Cant receive a shoot
		return false;
	}

	@Override
	public boolean receiveExplosion() { // Deletes from the road
		game.removeDead(this);
		return true;
	}

	@Override
	public boolean receiveWave() { // Moves one column
		this.x++; 
		return false;
	}

	@Override
	public boolean receiveThunder() { // Deletes from the road
		game.removeDead(this);
		return true;
	}

	

}
