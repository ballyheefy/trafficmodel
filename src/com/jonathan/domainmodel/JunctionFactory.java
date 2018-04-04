package com.jonathan.domainmodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class JunctionFactory {
	
	private static final JunctionFactory _jf = new JunctionFactory(new Random());
	private static Random _rng;
	private static final long randomNumberSeed=1966l;
	private static final int maxExpectedJunctions=100;
	/*
	 *  A collection to keep all created junctions - in a bigger program might create a class JunctionWarehouse
	 */
	private static HashSet<Junction> _allJunctions = new HashSet<Junction>(maxExpectedJunctions);
	
	private JunctionFactory(Random r) {
		_rng = r;
		_rng.setSeed(randomNumberSeed);
	}

	public static JunctionFactory getInstance() {
		return _jf;
	}
	
	/*
	 *  This method will create a new junction with a unique key and add it to the collection of all junctions so that it can be found again. 
	 *  The collection does not allow duplicate entries.
	 *  @param - name - expected to be upper case with no leading or trailing blanks
	 */
	public Junction makeJunction(String name) {
		
		Junction je = junctionExists(name.toUpperCase().trim());
		if( je !=null) {
			return je;
		}
		String formatName = name.toUpperCase().trim();
		long key = Math.round(_rng.nextDouble());
		while(junctionExists(key) != null)
			// I think this is safe as the RNG will eventually find a new unique key 
			key = Math.round(_rng.nextDouble());
		Junction j = new Junction(key, formatName);
		_allJunctions.add(j);
		return j; 
		
	}
	
	/**
	 * Method : junctionExists(final int)
	 * 
	 * This method searches for the Junction with name matching the passed string - the method does an exhaustive search of the container class returning a reference 
	 * to the object on success and null if not found and the iterator has traversed the entire collection.
	 * 
	 * @param name - the name of the junction that you are looking for
	 * @return - a handle on the junction if it exists or null to indicate that it does not exist
	 * @author Jonathan Walsh
	 * @
	 */
	
	public static Junction junctionExists(final long key) {
		Iterator<Junction> i = _allJunctions.iterator();
		while(i.hasNext()) {
			Junction cur = i.next();
			if (cur.get_jid() == key) 
				return cur;
		}
		return null;
	}
	
	/**
	 * Method : junctionExists(final String)
	 * 
	 * This method searches for the Junction with name matching the passed string - the method does an exhaustive search of the container class returning a reference 
	 * to the object on success and null if not found and the iterator has traversed the entire collection.
	 * 
	 * @param name - the name of the junction that you are looking for
	 * @return - a handle on the junction if it exists or null to indicate that it does not exist
	 * @author Jonathan Walsh
	 * @
	 */
	
	public static Junction junctionExists(final String name) {
		Iterator<Junction> i = _allJunctions.iterator();
		while(i.hasNext()) {
			Junction cur = i.next();
			if (cur.toString().equals(name)) 
				return cur;
		}
		return null;
	}
	
	public int numberOfJunctions() {
		return _allJunctions.size();
	}
	
	/*
	 *  Utility for debug and diagnostics to count badly formed junctions 
	 */
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
}
