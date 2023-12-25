package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy{

	public MoveAllStrategy() {
	}
	
	// DEVUELVE UNA LISTA QUE INCLUYE EL PRIMER VEHICULO DE q
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {		
		List<Vehicle> ret = new ArrayList<Vehicle>();		
		
		Iterator<Vehicle> iter = q.iterator();
		int i = 0;
		
		while (iter.hasNext() && i < q.size()) {
			//System.out.println(iter.next());
			ret.add(q.get(i));	
			++i;
		}
		
		return ret;
	}

}
