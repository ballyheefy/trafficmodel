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
	void test1() {
		VehicleFactory factory = VehicleFactory.getInstance(); 
		assert factory != null;
	}
	
	/**
	 * Test 2.
	 */
	@Test 
	void Test2() {
	
		BaseVehicle thisCar = VehicleFactory.getInstance().buildVehicle(VehicleType.CAR, 150, "141 CE 76", 1685, 4.9f);
		System.out.println("safe breaking distance at 100 : " + thisCar.safeBreakingDistance(100));
		System.out.println("safe breaking distance at 100 : " + thisCar.safeBreakingDistance(100,RoadCondition.NORMAL));
		System.out.println("safe breaking distance at 100 : " + thisCar.safeBreakingDistance(100,RoadCondition.BAD));
		System.out.println("safe breaking distance at 100 : " + thisCar.safeBreakingDistance(100,RoadCondition.DANGEROUS));
		
	}

}
