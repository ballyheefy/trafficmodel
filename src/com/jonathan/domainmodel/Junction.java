/**
 *  @author Jonathan Walsh
 *  
 *  Class Junction
 *  
 *  This class models a junction where a TrafficRoute is terminated a Junction in this model must be associated with one TrafficRoute in which case it is a source/sink
 *  of traffic.
 *  
 *  Ordinarily a Junction will be associated with two (2) or more TrafficRoute objects - the these must be unique.
 *  
 */

package com.jonathan.domainmodel;

import java.util.ArrayList;

/*
 *  Class Junction 
 *  
 *  
 *  This is the core of the domain model we will keep a record of all junctions created 
 *  
 */

public class Junction {
	
	public static final int initalMaxTrafficRoutesPerJunction = 10;
	
	public enum JunctionNumber {
		JUNCTION_ONE,
		JUNCTION_TWO
	}
	
	public Junction(long id, final String name) {
		this._jid = id;
		_name = name;
		this._trafficRoutes = new ArrayList<TrafficRoute>(initalMaxTrafficRoutesPerJunction);
	}
	
	public String toString() {
		return "Junction: " + _name + " ";
		
	}
	// Get methods
	
	/*
	 * This function returns a read only copy of the traffic routes associated with a junction 
	 */
	public final TrafficRoute [] getTrafficRoutes() {
		
		if( _trafficRoutes.size() > 0 ) {
			TrafficRoute[] tra = new TrafficRoute[_trafficRoutes.size()];
			_trafficRoutes.toArray(tra);
			return tra;
		}
		return null;
	}
	
	public int numberOfRoads( ) {
		return _trafficRoutes.size();
	}
	
	public long get_jid() {
		return _jid;
	} 

	
	
	/*
	 *  Private variable 
	 */
	
	private long _jid;
	ArrayList<TrafficRoute> _trafficRoutes; 
	String _name;

}
