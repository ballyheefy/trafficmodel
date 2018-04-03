package com.jonathan.domainmodel;

import com.jonathan.domainmodel.BaseVehicle.VehicleType;
import com.jonathan.local.IrishVehicleValidator;

// TODO: Auto-generated Javadoc
/*
 *  Class : VehicleFactory
 */

/**
 * A factory for creating Vehicle objects.
 */
public class VehicleFactory {
	
	/** The Constant vf. */
	private static final VehicleFactory vf = new VehicleFactory();
	
	/**
	 * Instantiates a new vehicle factory.
	 */
	private  VehicleFactory() {
		
	}

	/**
	 * Gets the single instance of VehicleFactory.
	 *
	 * @return single instance of VehicleFactory
	 */
	static public VehicleFactory getInstance() {
		return vf;
	}
	
	/**
	 * Builds the vehicle.
	 *
	 * @param vt the vt
	 * @param maxSpeed the max speed
	 * @param reg the reg
	 * @param kgs the kgs
	 * @param lphkm the lphkm
	 * @return the base vehicle
	 */
	public BaseVehicle buildVehicle(VehicleType vt,int maxSpeed,String reg, int kgs, float lphkm) {
		if( ValidateTheParameters(maxSpeed,reg,kgs,lphkm)) {
			switch(vt) {
				case CAR:
					return new Car(vt, maxSpeed, reg, kgs, lphkm);
				case TRUCK:
				case MOTORCYCLE:
				case ARTIC:
				case VAN:
				default:
					// <TODO> remove this default case from the model 
					return new Car(vt, maxSpeed, reg, kgs, lphkm);	
			}
		} else {
			System.err.println("Failed to validate the paramters");
			return null;
		}
			
		
	}
	
	/**
	 * Validate the parameters.
	 *
	 * @param maxSpeed the max speed
	 * @param reg the reg
	 * @param kgs the kgs
	 * @param lphkm the lphkm
	 * @return true, if successful
	 */
	/*
	 *  TODO : need to use static const variable here 
	 *  
	 */
	private boolean ValidateTheParameters(int maxSpeed,String reg, int kgs, float lphkm) {
		return IrishVehicleValidator.getInstance().validate(maxSpeed, reg, kgs, lphkm);
		
	}
}
