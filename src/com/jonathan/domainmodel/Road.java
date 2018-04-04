package com.jonathan.domainmodel;

/**
 *  Road is one of the major elements in this model 
 */

import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class Road.
 *
 * @author jonth
 * 
 * Class Road
 */

public class Road {
	
	public static final int minLaneCount = 1;
	public static final int maxLaneCount = 3;
	
	
	/**
	 * Instantiates a new road.
	 * 
	 *  @param - length is provided in meters
	 *  @param - laneCount 
	 */
	public Road(Junction source, Junction destination, int laneCount, int length) {
		assert source != null;
	    assert destination != null;
	    assert length !=0; 
	    assert laneCount >= minLaneCount && laneCount <= maxLaneCount;
		this._con = new LinkedList<BaseVehicle>();
		this._destination = destination;
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
	}
	
	public Road(Junction source, int laneCount, int length) {
		assert source != null;
	    assert length !=0; 
	    assert laneCount >= minLaneCount && laneCount <= maxLaneCount;
		this._con = new LinkedList<BaseVehicle>();
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
	}
	
	public Road(int laneCount, int length) {
	    assert length !=0; 
	    assert laneCount >= minLaneCount && laneCount <= maxLaneCount;
		this._con = new LinkedList<BaseVehicle>();
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
	}
	
	public void setDestination(Junction j) {
		_destination = j;
	}
	
	public void setSource(Junction j) {
		_source = j;
	}
	
	public Junction otherJunction(Junction thisJunction) {
		return this._source.equals(thisJunction) ? this._destination : this._source;
	}
	
	public void setRoadCondition(RoadCondition c) {
		this._rc = c;
	}
	
	public RoadCondition getRoadCondition() {
		return this._rc;
	}
	
	
	/**
	 * 
	 * @param j - the junction that you are looking for
	 * @return - distance in meters to this junction 
	 * 
	 *  TODO: Think about how to to OSPF route - not sure this approach is good yet
	 */
	public int leadsTo(Junction j) {
		return 0;
	}
	
	
	/**
	 * The Enum RoadCondition.
	 */
	public enum RoadCondition {
		
		/** The normal. */
		NORMAL,
		
		/** The bad. */
		BAD,
		
		/** The dangerous. */
		DANGEROUS
	}

	
	/*
	 *  Private variables
	 */
	
	/** The con. */
	LinkedList<BaseVehicle> _con;  // cars on road
	Junction _source; 
	Junction _destination;
	int _laneCount;
	RoadCondition _rc;
	int _length;
}
