package logic.actions;

import logic.Collider;
import logic.Game;


public class ExplosionAction implements InstantAction{
	
	private int x, y;
	
	public ExplosionAction(int x, int y) {
		this.x =x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game) {
		Collider C; // ANTES COLLIDER
		
		for(int i = this.x - 1; i <= this.x + 1; ++i) {
			for (int j = this.y - 1; j <= this.y + 1; ++j) {
				C = game.getObjectInPosition(i, j);
				if (C != null) C.receiveExplosion();						
			}			
		}		
		
	}

}
