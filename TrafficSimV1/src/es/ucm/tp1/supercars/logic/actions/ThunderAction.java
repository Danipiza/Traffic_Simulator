package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.Collider;

public class ThunderAction implements InstantAction{
	
	private static final String THUNDER_MSG = "Thunder hit position: ";
	
	
	@Override
	public void execute(Game game) {
		int x = game.getRandomVisibleColumn();
		int y = game.getRandomLane();
				
		Collider C = game.getObjectInPosition(game.getPlayerX() + x, y);
		
		GameObject GO = (GameObject) C;
		
		System.out.print(THUNDER_MSG + "(" + x + ", " + y + ") ");
		
		if (C != null) {
			String aux = GO.toString();
			if (C.receiveThunder()) System.out.print("-> " + aux + " hit"); // FALTA PONER EL SIMBOLO
			System.out.print("\n");
			// PUEDE QUE HAYA QUE SUMAR COINS
		}
		else System.out.print("\n");		
        
 		
	}	
	
}
