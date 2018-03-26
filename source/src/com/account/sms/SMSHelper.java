package com.account.sms;

import static com.account.controller.CommonConstants.*;

import java.sql.Connection;
import com.account.controller.SMS;
import com.account.controller.Ticket;
import com.account.controller.Transaction;
import com.account.model.Util;
 
public class SMSHelper 
{

	public static void sendSMS(String pMobile, String pSubject, String pBody) throws Exception
	{
		SMSHandler_Send.sendSMS(pMobile, pSubject, pBody);
	}
	
	public static void sendSMS(Connection pConnection, int pSMSId) throws Exception
	{
		SMS lSMS = SMS.getSMS(pConnection, pSMSId);
		sendSMS(lSMS.getMobile(), lSMS.getSubject(), lSMS.getBody());
	}
	
	public static String prepareSMSSubject(int pTransactionType)
	{
		return COMPANY_DISPLAY_NAME +" : Transaction : "  + Transaction.getDisplayType(pTransactionType);
	}
	
	
	public static String prepareSMSBody(Connection pConnection, 
			int pTransactionId
			) throws Exception
	{
		Ticket lTicket = Ticket.getInstance(pConnection, pTransactionId, false);
		double balance = Ticket.getCurrentBalance(pConnection, lTicket.getId());
		
		StringBuilder sbSMSBody = new StringBuilder();
		sbSMSBody.append("Ticket No: " + lTicket.getId());
		sbSMSBody.append("\n" + Util.getDisplayDate(lTicket.getCreatedTime()));
		sbSMSBody.append("\n");
		sbSMSBody.append("\n" +lTicket.getFromAddress() + " to " + lTicket.getToAddress());
		sbSMSBody.append("\n");
		sbSMSBody.append("Rs " + lTicket.getAmount() + "/-"); 
		sbSMSBody.append("\n");
		sbSMSBody.append("Journey should commence within 1 hour.");
		if(balance >= 0)
		{
			sbSMSBody.append("\n");
			sbSMSBody.append("Balance : " + balance + "/-");
			sbSMSBody.append("\n");
		}
		sbSMSBody.append("Happy Journey.");
		sbSMSBody.append(COMPANY_DISPLAY_NAME);

		return sbSMSBody.toString();
	}
	
}
