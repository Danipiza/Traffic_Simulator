package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class UpCommand extends Command {

	private static final String NAME = "up";

	private static final String DETAILS = "[q]";

	private static final String SHORTCUT = "q";

	private static final String HELP = "go up";
	
	public UpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	@Override
	public boolean execute(Game game) {
		game.goUp();
		
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
