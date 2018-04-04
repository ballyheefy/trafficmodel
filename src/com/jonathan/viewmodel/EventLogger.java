package com.jonathan.viewmodel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class EventLogger {
	
	private final static EventLogger _el = new EventLogger();
	private final static String fn = "debug_trace_output.txt";
	private final static String criticalMessage="CRITICAL : ";
	private final static String highMessage="HIGH : ";
	private final static String mediumMessage="MEDIUM : ";
	private final static String lowMessage="LOW : ";
	
	public enum TraceLevel {
		CRITIAL,
		HIGH,
		MEDIUM,
		LOW
	}
	
	
	private EventLogger() {
		
		try {
			EventLogger._fw = new FileWriter(fn,false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_pw = new PrintWriter(_fw);
	}
	
	public static EventLogger getInstance() {
		return _el;
	}
	
	// TODO make this real
	public boolean logError(TraceLevel tl,final String s) {
		
		if(s.length()>0) {
			switch (tl) {
			case CRITIAL:
				_pw.println(new Timestamp(System.currentTimeMillis())+ " " + criticalMessage + s);
				return true;
			case HIGH:
				if( _tl == TraceLevel.CRITIAL)
					return false;
				_pw.println(new Timestamp(System.currentTimeMillis())+ " "+ highMessage + s);
				return true;
			case MEDIUM:
				if( _tl == TraceLevel.CRITIAL || _tl == TraceLevel.HIGH)
					return false;
				_pw.println(new Timestamp(System.currentTimeMillis())+ " "+ mediumMessage + s);
				return true;
			case LOW:
				if( _tl == TraceLevel.CRITIAL || _tl == TraceLevel.HIGH || _tl == TraceLevel.MEDIUM)
					return false;
				_pw.println(new Timestamp(System.currentTimeMillis())+ " " + lowMessage + s);
				return true;
			default:
				assert true; 
				break;
			}
		}	
		return false;
	}
	
	public void setTraceLevel(TraceLevel tl) {
		_tl = tl;
	}
	
	public void close() {
		_pw.close();
	}
	
	// private variables 
	private static FileWriter _fw;
	private static PrintWriter _pw;
	private static TraceLevel _tl;

}
