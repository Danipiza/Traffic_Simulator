package control.commands;

import control.exceptions.CommandParseException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for Test command
 * ESP: Clase para el comando Prueba
 */
public class TestCommand extends Command {
	
	private static final String NAME = "test";
	private static final String DETAILS = "[t]est";
	private static final String SHORTCUT = "t";
	private static final String HELP = "test mode";

	/**
	 * 
	 * ENG: Class constructor TestCommand
	 * ESP: Constructor de la clase comando Prueba
	 */
	public TestCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	/**
	 *  ENG: Toggle the test mode
	 *  ESP: Activa el modo test
	 */
	@Override
	public boolean execute(Game game) {
		game.toggleTest();
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if("".equalsIgnoreCase(commandWords[0])) commandWords[0] = SHORTCUT;
		
		return super.parse(commandWords);
	}

}
