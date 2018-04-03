package com.jonathan.domainmodel;

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
	
	

}
