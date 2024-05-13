package logic.gameobjects;

import logic.Game;

// FALTA HACER QUE SE PONGAN 2 OBJETOS EN LA MISMA CASILLA DEPENDIENDO
// DE QUIEN ENTRA PRIMERO 
// (ES DECIR PONER EL TRUCK A LA DERECHA DE LOS DEMAS OBJETOS)
public class Truck extends GameObject{	

	private static final String SYMBOL = "←";  
	
	public static final Object INFO = "Hits the car and moves to the left";
	
	//private Game game;
	
	//private GameObjectContainer objects;

	// CONSTRUCTORA DEL TRUCK
	public Truck(Game game, int x, int y) {
		super(game, x, y);		
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
	}
	
	// IGUAL QUE EL PLAYER
	public void goFoward() {
		this.x--;
		doCollision();
		
	}

	// FUNCIONES DE HERENCIA
	
	@Override
	public void onDelete() {
		this.x = -1;		
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	// CREO QUE HAY QUE CREAR OTRA FUNCION 
	// PARA PRIMERO PONERLOS EN LA MISMA CASILLA Y LUEGO MATAR AL PLAYER
	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed(); 
		return false;
	} 

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update() {
		this.x--;  
		// ESTO ES PARA QUE EL CAMION AVANCE
		// PERO NO SE SI FUNCIONA ASI
		
	}

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public boolean receiveShoot() { // NO SE SI LE PUEDE DAR UN SHOOT
		
		return false;
	}

	@Override
	public boolean receiveExplosion() {
		game.removeDead(this);
		return false;
	}

	@Override
	public boolean receiveWave() {
		this.x++;
		return false;
	}

	@Override
	public boolean receiveThunder() {
		game.removeDead(this);
		return false;
	}
	
	
	

}
