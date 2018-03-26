package com.account.sms;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


import com.account.model.TicketProcesser;
import com.account.model.Util;

public class ReaderEngine //implements Runnable
{
	
	public void readSMS()
	{
	
		processNewSMSFromDB();
//		Thread t = new Thread(this);
//		t.setDaemon(true);
//		t.start();
	}
	
	/*public void run() {
		try {
			while (true) {

				Calendar lCalendar = Calendar.getInstance();
				// Util.storeReceivedSMS(pMobile, pFromAddress,
				// pToAddress, lCalendar);
				Vector<Vector> lCollection = Util.getQueuedSMS();
				Set<Integer> bookedTicket =  new HashSet<Integer>();
				Set<Integer> failedBookedTicket =  new HashSet<Integer>();
				int index = 0;
				//for (Vector lvec : lCollection) {
				for (int i=0; i<lCollection.size();i++) 
				{
					Vector lvec = lCollection.elementAt(i);
					
					int id = (Integer) lvec.elementAt(0);
					String mobile = (String) lvec.elementAt(1);
					String fromaddress = (String) lvec.elementAt(2);
					String toaddress = (String) lvec.elementAt(3);

					try {
						TicketProcesser.processBookTicket(mobile,
								fromaddress, toaddress);
					} catch (Exception e) {
						System.out.println("Error: booking ticket for sms no : "+ id);
						failedBookedTicket.add(id);
						continue;
					}
					
					bookedTicket.add(id);
				}
				try
				{
					if(bookedTicket.size() > 0)
					{
						Util.ticketBooked(bookedTicket, 1);
					}
					if(failedBookedTicket.size() > 0)
					{
						Util.ticketBooked(failedBookedTicket, 2);
					}
					
				}
				catch (Exception e) {
					System.out.println("Error: booking confirmed tickets " );
					continue;
				}
				
				 //read the messages every 10 secs.
				 
				try {
					System.out.println("sleeping now");
					Thread.sleep(1000);
				} catch (InterruptedException e) { do nothing 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	void processNewSMSFromDB()
	{
		try {
				Calendar lCalendar = Calendar.getInstance();
				// Util.storeReceivedSMS(pMobile, pFromAddress,
				// pToAddress, lCalendar);
				Vector<Vector> lCollection = Util.getQueuedSMS();
				Set<Integer> successBookedTicket =  new HashSet<Integer>();
				Set<Integer> failedBookedTicket =  new HashSet<Integer>();
				int index = 0;
				//for (Vector lvec : lCollection) {
				for (int i=0; i<lCollection.size();i++) 
				{
					Vector lvec = lCollection.elementAt(i);
					
					int id = (Integer) lvec.elementAt(0);
					String mobile = (String) lvec.elementAt(1);
					String fromaddress = (String) lvec.elementAt(2);
					String toaddress = (String) lvec.elementAt(3);

					try {
						TicketProcesser.processBookTicket(mobile,
								fromaddress, toaddress);
					} catch (Exception e) {
						System.out.println("Error: booking ticket for sms no : "+ id);
						failedBookedTicket.add(id);
						continue;
					}
					
					successBookedTicket.add(id);
				}
				try
				{
					if(successBookedTicket.size() > 0)
					{
						Util.ticketBooked(successBookedTicket, 1);
					}
					if(failedBookedTicket.size() > 0)
					{
						Util.ticketBooked(failedBookedTicket, 2);
					}
					
				}
				catch (Exception e) {
					System.out.println("Error: booking confirmed tickets " );
					// continue;
				}
				/*
				 * read the messages every 10 secs.
				 */
				try 
				{
					System.out.println("sleeping now");
					Thread.sleep(1000);
				} catch (InterruptedException e) {/* do nothing */
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}

