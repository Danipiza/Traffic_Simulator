package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class DownCommand extends Command {

	private static final String NAME = "down";

	private static final String DETAILS = "[a]";

	private static final String SHORTCUT = "a";

	private static final String HELP = "go down";
	
	public DownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

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
