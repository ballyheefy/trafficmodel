package com.jonathan.domainmodel;

import com.jonathan.domainmodel.Road.RoadCondition;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseVehicle.
 *
 * @author jonth
 * 
 * 
 * This is the abstract base class for all vehicles in the model this means that no variable of this type can be instantiated but that all variables (objects) of this
 * base type shall inherit from this base class.
 * 
 * <TODO> Factory classes for vehicle needs to be figured out.
 */

public abstract class BaseVehicle {
	
	/**
	 *  Enumerations.
	 */
	
	public static int defaultSafeDistance = 100;
	
	/** The tire coefficient friction. */
	public static float tireCoefficientFriction = 0.7f; 
	
	/** The driver reaction time. */
	public static float driverReactionTime = 1.5f;
	
	/** The small G. */
	public static float smallG = 9.80665f;
	
	/** The error safe breaking distance. */
	public static int errorSafeBreakingDistance=0;
	
	/** The bad safe distance multiplier. */
	public static float badSafeDistanceMultiplier=1.5f;
	
	/** The dangerous safe distance multiplier. */
	public static float dangerousSafeDistanceMultiplier=2.0f;
	
	/**
	 * The Enum VehicleType.
	 */
	public enum VehicleType {
		
		/** The motorcycle. */
		MOTORCYCLE,
		
		/** The car. */
		CAR,
		
		/** The van. */
		VAN,
		
		/** The truck. */
		TRUCK,
		
		/** The artic. */
		ARTIC
	}
	
	/**
	 *  Constructors.
	 */
	@SuppressWarnings("unused") // want to be clear that this is never used
	private  BaseVehicle() {
		// Not possible to have a vehicle that does not have some minimum values 
		assert true;
	}
	
	/**
	 * Instantiates a new base vehicle.
	 *
	 * @param vt the vt
	 * @param maxSpeedKPH the max speed KPH
	 * @param reg the reg
	 * @param weight the weight
	 * @param lpkm the lpkm
	 */
	/*
	 *  We will assume that the vehicle factory wont try to create a vehicle that is too fast etc
	 */
	public BaseVehicle(VehicleType vt, int maxSpeedKPH, String reg, int weight, float lpkm) {
		this.vt = vt;
		this.maxSpeedKPH = maxSpeedKPH;
		this.reg = reg;
		this.weight = weight;
		this.lphkm = lpkm;
	}

	
	/**
	 * This formula returns the safe distance of based on https://en.wikipedia.org/wiki/Braking_distance 
	 * 
	 * Ths model does 
	 * 
	 * 
	 * @param currentSpeed - speed in KPH
	 * @return - distance in meters
	 */
	public int safeBreakingDistance(int currentSpeed) {
		float dtr;  // distance to react 
		float dtb;  // distance to break
		float spms; // speed in meters per second
		
		dtr = currentSpeed / 3.6f;
		spms = currentSpeed / 360000f;
		dtb = spms*spms / tireCoefficientFriction * smallG;
		
		return Math.round(dtr+dtb); 
	}

	
	/**
	 * Safe breaking distance.
	 *
	 * @param currentSpeed the current speed
	 * @param rc the rc
	 * @return the int
	 */
	public int safeBreakingDistance(int currentSpeed, RoadCondition rc) {
		switch(rc) {
			case NORMAL:
					return safeBreakingDistance(currentSpeed); 
			case BAD:
					return Math.round(safeBreakingDistance(currentSpeed)*badSafeDistanceMultiplier);
			case DANGEROUS:
					return Math.round(safeBreakingDistance(currentSpeed)*dangerousSafeDistanceMultiplier);
			default:
					System.err.println("ERROR: Got to enexpected code in safeBreakingDistance(int,RoadCondition");
					return errorSafeBreakingDistance;
		}
	}
	
	
	/**
	 *   Getters and Setters.
	 *
	 * @return the vt
	 */
	
	public VehicleType getVt() {
		return vt;
	}

	/**
	 * Gets the max speed KPH.
	 *
	 * @return the max speed KPH
	 */
	public int getMaxSpeedKPH() {
		return maxSpeedKPH;
	}

	/**
	 * Gets the reg.
	 *
	 * @return the reg
	 */
	public String getReg() {
		return reg;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Gets the lpkm.
	 *
	 * @return the lpkm
	 */
	public float getLpkm() {
		return lphkm;
	}



	
	
	
	/** The vt. */
	/* 
	 * Private variables 
	 */
	VehicleType vt;
	
	/** The max speed KPH. */
	int maxSpeedKPH; 
	
	/** The reg. */
	String reg;
	
	/** The weight. */
	int weight;
	
	/** The lpkm. */
	float lphkm;
	

}
