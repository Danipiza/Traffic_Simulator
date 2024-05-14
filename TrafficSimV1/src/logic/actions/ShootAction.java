package logic.actions;

import logic.Game;
import logic.Collider;

/**
 * @author DannyP39
 
 * ENG: Class for the Shoot action
 * ESP: Clase para la accion Disparar
 */
public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) { // Iterates the visible columns where is located the player, until hits something.		
		Collider C = null;
		boolean object = false; 
		
		int x=1;
		while (!object && x<8) {
			C = game.getObjectInPosition(game.getPlayerX()+x, game.getPlayerY());
			if (C != null && C.receiveShoot()) object=true;
			
			x++;
		}		
		
	}

}
