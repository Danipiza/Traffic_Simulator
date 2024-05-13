package logic;

import logic.gameobjects.Player;

public interface Collider {

	boolean doCollision();

	boolean receiveCollision(Player player);
	
	boolean receiveShoot();
	
	boolean receiveExplosion();
	
	boolean receiveWave();
	
	boolean receiveThunder();
	

}

// SHOOT ATRAVIESA CUAL LASER
// THUNDER FALTA SYMBOL
// FALTA SUMAR COINS AL DESTRUIR OBSTACULOS OBSTACULO NADA WALL 5 
// FALTA SUMAR COINS 