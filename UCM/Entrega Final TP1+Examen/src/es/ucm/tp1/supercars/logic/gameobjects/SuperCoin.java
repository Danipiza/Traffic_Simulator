package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

// FALTA VER QUE FUNCIONES Y ATRIBUTOS QUITAR
// Y HACER QUE SOLO HAYA UNA SUPERCOIN (PERO ESO YA CUANDO SE IMPRIMAN LOS OBJETOS)
public class SuperCoin extends GameObject {

	private static final String SYMBOL = "$";
	
	public static final String INFO = "The player receive 1000 coins, there is only 1 SuperCoin";
	
	//public static final Object INFO = "The car jumps 3 positions"; 
	
	private static final int VALUE = 1000;
	
	private boolean alive;	
	
	private static boolean cont;
	
	private Game game;
	
	// CONSTRUCTORA DE LA SUPERCOIN
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		cont = false;
		symbol = SYMBOL;
		this.game = game;
		
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String Symbol = " ";
		
		if(isAlive()) Symbol = SYMBOL;
		
		
		return Symbol;
	}
	
		
	
	// ESTA FUNCION ES LA MISMA QUE LA COIN
	public void recieveCollision() {		
			this.x = -1; 
	}	
	

	
	public static boolean hasSuperCoin() {	
		
		if(cont) return true;
		else return false;
		
		
	}

	// FUNCION QUE ESCRIBE CUANDO HAYA UNA SUPERMONEDA
	public void escribeSuperCoin() {		
		System.out.println("Supercoins is present" + "\n");
		
	}
	
	
	
	
	// FUNCIONES DE HERENCIA
	
	@Override
	public boolean doCollision() {
		return false;
	}

	
	// FUNCION QUE SUMA 1000 COINS SI EL PLAYER COLISIONA Y PONE A -1 LA x DE LA COIN
	@Override
	public boolean receiveCollision(Player player) {
		player.addSuperCoin();
		this.x = -1;
		return true;
	}

	@Override
	public void onEnter() {
		cont = true;
		//System.out.print("Supercoins is present" + "\n");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		
	}		
	
	// AHORA MISMO NO LO USO
	public int getSuperCoinValue() {
		return this.VALUE;
	}

	public static void Exists(boolean b) {
		cont = true;
		
	}

	@Override
	public boolean receiveShoot() {		
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
		//game.removeDead(this);
		return false;
	}
		

}