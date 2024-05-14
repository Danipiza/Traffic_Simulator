package control;

import control.exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.gameobjects.*;

/**
 * @author DannyP39
 
 * ENG: Interface to make purchases 
 * ESP: Interfaz para realizar las compras
 */
public interface Buyable {

	public int cost();
	
	/** 
	 * @param game
	 * @return	boolean
	 * @throws NotEnoughCoinsException
	 *
	 * ENG: Method for buying actions (if the player has enough money)
	 * ESP: Funcion para comprar acciones (si el jugador tiene suficiente dinero)
	 */
	public default boolean buy(Game game) throws NotEnoughCoinsException{		
		
		if (game.getPlayer().getCoins()>=cost()) game.subCoins(cost());
		else {
			throw new NotEnoughCoinsException("Not enough coins");
		}				
		return true;
	}

	
}
