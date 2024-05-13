package control;

import control.exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.gameobjects.*;

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
