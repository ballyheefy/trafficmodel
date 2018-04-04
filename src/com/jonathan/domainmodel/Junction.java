package com.jonathan.domainmodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

/*
 *  Class Junction 
 *  
 *  
 *  This is the core of the domain model we will keep a record of all junctions created 
 *  
 */

public class Junction {
	private static final int maxNumberOfJunctionNodes=100; 
	
	private static HashSet<Junction> _allJunctions = new HashSet<Junction>(maxNumberOfJunctionNodes);
	public static final int initalMaxRoadsPerJunction = 10;
	
	public Junction(long id, final String name) {
		this._jid = id;
		if( !_allJunctions.add(this)) {
			System.err.println("Error adding the junction to the hash of all junctions");
			assert true; // die 
		}
		_name = name;
		this._roads = new ArrayList<Road>(initalMaxRoadsPerJunction);
	}
	
	/**
	 * 
	 * @param r - the road that you wish to associate with this junction 
	 * @return - true the add worked otherwise false;
	 */
	public boolean addRoad(Road r) {
		Iterator<Road> i = this._roads.iterator();
		while(i.hasNext())
			// don not add the same road twice a road can meet a junction once and only once 
			if( i.next().equals(r)) {
				System.err.println("The road already exists not adding a second time");
				return false;
			}
		r.setDestination(this);
		return this._roads.add(r);
	}
	
	public String toString() {
		return _name;
		
	}
	// Get methods
	
	public int numberOfRoads( ) {
		return _roads.size();
	}
	
	public long get_jid() {
		return _jid;
	} 
	
	public static HashSet<Junction> allJunctionCollection() {
		return _allJunctions;
	}
	
	
	public static boolean junctionExists(long key) {
		Iterator<Junction> i = _allJunctions.iterator();
		while(i.hasNext()) {
			if (i.next().get_jid() == key) 
				return true;
		}
		return false;
	}
	
	public static Junction junctionExists(String name) {
		Iterator<Junction> i = _allJunctions.iterator();
		while(i.hasNext()) {
			Junction cur = i.next();
			if (cur.toString().equals(name)) 
				return cur;
		}
		return null;
	}
	
	public static int numberOfBadJunctions() {
		Iterator<Junction> i = _allJunctions.iterator();
		int bjc = 0;
		while(i.hasNext()) {
			if (i.next().numberOfRoads() == 0 ) {
				bjc++;
			}
				
		}
		return bjc;
	}
	
	/*
	 *  Private variable 
	 */
	
	private long _jid;
	ArrayList<Road> _roads; 
	String _name;
	
	

	

}
