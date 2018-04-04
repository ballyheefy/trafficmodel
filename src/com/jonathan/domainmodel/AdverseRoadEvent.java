/**
 *  Class AdverseRoadEvent
 *  
 *  @author jonathan walsh
 *  
 *  This class shall be used to model problems on the road 
 *  
 *  The adverse road event can impact specific lanes and has a start and end position stated as distance from the source node.
 *  
 *  When a road event happens it shall be associated with a Road - 
 *  
 *  
 *  TFO: do we model crashes, road works etc as separate classes or not?
 */

package com.jonathan.domainmodel;

public class AdverseRoadEvent {
	
	enum RoadEventType {
		CRASH,
		ROAD_WORKS,
		SPEED_TRAP
	}
	
	public AdverseRoadEvent(int start, int end, RoadEventType ret, String name) {
		this._start=start;
		this._end=end;
		this._ret=ret;
		this._name=name;
	}

	// private vars
	
	public int get_start() {
		return _start;
	}
	public int get_end() {
		return _end;
	}
	public RoadEventType get_ret() {
		return _ret;
	}
	public String get_name() {
		return _name;
	}

	int _start;
	int _end;
	RoadEventType _ret;
	String _name;
	
}
