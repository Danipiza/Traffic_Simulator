package es.ucm.tp1.supercars.logic.gameobjects;


import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;
import es.ucm.tp1.supercars.logic.Collider;

// FALTA HACER TAMBIEN EL COMANDO Grenade
public class Grenade extends GameObject{

	private static final String SYMBOL = "ð";
	
	public static final Object INFO = "Explodes in 3 cycles";
	
	private int time;
	
	//private Collider Collider;

	// CONSTRUCTORA DEL GRENADE
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		time = 3;
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String ret = " ";
		if (isAlive()) ret = SYMBOL + "[" + time + "]";
		return ret;
	}
	
	// FUNCIONES DE HERENCIA
	
	@Override
	public void onDelete() {
		game.removeDead(this);
		game.execute(new ExplosionAction(this.x, this.y));
	
		/*Collider C;
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				System.out.println((this.x + j) + " " + (this.symbol + i));
				C = game.getObjectInPosition(this.x + j, this.y + i);
				System.out.println();
				if (C != null) C.receiveExplosion();
			}
			
		}*/
	}
	
	@Override
	public boolean doCollision() {
		return false;
	}

	// NO COLISIONA CON EL PLAYER
	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}

	
	@Override
	public void onEnter() {
		// NO SE TOCA
	}

	@Override
	public void update() {
		time--;
		if (time == 0) {
			onDelete();
		}
	}

	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveExplosion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveWave() {
		this.x++; // ESTE NO SE SI TAMBIEN LE AFECTA LA WAVE
		return false;
	}

	@Override
	public boolean receiveThunder() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
