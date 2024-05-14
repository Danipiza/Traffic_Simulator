package control.commands;

import control.Buyable;
import control.exceptions.CommandExecuteException;
import control.exceptions.CommandParseException;
import control.exceptions.InvalidPositionException;
import control.exceptions.NotEnoughCoinsException;
import logic.Game;
import logic.gameobjects.*;

/**
 * @author DannyP39
 
 * ENG: Class for Grenade command
 * ESP: Clase para el comando Granada
 */
public class GrenadeCommand extends Command implements Buyable {

	private static final String NAME = "Grenade";
	private static final String DETAILS = "[g]renade <x> <y>";
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";	
	private static final String FAILED_MSG = "Failed to add a grenade"; // Money exception	
	private static final String COORDINATES_NUMBER_ERROR = "Coordinates must be on the road"; // Out of road exception
	
	private int x, y;
	
	/**
	 * 
	 * ENG: Class constructor for GrenadeCommand
	 * ESP: Constructor de la clase ComandoGranada
	 */
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

		
	
	/**
	 *  ENG: Throws the grenade to the desire coordinates
	 *  ESP: Lanza la granada a la coordenada deseada
	 */
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean ret = false;
		
		try  {
			ret = true;
			game.forceAddGrenade(new Grenade(game, game.getPlayerX() + x, y));
			buy(game);
			game.update();
		} catch (InvalidPositionException e) {
			ret = false;
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", COORDINATES_NUMBER_ERROR));
		} catch (NotEnoughCoinsException e2) {
			ret = false;
			System.out.println(e2.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG));
			
		}
		
		
		return ret;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException{
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %S%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
				//System.out.format("[ERROR]: Command %s: %S%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
			} else {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
				return this;
			}
		}
		return null;
	}



	@Override
	public int cost() {
		return 3;
	}

}
