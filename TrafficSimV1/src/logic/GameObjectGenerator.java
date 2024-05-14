package logic;

import control.Level;
import logic.gameobjects.*;
import logic.Game;
import logic.actions.*;

/**
 * @author DannyP39
 
 * ENG: Class for the generation of objects in the execution
 * ESP: Clase para la generacion de objetos de la ejecucion
 */
public class GameObjectGenerator {
	

	/** 
	 * @param game
	 * @param level
	 *
	 * ENG: Method for adding objects 
	 * ESP: Funcion para añadir objetos
	 */
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
	
	
	// TODO
	public static void reset(Level level ) {
		//Obstacle.reset();
		//Coin.reset();
	}
	
	// TODO
	public static void generateRuntimeObjects(Game game) {
		//if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		//}
	}


	/** 
	 * @param game
	 * @param id
	 * @param x
	 *
	 * ENG: Method for adding advanced objects
	 * ESP: Funcion para añadir objetos avanzados
	 */
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
