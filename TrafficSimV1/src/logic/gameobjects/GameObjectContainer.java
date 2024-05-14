package logic.gameobjects;

import java.util.ArrayList;
import java.util.List;
import logic.gameobjects.GameObject;

/**
 * @author DannyP39
 
 * ENG: Class with the game objects of the road
 * ESP: Clase con los objetos de la carretera
 */
public class GameObjectContainer {
	
	private ArrayList<GameObject> gameObjects; // ARRAYLIST
	
	private int obstacleCount = 0;	
	private int coinsCount = 0;
	
	
	/**
	 * 
	 * ENG: Class constructor GameObjectContainer
	 * ESP: Constructor de la clase GameObjectContainer
	 */
	public GameObjectContainer() {
		this.gameObjects = new ArrayList<GameObject>();	
	}
	
	/** 
	 * @param gameObject
	 *
	 * ENG: Method for adding an object to the road
	 * ESP: Funcion para añadir un objeto a la carretera
	 */
	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
		
		if(gameObject.toString() == "¢") coinsCount++;
		else if (gameObject.toString() == "░") obstacleCount++;
		else if (gameObject.toString() == "$") SuperCoin.Exists(true);	
	}
	
	
	/** 
	 * @param x	int	(coordX)
	 * @param y	int	(coordY)
	 * @return 	GameObject
	 *
	 * ENG: Method for getting an objet from the road in an expecific position
	 * ESP: Funcion para obtener un objeto en una posicion de la carretera.  
	 */
	public GameObject getObjectInPosition(int x, int y) {
		GameObject ret = null; 
		for(GameObject GO: gameObjects) {
			if(GO.isInPosition(x, y)) ret = GO;		 
		}
		
		return ret;
	}
	
		
	
	/** 
	 * @param x	int	(coordX)
	 * @param y	int	(coordY)
	 * @return	boolean
	 *
	 * ENG: Method for checking if a position in the road es empty
	 * ESP: Funcion para comprobar si una posicion de la carretera esta vacia
	 */
	public boolean isPositionEmpty(int  x, int y) {
		return getObjectInPosition(x, y) == null;
	}	
	
		
	/** 
	 * 
	 * ENG: Method for updating the objects in the road. Calls each respective update functions
	 * ESP: Funcion para actualizar los objetos de la carretera. Llama a sus funciones de actualizar
	 */
	public void update() {				
		for (int i = 0; i < gameObjects.size(); ++i) {
			gameObjects.get(i).update();
		}
	}
	
	
	/** 
	 * 
	 *
	 * ENG: Method for removing all the objects from the road
	 * ESP: Funcion para eliminar todos los objetos de la carretera
	 */
	public void clear() {		
		gameObjects.clear();				
	}
		
	
	/** 
	 * @param column
	 *
	 * ENG: Method for removing all the objects in a column
	 * ESP: Funcion para eliminar los objetos de una columna
	 */
	public void clearColumn(int column) {
		for (int x = 0; x < gameObjects.size(); ++x) {
			if (gameObjects.get(x).getX() == column) { 
			gameObjects.get(x).onDelete();
			gameObjects.remove(x);  
			x--;
			}
			
		}
	}
	
	/** 
	 * @param GO
	 *
	 * ENG: Method for removing the dead objects
	 * ESP: Funcion para eliminar los objetos que han "muerto"
	 */
	public void removeDead(GameObject GO) {
		gameObjects.remove(gameObjects.indexOf(GO));
		
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	public int getObstacleCount() 	{ return obstacleCount; }	// Obstacle count
	public int getCoinsCount() 		{ return coinsCount; } 		// coins count
	
	
	
}
