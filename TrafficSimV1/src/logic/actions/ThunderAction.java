package logic.actions;

import logic.Game;
import logic.gameobjects.GameObject;
import logic.Collider;

/**
 * @author DannyP39
 
 * ENG: Class for Thunder action
 * ESP: Clase para la accion Trueno
 */
public class ThunderAction implements InstantAction{
	
	private static final String THUNDER_MSG = "Thunder hit position: ";
	
	
	@Override
	public void execute(Game game) { // Lands in a random visible position of the road
		int x=game.getRandomVisibleColumn();
		int y=game.getRandomLane();
				
		Collider C=game.getObjectInPosition(game.getPlayerX() + x, y);
		
		GameObject GO=(GameObject) C;
		
		// Message where its lands
		System.out.print(THUNDER_MSG+"("+x+", "+y+") ");
		
		if (C!=null) {
			String aux = GO.toString();
			if (C.receiveThunder()) System.out.print("-> "+aux+" hit"); 
			System.out.print("\n");
		}
		else System.out.print("\n");		
        
 		
	}	
	
}
