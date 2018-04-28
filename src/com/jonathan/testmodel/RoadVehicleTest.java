package com.jonathan.testmodel;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.BaseVehicle.VehicleType;
import com.jonathan.domainmodel.Car;
import com.jonathan.domainmodel.Junction;
import com.jonathan.domainmodel.Road;
import com.jonathan.domainmodel.Road.LaneCount;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;

class RoadVehicleTest {
	
	@Before
	void test1() {
		EventLogger.getInstance().setTraceLevel(TraceLevel.LOW);
	}
	
	/*
	 *  Create a single car and simulate a drive from source to destination 
	 */
	@Test
	void test() {
		Junction j1= new Junction(1l, "LIMERICK");
		Junction J2= new Junction(2l, "DUBLIN");
		Road r = new Road(j1, J2, LaneCount.ONE_LANE, 10_000, "LIMERICK->DUBLIN");
		Car c = new Car(VehicleType.CAR, 100, "TEST CAR", 2000, 6.5f);
		r.access().addVehicleToRoad(c);
		// drive the car from source to destination 
		while(!r.isEmpty()) {
			r.SystemTickEvent();
			if (c.getLocation() >= r.access().get_length()) {
				// car got to end of road 
				r.access().removeVehicleFromRoad(c);
			}
			
		}
		
	}

}
