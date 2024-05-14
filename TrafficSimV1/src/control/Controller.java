package control;

import java.util.Scanner;

import View.GamePrinter;
import control.commands.Command;
import control.exceptions.GameException;
import logic.Game;



/**
 * @author DannyP39
 
 * ENG: Class for controlling the program execution
 * ESP: Clase para controlar la ejecucion del programa
 */
public class Controller {

	private static final String PROMPT = "Command > ";
	
	private Game game;
	private Scanner scanner;
	private GamePrinter printer;
	
	/**
	 * @param game
	 * @param scanner
	 *
	 * ENG: Class constructor for Controller
	 * ESP: Constructor de la clase Controller
	 */
	public Controller(Game game, Scanner scanner) {
		this.game=game;
		this.scanner=scanner;
		this.printer=new GamePrinter(game);
	}

	/** 
	 *
	 * ENG: Method for printing the road (with the actual state)
	 * ESP: Funcion para imprimir la carretera (con el estado actual)
	 */
	public void printGame() { System.out.println(printer); }
	
	/** 
	 *
	 * ENG: Method for printing the end message
	 * ESP: Funcion para imprimir el mensaje de finalizacion 
	 */
	public void printEndMessage() { System.out.println(printer.endMessage()); }
	
	
	
	
	/** 
	 * @throws GameException
	 *
	 * ENG: Method for executing the program
	 * ESP: Funcion para ejecutar el programa
	 */
	public void run() throws GameException {
		boolean refreshDisplay = true;
		
		printGame();
		
		while (!game.isFinished()) {			
			refreshDisplay=false;
			System.out.println(PROMPT);
		
			String s=scanner.nextLine();
			String[] parameters=s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: "+s);
			try {
				Command command=Command.getCommand(parameters);
				refreshDisplay=command.execute(game);
			}
			catch (GameException ex) {
				System.out.format(ex.getMessage()+"[h]elp: prints commands \n\n");
			}
			
			if (refreshDisplay) printGame();
			
		}
		
		// End of the program
		printEndMessage(); 
		
	}
}
