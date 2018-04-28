package com.jonathan.helper;

import com.jonathan.domainmodel.BaseVehicle.VehicleType;
import com.jonathan.domainmodel.Car;
import com.jonathan.domainmodel.VehicleState;

/**
 * This is using the builder pattern as on the open source project 
 * 
 * 
 * @author jonth
 *
 */

public class CarBuilder extends AbstractVehicleBuilder {
	
	public static CarBuilder _instance = new CarBuilder();
	
	private CarBuilder() {
		
	}
	
	public Car build() throws CodeNeverHereException {
		
		return new Car(this);
		
	}
	
	public static CarBuilder getInstance() {
		return _instance;
	}
	public CarBuilder withVehicleType(VehicleType vt) {
		this._vt = vt; 
		return this;
	}
	public CarBuilder withMaxSpeed(int ms) {
		this._maxSpeedKPH=ms;
		return this;
	}
	public CarBuilder withCurrentSpeed(int cs) {
		this._currentSpeedKPH=cs;
		return this;
	}
	public CarBuilder withRegistration(String reg) {
		this._reg=new String(reg);
		return this;
	}
	public CarBuilder withWeight(int weight) {
		this._weight=weight;
		return this;
	}
	public CarBuilder withLPHKM(float lph) {
		this._lphkm=lph;
		return this;
	}
	public CarBuilder withLocation(int loc) {
		this._location=loc;
		return this;
	}
	public CarBuilder withVehicleState(VehicleState vs) {
		this._vs=vs;
		return this;
	}
	/*******************************************************************************************************************************************************************
	 *  I do not like this we should have a class that basically holds the data vector and avoid tying all this N times.
	 *  TODO
	 */
	
}
