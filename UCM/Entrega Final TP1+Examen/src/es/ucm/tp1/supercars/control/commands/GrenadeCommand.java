package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.*;

public class GrenadeCommand extends Command implements Buyable {

	private static final String NAME = "Grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String FAILED_MSG = "Failed to add a grenade"; // ERROR DE EXCEPCION DINERO
	
	private static final String COORDINATES_NUMBER_ERROR = "Coordinates must be on the road"; // ERROR DE FUERA DEL TABLERO
	
	private int x, y;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

		
	
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
