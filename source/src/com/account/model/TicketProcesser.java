package com.account.model;
import java.util.Calendar;

import com.account.controller.Accountant;
import com.account.controller.RailAccount;
import com.account.controller.Ticket;

public class TicketProcesser
{

	public static void processBookTicket(String pMobileNo,
			String pFromAddress,
			String pToAddress) throws Exception
		{
			int liAccountId;
			double liAmount; 
			System.out.println("TicketProcesser :: processBookTicket :: START");
			liAccountId = RailAccount.getAccountIdFromMobile(pMobileNo);
			System.out.println("TicketProcesser :: processBookTicket ::  mobile::  " + pMobileNo);
			System.out.println("TicketProcesser :: processBookTicket :: acc no " +liAccountId);
			liAmount = Ticket.calculateTicketAmount(pFromAddress, pToAddress);
			System.out.println("TicketProcesser :: processBookTicket :: amt "+ liAmount);
			Calendar lcalCurrentTime = Calendar.getInstance();
			Accountant lAccountant = Accountant.getAccountant();
			
			if( (liAccountId == 0) ||(liAmount == 0))
			{
				throw new Exception("Error while processing ticket: ");
			}
				
			lAccountant.withdrawAccount(liAccountId, liAmount, pFromAddress, pToAddress, lcalCurrentTime);
				
		}
}
