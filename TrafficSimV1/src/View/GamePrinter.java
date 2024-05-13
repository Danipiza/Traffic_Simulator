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



public class GamePrinter {

	private static final String SPACE = " ";
	private static final String ROAD_BORDER_PATTERN = "═"; // = ASCII
	private static final String LANE_DELIMITER_PATTERN = "─"; // - ASCII

	private static final int CELL_SIZE = 7;
	private static final int MARGIN_SIZE = 2;

	private static final String CRASH_MSG = String.format("Player crashed!%n");
	private static final String WIN_MSG = String.format("Player wins!%n");
	private static final String DO_EXIT_MSG = "Player leaves the game";
	private static final String GAME_OVER_MSG = "[GAME OVER] ";
	
	
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

	private Game game;		
	
	private String indentedRoadBorder;
	private String indentedLanesSeparator;
	private String margin;
	
	private static boolean advObjs;
	
	
	private Level currentLevel;	
	
	@SuppressWarnings("static-access")
	public GamePrinter(Game game) { 
	    this.game = game; 	    
	    this.currentLevel = game.getLevel(); 
	    this.advObjs=game.level.hasAdvancedObjects();
	    whenLevelChanged(); 
	} 

	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		/* @formatter:off */
		buffer
		.append(DISTANCE_MSG).append(game.distanceToGoal()).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_OBSTACLES_MSG).append(game.getObstacleCount()).append(StringUtils.LINE_SEPARATOR)
		.append(TOTAL_COINS_MSG).append(game.getCoinsCount());
		/* @formatter:on */
		
		
		if (game.getLevel().hasAdvancedObjects()) buffer.append("\n").append(SUPERCOIN_PRESENT);

		if (!game.isTestMode()) {
			buffer
			.append(StringUtils.LINE_SEPARATOR)
			.append(ELAPSED_TIME_MSG).append(formatTime(game.elapsedTime()));	
		}

		return buffer.toString();
	}

	@Override
	public String toString() {
		Level actualLevel = game.getLevel(); 
	    if (this.currentLevel != actualLevel) { 
	      this.currentLevel = actualLevel; 
	      whenLevelChanged(); 
	    } 
		
		StringBuilder str = new StringBuilder();

		// Game Status
		str.append(getInfo());

		// Paint game
		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < game.getVisibility(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE)).append(verticalDelimiter);
			}
			if (y<game.getRoadWidth()-1) str.append(this.indentedLanesSeparator);
			
		}
		str.append(this.indentedRoadBorder);
		
		return str.toString();
	}

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
	
	// METODO QUE ENCAPSULA LA CREACION DE LOS LIMITES DE LA CARRETERA 
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
