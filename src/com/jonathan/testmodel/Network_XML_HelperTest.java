package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.domainmodel.JunctionFactory;
import com.jonathan.helper.EventLogger;
import com.jonathan.helper.Network_XML_Helper;


class Network_XML_HelperTest {

	
	/* @BeforeAll
	void prepare() {
		// right now while testing we want to see what is going on in the log output file
		// EventLogger.getInstance().setTraceLevel(TraceLevel.LOW);
	}
	*/ 
	
	@Test
	void test() {
		System.out.println("Starting test 1");
		assert Network_XML_Helper.getInstance().buildTheModel();
		String[] allJunc = JunctionFactory.getInstance().toStringArrayAllJunctions();
		for(int i=0 ; i< allJunc.length ; i++) {
			EventLogger.getInstance().logEvent(EventLogger.TraceLevel.LOW, "Junction [" + i + "] name [" +allJunc[i] + "]");
		}
	}

}
