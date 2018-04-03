package com.jonathan.local;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstactVehicleValidator.
 */
/*
 *  Class: AbstractVehicleValidator 
 *  
 *  This is intended to provide a helper service to the VehicleFactory class while creating a layer of abstraction to make the validation of legal vehicles in different
 *  Jurisdictions easier.
 *  
 *  Not sure if this is the best way to implement localization but if feels pretty clean to me.
 */
public abstract class AbstactVehicleValidator {
	
	
	
		
	/**
	 * Registration good.
	 *
	 * @param reg the reg
	 * @return true, if successful
	 */
	boolean RegistrationGood(String reg) {
		return false; // force override 
	}

	/**
	 * Vehicle details good.
	 *
	 * @param maxSpeed the max speed
	 * @param reg the reg
	 * @param kgs the kgs
	 * @param lphkm the lphkm
	 * @return true, if successful
	 */
	boolean vehicleDetailsGood(int maxSpeed,String reg, int kgs, float lphkm) {
		return false;  // force override 
	}
	
	
	/**
	 * Instantiates a new abstact vehicle validator.
	 */
	public AbstactVehicleValidator() {
		
	}
	

}
