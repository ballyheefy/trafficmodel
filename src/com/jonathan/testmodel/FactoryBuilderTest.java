package com.jonathan.testmodel;

import static org.junit.jupiter.api.Assertions.*;

import com.jonathan.domainmodel.BaseVehicle;
import com.jonathan.domainmodel.BaseVehicle.VehicleType;
import com.jonathan.domainmodel.Road.RoadCondition;
import com.jonathan.domainmodel.Car;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.VehicleFactory;
import com.jonathan.helper.CarBuilder;
import com.jonathan.helper.CodeNeverHereException;

// TODO: Auto-generated Javadoc
/**
 * The Class FactoryBuilderTest.
 */
@SuppressWarnings("unused")
class FactoryBuilderTest {
	
	/**
	 * Before.
	 */
	@Before 
	void before() {
		
	}
	
	/**
	 * After.
	 */
	@After
	void after() {
		
	}

	/**
	 * Test 1.
	 */
	@Test
	void Test1() {

		
		Car thisCar;
		try {
			thisCar = CarBuilder.getInstance().withMaxSpeed(150).withLPHKM(4.9f).withVehicleType(VehicleType.CAR).withWeight(1685).withRegistration("141 CE 76").build();
			for(int i = 10 ; i < 120; i++ ) {
				
					System.out.println("safe breaking distance at " + i + " : " + thisCar.safeBreakingDistance(i,RoadCondition.NORMAL));
			}
		} catch (CodeNeverHereException e) {
			e.printStackTrace();
		}
	}
	

}
