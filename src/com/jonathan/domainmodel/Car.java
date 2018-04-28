package com.jonathan.domainmodel;

import com.jonathan.helper.CarBuilder;
import com.jonathan.helper.CodeNeverHereException;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
/*
 *  Class : Car
 *  
 *  For the moment this does nothing more than allow us to instantiate a vehicle to test with
 *  
 */
public class Car extends BaseVehicle  {

	/**
	 * Instantiates a new car.
	 *
	 * @param vt the vt
	 * @param maxSpeedKPH the max speed KPH
	 * @param reg the reg
	 * @param weight the weight
	 * @param lphkm the lphkm
	 */
	public Car(VehicleType vt, int maxSpeedKPH, String reg, int weight, float lphkm) {
		super(vt, maxSpeedKPH, reg, weight, lphkm);
		
	}
	
	public Car(CarBuilder cb) throws CodeNeverHereException {
		super(cb);
		// take the data from the CarBuilder object and assign to the car
		super._vt = cb.getVehicleType();
	}

	
	

}
