package logic;

//import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
//import es.ucm.tp1.supercars.logic.gameobjects.Coin;
//import es.ucm.tp1.supercars.logic.gameobjects.Truck;
//import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import control.Level;
import logic.gameobjects.*;
import logic.Game;
import logic.actions.*;

public class GameObjectGenerator {
	
	
	
	// SIRVE PARA AÑADIR OBJETOS
	// SE LE LLAMA EN EL RESET DEL GAME
	public static void generateGameObjects(Game game, Level level) {
		for (int x = game.getVisibility() / 2; x < game.getRoadLength(); x++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());   	// EN LA PLANTILLA ES SIN get			
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency()); 			 // EN LA PLANTILLA ES SIN get
			
			// CODIGO PARA AÑADIR LOS OBJETOS
			  if (level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				if (!SuperCoin.hasSuperCoin()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.advancedObjectsFrequency());					
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.advancedObjectsFrequency());
			 }
		}
		
	}
	
	/*public void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		
		switch (id) {
		case 1:
			o = new Wall(game, x, game.getRandomLane());
			break;
		case 2:
			o = new Turbo(game, x, game.getRandomLane());
			break;
		case 3:
			o = new SuperCoin(game, x, game.getRandomLane());
			break;
		case 4:
			o = new Truck(game, x, game.getRandomLane());
			break;
		case 5:
			o = new Pedestrian(game, x, 0);
			break;
			}
			game.forceAddObject(o);
		}*/
	
	
	

	public static void reset(Level level ) {
		//Obstacle.reset();
		//Coin.reset();
	}
	
	// NUEVO METODO 
	public static void generateRuntimeObjects(Game game) {
		//if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		//}
	}


	public static void forceAdvancedObject(Game game, int id, int x) {
		GameObject o = null;
		
		switch (id) {
		case 1:
			o = new Wall(game, x, game.getRandomLane());
			break;
		case 2:
			o = new Turbo(game, x, game.getRandomLane());
			break;
		case 3:
			o = new SuperCoin(game, x, game.getRandomLane());
			break;
		case 4:
			o = new Truck(game, x, game.getRandomLane());
			break;
		case 5:
			o = new Pedestrian(game, x, 0);
			break;
			}
			game.forceAddObject(o);		
	
		
	}
}
