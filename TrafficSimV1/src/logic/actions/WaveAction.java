package logic.actions;

import logic.Collider;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for the Wave action
 * ESP: Clase para la accion Ola
 */
public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) { // Iterates all the road moving one position to the right each object
		Collider C=null;
		Collider Caux=null;
		
		
		for (int i=0;i<game.getLevel().getWidth(); i++) {
			for (int j=game.getLevel().getVisibility()-1;j>0;j--) {
				C=game.getObjectInPosition(game.getPlayerX()+j,i);
				
				if (C!=null) {
					Caux=game.getObjectInPosition(game.getPlayerX() + j+1, i);
					if (Caux==null) C.receiveWave();
				}
			}
			
		}
		
	}

}
