package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

// FALTA HACER QUE CUANDO COGE UN TURBO Y CAE EN UN OBSTACULO 
// SE IMPRIME EL TABLERO CON 2 OBJETOS EN LA MISMA CASILLA Y 
// LUEGO HACE EL DOCOLLISION
public class Turbo extends GameObject {	

	private static final String SYMBOL = ">>>"; 
	
	public static final Object INFO = "The car jumps 3 positions"; 

	// private Game game;	
	
	// private GameObjectContainer objects;
	
	// CONSTRUCTORA DE TURBO
	public Turbo(Game game, int x, int y) {
		super(game, x, y);		
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO	
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	
	// FUNCIONES DE HERENCIA
	
	// BORRA EL TURBO
	@Override
	public void onDelete() {
		this.x = -1;
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	// AUMENTA LA x EN 3
	@Override
	public boolean receiveCollision(Player player) {
		player.getTurbo();		
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	// NO SE SI HAY QUE USAR ESTA FUNCION PARA AUMENTAR LA X DEL PLAYER
	@Override
	public void update() {
		
	}

	@Override
	public boolean receiveShoot() { // NO LE PUEDE DISPARAR
		return false;
	}

	@Override
	public boolean receiveExplosion() { // CREO QUE LA GRANADA PUEDE DAR A UN TURBO
		game.removeDead(this);
		return false;
	}

	@Override
	public boolean receiveWave() {
		this.x++; 
		return false;
	}

	@Override
	public boolean receiveThunder() { // CREO QUE LE PUEDE DAR UN THUNDER
		game.removeDead(this);
		return false;
	}

	

}
