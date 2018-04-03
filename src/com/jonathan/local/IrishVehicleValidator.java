package com.jonathan.local;

// TODO: Auto-generated Javadoc
/**
 * The Class IrishVehicleValidator.
 */
public class IrishVehicleValidator extends AbstactVehicleValidator  {
	
	/** The Constant irishMinRegLength. */
	/*
	 * Note these values are not intended to be super precise but rather to give a flavor of validation 
	 * 
	 * A better technique here would be to bring these in at run time from an XML file making this a configuration rather than coding change.
	 */
	public static final int irishMinRegLength=7; // this includes spaces "YYY C N"
	
	/** The Constant irishMaxRegLen. */
	public static final int irishMaxRegLen=13;      // YYY CC NNNNNN
	
	/** The Constant irishMinSpeed. */
	public static final int irishMinSpeed=50;
	
	/** The Constant irishMaxSpeed. */
	public static final int irishMaxSpeed=220;
	
	/** The Constant irishMinWeight. */
	public static final int irishMinWeight=200;
	
	/** The Constant irishMaxWeight. */
	public static final int irishMaxWeight=30000;
	
	/** The Constant irishMinEconomy. */
	public static final int irishMinEconomy=30;
	
	/** The Constant irishFuelEconomyTooGoodToBeTrue. */
	public static final int irishFuelEconomyTooGoodToBeTrue=1;
	
	
	
	/**
	 * Instantiates a new irish vehicle validator.
	 */
	private IrishVehicleValidator() {
		
	}

	/**
	 * Gets the single instance of IrishVehicleValidator.
	 *
	 * @return single instance of IrishVehicleValidator
	 */
	public static IrishVehicleValidator getInstance() {
		return IrishVehicleValidator.vv;
	}
	
	/*
	 *  Reg format YYY CC N 
	 */
	
	
	/**
	 * Validate.
	 *
	 * @param maxSpeed the max speed
	 * @param reg the reg
	 * @param kgs the kgs
	 * @param lphkm the lphkm
	 * @return true, if successful
	 */
	public boolean validate(int maxSpeed,final String reg, int kgs,float lphkm) {
		if( maxSpeed < irishMinSpeed || maxSpeed > irishMaxSpeed) return false;
		if( !RegistrationGood(reg)) return false;
		if( kgs < irishMinWeight || kgs > irishMaxWeight) return false;
		if(lphkm > irishMinEconomy || lphkm < irishFuelEconomyTooGoodToBeTrue) return false; 
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.jonathan.local.AbstactVehicleValidator#RegistrationGood(java.lang.String)
	 */
	public boolean RegistrationGood(final String reg) {
		
		boolean ccFound=false;
		
		if(reg.isEmpty()) return false;
		for(int cc = 0; cc < counties.length ; cc++ )
			if(reg.contains(counties[cc])) {
				ccFound=true;
				break;
			}
		if( !ccFound ) return false;
		if( reg.length() < irishMinRegLength || reg.length() > irishMaxRegLen) return false;
		
		return true;
	}
	/*
	 *  Private members
	 */
	
	/** The Constant vv. */
	private static final IrishVehicleValidator vv = new IrishVehicleValidator();
	
	
	/** The Constant counties. */
	private static final String[] counties = {
			"D","C","CE","CN","CW","DL","G","KE","KY","KK","L","LH","LM","LS","MH","MN","MO","OY","RN","SO","T","W","WH","WX","WW"
	};
}
