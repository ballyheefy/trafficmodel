package com.jonathan.domainmodel;

import java.util.Random;

public class JunctionFactory {
	
	private static final JunctionFactory _jf = new JunctionFactory(new Random());
	private static Random _rng;
	private static final long randomNumberSeed=1966l;
	
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
	 */
	public Junction makeJunction() {
		
		long key = Math.round(_rng.nextDouble());
		while(Junction.junctionExists(key))
			// I think this is safe as the RNG will eventually find a new unique key 
			key = Math.round(_rng.nextDouble());
		Junction j = new Junction(key);
		Junction.allJunctionCollection().add(j);
		return j; 
		
	}
	
	public int numberOfJunction() {
		return Junction.allJunctionCollection().size();
	}
	
	
}
