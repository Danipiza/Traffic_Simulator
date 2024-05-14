package logic;

import logic.gameobjects.Player;

/**
 * @author DannyP39
 
 * ENG: Interface to manage the collisions
 * ESP: Interfaz para manejar las colisiones
 */
public interface Collider {

	/** 
	 * @return
	 *
	 * ENG: Method for doing a colision
	 * ESP: Funcion para hacer una colision
	 */
	public boolean doCollision();

	/** 
	 * @param player
	 * @return
	 *
	 * ENG: Method for receiving a collision
	 * ESP: Funcion para recibir una colision 
	 */
	public boolean receiveCollision(Player player);
	
	/** 
	 * @return
	 *
	 * ENG: Method for receiving a shoot
	 * ESP: Funcion para recibir un disparo
	 */
	public boolean receiveShoot();
	
	/** 
	 * @return
	 *
	 * ENG: Method for receiving an explosion
	 * ESP: Funcion para recibir una explosion
	 */
	public boolean receiveExplosion();
	
	/** 
	 * @return
	 *
	 * ENG: Method for receiving a wave
	 * ESP: Funcion recibir una ola
	 */
	public boolean receiveWave();
	
	/** 
	 * @return
	 *
	 * ENG: Method for receiving a thunder
	 * ESP: Funcion para recibir un trueno 
	 */
	public boolean receiveThunder();
	

}

// SHOOT ATRAVIESA CUAL LASER
// THUNDER FALTA SYMBOL
// FALTA SUMAR COINS AL DESTRUIR OBSTACULOS OBSTACULO NADA WALL 5 
// FALTA SUMAR COINS 