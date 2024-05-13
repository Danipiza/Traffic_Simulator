package control.commands;

import logic.Game;
import logic.gameobjects.Player;
import control.Level;
import control.exceptions.CommandParseException;

public class ClearCommand extends Command {

	private static final String NAME = "clear";

	private static final String DETAILS = "Clear [0]";

	private static final String SHORTCUT = "0";

	private static final String HELP = "Clears the road";
	
	private Player player;
	
	private Level level;
	
	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	// EXECUTE: ELIMINA TODOS LOS OBJETOS MENOS EL COCHE
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
