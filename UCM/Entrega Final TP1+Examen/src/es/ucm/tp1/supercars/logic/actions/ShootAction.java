package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.Collider;

public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) {
		Collider C = null;
		int x = 1;
		
		boolean objeto = false; // SOLO PUEDE DAR A OBSTACLE, WALL Y PEDASTRIAN
		
		while (!objeto && x < 8) {
			C = game.getObjectInPosition(game.getPlayerX() + x, game.getPlayerY());
			if (C != null) {
				if (C.receiveShoot()) {
					objeto = true;				
				}
			}
			
			x++;
		}
		// C.receiveShoot();		
		
	}

}
