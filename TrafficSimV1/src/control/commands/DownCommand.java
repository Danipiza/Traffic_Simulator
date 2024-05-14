package control.commands;

import control.exceptions.CommandParseException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for Down command
 * ESP: Clase para el comando Bajar
 */
public class DownCommand extends Command {

	private static final String NAME = "down";
	private static final String DETAILS = "[a]";
	private static final String SHORTCUT = "a";
	private static final String HELP = "go down";
	
	/**
	 * 
	 * ENG: Class constructor for DownComand
	 * ESP: Constructor de la clase ComandoBaja
	 */
	public DownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	/**
	 *  ENG: The player moves down one lane
	 *  ESP: El jugador baja un carril 
	 */
	@Override
	public boolean execute(Game game) {
		game.goDown();
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
