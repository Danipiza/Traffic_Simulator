package control.commands;

import logic.Game;
import logic.GameObjectGenerator;

/**
 * @author DannyP39
 
 * ENG: Class for Cheat command
 * ESP: Clase para el comando Trampa
 */
public class CheatCommand extends Command {

	private static final String NAME = "Cheat";
	private static final String DETAILS = "Cheat [1..5]";
	private static final String SHORTCUT = "[1..5]";	
	private static final String HELP = " Removes all elements of last visible column, and adds an random Advanced Object";
	
	private int id;
	
	
	/**
	 * 
	 * ENG: Class constructor for CheatCommand
	 * ESP: Constructor de la clase CheatCommand
	 */
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	 
	
	/**
	 *  ENG: Clear the last visible column and generate a random advanced object
	 *  ESP: Limpia la ultima columna visible y genera un objeto avanzado aleatorio
	 */
	@Override
	public boolean execute(Game game) {
		// Clear 
		game.clearColumn(game.getPlayerX() + game.getLevel().getVisibility() -1); 
		
		// Generates
		GameObjectGenerator.forceAdvancedObject(game, id, game.getPlayerX() + game.getLevel().getVisibility() -1); 
		
		return true;
	}
 
	@Override
	protected Command parse(String[] commandWords) {
		if(commandWords.length != 1) {
			System.out.format("[ERROR]: Command %s: %S%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
		}
		
		try {
			id = Integer.parseInt(commandWords[0]);
			if (id > 0 && id < 6) return this;							
		} 
		catch (NumberFormatException e) { return null; }			
			
		
		return null;
	}
	
}
