package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.Junction;
import com.jonathan.domainmodel.Road;
import com.jonathan.domainmodel.TrafficRoute;
import com.jonathan.domainmodel.TrafficRouteFactory;

class RoadTest {
	
	
	@Test
	void test() {
		Junction j1 = new Junction(1l, "Limerick");
		Junction j2 = new Junction(2l, "Dublin");
		Road r1 = new Road(j1, j2, Road.LaneCount.ONE_LANE, 10_000, "Limerick->Dublin");
		Road r2 = new Road(j2, j1, Road.LaneCount.ONE_LANE, 10_000, "Dublin->Limerick");
		TrafficRoute tr = TrafficRouteFactory.getInstance().MakeTrafficRoute(r1, r2, "N7");
		assert tr != null;
		String s[] = TrafficRouteFactory.getInstance().getRouteDetails(); 
		for(int i=0 ; i <s.length ; i++) 
			System.out.println(s[i]);
	}

}

