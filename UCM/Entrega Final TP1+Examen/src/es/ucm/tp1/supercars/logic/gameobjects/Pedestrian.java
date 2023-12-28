package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.*;
import es.ucm.tp1.supercars.control.Level;

public class Pedestrian extends GameObject{

	private static final String SYMBOL = "☺";

	public static final Object INFO = "Hits the car";   
	
	//private Game game;	
	
	//private GameObjectContainer objects;	
	
	private Level level;
	
	private Player player;
	
	boolean sube = false;
	boolean baja = true;
	
	// CONSTRUCTORA DEL PEDESTRIAN
	public Pedestrian(Game game, int x, int y) {	  
		super(game, x, y);	
	}	
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL;
	
		return ret;
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

	// SE CHOCA Y EL PLAYER MUERE
	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	// ESTA FUNCION DE update HACE QUE SE MUEVA DE ARRIBA ABAJO, 
	// SIEMPRE QUE PUEDA EMPIEZA SUBIENDO
	public void update() {
		
		if (baja && this.y != game.getLevel().getWidth()) {
			this.y += 1;
			if ((this.y) == game.getLevel().getWidth()-1) {
				sube = true;
				baja = false;
			}
		}
		else if (this.y != 0){ // HAY QUE CAMBIARLO
			this.y -= 1;
			sube = true;
			baja = false;
			
			if ((this.y) == 0) {
				sube = false;
				baja = true;
			}
		}
		
		
	}

	@Override
	public boolean receiveShoot() {			
		game.getPlayer().resetCoins(); // MONEDAS A CERO SI MATAS A UN PEDESTRIAN
		
		return true; // LE DA
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
