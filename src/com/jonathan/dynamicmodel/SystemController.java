package com.jonathan.dynamicmodel;

import com.jonathan.viewmodel.EventLogger;
import com.jonathan.viewmodel.EventLogger.TraceLevel;

public class SystemController implements Runnable {

	public SystemController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void run() {
		
		Thread t = new Thread();
		t.start();
		
		for(int i=0 ; i< 10 ; i++ ) {
			EventLogger.getInstance().logError(TraceLevel.CRITIAL,"Running System Controller");
			synchronized(this) {
				try {
					this.wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalMonitorStateException e) {
					e.printStackTrace();
				}
			}
		}
		EventLogger.getInstance().close();
	}

}
