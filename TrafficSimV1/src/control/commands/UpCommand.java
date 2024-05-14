package control.commands;

import control.exceptions.CommandParseException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for Up command
 * ESP: Clase para el comando Sube
 */
public class UpCommand extends Command {

	private static final String NAME = "up";
	private static final String DETAILS = "[q]";
	private static final String SHORTCUT = "q";
	private static final String HELP = "go up";
	
	/**
	 * 
	 * ENG: Class constructor UpCommand
	 * ESP: Constructor de la clase comando Sube
	 */
	public UpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	/**
	 *  ENG: The player moves up one lane
	 *  ESP: El jugador sube un carril
	 */
	@Override
	public boolean execute(Game game) {
		game.goUp();
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if("".equalsIgnoreCase(commandWords[0])) commandWords[0]=SHORTCUT;
		
		return super.parse(commandWords);
	}

}
