package View;

import control.Level;
import logic.Game;
import logic.gameobjects.Coin;
import logic.gameobjects.Grenade;
import logic.gameobjects.Obstacle;
import logic.gameobjects.Pedestrian;
import logic.gameobjects.Truck;
import logic.gameobjects.Turbo;
import logic.gameobjects.Wall;
import utils.StringUtils;


/**
 * @author DannyP39
 
 * ENG: Class for printing the program
 * ESP: Clase para imprimir el programa
 */
public class GamePrinter {

	/**
	 *  ENG: Pretty printing
	 *  ESP: Impresion bonita
	 */
	private static final String SPACE = " ";
	private static final String ROAD_BORDER_PATTERN = "═"; // = ASCII
	private static final String LANE_DELIMITER_PATTERN = "─"; // - ASCII

	private static final int CELL_SIZE = 7;
	private static final int MARGIN_SIZE = 2;	
	private String indentedRoadBorder;
	private String indentedLanesSeparator;
	private String margin;

	
	
	/**
	 *  ENG: Finalization messages
	 *  ESP: Mensajes de finalizacion
	 */
	private static final String CRASH_MSG = String.format("Player crashed!%n");
	private static final String WIN_MSG = String.format("Player wins!%n");
	private static final String DO_EXIT_MSG = "Player leaves the game";
	private static final String GAME_OVER_MSG = "[GAME OVER] ";	
	
	/**
	 *  ENG: Information messages
	 *  ESP: Mensajes de informacion
	 */
	private static final String DISTANCE_MSG = "Distance: ";
	private static final String COINS_MSG = "Coins: ";
	private static final String CYCLE_MSG = "Cycle: ";
	private static final String TOTAL_OBSTACLES_MSG = "Total obstacles: ";
	private static final String TOTAL_COINS_MSG = "Total coins: ";
	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";
	private static final String NEW_RECORD_MSG = "New record!: ";
	private static final String RECORD_MSG = "Record: ";	
	private static final String SUPERCOIN_PRESENT = "Supercoin is present";
	

	private static String formatTime(long t) {  return String.format("%.2f s", t/1000.0); }

	
	/**
	 *  ENG: Class variables
	 *  ESP: Variables de la clase 
	 */
	private Game game;		
	private Level currentLevel;	
	private static boolean advObjs;
	
	
		
		
	/**
	 * @param game
	 *
	 * ENG: Class constructor
	 * ESP: Constructor de la clase
	 */
	@SuppressWarnings("static-access")
	public GamePrinter(Game game) { 
	    this.game=game; 	    
	    this.currentLevel=game.getLevel(); 
	    this.advObjs=game.level.hasAdvancedObjects();
	    
	    whenLevelChanged(); 
	} 

	/** 
	 * @return String
	 *
	 * ENG: Method for printing the state's info 
	 * ESP: Funcion para imprimir la informacion del estado 
	 */
	protected String getInfo() {

		// String builder to optimize the concatenation of Strings
		StringBuilder buffer=new StringBuilder();
		
		buffer
		.append(DISTANCE_MSG).append(game.distanceToGoal()).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_OBSTACLES_MSG).append(game.getObstacleCount()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_COINS_MSG).append(game.getCoinsCount());		
		
		// SuperCoin for advanced execution
		if (game.getLevel().hasAdvancedObjects()) buffer.append("\n").append(SUPERCOIN_PRESENT);
		
		// Test execution
		if (!game.isTestMode()) {
			buffer
			.append(StringUtils.LINE_SEPARATOR)
			.append(ELAPSED_TIME_MSG).append(formatTime(game.elapsedTime()));	
		}

		return buffer.toString();
	}

	/**
	 * ENG: toString function to print the road
	 * ESP: funcion toString para imprimir la carretera 
	 */
	@Override
	public String toString() {
		Level actualLevel = game.getLevel(); 
	    if (this.currentLevel != actualLevel) { 
	    	this.currentLevel = actualLevel; 
	    	whenLevelChanged(); 
	    } 
		
	    // String builder to optimize the concatenation of Strings
		StringBuilder str = new StringBuilder();

		// Game Status
		str.append(getInfo());

		// Paint game
		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;
		
		// Print the visible road, with all the objects
		for (int i=0;i<game.getRoadWidth();i++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int j=0;j<game.getVisibility();j++) {
				str.append(StringUtils.centre(game.positionToString(j, i), CELL_SIZE)).append(verticalDelimiter);
			}
			if (i<game.getRoadWidth()-1) str.append(this.indentedLanesSeparator);
			
		}
		str.append(this.indentedRoadBorder);
		
		return str.toString();
	}

	/** 
	 * @return String
	 *
	 * ENG: Method for printing the end message (crash or success)
	 * ESP: Funcion para imprimir el mensaje de finalizacion (choque o exito)
	 */
	public String endMessage() {
		StringBuilder buffer = new StringBuilder(GAME_OVER_MSG);
		if (game.isUserExit()) return buffer.append(DO_EXIT_MSG).toString();		

		if (game.hasArrived()) {
			buffer.append(WIN_MSG);
			if (!game.isTestMode()) {
				if (game.isNewRecord()) buffer.append(NEW_RECORD_MSG).append(formatTime(game.elapsedTime()));
				else buffer.append(RECORD_MSG).append(formatTime(game.getRecord()));				
			}
		} 
		else buffer.append(CRASH_MSG);
		

		return buffer.toString();
	}
	
	/** 
	 * @param string
	 * @return String
	 *
	 * ENG: Method for printing the info command
	 * ESP: Funcion para imprimir el comando info
	 */
	public static String description(String string) {		
		StringBuilder buffer = new StringBuilder("\n" + " [Car] The racing car");
		
		/* @formatter:off */
		buffer
		.append(StringUtils.LINE_SEPARATOR).append(" [Coin] " ).append(Coin.INFO)
		.append(StringUtils.LINE_SEPARATOR).append(" [Obstacle] " ).append(Obstacle.INFO);
		/* @formatter:on */
			
		if (advObjs) {
			buffer
			.append(StringUtils.LINE_SEPARATOR).append(" [Grenade] " ).append(Grenade.INFO)
			.append(StringUtils.LINE_SEPARATOR).append(" [Wall] " ).append(Wall.INFO)
			.append(StringUtils.LINE_SEPARATOR).append(" [Turbo] " ).append(Turbo.INFO)
			.append(StringUtils.LINE_SEPARATOR).append(" [SuperCoin] " ).append("The player receive 1000 coins, there is only 1 SuperCoin")
			.append(StringUtils.LINE_SEPARATOR).append(" [Truck] " ).append(Truck.INFO)
			.append(StringUtils.LINE_SEPARATOR).append(" [Pedestrian] " ).append(Pedestrian.INFO);			
		}

		return buffer.toString();
	}
	
	 
	/** 	
	 * ENG: Method that encapsulating the road's limits creation
	 * ESP: Funcion que encapsula la creacion de limites de la carretera 
	 */
	private void whenLevelChanged() {     
	    margin = StringUtils.repeat(SPACE, MARGIN_SIZE); 
	    String roadBorder = ROAD_BORDER_PATTERN 
	        + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * game.getVisibility()); 
	    indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder); 
	    String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE); 
	    String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, game.getVisibility()-1) 
	        + laneDelimiter + SPACE; 
	    indentedLanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator); 
	}
	
}
