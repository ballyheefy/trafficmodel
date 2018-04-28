package com.jonathan.domainmodel;

public class VehicleState {
	
	public enum AccelerationState {
		
		ACCELERATING(1),
		DECELERATING(2),
		CONSTANT_SPEED(3),
		UNKNOWN_STATE(4),
		ERROR_STATE(5);
		
		private int _value;
		
		private AccelerationState(int value) {
			this._value = value;
		}
	}
	
	public VehicleState(AccelerationState as) {
		this._as=as;
	}
	
	public VehicleState() {
		this._as=AccelerationState.CONSTANT_SPEED;
	}
	
	public AccelerationState get_as() {
		return _as;
	}

	public void set_as(AccelerationState _as) {
		this._as = _as;
	}

	private AccelerationState _as;
}
