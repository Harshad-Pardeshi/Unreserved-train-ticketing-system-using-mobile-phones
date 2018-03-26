package net.java.railway.messaging;

import java.util.Set;

import net.java.railway.SMS;
import net.java.railway.core.RailwayProcesser;

public class PollingEngine {

	RailwayProcesser railwayProcesser = new RailwayProcesser();
	public static final int POLLING_STATUS_PAUSED = 0;
	public static final int POLLING_STATUS_RUNNING = 1;
	public static int globalPollingStatus = POLLING_STATUS_PAUSED;

	public void listenSMS() {
		Thread t1 = new Thread(SMSPollingThread);
		t1.setDaemon(true);
		t1.start();

		Thread t2 = new Thread(SMSProcessingThread);
		t2.setDaemon(true);
		t2.start();
	}

	Runnable SMSPollingThread = new Runnable() {
		@Override
		public void run() {
			SMSHandler.readSMS();
		}
	};

	Runnable SMSProcessingThread = new Runnable() {
		@Override
		public void run() {
			while (true) {

				// if (PollingEngine.globalPollingStatus ==
				// PollingEngine.POLLING_STATUS_PAUSED) {
				// return;
				// }

				System.out.println("@@@@ Polling run");
				try {
					Set<SMS> setOutSMS = railwayProcesser.processSMS();
					SMSHandler.sendSMS(setOutSMS);

					try {
						System.out.println("sleeping now");
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// do nothing
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
}
