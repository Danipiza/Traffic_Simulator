package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ShootAction;
import es.ucm.tp1.supercars.logic.gameobjects.*; 

public class ShootCommand extends Command implements Buyable{

	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";
	
	private static final String FAILED_MSG = "Failed to shoot";
	
	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}
	
	// FUNCION QUE HACE QUE EL PLAYER DISPARE 
	// SOLO AFECTA A LA PARTE VISIBLE DEL TABLERO
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
		
		/* ANTIGUA VERSION
		 
			game.execute(new ShootAction());
			game.update();
		}
		return true;*/
	}

	@Override
	public int cost() {		
		return 1;
	}
	
	
	
	
	
}
