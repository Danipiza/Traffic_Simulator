package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import simulator.control.Controller;
import simulator.factories.*;
import simulator.misc.Pair;
import simulator.model.*;
import simulator.view.MainWindow;


public class Main {

	private final static Integer _timeLimitDefaultValue = 10;
	
	private static String _inFile = null;
	
	private static String _outFile = null;
	
	private static Factory<Event> _eventsFactory = null;
	
	private static Integer ticks;
	
	private static String _mode = "gui";
	
	
	private static Pair<String, Integer> _loc = null;
	
	private static Integer n;
	
	private static int numVehicles;

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseModeOption(line); // NUEVA LLAMADA			
			parseInFileOption(line);			
			parseOutFileOption(line);
			parseTicks(line);
			parseLocation(line);
			
			

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		
		//TODO En el test esta puesta una opcion 
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Mode of the simulation").build());
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg().desc("Ticks to the simulator’s main loop").build());
		cmdLineOptions.addOption(Option.builder("l").longOpt("loc").hasArg().desc("Road and a mark point").build());
		
		return cmdLineOptions;
	}
	
	// NUEVO METODO
	private static void parseModeOption(CommandLine line) throws ParseException {
		_mode = line.getOptionValue("m");
		if (_mode == null) {
			_mode = "gui";
			// AHORA NO HAY EXCEPCION 
			//throw new ParseException("The mode is missing");
		}
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null && _mode.equals("console")) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}
	
	private static void parseTicks(CommandLine line) {
		
		if (line.hasOption("t")) {
			ticks = Integer.parseInt(line.getOptionValue("t"));
		}
	}
	
	
	
	private static void initFactories() {

		List<Builder<LightSwitchingStrategy>> lsbs = new ArrayList<>();
		lsbs.add( new RoundRobinStrategyBuilder() );
		lsbs.add( new MostCrowdedStrategyBuilder() );
		Factory<LightSwitchingStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);
		
		List<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
		dqbs.add( new MoveFirstStrategyBuilder() );
		dqbs.add( new MoveAllStrategyBuilder() );
		Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs);
		
		List<Builder<Event>> ebs = new ArrayList<>();
		ebs.add(new NewJunctionEventBuilder(lssFactory, dqsFactory));
		ebs.add(new NewCityRoadEventBuilder());
		ebs.add(new NewInterCityRoadEventBuilder());
		ebs.add(new NewVehicleEventBuilder());
		ebs.add(new SetWeatherEventBuilder());
		ebs.add(new SetContClassEventBuilder());
		ebs.add(new SetStrategiesEventBuilder(lssFactory, dqsFactory));
		
		_eventsFactory = new BuilderBasedFactory<>(ebs);

	}

	private static void startBatchMode() throws IOException {
		
		OutputStream out;
		InputStream in = new FileInputStream(new File(_inFile));
		TrafficSimulator traff_sim = new TrafficSimulator();
		Controller controller = new Controller(traff_sim, _eventsFactory);
		
		if (_outFile == null) {
			out = System.out;
		} else	{
			out = new FileOutputStream(new File(_outFile));
		}
		
		controller.loadEvents(in);
		controller.run(ticks, out);
		
		//imprimeN(n);
		for (Vehicle v : traff_sim.getRoadMap().getVehicle()) {			
			Object src = traff_sim.getRoadMap().getRoad(_loc.getFirst()).getSrc();					
			
			for(int i = 0; i < v.getItinerary().size(); i++) {				
				if(v.getItinerary().get(i).equals(src) && v.getLocation() >= _loc.getSecond()) {
					numVehicles++;
				}
			}							
			
		}
		
		System.out.println("La localización " + _loc.getSecond() + " de la carretera "
				+ _loc.getFirst() +" ha sido cruzada " + numVehicles + " veces");
		
		in.close();
		out.flush();
		//out.close();
	}
	
	public static void imprimeN(int n) {
		
	}
	
	
	private static void parseLocation(CommandLine line) throws ParseException {
		String l = line.getOptionValue("l");
		
		if (l != null) {
			try {
				int i = l.lastIndexOf(':');
				_loc = new Pair<>(l.substring(0, i), Integer.parseInt(l.substring(i + 1)));
			} catch (Exception e) {
				throw new ParseException("Invalid location: " + l);
			}
		}
	}

	private static void start(String[] args) throws IOException {				
		ticks = _timeLimitDefaultValue;
					
		initFactories();
		parseArgs(args);		
		if(_mode.equals("gui")) startGUIMode();
		else if(_mode.equals("console")) {					
			startBatchMode();
		}
		
		
	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help
	
	// NUEVOS EJEMPLOS
	// CONSOLE opcional o y t
	// -m console -i resources/examples/ex1.json (-o resources/tmp/ex1.out.json -t 300)
	// GUI SE IGNORAN o y t , i es opcional
	// -m gui (-i resources/examples/ex1.json)
	
		
	public static void startGUIMode() throws IOException {
		
		TrafficSimulator traff_sim = new TrafficSimulator(); // CREA EL SIMULADOR
		Controller ctrl = new Controller(traff_sim, _eventsFactory); // CREA EL CONTROLADOR
		if(_inFile != null) {
			InputStream in = new FileInputStream(new File(_inFile));
			ctrl.loadEvents(in); // CARGA LOS EVENTOS SI SE PROPORCIONAN
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(ctrl);
			}
		});
	}
	
	
	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
