package simulator.view;

import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;


import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements TrafficSimObserver {

	private Controller ctrl;
	
	private JLabel jlT;
	
	private JLabel jlM;	
	
	private JLabel jlE;	
	
	
	public StatusBar(Controller _ctrl) {
		ctrl = _ctrl;
		ctrl.addObserver(this);
		initGUI();
	}
	
	public void initGUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT));	
		this.setBorder(getBorder());
		
		jlT = new JLabel();
		jlM = new JLabel();
		jlE = new JLabel();
		
		this.add(jlT);
		this.add(jlM);
		this.add(jlE);
		
		
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		jlM.setText("");
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {						
		jlT.setText("Time: "+ time);
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {			
		jlT.setText(""+ time); 	
		jlM.setText("Event added (" + e.toString() + ")");
				
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {				
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onError(String msg) {	
		jlE.setText("Error");
		
	}
}
