package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.*;

public interface Buyable {

	public int cost();
	
	public default boolean buy(Game game) throws NotEnoughCoinsException{
		boolean ret = false;
		
		if (game.getPlayer().getCoins() >= cost()){
			ret = true;
			game.subCoins(cost());
		} else {
			throw new NotEnoughCoinsException("Not enough coins");
		}		
		
		return true;
	}

	
}
