package control.commands;

import control.Buyable;
import control.exceptions.CommandExecuteException;
import control.exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.actions.ShootAction;
import logic.gameobjects.*; 

/**
 * @author DannyP39
 
 * ENG: Class for the Shoot command
 * ESP: Clase para el comando Dispara
 */
public class ShootCommand extends Command implements Buyable{

	private static final String NAME = "shoot";
	private static final String DETAILS = "[s]hoot";
	private static final String SHORTCUT = "s";
	private static final String HELP = "shoot bullet";	
	private static final String FAILED_MSG = "Failed to shoot";
	
	/**
	 *
	 * ENG: Class constructor for ShootCommand
	 * ESP: Constructor de la clase ComandoDispara
	 */
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}
	
	/**
	 *  ENG: Player shoot in his actual lane, only affects the visible column
	 *  ESP: El jugador dispara en su carril actual, solo afecta a las columnas visibles
	 */
	public boolean execute(Game game) throws CommandExecuteException {
		boolean ret;
		
		try {
			ret = true;
			buy(game);
			game.execute(new ShootAction());
			game.update();
		} catch (NotEnoughCoinsException e) {
			ret = false;
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG));
		}
		
		return ret;
		
	}

	@Override
	public int cost() {		
		return 1;
	}
	
	
	
	
	
}
