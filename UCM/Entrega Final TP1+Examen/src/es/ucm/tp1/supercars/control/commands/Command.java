package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.GameException;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";

	/* @formatter:off */
	protected static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new UpCommand(), 
		new DownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),		
		new ClearCommand(),
		new CheatCommand(),
	};
	/* @formatter:on */

	// ASI ESTABA ANTES 	public static Command getCommand(String[] commandWords)
	public static Command getCommand(String[] commandWords) throws GameException {
	//public static Command getCommand(String[] commandWords) {
		Command command = null;
		
		for (Command C: AVAILABLE_COMMANDS) {
			command = C.parse(commandWords);
			if(command != null) return command;			
		}
		if (command == null)
			throw new GameException(String.format("[ERROR]: %s%n%n", UNKNOWN_COMMAND_MSG));
		
		return command;
	}

	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) throws CommandParseException{
		Command ret = null;
		if(matchCommandName(words[0])) {
			if (words.length != 1) {			
				throw new CommandParseException(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG));
			} else ret = this;
		} 
		
		return ret;
	}
	/* ANTES
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", name, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		}
		return null;
	}*/
	
	protected String getDetails() {		
		return this.details;
	}
	
	protected String getHelp() {		
		return this.help;
	}

	// NUEVAS FUNCIONES
	protected String getName() {
		return this.name;
	}
	
	protected String getShortcut() {
		return this.shortcut;
	}
	
	
	

}
