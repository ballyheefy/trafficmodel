package com.jonathan.testmodel;

// import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.JunctionFactory;
import com.jonathan.helper.EventLogger;
// import com.jonathan.helper.EventLogger.TraceLevel;
import com.jonathan.helper.Network_XML_Helper;

class Network_XML_HelperTestTwo {
	
	@Test
	void test() {
		System.out.println("Starting test 1");
		assert Network_XML_Helper.getInstance().buildTheModel();
		String[] allJunc = JunctionFactory.getInstance().toStringArrayAllJunctions();
		int i;
		for(i=0 ; i< allJunc.length ; i++) {
			String ts = "Junction [" + i + "] name [" +allJunc[i] + "]";
			System.out.println(ts);
			EventLogger.getInstance().logEvent(EventLogger.TraceLevel.LOW, "Junction [" + i + "] name [" + allJunc[i] + "]");
		}
		assert (i >= 2);
		EventLogger.getInstance().flushEventLoggerOutput();
	}

}
