package logic.gameobjects;


import logic.Game;
import logic.actions.ExplosionAction;
import logic.Collider;

/**
 * @author DannyP39
 
 * ENG: Class for the object Grenade
 * ESP: Clase para el objeto Granada
 */
public class Grenade extends GameObject{

	private static final String SYMBOL = "ð";	
	public static final Object INFO = "Explodes in 3 cycles";
	
	private int time; // To explode
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Grenade
	 * ESP: Constructor de la clase Granada
	 */
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		time=3; // cycles to explode
	}
	
	/**
	 *  ENG: toString function to print Grenade
	 *  ESP: funcion toString para imprimir Granada
	 */
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL + "[" + time + "]";
		return ret;
	}
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public void onDelete() { // Explode and remove from the road
		game.removeDead(this);
		game.execute(new ExplosionAction(this.x, this.y));		
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) { // Cant collide with the player
		return false;
	}

	
	@Override
	public void onEnter() {	} 

	@Override
	public void update() { // Doesnt moves, but reduce the time to explode
		time--;
		if (time==0) onDelete();
	}

	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean receiveShoot() { // Instantly explode
		onDelete();	
		return true;
	}

	@Override
	public boolean receiveExplosion() { // Instantly explode
		onDelete();
		return true;
	}

	@Override
	public boolean receiveWave() { // Moves one column to the right
		this.x++; 
		return true;
	}

	@Override
	public boolean receiveThunder() { // Instantly explode
		onDelete();
		return true;
	}
	

}
