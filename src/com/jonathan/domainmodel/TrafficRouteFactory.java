/**
 *  Class TrafficRouteFactory
 *  
 *  @author jonathan walsh
 *  
 *  This class provides Factory to create instance of TrafficRoutes using the singleton design pattern
 *  
 *  This class also provides a data warehouse function to keep a collection of all created items of this class. In a larger application this responsibility might be placed 
 *  into a separate "TrafficRouteWareHouse" class - perhaps something that I will do eventually - where the storage becomes more complex to xml file, sql db etc this may be 
 *  a useful abstraction to create a clear delineation between creation and storage of the objects.
 *   
 */

package com.jonathan.domainmodel;

import java.util.HashSet;
import java.util.Iterator;

public class TrafficRouteFactory {
	
	private static final TrafficRouteFactory _trf = new TrafficRouteFactory();
	private static final int maxExpectedRoutes=100;
	private static HashSet<TrafficRoute> _allTrafficRoutes = new HashSet<TrafficRoute>(maxExpectedRoutes);
	
	private TrafficRouteFactory() {
		
	}
	
	public static TrafficRouteFactory getInstance() {
		return _trf;
	}
	
	public TrafficRoute MakeTrafficRoute(Road r1, Road r2, String name) {
		
		TrafficRoute tr = new TrafficRoute(r1, r2, name);
		TrafficRouteFactory.addTrafficRouteToCollection(tr);
		return tr; 
	}
	
	private static void addTrafficRouteToCollection(TrafficRoute tr) {
			
		// TODO - does a HashSet resize automatically 
		_allTrafficRoutes.add(tr);
	
	}
	

	/**
	 * 
	 * @return - an array of String with names of all routes 
	 */
	public String[] getRouteNames() {
		
		String names[] = new String[_allTrafficRoutes.size()];
		Iterator<TrafficRoute> i = _allTrafficRoutes.iterator();
		char idx=0;
		while(i.hasNext()) {
			names[idx++] = i.next().getName();
		}
		return names;
	}
	
	public int numberOfTrafficRoutes() {
		return _allTrafficRoutes.size();
	}
	/**
	 * 
	 * @return - an array of String with details of all routes 
	 */
	public String[] getRouteDetails() {
		
		String names[] = new String[_allTrafficRoutes.size()];
		Iterator<TrafficRoute> i = _allTrafficRoutes.iterator();
		char idx=0;
		while(i.hasNext()) {
			names[idx++] = i.next().toString();
		}
		return names;
	}
	
}
