package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.Junction;
import com.jonathan.domainmodel.JunctionFactory;
import com.jonathan.domainmodel.Road;

class RoadTest {
	private static final int oneLane=1;
	
	
	@Test
	void test() {
		Junction j1 = JunctionFactory.getInstance().makeJunction("Limerick"); 
		Junction j2 = JunctionFactory.getInstance().makeJunction("Dublin"); 
		Road r1 = new Road(j1,j2,oneLane,20000 /* 20 KM*/);
		j1.addRoad(r1);
		j2.addRoad(r1);
		
		assert JunctionFactory.getInstance().numberOfJunction() == 2;
		assert j1.numberOfRoads() == 1;
		assert j2.numberOfRoads() == 1;
		assert Junction.numberOfBadJunctions() == 0;
		
		// create a road with only a source junction the factory will add the new junction to the collection 
		Road r2 = new Road(JunctionFactory.getInstance().makeJunction("Dungarvan"),oneLane,10000 );
		assert j2.addRoad(r2);
		assert j2.numberOfRoads()==2;
		
		assert Junction.junctionExists("Dungarvan") != null;
		assert Junction.junctionExists("Limerick") != null;
		assert Junction.junctionExists("Dublin") != null;
		
		Road r4 = new Road(oneLane,10000 );
		r4.setDestination(JunctionFactory.getInstance().makeJunction("Limerick"));
		r4.setSource(JunctionFactory.getInstance().makeJunction("Tralee"));
		
		
		
	}

}
