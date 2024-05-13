package logic.gameobjects;

import logic.Game;

public class Coin extends GameObject{
	
	private static final String SYMBOL = "¢"; 

	public static final Object INFO = "The player receive 1 coin"; 
	
	private static final int VALUE = 1;
	
	private boolean alive;
	
	// CONSTRUCTORA DE LA COIN
	public Coin(Game game, int x, int y) {		  
		super(game, x, y);		
	}
	
	// FUNCION QUE DEVUELVE EL SIMBOLO
	public String toString() {
		String Symbol = " ";
		
		if(isAlive()) Symbol = SYMBOL;
		
		return Symbol;
	}
		
	
	
	// FUNCIONES DE HERENCIA

	@Override
	public boolean doCollision() {		
		return false;
	}

	// FUNCION QUE SUMA UNA COIN SI EL PLAYER COLISIONA Y PONE A -1 LA x DE LA COIN
	@Override
	public boolean receiveCollision(Player player) {
		player.addCoins();
		this.x = -1; // ANTES ESTABA EN OTRA FUNCION (MAS ARRIBA)
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onDelete() {
		
	}
	
	// PA QUE FUNCIONE EL GAMEPRINTER 
	public static Object getCoinsCount() {		
		return null;
	}

	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;  // NO LE DA
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
	public boolean receiveThunder() {
		//game.removeDead(this);
		return false; // NO LE DA
	}	
	
	
	
}
