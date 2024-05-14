package logic.gameobjects;

import logic.Collider;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Father class for the GameObjects
 * ESP: Clase padre para los objetos
 */
public abstract class GameObject implements Collider {

	protected int x, y;
	protected Game game;
	protected String symbol;

	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for GameObject
	 * ESP: Constructor de la clase GameObject
	 */
	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	

	/**
	 *  ENG: toString function to print the GameObject
	 *  ESP: funcion toString para imprimir el GameObject
	 */
	@Override
	public String toString() {			
		return (isAlive()?getSymbol():"");
	}

	/** 
	 * @param x
	 * @param y
	 * @return
	 *
	 * ENG: Method for checking if the gameobject is in a position of the road
	 * ESP: Funcion para comprobar si el objeto esta en una posicion en la carretera
	 */
	public boolean isInPosition(int x, int y) {
		return this.x==x && this.y==y;
	}

	
	// Abstract functions for inheritance
	public abstract void onEnter();
	public abstract void update();
	public abstract void onDelete();
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------

	public int getX() 			 { return x; } // X coord in the road
	public int getY() 			 { return y; }	// Y coord in the road
	public boolean isAlive() 	 { return (this.x != -1); } // Alive?	
	protected String getSymbol() { return symbol; } // Symbol to print the road

}
