package com.jonathan.helper;

import com.jonathan.domainmodel.BaseVehicle;
import com.jonathan.domainmodel.VehicleState;
import com.jonathan.domainmodel.BaseVehicle.VehicleType;

public abstract class AbstractVehicleBuilder {
	
	
	BaseVehicle build() throws CodeNeverHereException {
		
		// should never ever get here!!! - not sure what to do to make this less of a kludge 
		return (BaseVehicle) new Object();
		
	}
	
	protected AbstractVehicleBuilder() {
		
	}
	
	public VehicleType getVehicleType() {
		return _vt;
	}
	public int getMaxSpeed() {
		return _maxSpeedKPH;
	}
	public int getCurrentSpeed() {
		return _maxSpeedKPH;
	}
	public String getReg() {
		return _reg;
	}
	public int getWeight() {
		return _weight;
	}
	public float getLPHKM() {
		return _lphkm;
	}
	public int getLocation() {
		return _location;
	}
	public VehicleState getVehicleState() {
		return _vs;
	}
	/*****************************************************************************************************************************************************************
	 * 
	 *  TEMP PRIVATE VARIABLES
	 */

	VehicleType _vt;
	int _maxSpeedKPH; 
	int _currentSpeedKPH;
	String _reg;
	int _weight;
	float _lphkm;
	int _location;
	VehicleState _vs;

}
