package logic.actions;

import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Interface for the actions
 * ESP: Interfaz para las acciones
 */
public interface InstantAction {
	/** 
	 * @param game
	 *
	 * ENG: Method for executing the action
	 * ESP: Funcion para ejecutar la accion
	 */
	void execute(Game game);
}
