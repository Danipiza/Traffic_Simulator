package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.*;

public class Player extends GameObject{

	private String SYMBOL = ">";
	
	private String CRASHSYMBOL = "@";
	
	private int INT_COINS = 5;
	
	private int playerCoins;
	
	private boolean isCrashed;
	
	private SuperCoin superCoin;
	
	public Player(Game game, int x, int y) {		
		super(game, x, y);
		this.playerCoins = INT_COINS;
		this.isCrashed = false;
		
	}

	public String toString() {
		String Symbol;
		
		if(isCrashed) Symbol = CRASHSYMBOL;
		else Symbol = SYMBOL;
		
		return Symbol;
	}
	
	public void goUp() {			
		this.y--;
		//this.x++;
		
	}
	
	public void goDown() {					
		this.y++;
		//this.x++;
	
	}
	
	
	public boolean isInPosition(int x, int y) {		
		return (this.x == x) && (this.y == y);
		
	}
	
	public void update() {		
		//x++;
		doCollision();
	}	
	
	
	
	public void addCoins() {		//int numCoins
		this.playerCoins++;
	
	}
	
	public void resetCoins() {
		this.playerCoins = 0;
	}	
	
	
	public void addCoins2(int x) {
		this.playerCoins += x;
	}
	
	public void subCoins() {		
		this.playerCoins--;
	
	}
	
	public int getCoins() {			
		return this.playerCoins;	
	}		
	
	public void setCoins(int x) {
		this.playerCoins = x;		
	}
	
	public int getX() {		
		return x;			
	}
	
	public void setX(int x) {		
		this.x = x;		
	}
	
	public int getY() {		
		return y;			
	}
	
	public void setY(int y) {		
		this.y = y;		
	}
	
	public void setCrashed() {
		this.isCrashed = true;
	}
	
	public boolean hasArrived() {		
		return this.getX() == game.getLevel().getLength() +1;
	}

	public void goFoward() {
		//doCollision();
		this.x++;
		//doCollision();
		
	}	

	// NUEVAS FUNCIONES
	
	public void addSuperCoin( ) {
		this.playerCoins = this.playerCoins + 1000;
	}
	
	// AUMENTA LA x DEL PLAYER EN 3 SI COLISIONA CON EL TURBO
	// Y MIRA SI COLISIONA CON ALGUN OBJETO
	public void getTurbo() {
		this.x = this.x + 3;
		doCollision();		
	}
	
	// FUNCIONES DE HEREDACION
	
	@Override
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x, y);
		
		if(other != null) {
			return other.receiveCollision(this);
		}
		
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onDelete() {	
		
	}

	@Override
	public boolean receiveShoot() { // NO SE PUEDE DAR ASI MISMO		
		return false;
	}

	@Override
	public boolean receiveExplosion() { // MATA AL PLAYER
		isCrashed = true;
		return false;
	}

	@Override
	public boolean receiveWave() { // NO HACE NADA
		return false;
	}

	@Override
	public boolean receiveThunder() { // LO TENGO PUESTO QUE NO LE PUEDE CAER UN RAYO		
		return false;
	}

	

	
	
	
	
}
