package com.account.sms;

import static com.account.controller.CommonConstants.*;

import java.util.ArrayList;
import java.util.Calendar;


import com.account.model.Util;

public class PollingEngine implements Runnable
{
	
	public void listenSMS()
	{
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	
	public void run ()
	{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Polling run");
		try
		{
			// SMSReader.startEngine("COM7", 115200, "", "");
			while( true )
			{
				boolean isNewSMS = false;
				SMSHandler.initialize(SMS_IN_PORT, SMS_IN_BAUD_RATE, "", "");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ in while");
				
				ArrayList<SMSMessage> unReadMessages = SMSHandler.getUnReadMessages();
				
				SMSHandler.stopService();
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ after unread");
				/*
				 * Insert every message into the db
				 */
				System.out.println("unReadMessages : " +unReadMessages.size());
				
				for( int i = 0; i < unReadMessages.size(); i++ )
				{
					isNewSMS = true;
					SMSMessage smsMessage = unReadMessages.get(i);
					/*
					 * Insert the message into the database
					 */
					
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println(smsMessage.getBody());
					System.out.println(smsMessage.getOriginator());
					String sms = smsMessage.getBody();
					
					System.out.println("********** SMS : " + sms);
					if((sms == null)||(sms.length() != 11))
					{
						continue;
					}
					String lstrMobileNo = smsMessage.getOriginator();
					int length = lstrMobileNo.length();
					if(length > 10)
					{
						lstrMobileNo = lstrMobileNo.substring(length-10 , length);
					}
					String token = sms.substring(0, 3);
					if("TKT".equals(token) == false)
					{
						continue;
					}
					
					String from = sms.substring(4, 7);
					String to = sms.substring(8, 11);
					
					System.out.println("$$$$$$$ VALIDATION SUCCESS");
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					Calendar lCalendar = Calendar.getInstance();
//					Util.storeReceivedSMS(pMobile, pFromAddress, pToAddress, lCalendar);
					// Util.storeReceivedSMS("9619783001", "CST", "DOM", lCalendar);
					Util.storeReceivedSMS(lstrMobileNo, from, to, lCalendar);
					System.out.println("Inserted at : " + lCalendar.getTime());

				}
				/*try{
					Thread.sleep(1000);
					SMSHandler.stopService(); 					
				}
				catch (Exception e) {
					e.printStackTrace();
				}*/
				if(isNewSMS)
				{
					try
					{
						ReaderEngine lReaderEngine = new ReaderEngine();
						lReaderEngine.readSMS();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("@@@@@ Polling run END");
		
	}
}
