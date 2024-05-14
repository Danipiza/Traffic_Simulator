package control.commands;

import logic.Game;
import logic.gameobjects.Player;
import control.Level;
import control.exceptions.CommandParseException;

/**
 * @author DannyP39
 
 * ENG: Class for Clear command
 * ESP: Clase para el comando Clear
 */
public class ClearCommand extends Command {

	private static final String NAME = "clear";
	private static final String DETAILS = "Clear [0]";
	private static final String SHORTCUT = "0";
	private static final String HELP = "Clears the road";
	
	/**
	 * 
	 * ENG: Class constructor for Clear
	 * ESP: Constructor de la clase Clear
	 */
	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	// EXECUTE: ELIMINA TODOS LOS OBJETOS MENOS EL COCHE
	/**
	 *  ENG: Deletes all the objects from the entire road (doesnt affects the player)
	 *  ESP: Elimina todos los objetos de la carretera (no afecta al jugador)
	 */
	@Override
	public boolean execute(Game game) {
		game.clear();
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;			
		}
		
		return super.parse(commandWords);
	}

}
