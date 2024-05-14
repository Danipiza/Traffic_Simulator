package logic.actions;

import logic.Collider;
import logic.Game;


/**
 * @author DannyP39
 
 * ENG: Class for Explosion action (from the grenade)
 * ESP: Clase para la accion Explosion (de la granada)
 */
public class ExplosionAction implements InstantAction{
	
	private int x, y;
	
	/**
	 * @param x	int		(coordX)
	 * @param y	int		(coordY) of the grenade
	 *
	 * ENG: Class constructor for ExplosionAction
	 * ESP: Constructor de la clase ExplosionAction
	 */
	public ExplosionAction(int x, int y) { 
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void execute(Game game) { // Explote in a 3x3 square radius
		Collider C; 
		
		for(int i=x-1;i<=x+1;i++) {
			for (int j=this.y-1;j<=y+1;j++) {
				C=game.getObjectInPosition(i, j);
				if (C!=null) C.receiveExplosion();						
			}			
		}		
		
	}

}
