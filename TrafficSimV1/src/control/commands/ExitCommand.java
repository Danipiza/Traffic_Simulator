package control.commands;

import control.exceptions.CommandParseException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for Exit command
 * ESP: Clase para el comando Salir
 */
public class ExitCommand extends Command  {

	private static final String NAME = "exit";
	private static final String DETAILS = "[e]xit";
	private static final String SHORTCUT = "e";
	private static final String HELP = "exit game";
	
	/**
	 * 
	 * ENG: Class constructor for ExitCommand
	 * ESP: Constructor de la clase ComandoSalir
	 */
	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);	
	}

	/**
	 *  ENG: Ends the execution 
	 *  ESP: Termina la ejecucion
	 */
	@Override
	public boolean execute(Game game) {
		game.setUserExit();		
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
