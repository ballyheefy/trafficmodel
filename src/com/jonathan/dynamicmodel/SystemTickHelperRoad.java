
package com.jonathan.dynamicmodel;

import java.util.List;

import com.jonathan.domainmodel.BaseVehicle;
import com.jonathan.domainmodel.Road;
import com.jonathan.helper.BadClassPassedException;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;

public class SystemTickHelperRoad extends SystemTickHelper {
	
	private static final float SYSTEM_ONE_SECOND_TICK = 1;

	public SystemTickHelperRoad() {
		
	}
	
	/**
	 * 
	 * @see com.jonathan.dynamicmodel.SystemTickHelper#tickEvent(java.lang.Object)
	 * TODO - 
	 */
	
	@Override
	public void tickEvent(Object thisObjectInstance, int timeInSeconds) throws BadClassPassedException {
		if( thisObjectInstance.getClass() != Road.class) {
			EventLogger.getInstance().logEvent(TraceLevel.CRITIAL, "Non Road object passed to SystemTickhelper");
			throw new BadClassPassedException("Non Road object passed to SystemTickhelper");
		}
		Road rd = (Road) thisObjectInstance; // safe to cast with check above
		
		List<BaseVehicle>  con = rd.access().get_con();
		int lastElement = con.size()-1;
		// Iterate through all he vehicles on the road
		for(int idx=lastElement; idx>=0 ; idx--) {
			synchronized (con) {
				try {
					BaseVehicle v = con.get(idx);
					int distanceToNext = rd.distanceToNextvehicle(idx);
					int distanceToEnd = rd.distanceToEnd(idx);
					v.computeLocation(timeInSeconds);
					v.computeSpeed(distanceToNext, distanceToEnd,SYSTEM_ONE_SECOND_TICK,Road.RoadCondition.NORMAL);  // TODO - need to account for a bad road condition added to task list
					v.logProgress();
					
				} catch (ArrayIndexOutOfBoundsException e) {
					EventLogger.getInstance().logEvent(TraceLevel.CRITIAL, e.toString());
					System.exit(-1);
				}
			}
			
		}
		
	}

}
