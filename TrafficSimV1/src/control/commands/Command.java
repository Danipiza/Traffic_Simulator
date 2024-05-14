package control.commands;

import control.exceptions.CommandExecuteException;
import control.exceptions.CommandParseException;
import control.exceptions.GameException;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Father class for all the commands
 * ESP: Clase padre para todos los comandos
 */
public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	private final String name;
	private final String shortcut;
	private final String details;
	private final String help;

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

	
	

	/**
	 * @param name
	 * @param shortcut
	 * @param details
	 * @param help
	 *
	 * ENG: Class constructor for Command
	 * ESP: Constructor de la clase Commando
	 */
	public Command(String name, String shortcut, String details, String help) {
		this.name=name;
		this.shortcut=shortcut;
		this.details=details;
		this.help=help;
	}

	
	/** 
	 * @param commandWords
	 * @return
	 * @throws GameException
	 *
	 * ENG: Method for return the Command Class, by receiving a string from the terminal
	 * ESP: Funcion para devolver la Clase Comando, recibiendo un string por la terminal
	 */
	public static Command getCommand(String[] commandWords) throws GameException {	
		Command command=null;
		
		for (Command C: AVAILABLE_COMMANDS) {
			command=C.parse(commandWords);
			if(command!=null) return command;			
		}
		
		throw new GameException(String.format("[ERROR]: %s%n%n", UNKNOWN_COMMAND_MSG));			
	}
	
	
	/** 
	 * @param words
	 * @return
	 * @throws CommandParseException
	 *
	 * ENG: Method for checking if match with a command
	 * ESP: Funcion para comprobar si coincide con un comando
	 */
	protected Command parse(String[] words) throws CommandParseException{
		Command ret=null;
		if(matchCommandName(words[0])) {
			if (words.length != 1) {			
				throw new CommandParseException(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG));
			} 
			else ret = this;
		} 
		
		return ret;
	}

	/** 
	 * @param name
	 * @return
	 *
	 * ENG: Method for checking if the string match with an avaible command
	 * ESP: Funcion para comprobar si coincide el string coincide con algun comando disponible
	 */
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	
	
	
	// --- INHERITANCE ---------------------------------------------------------------------------------------
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	
	// -------------------------------------------------------------------------------------------------------
	// --- GETTERS -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	protected String getDetails() {	return this.details; }	
	protected String getHelp() { return this.help; }
	protected String getName() { return this.name; }	
	protected String getShortcut() { return this.shortcut; }
	
	
	

}
