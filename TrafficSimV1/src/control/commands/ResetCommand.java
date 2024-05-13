package control.commands;

import java.util.Scanner;

import control.Level;
import control.exceptions.CommandParseException;
import logic.Game;

public class ResetCommand extends Command {

	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private static Long seed;
	
	private static String levelString;
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);	
	}


	@Override
	public boolean execute(Game game) {
		game.clear();
		Level level = Level.valueOfIgnoreCase(levelString);
		game.reset(level, seed);
		
		
		
		/*ANTERIOR RESET
		Scanner scanner = new Scanner(System.in);
		
		
		String l = scanner.nextLine();
		Level level = Level.valueOfIgnoreCase(l);
		
		String s = scanner.nextLine();
		Long seed = Long.parseLong(s);
		
		game.reset(level, seed);*/
		
		
		return true;
	}

	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		/*Level levelName = Level.valueOfIgnoreCase(commandWords[1]);
		
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 3) {
				System.out.println("ERROR, COMMAND NOT ACCURRATE");
				//return null;
			}
			else if (levelName == null) {
				System.out.println("ERROR, ONLY TEST, EASY, HARD OR ADVANCED");
				//return null;
			}
			else {
				try {	
					levelString = commandWords[1];
					seed = Long.parseLong(commandWords[1]);
				} catch (Exception e) {
					System.out.println("ERROR, INCORRECT FORMAT");
					//return null;
				}
			}
			//return null;
		}
		
		return null;*/
		
		// ANTIGUO
		if("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;			
		}
		
		return super.parse(commandWords);
	}
}
