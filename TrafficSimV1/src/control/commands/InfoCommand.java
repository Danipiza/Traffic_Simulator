package control.commands;

import View.GamePrinter;
import logic.Game;

/**
 * @author DannyP39
 
 * ENG: Class for Info command
 * ESP: Clase para el comando Info 
 */
public class InfoCommand extends Command {

	private static final String NAME = "info";
	private static final String DETAILS = "[i]nfo";
	private static final String SHORTCUT = "i";
	private static final String HELP = "prints gameobject info";

	/**
	 *
	 * ENG: Class constructor for InfoCommand
	 * ESP: Constructor de la clase ComandoInfo
	 */
	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	/**
	 *  ENG: Prints the avaible objects of the game
	 *  ESP: Imprime los objetos disponibles de la partida
	 */
	@Override
	public boolean execute(Game game) {
		System.out.print("Available objects:");				
		System.out.println(GamePrinter.description(" "));
		
		return false;
	}

}