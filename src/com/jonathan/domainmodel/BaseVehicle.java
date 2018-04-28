package com.jonathan.domainmodel;

import com.jonathan.domainmodel.Road.RoadCondition;
import com.jonathan.helper.AbstractVehicleBuilder;
import com.jonathan.helper.CodeNeverHereException;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;

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
	
	static public class BaseVehilceContstants {
	
		public static final int defaultSafeDistance = 100;
		
		/** The tire coefficient friction. */
		public static final float tireCoefficientFriction = 0.7f; 
		
		/** The driver reaction time. */
		public static final float driverReactionTime = 1.5f;
		
		/** The small G. */
		public static final float smallG = 9.80665f;
		
		/** The error safe breaking distance. */
		public static final int errorSafeBreakingDistance=-99;
		
		/** The bad safe distance multiplier. */
		public static final float badSafeDistanceMultiplier=1.5f;
		
		/** The dangerous safe distance multiplier. */
		public static final float dangerousSafeDistanceMultiplier=2.0f;
		
		// Assume that the vehicle is at a stop as it enters the Road.
		public static final int VEHICLE_START_SPEED=0;
		
		public static final float KPH_TO_METERS_PER_SECOND_CONVERSION_FACTOR=3.6f;
		public static final float TIME_TO_TREACT=1.5f;

		public static final float RATE_OF_DEACCELERATION_METERS_PER_SECOND_PER_SECOND=10;
		public static final float RATE_OF_ACCELERATION_METERS_PER_SECOND_PER_SECOND=10;
	
	}
	
	/**
	 * The Enum VehicleType.
	 */
	public enum VehicleType {
		MOTORCYCLE,
		CAR,
		VAN,
		TRUCK,
		ARTIC,
		ILLEGAL_TYPE
	}
	
	/**
	 *  Constructors.
	 *  
	 *  TODO - throw an exception from here 
	 */
	@SuppressWarnings("unused") // want to be clear that this is never used
	private  BaseVehicle() throws CodeNeverHereException {
		// Not possible to have a vehicle that does not have some minimum values 
		this._vt = VehicleType.CAR;
		this._maxSpeedKPH = 0;
		this._reg = "";
		this._weight = 0;
		this._lphkm = 0;
		this._location= Road.VEHICLE_START_LOCATION; // should this be road or vehicle? 
		this._currentSpeedKPH= BaseVehilceContstants.VEHICLE_START_SPEED;
		this._vs = new VehicleState();
		throw new CodeNeverHereException("This constructor should never get called");
	}
	
	/**
	 * @param vb  
	 * 
	 * 
	 */
	protected BaseVehicle(AbstractVehicleBuilder vb) throws CodeNeverHereException {
		this._maxSpeedKPH = vb.getMaxSpeed();
		this._reg = new String(vb.getReg());
		this._weight = vb.getWeight();
		this._lphkm = vb.getLPHKM();
		this._location= Road.VEHICLE_START_LOCATION; // should this be road or vehicle? 
		this._currentSpeedKPH= BaseVehilceContstants.VEHICLE_START_SPEED;
		this._vs = vb.getVehicleState();
	}
	
	/*******************************************************************************************************************************************************************
	 * Instantiates a new base vehicle.
	 *
	 * @param vt the vt
	 * @param maxSpeedKPH the max speed KPH
	 * @param reg the reg
	 * @param weight the weight
	 * @param lpkm the lpkm
	 */
	
	
	/**
	 *  We will assume that the vehicle factory wont try to create a vehicle that is too fast etc
	 */
	public BaseVehicle(VehicleType vt, int maxSpeedKPH, String reg, int weight, float lpkm) {
		this._vt = vt;
		this._maxSpeedKPH = maxSpeedKPH;
		this._reg = reg;
		this._weight = weight;
		this._lphkm = lpkm;
		this._location= Road.VEHICLE_START_LOCATION; // should this be road or vehicle? 
		this._currentSpeedKPH= BaseVehilceContstants.VEHICLE_START_SPEED;
		this._vs = new VehicleState();
	}

	
	
	/****************************************************************************************************************************************************************
	 * This formula returns the safe distance of based on https://en.wikipedia.org/wiki/Braking_distance 
	 * 
	 * This model does 
	 * 
	 * 
	 * @param currentSpeed - speed in KPH
	 * @return - distance in meters
	 */
	
	//private final double conversionFactorToMetersPerSecond=0.27; // 1000/3600
	private final int distanceCol=1;
	private final int [][]  breakingDistances = new int [][] {
			{10,4},
			{20,8},
			{30,16},
			{40,26},
			{50,35},
			{60,45},
			{70,56},
			{80,69},
			{90,83},
			{100,98},
			{110,113},
			{120,135}
	};

	public int safeBreakingDistance(int currentSpeedKPH) {
		// double dtrInMeters=0;  // distance to react  
		// double dtbInMeters=0;  // distance to break
		// double spms=0; 		// speed in meters per second
		
		/*
		dtrInMeters = currentSpeedKPH * conversionFactorToMetersPerSecond * BaseVehilceContstants.TIME_TO_TREACT ;       // convert to meters per second
		spms = (currentSpeedKPH * conversionFactorToMetersPerSecond);
		dtbInMeters = (spms*spms) / BaseVehilceContstants.tireCoefficientFriction * BaseVehilceContstants.smallG;
		*/
		
		int idx = (currentSpeedKPH / 10)-1;
		idx = idx < 0 ? 0 : idx;
		idx = idx > 12 ? 12 : idx;
		
		return  breakingDistances[ idx ] [distanceCol ];
		
		// return (int) Math.round(dtrInMeters+dtbInMeters); 
		
	}

	
	/*****************************************************************************************************************************************************************
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
					return Math.round(safeBreakingDistance(currentSpeed)*BaseVehilceContstants.badSafeDistanceMultiplier);
			case DANGEROUS:
					return Math.round(safeBreakingDistance(currentSpeed)*BaseVehilceContstants.dangerousSafeDistanceMultiplier);
			default:
					System.err.println("ERROR: Got to enexpected code in safeBreakingDistance(int,RoadCondition");
					return BaseVehilceContstants.errorSafeBreakingDistance;
		}
	}
	
	
	/**
	 *   Getters and Setters.
	 *
	 * @return the vt
	 */
	
	public VehicleType getVt() {
		return _vt;
	}

	/**
	 * Gets the max speed KPH.
	 *
	 * @return the max speed KPH
	 */
	public int getMaxSpeedKPH() {
		return _maxSpeedKPH;
	}

	/**
	 * Gets the reg.
	 *
	 * @return the reg
	 */
	public String getReg() {
		return _reg;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return _weight;
	}

	/**
	 * Gets the lpkm.
	 *
	 * @return the lpkm
	 */
	public float getLpkm() {
		return _lphkm;
	}
	
	public int getLocation() {
		return _location;
	}

	/**********************************************************************************************************************************************************************
	 * 
	 * public void computeLocation(int timePassed)
	 * 
	 * @param timePassed in seconds
	 * 
	 * Compute distance traveled in the time passed assuming the vehicle is traveling at constant speed
	 * 
	 * The simplifying assumption that speed is calculated every unit of time introduces a small quantization distortion. By running the tick at a short interval for example
	 *  1/10th of a second it will be negligible.
	 *  
	 */
	public void computeLocation(int timePassed) {
		_location = _location + Math.round(_currentSpeedKPH/BaseVehilceContstants.KPH_TO_METERS_PER_SECOND_CONVERSION_FACTOR*timePassed) ;
	}
	
	/******************************************************************************************************************************************************************
	 * public void computeSpeed(int distanceToNext,float timeToNextTick) 
	 * 
	 * Calculates speed at the end of this time interval 
	 * 
	 * @param location
	 * @param timeToNextTick - how many seconds until next tick event for the moment the model will use one second ticks
	 */
	public void computeSpeed(int distanceToNext,int distanceToEnd, float timeToNextTick,@SuppressWarnings("unused") Road.RoadCondition rc) {
		if(_currentSpeedKPH < _maxSpeedKPH) {
			if((distanceToNext <= safeBreakingDistance(_currentSpeedKPH)) || (distanceToEnd < safeBreakingDistance(_currentSpeedKPH) )) {
				_currentSpeedKPH = _currentSpeedKPH - Math.round((BaseVehilceContstants.RATE_OF_DEACCELERATION_METERS_PER_SECOND_PER_SECOND*timeToNextTick));
				return;
			} 
			_currentSpeedKPH = _currentSpeedKPH + Math.round((BaseVehilceContstants.RATE_OF_ACCELERATION_METERS_PER_SECOND_PER_SECOND*timeToNextTick));
			// Safety check don't allow current speed to exceed maximum speed
			_currentSpeedKPH = _currentSpeedKPH > _maxSpeedKPH? _maxSpeedKPH : _currentSpeedKPH;
		}	
	}
	
	/********************************************************************************************************************************************************************
	 * 
	 * @param location
	 */
	public void setLocation(int location) {
		this._location = location;
	}
	
	/*********************************************************************************************************************************************************************
	 * 
	 */
	public void logProgress() {
		EventLogger.getInstance().logEvent(TraceLevel.MEDIUM, toString());
		EventLogger.getInstance().flushEventLoggerOutput();
	}
	
	/*********************************************************************************************************************************************************************
	 * 
	 */
	public String toString() {
		return("Registration : [" + _reg + "] loacation : [" + _location + "]" + " _speed :  [" + _currentSpeedKPH +"]");
	}
	
	/**********************************************************************************************************************************************************************
	/* 
	 * Private variables 
	 */
	protected VehicleType _vt;
	protected final int _maxSpeedKPH; 
	protected int _currentSpeedKPH;
	protected final String _reg;
	protected final int _weight;
	protected final float _lphkm;
	protected int _location;
	protected VehicleState _vs;
}
