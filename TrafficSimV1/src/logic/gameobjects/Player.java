package logic.gameobjects;

import logic.Collider;
import logic.Game;
import logic.gameobjects.*;

/**
 * @author DannyP39
 
 * ENG: Class for the Player
 * ESP: Clase para el Jugador
 */
public class Player extends GameObject{

	private String SYMBOL = ">";	
	private String CRASHSYMBOL = "@";
	
	private int INT_COINS = 5;	
	private int playerCoins;	
	private boolean isCrashed;
	
	
	/**
	 * @param game
	 * @param x
	 * @param y
	 *
	 * ENG: Class constructor for Player
	 * ESP: Constructor de la clase Jugador
	 */
	public Player(Game game, int x, int y) {		
		super(game, x, y);
		this.playerCoins = INT_COINS;
		this.isCrashed = false;
		
	}
	
	/**
	 *  ENG: toString function to print Player
	 *  ESP: funcion toString para imprimir Jugador
	 */
	public String toString() {
		String Symbol;
		
		if(isCrashed) Symbol = CRASHSYMBOL;
		else Symbol = SYMBOL;
		
		return Symbol;
	}
	
	/** 
	 * 
	 * ENG: Method for go up a lane from the road
	 * ESP: Funcion para subir un carril de la carretera
	 */
	public void goUp() { this.y--; }
	
	/** 
	 * 
	 * ENG: Method for go down a lane from the road
	 * ESP: Funcion para bajar un carril de la carretera 
	 */
	public void goDown() { this.y++; }
	
	
	/**
	 * @params x
	 * @params y
	 * 
	 * ENG: Method to find out if its is in a position 
	 * ESP: Funcion para comprubar si esta en una posicion 
	 */
	public boolean isInPosition(int x, int y) {		
		return (this.x == x) && (this.y == y);		
	}
	
	
	/**
	 * ENG: Method for updating a posible collision
	 * ESP: Funcion para actualizar una posible colision 
	 */
	public void update() { doCollision(); }	
	
	
	
	/** 
	 * 
	 * ENG: Method for adding a coin to the player
	 * ESP: Funcion para sumar una moneda al jugador
	 */
	public void addCoins() { this.playerCoins++; }
	
	/** 
	 * @param x
	 *
	 * ENG: Method for adding a "x" value of coins to the player
	 * ESP: Funcion para sumar un valor "x" de monedas al jugador 
	 */
	public void addCoins2(int x) { this.playerCoins += x; }
	
	/** 
	 *
	 * ENG: Method for reseting the number of coins from the player
	 * ESP: Funcion para reiniciar el numero de monedas del jugador
	 */
	public void resetCoins() { this.playerCoins = 0; }	
	
	/** 
	 *
	 * ENG: Method for substracting a coin to the player
	 * ESP: Funcion para restar una moneda al jugador
	 */
	public void subCoins() { this.playerCoins--; }
		

	/** 
	 *
	 * ENG: Method to go forward in the road (right)
	 * ESP: Funcion para avanzar el la carretera  (derecha)
	 */
	public void goForward() { this.x++; }	
	
	
	/** 
	 *
	 * ENG: Method for adding a SuperCOin to the player
	 * ESP: Funcion para añadir una SuperMoneda al jugador 
	 */
	public void addSuperCoin() { this.playerCoins+=1000; }
	
	
	/** 
	 *
	 * ENG: Method to move forward the player by three positions. (Check the posible collision)
	 * ESP: Funcion para mover al jugador three casillas. (Comprueba la posible colision)
	 */
	public void getTurbo() {
		this.x+=3;
		doCollision();		
	}
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean doCollision() { // Check for posible colisions
		Collider other = game.getObjectInPosition(x, y);
		
		if(other != null) return other.receiveCollision(this);
		
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	@Override
	public void onEnter() { }

	@Override
	public void onDelete() { }
	
	// -------------------------------------------------------------------------------------------------------
	// --- COLLISION -----------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------

	@Override
	public boolean receiveShoot() { // Cant Shoot himself		
		return false;
	}

	@Override
	public boolean receiveExplosion() { // Explode the player
		isCrashed = true;
		return true;
	}

	@Override
	public boolean receiveWave() { // The wave doesnt affects the player
		return false;
	}

	@Override
	public boolean receiveThunder() { // Doesnt affects by a thunder	
		return false;
	}

	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS & SETTERS ---------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	
	public int getCoins() { return this.playerCoins; }
	public void setCoins(int x) { this.playerCoins = x;	}
	
	public int getX() { return x; }	
	public void setX(int x) { this.x = x; }
	
	public int getY() {	return y; }	
	public void setY(int y) { this.y = y; }
	
	public void setCrashed() { this.isCrashed = true; }
	
	public boolean hasArrived() { return this.getX() == game.getLevel().getLength() +1; }
	
	
	
	
}
