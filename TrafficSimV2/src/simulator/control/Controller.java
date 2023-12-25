package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimObserver;
import simulator.model.TrafficSimulator;

public class Controller {
	
	private TrafficSimulator traffic_sim;
	private Factory<Event> events_fact;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory)	{
	
		if (sim == null) {
			throw new IllegalArgumentException("Traffic simulator no puede estar vacio.");
		}
		if (eventsFactory == null) {
			throw new IllegalArgumentException("EventsFactory no puede estar vacio.");
		}
	
		this.traffic_sim = sim;
		this.events_fact = eventsFactory;
	}
	
	public void loadEvents(InputStream in) {
		
		JSONObject jo1 = new JSONObject(new JSONTokener(in));
		JSONArray aux = jo1.getJSONArray("events");
		
		if (!jo1.has("events"))	{
			throw new IllegalArgumentException("La entrada JSON no es correcta");
		}
		
		for (int i = 0; i < aux.length(); i++)	{
			traffic_sim.addEvent(events_fact.createInstance(aux.getJSONObject(i)));
		}
	}
	
	public void run(int n, OutputStream out) {
		if(out == null) {
			out = new OutputStream() {
				public void write (int b) throws IOException{
				};
			};
		}
		
		PrintStream p = new PrintStream(out);
		
		p.println("{");
		p.println("  \"states\": [");
		
		for(int i = 0; i <= n-1; ++i) {
			traffic_sim.advance();
			p.println(traffic_sim.report() + ",");
		}		
		
		p.println("]");
		p.println("}");
		p.close();
	}
	
	// NUEVO METODO RUN
	public void run() {		
		//for(int i = 0; i <= n-1; ++i) {
		traffic_sim.advance();
		//}			
		
	}
	
	public void reset() {
		traffic_sim.reset();
	}
	
	// NUEVOS METODOS
	
	public int getTicks() {
		return traffic_sim.getTicks();
	}
	
	public TrafficSimulator getTraffic() {
		return traffic_sim;
	}
	
	public void addObserver(TrafficSimObserver o) {
		traffic_sim.addObserver(o);
	}
	
	public void removeObserver(TrafficSimObserver o) {
		traffic_sim.removeObserver(o);
	}
	
	public void addEvent(Event e) {
		traffic_sim.addEvent(e);
	}	

}
