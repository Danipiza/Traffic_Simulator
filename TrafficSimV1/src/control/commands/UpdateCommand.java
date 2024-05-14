package control.commands;

import control.exceptions.CommandParseException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for UpdateCommand
 * ESP: Clase para el comando Actualiza
 */
public class UpdateCommand extends Command {

	private static final String NAME = "update";
	private static final String DETAILS = "[n]one | []";
	private static final String SHORTCUT = "n";
	private static final String HELP = "update";

	/**
	 * 
	 *
	 * ENG: Class constructor for UpdateCommand
	 * ESP: Constructor de la clase ComandoActualiza
	 */
	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	/**
	 *  ENG: Updates the game
	 *  ESP: Actualiza la partida
	 *
	 */
	@Override
	public boolean execute(Game game) {
		game.update();			
		return true;
	}

	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if ("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;
		}
		return super.parse(commandWords);
	}
}
