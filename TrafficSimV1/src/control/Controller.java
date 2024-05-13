package control;

import java.util.Scanner;

import View.GamePrinter;
import control.commands.Command;
import control.exceptions.GameException;
import logic.Game;


/**
 * @author DannyP39
 *
 */
public class Controller {

	private static final String PROMPT = "Command > ";
	
	private Game game;

	private Scanner scanner;

	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game=game;
		this.scanner=scanner;
		this.printer=new GamePrinter(game);
	}

	public void printGame() { System.out.println(printer); }
	
	public void printEndMessage() { System.out.println(printer.endMessage()); }
	
	
	
	/**
	 * @throws GameException
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
		
		printEndMessage();
		
		
		
	}
}
