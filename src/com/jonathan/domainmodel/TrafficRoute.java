package com.jonathan.domainmodel;

/*
 * Class TrafficRoute
 * 
 * A traffic route is made of two Roads one from A->B and the other from B->A
 * 
 * Each TrafficRoute is terminated at either end by two (2) instances of Junction objects.
 */

public class TrafficRoute {
	
	private static final char roadCount=2;
	private static final char firstRoad=0;
	private static final char secondRoad=1;
	
	/**
	 * Constructor 
	 * 
	 * @param j1 - first junction 
	 * @param j2 - second junction 
	 * @param r1 - first road 
	 * @param r2 - second road
	 * @param name - name assigned to this TrafficRoute
	 */
	public TrafficRoute(Road r1, Road r2, String name) {
		// this._junction1=j1;
		// this._junction2=j2;
		this._routeName=name;
		this._roads = new Road[roadCount];
		this._roads[firstRoad] = r1;
		this._roads[secondRoad] = r2;
	}
	
	public TrafficRoute () {
		// Default constructor to allow creation of an array
		
	}
	/*
	public void SetJunction(Junction j, JunctionNumber jn) {
		if(jn == JunctionNumber.JUNCTION_ONE) {
			this._junction1 = j;
		} else {
			this._junction2 = j;
		}
	}
	*/
	
	/**
	 * 
	 * @return - this TrafficRoute is well formed i.e. has two roads and two junctions associated with it.
	 */
	public boolean TrafficRouteWellFormed() {
		
		if( _roads == null || _roads[firstRoad]==null|| _roads[secondRoad] == null || _routeName.isEmpty()) 
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @return - Array of Junction element[0]=road1.src and element[1]=road2.src
	 * 
	 * The method does not check for null the caller is required to error check as needed
	 */
	public Junction[] getJunction() {
		
		Junction j1[] = new Junction[roadCount];
		j1[firstRoad] = _roads[firstRoad].access().get_source();
		j1[secondRoad] = _roads[secondRoad].access().get_source();
		return j1;
		
	}
	
	public String toString() {
		// TODO : also return names of Junctions and format text 
		return "TR :" + _routeName + " " + _roads[firstRoad].toString() + _roads[firstRoad].access().get_source().toString() + 
				_roads[secondRoad].toString() + _roads[secondRoad].access().get_source().toString();
	}
	
	public String getName() {
		return "TR: "+ this._routeName + " ";
	}
	
	// Private variables
	
	private String _routeName;
	private Road[] _roads;

}
