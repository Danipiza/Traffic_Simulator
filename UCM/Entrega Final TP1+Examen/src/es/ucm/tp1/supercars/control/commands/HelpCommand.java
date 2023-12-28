package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;

public class HelpCommand extends Command {

	private static final String NAME = "help";

	private static final String DETAILS = "[h]elp";

	private static final String SHORTCUT = "h";

	private static final String HELP = "show this help";

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		StringBuilder buffer = new StringBuilder("Available commands:");

		buffer
		.append(StringUtils.LINE_SEPARATOR).append(DETAILS + ": ").append(HELP)
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[1].getDetails() + ": ").append(AVAILABLE_COMMANDS[1].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[2].getDetails() + ": ").append(AVAILABLE_COMMANDS[2].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[3].getDetails() + ": ").append(AVAILABLE_COMMANDS[3].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[4].getDetails() + ": ").append(AVAILABLE_COMMANDS[4].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[5].getDetails() + ": ").append(AVAILABLE_COMMANDS[5].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[6].getDetails() + ": ").append(AVAILABLE_COMMANDS[6].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[7].getDetails() + ": ").append(AVAILABLE_COMMANDS[7].getHelp());
		if (game.getLevel().hasAdvancedObjects()) {
			buffer
			.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[8].getDetails() + ": ").append(AVAILABLE_COMMANDS[8].getHelp())
			.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[9].getDetails() + ": ").append(AVAILABLE_COMMANDS[9].getHelp())
			.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[10].getDetails() + ": ").append(AVAILABLE_COMMANDS[10].getHelp());
		}
		buffer
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[11].getDetails() + ": ").append(AVAILABLE_COMMANDS[11].getHelp())
		.append(StringUtils.LINE_SEPARATOR).append(AVAILABLE_COMMANDS[12].getDetails() + ": ").append(AVAILABLE_COMMANDS[12].getHelp());

		
		System.out.println(buffer.toString());

		return false;
	}

}
