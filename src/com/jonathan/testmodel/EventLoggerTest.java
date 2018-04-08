package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.helper.EventLogger;
import com.jonathan.helper.EventLogger.TraceLevel;

class EventLoggerTest {

	@Test
	void test() {
		EventLogger.getInstance().setTraceLevel(TraceLevel.CRITIAL);
		EventLogger.getInstance().logEvent(TraceLevel.CRITIAL,"This is an error string");
		EventLogger.getInstance().setTraceLevel(TraceLevel.MEDIUM);
		EventLogger.getInstance().logEvent(TraceLevel.LOW,"This is an error string that should not appear");
		EventLogger.getInstance().setTraceLevel(TraceLevel.MEDIUM);
		EventLogger.getInstance().logEvent(TraceLevel.MEDIUM,"This is an MEDIUM error string that should  appear");
		EventLogger.getInstance().closeEventLogger();
	}

}
