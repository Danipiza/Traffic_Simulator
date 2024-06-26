package main;

import java.util.Scanner;
									
import control.Controller;	
import control.Level;		
import control.exceptions.GameException;
import logic.Game;			


/**
 * @author DannyP39
 
 * ENG: Class for executing the program
 * ESP: Clase para ejecutar el programa
 */
public class SuperCars {
	
	/**
	 *  ENG: Init messages
	 *  ESP: Mensajes de inicializacion
	 */
	private static final String VERSION = "1.0";
	private static final String USAGE_MSG = "Usage: Super cars <level> [<seed>]";
	private static final String WELCOME_MSG = String.format("Super cars %s\n\n", VERSION);
	private static final String LEVEL_INFO_MSG = "Level must be one of: " + Level.all(", ");
	private static final String SEED_IS_NUMBER_MSNG = "the seed must be a number";
	private static final String SEED_INFO_MSG = "Random generator initialized with seed: ";
	
	
	
	/**
	 * 
	 * ENG: Method for printing the usage mode
	 * ESP: Funcion para imprimir el modo de uso
	 */
	private static void usage() {
		System.out.println(USAGE_MSG);
		System.out.println("\t<level>: " + Level.all(", "));
		System.out.println("\t<seed>: " + SEED_IS_NUMBER_MSNG);
	}
	
	/**
	 * @param args
	 * @throws GameException
	 * 
	 * ENG: Main method 
	 * ESP: Funcion main 
	 */
	public static void main(String[] args) throws GameException {
		if (args.length<1 || args.length>2) {
			usage();
			return;
		} 
		
		Level level = Level.valueOfIgnoreCase(args[0]);
		if (level == null) {
			System.out.println(LEVEL_INFO_MSG);
			usage();
			return;
		}
		
		Long seed;
		try {
			if (args.length==2) seed=Long.parseLong(args[1]);
			else seed = System.currentTimeMillis()%1000;			

			System.out.print(WELCOME_MSG);
			System.out.println("Level: " + level.name());
			System.out.println(SEED_INFO_MSG + seed);

			Game game = new Game(seed, level); 			
			if(Level.TEST.equals(level)) game.toggleTest(); 
			
			Controller controller = new Controller(game, new Scanner(System.in));
			controller.run();
		} catch (NumberFormatException nfe) {
			System.out.println(SEED_IS_NUMBER_MSNG);
			usage();
		}
		
		
	}

}

