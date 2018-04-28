package com.jonathan.domainmodel;



import java.util.HashMap;

/**
 *  @author jonathan walsh
 *  
 *  Road is one of the major elements in this model a road allows traffic to flow from source to destination in the model traffic can only ingress the network at a source
 *  and egress at the sink
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jonathan.dynamicmodel.SystemTick;
import com.jonathan.dynamicmodel.SystemTickHelper;
import com.jonathan.dynamicmodel.SystemTickHelperRoad;
import com.jonathan.helper.BadClassPassedException;
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
	
	public static final int NO_VEHICLE_AHEAD=Integer.MAX_VALUE;
	public static final int VEHICLE_START_LOCATION=0;   // vehicle always starts zero meters onto the road
	
	// this is a rich enumeration 
	public enum LaneCount {
		
		ONE_LANE(1),
		TWO_LANE(2),
		THREE_LANE(3),
		BAD_LANE_COUNT;
		
		private static final char _minLaneCount = 1;
		private static final char _maxLaneCount = 3; 
		public static final char badLaneCount = 99;
		
		private static String _oneLane = "onelane";
		private static String _twoLane = "twolane";
		private static String _threeLane = "threelane";
		
		private static final Map<Integer, LaneCount> laneCountByValue = new HashMap<>();
		
		// populate the map with enum,int pairs
		static {
	        for (LaneCount type : LaneCount.values()) {
	            laneCountByValue.put(type._value, type);
	        }
	    }

		private final int _value;

	    private LaneCount(int value) {
	        this._value = value;
	    }

	    private LaneCount() {
	    	this._value = badLaneCount;
	    }
	    
	    public static LaneCount forValue(int value) {
	        return laneCountByValue.get(value);
	        
	    }
	    
	    public static char maxLaneCount () {
	    	return _maxLaneCount; 
	    }
	    
	    public static char minLaneCount() {
	    	return _minLaneCount;
	    }
		
	    public static final String ONE_LANE() {
	    	return _oneLane;
	    }
	    public static final String TWO_LANE() {
	    	return _twoLane;
	    }
	    public static final String THREE_LANE() {
	    	return _threeLane;
	    }
	    
	    public static char LaneCountValue(final String str){
	    	
	    		return str.equals(_oneLane)?(char) 1:
	    			                        (char) (str.equals(_twoLane)?(char)2: 
	    			                        (char) ((str.equals(_threeLane)?(char)3:
	    			                        (char) badLaneCount)));
	    
	    }
	}
	
	/**
	 * The Enum RoadCondition.
	 */
	public enum RoadCondition {
		NORMAL,
		BAD,
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
		this._von = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._destination = destination;
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
		this._access= new Road.Access();
	}
	
	public Road(Junction source, LaneCount laneCount, int length,String name) {
		assert source != null;
	    assert length !=0; 
		this._von = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._source = source; 
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
		this._access= new Road.Access();
	}
	
	public Road(LaneCount laneCount, int length,String name) {
	    assert length !=0; 
		this._von = new LinkedList<BaseVehicle>();
		this._are = new LinkedList<AdverseRoadEvent>();
		this._laneCount = laneCount;
		this._rc = RoadCondition.NORMAL;
		this._length = length;
		this._name=name;
		this._access= new Road.Access();
	}
	
	public void SystemTickEvent() {
		
		try { 
			new SystemTickHelperRoad().tickEvent(this,SystemTickHelper.ONE_SECOND);
		} catch (BadClassPassedException e) {
			EventLogger.getInstance().logEvent(TraceLevel.CRITIAL, e.toString());
		}
	}
	
	public static class StaticAccess {
		public static char getMinlanecount() {
			return LaneCount.minLaneCount();
		}

		public static char getMaxlanecount() {
			return LaneCount.maxLaneCount();
		}
	}
	
	/*
	 *  Place all trivial access methods in a wrapper class to help read the code
	 */
	public class Access {
		public List<BaseVehicle> get_con() {
			return _von;
		}
		public void addVehicleToRoad(BaseVehicle v) {
			synchronized (_von) {
				_von.add(v);
			}
		}
		public void removeVehicleFromRoad(BaseVehicle v) {
			synchronized (_von) {
				_von.remove(v);
			}
		}
		public boolean isEmpty() {
			return _von.size() == 0;
		}
		public List<AdverseRoadEvent> get_are() {
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
		public void setDestination(Junction j) {
			_destination = j;
		}
		public void setSource(Junction j) {
			_source = j;
		}
		public void setRoadCondition(RoadCondition c) {
			_rc = c;
		}
		public RoadCondition getRoadCondition() {
			return _rc;
		}

	}
	
	public int distanceToNextvehicle(int idx) {
		if(idx != _von.size()-1) {
			return _von.get(idx+1).getLocation()-_von.get(idx).getLocation();
		} else {
			return NO_VEHICLE_AHEAD;
		}
	}
	
	public int distanceToEnd(int idx) {
		if(idx != _von.size()-1) {
			return _von.get(idx+1).getLocation()-this._length;
		} else {
			return NO_VEHICLE_AHEAD;
		}
	}
	public void attachAdverseRoadEvent(final AdverseRoadEvent re) {
		if(re.get_end() < this.access().get_length()) {
			synchronized (this._are) {
				try { 
					_are.add(re);
				} catch (NullPointerException e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,e.toString());
				} catch (UnsupportedOperationException e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,e.toString());
				} catch (ClassCastException e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,e.toString());
				} catch (IllegalArgumentException e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,e.toString());
				} catch (Exception e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,e.toString());
				}
			}
		} else {
			EventLogger.getInstance().logEvent(TraceLevel.MEDIUM,"Attempt to create traffic event beyond the end of road");
		}
	}
	
	
	public Junction otherJunction(Junction thisJunction) {
		return this._source.equals(thisJunction) ? this._destination : this._source;
	}
	
	/**
	 * 
	 * @param j - the junction that you are looking for
	 * @return - distance in meters to this junction 
	 * 
	 *  TODO: Think about how to to OSPF route - not sure this approach is good yet
	 */
	
	@Deprecated
	public boolean leadsTo(Junction j) {
		return true;
	}
	
	public boolean isEmpty() {
		return access().isEmpty();
	}
	
	public Road.Access access() {
		return _access;
	}
	
	/*
	 *  Private variables
	 */
	
	List<BaseVehicle>      		 _von;          // vehicles on road
	List<AdverseRoadEvent>		 _are;          // open adverse roadevent 
	Junction                     _source; 	    // cars move from src->dest 
	Junction                     _destination;
	LaneCount                    _laneCount;
	RoadCondition                _rc;
	int                          _length;       // meters
	String                       _name;
	Road.Access					 _access;					
} 
