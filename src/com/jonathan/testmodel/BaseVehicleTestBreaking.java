package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.Car;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;
import com.jonathan.domainmodel.BaseVehicle.VehicleType;

class BaseVehicleTestBreaking {

	@Test
	void test() {
		EventLogger.getInstance().setTraceLevel(TraceLevel.LOW);
		int speed = 10;
		Car c = new Car(VehicleType.CAR, 100, "TEST CAR", 2000, 6.5f); 
		for(int i=0 ;i <200;i++) {
			// c.computeSpeed(c.d, distanceToEnd, timeToNextTick, rc);
			EventLogger.getInstance().logEvent(TraceLevel.HIGH, "speed : [" + i + "] safe distance : [" + c.safeBreakingDistance(i) + "]");
		}
				
	}

}
