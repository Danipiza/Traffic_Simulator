package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

// YA TERMINADO FALTA VER LAS COSAS QUE NO SE USAN
public class Wall extends GameObject {

	public static final Object INFO = "The same as obstacle, but with points of life";   
	
	// VIDA DEL WALL
	private int vida = 3;
	
	// STRINGS DEL MURO
	private static final String wallMax = "█";

	private static final String wallMedium = "▒";
	
	private static final String wallLow = "░";
	
	//private Game game;	
	
	//private GameObjectContainer objects;
	
	// CONSTRUCTORA DEL WALL
	public Wall(Game game, int x, int y) {	  
		super(game, x, y);	
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String ret = " ";
		if(isAlive()) {
			if (vida == 3) ret = wallMax;
			else if (vida == 2) ret = wallMedium;
			else if (vida == 1) ret = wallLow;
		}
		
		return ret;
	}	
	
	// FUNCIONES DE HERENCIA
	
	// BORRA LA WALL
	@Override
	public void onDelete() {
		this.x = -1;	
	}
	
	// FUNCION QUE DEVUELVE TRUE SE COLLISIONA EL PLAYER
	@Override
	public boolean doCollision() {		
		return false;
	}

	// SE CHOCA Y EL PLAYER MUERE (COMO CON EL OBJETO OBSTACLE)
	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean receiveShoot() {		
		this.vida--;
		
		if (this.vida == 0) {
			game.removeDead(this); // SI LE DISPARA 3 VECES MUERE LA WALL
			game.getPlayer().addCoins2(5);
		}
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		game.removeDead(this);
		return true;
	}

	@Override
	public boolean receiveWave() {
		this.x++; 
		return true;
	}

	@Override
	public boolean receiveThunder() { // LE MATA INSTANTANEAMENTE
		game.removeDead(this);
		return true;
	}

	
		
	
}
