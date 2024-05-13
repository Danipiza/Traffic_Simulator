package control.commands;

import logic.Game;
import logic.GameObjectGenerator;

public class CheatCommand extends Command {

	private static final String NAME = "Cheat";
	private static final String DETAILS = "Cheat [1..5]";

	// HAY QUE CAMBIARLO PORQUE SE ELEIGE UNO DE ESOS NUMEROS Y SE IMPLEMENTA UN OBJETO
	private static final String SHORTCUT = "[1..5]";	
	private static final String HELP = " Removes all elements of last visible column, and adds an random Advanced Object";
	
	private int id;
	
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	 
	
	@Override
	public boolean execute(Game game) {
		// BORRA LA ULTIMA COLUMNA
		game.clearColumn(game.getPlayerX() + game.getLevel().getVisibility() -1); 
		// CREA UN OBJEO AVANZADO
		GameObjectGenerator.forceAdvancedObject(game, id, game.getPlayerX() + game.getLevel().getVisibility() -1); 
		
		return true;
	}

	// CREO QUE ASI ESTA BIEN 
	@Override
	protected Command parse(String[] commandWords) {
		if(commandWords.length != 1) {
			System.out.format("[ERROR]: Command %s: %S%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
		}
		else {
			try {
				id = Integer.parseInt(commandWords[0]);
				if (id > 0 && id < 6) return this;					
				
			} catch (NumberFormatException e) {
					return null;
			}			
		}		
		
		return null;
	}
	
}
