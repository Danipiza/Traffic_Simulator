package control.commands;

import logic.Game;
import logic.actions.WaveAction;
import logic.gameobjects.Player;
import control.Buyable;
import control.Level;
import control.exceptions.CommandExecuteException;
import control.exceptions.CommandParseException;
import control.exceptions.NotEnoughCoinsException;

/**
 * @author DannyP39
 
 * ENG: Class for Wave command
 * ESP: Clase para comando Ola
 */
public class WaveCommand extends Command implements Buyable{

	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave";
	private static final String SHORTCUT = "w";
	private static final String HELP = "do wave";	
	private static final String FAILED_MSG = "Failed to do a wave";	
	
	
	/**
	 * 
	 * ENG: Class constructor for Wave
	 * ESP: Constructor de la clase Ola
	 */
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	// -------------------------------------------------------------------------------------------------------
	// --- COMMAND -------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	
	
	
	/**
	 *  ENG: Moves one column to the right all the visible game objects from the road
	 *  ESP: Mueve una columna a la derecha todos los objetos visibles de la carretera
	 */
	@Override
	public boolean execute(Game game) throws CommandExecuteException { 
		boolean ret=false;
		
		try {
			ret=true;
			buy(game);
			game.execute(new WaveAction());
			
		} catch (NotEnoughCoinsException e) {
			ret = false;
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG));
		}
		return ret;
	}
	
		
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;			
		}
		
		return super.parse(commandWords);
	}

	// --- BUYABLE -------------------------------------------------------------------------------------------
	
	@Override
	public int cost() {
		return 5;
	}

}
