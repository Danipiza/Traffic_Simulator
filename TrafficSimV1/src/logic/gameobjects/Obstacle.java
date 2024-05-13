package logic.gameobjects;

import logic.Game;
import logic.gameobjects.*;

public class Obstacle extends GameObject{

	private static final String SYMBOL = "░";

	public static final Object INFO = "Hits the car";
	
	// VIDA DEL OBSTACLE
	private int vida = 1;
	
	//private Game game;	
	
	//private GameObjectContainer objects;	
	
	// CONSTRUCTORA DE OBSTACLE
	public Obstacle(Game game, int x, int y) {	  
		super(game, x, y);	
	}	
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	
	// FUNCIONES DE HERENCIA 
	
	// BORRA EL OBSTACLE
	@Override
	public void onDelete() {
		this.x = -1;
		//isAlive() = false;
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	// SE CHOCA Y EL PLAYER MUERE
	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() {
		System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAA");
	}

	@Override
	public void update() {
		
	}
	
	public static Object getObstaclesCount() {				
		return null;
	}

	public static void reset() {		
		
	}

	@Override
	public boolean receiveShoot() {
		game.removeDead(this);
		game.getPlayer().addCoins2(1);
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		game.removeDead(this);
		return true; // LE DA
	}

	@Override
	public boolean receiveWave() {
		this.x++;
		return true; // LE DA
	}

	@Override
	public boolean receiveThunder() {
		game.removeDead(this);
		return true; // LE DA
	}
	
}
