package es.ucm.tp1.supercars.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class GameObjectContainer {
	
	private ArrayList<GameObject> gameObjects; // ARRAYLIST
	
	private int obstacleCount = 0;
	
	private int coinsCount = 0;
	
	
	public GameObjectContainer() {
		this.gameObjects = new ArrayList<GameObject>();
		//this.obstacleCount = 0;
		//this.coinsCount = 0;		
	}
	
	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
		
		if(gameObject.toString() == "¢") coinsCount++;
		else if (gameObject.toString() == "░") obstacleCount++;
		else if (gameObject.toString() == "$") SuperCoin.Exists(true); 
		
		//System.out.println(gameObject.getX() + " " + gameObject.getY() + " " +  gameObject.toString()); // ESTO SIRVE PARA VER DONDE ESTA CADA OBJETO AL PRINCIPIO
		
	}
	
	
	public GameObject getObjectInPosition(int x, int y) {
		GameObject ret = null; 
		for(GameObject GO: gameObjects) {
			if(GO.isInPosition(x, y)) ret = GO;		 
		}
		
		return ret;
	}
	
	public String ObjectsInPosition(int x, int y) {
		String ret = ""; 
		for(GameObject GO: gameObjects) {
			if(GO.isInPosition(x, y)) ret += GO.toString() + " ";		  // ret = GO;		
		}
		
		return ret;
	}
		
	
	public boolean isPositionEmpty(int  x, int y) {
		return getObjectInPosition(x, y) == null;
	}
	
	
	
	
	public int getObstacleCount() {
		return obstacleCount;
	}
	
	public int getCoinsCount() {
		return coinsCount;
	} 	
	
	// NUEVAS FUNCIONES
	
	public void update() {
		
		
		for (int i = 0; i < gameObjects.size(); ++i) {
			gameObjects.get(i).update();
		}
	}
	
	public void clear() {	
		
		gameObjects.clear();		
		
	}
			
		
	
	
	public void clearColumn(int column) {
		for (int x = 0; x < gameObjects.size(); ++x) {
			if (gameObjects.get(x).getX() == column) { 
			gameObjects.get(x).onDelete();
			gameObjects.remove(x);  
			x--;
			}
			
		}
	}
	
	public void removeDead(GameObject GO) {
		//gameObjects.get(gameObjects.indexOf(GO)).onDelete();
		gameObjects.remove(gameObjects.indexOf(GO));
		
	}
	
	
	
	
	
	
}
