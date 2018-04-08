package com.jonathan.domainmodel;



/**
 *  @author jonathan walsh
 *  
 *  Road is one of the major elements in this model a road allows traffic to flow from source to destination in the model traffic can only ingress the network at a source
 *  and egress at the sink
 */

import java.util.LinkedList;

import com.jonathan.dynamicmodel.SystemTick;
import com.jonathan.dynamicmodel.SystemTickHelper;
import com.jonathan.dynamicmodel.SystemTickHelperRoad;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;

// TODO: Auto-generated Javadoc
/**
 * The Class Road.
 *
 * @author jonth
 * 
 * Class Road
 */

public class Road implements SystemTick {
	
	public static final char minLaneCount = 1;
	public static final char maxLaneCount = 3;
	
	public enum LaneCount {
		ONE_LANE,
		TWO_LANE,
		THREE_LANE;

		/*
		private final int _value;
		
		LaneCount(int value) {
			this._value=value;
		}
		
		int value () {
			return _value;
		}
		*/
		
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
	
	/**
	 * Instantiates a new road.
	 * 
	 *  @param - length is provided in meters
	 *  @param - laneCount 
	 */
	public Road(Junction source, Junction destination, LaneCount laneCount, int length,String name) {
		assert source != null;
	    assert destination != null;
	    assert length !=0; 
		this._con = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._destination = destination;
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
	}
	
	public Road(Junction source, LaneCount laneCount, int length,String name) {
		assert source != null;
	    assert length !=0; 
		this._con = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
	}
	
	public Road(LaneCount laneCount, int length,String name) {
	    assert length !=0; 
		this._con = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
	}
	
	public void SystemTickEvent() {
		
		new SystemTickHelperRoad().tickEvent(this);
		
	}
	
	public static char getMinlanecount() {
		return minLaneCount;
	}

	public static char getMaxlanecount() {
		return maxLaneCount;
	}

	public LinkedList<BaseVehicle> get_con() {
		return _con;
	}

	public LinkedList<AdverseRoadEvent> get_are() {
		return _are;
	}

	public Junction get_source() {
		return _source;
	}

	public Junction get_destination() {
		return _destination;
	}

	public LaneCount get_laneCount() {
		return _laneCount;
	}

	public int get_length() {
		return _length;
	}

	public String toString () {
		return "Road: " + _name + " ";
	}

	public void attachAdverseRoadEvent(final AdverseRoadEvent re) {
		if(re.get_end() < this.get_length()) {
		 _are.add(re);
		} else {
			EventLogger.getInstance().logEvent(TraceLevel.MEDIUM,"Attempt to create traffic event beyond the end of road");
		}
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
	
	/*
	private static final Map<Integer, LaneCount> intToTypeMap = new HashMap<Integer, LaneCount>();
	static {
	    for (LaneCount type : LaneCount.values()) {
	        intToTypeMap.put(type.value(), type);
	    }
	}
	
	public static LaneCount fromInt(int i) {
	    LaneCount type = LaneCount.value(Integer.valueOf(i));
	    return type;
	}
	*/
	
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
	
	/*
	 *  Private variables
	 */
	
	LinkedList<BaseVehicle>      _con;          // cars on road
	LinkedList<AdverseRoadEvent> _are;          // open adverse roadevent 
	Junction                     _source; 	    // cars move from src->dest 
	Junction                     _destination;
	LaneCount                    _laneCount;
	RoadCondition                _rc;
	int                          _length;       // meters
	String                       _name;
} 
