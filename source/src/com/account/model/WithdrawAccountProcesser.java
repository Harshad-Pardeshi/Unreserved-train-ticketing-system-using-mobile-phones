package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;

import com.account.controller.Account;
import com.account.controller.RailAccount;

public class WithdrawAccountProcesser extends Processer {

	public WithdrawAccountProcesser(int pAccountNo,
			double pAmount,
			Calendar pCurrentTime)
	{
		super(pAccountNo, pAmount, TRANSACTION_TYPE_WITHDRAW_ID, pCurrentTime);
	}
	
	public Account processSub(Connection pConnection) throws Exception
	{		
		return withdrawAccount(pConnection, miAccountNo, mdAmount, mcalTransactionTime);
	}
	
	@Override
	protected void saveTransactionDetails(Connection pConnection, int pTransactionId) throws Exception {
		// TODO SaveTicket information.
		String fromAddress = (String)mmapProcess.get(MAP_FROM_ADDRESS);
		String toAddress = (String)mmapProcess.get(MAP_TO_ADDRESS);
		
		saveTicket(pConnection, miAccountNo, pTransactionId, fromAddress, toAddress, mdAmount, mcalTransactionTime);
	}
	
	private Account withdrawAccount(Connection pConnection, int pAccountNo, double pAmount, Calendar pCreatedTime) throws Exception
	{
		String lstrMethodName = getClass().getName() + " :: withdrawAccount :: ";
		System.out.println(lstrMethodName + " START .");
		Account account = RailAccount.getAccountInstance(pConnection, pAccountNo);
		
		account.validateWithdraw(pAmount);
		
		Statement stmt = pConnection.createStatement();
		// Query : "UPDATE ACCOUNTMASTER SET BALANCE=BALANCE+{1,number,#}, LM_TIME=''{2,date,yyyy-MM-dd HH:mm:ss}'' WHERE ID={0,number,#}";
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_UPDATE_1, new Object[]{pAccountNo ,
				pAmount * (-1),
				pCreatedTime.getTime()
		});
		System.out.println(lstrMethodName + "Query_Key :: " + ACCOUNTMASTER_UPDATE_1 + " Query :: " + lstrQuery);
		int count = stmt.executeUpdate(lstrQuery);

		if(count == 0)
		{
			throw new Exception("Error while withdraw account");
		}
		
		account = RailAccount.getAccountInstance(pConnection, pAccountNo);
	
		System.out.println(lstrMethodName + "END");
		return account;
	}
	
	
	private void saveTicket(Connection pConnection, 
			int pAccountNo, 
			int pTransactionNo, 
			String fromAddress, 
			String toAddress, 
			double pAmount, 
			Calendar pCreatedTime) throws Exception
	{
		String lstrMethodName = getClass().getName() + " :: saveTicket :: ";
		System.out.println(lstrMethodName + " START .");
		
		int liTicketNo = Util.getNextFFN(pConnection, FFN_TICKET_ID );
		
		Statement stmt = pConnection.createStatement();
		/* Query : 
		 * "INSERT INTO TICKETMASTER 
		 * (ID, ACCOUNT_ID, TRANSACTION_ID, FROMADDRESS, TOADDRESS, AMOUNT, CREATED_TIME) 
		 * VALUES({0,number,#}, 
		 * {1,number,#}, 
		 * {2,number,#}, 
		 * ''{3}'', 
		 * ''{4}'', 
		 * {5,number,#}, 
		 * ''{6,date,yyyy-MM-dd HH:mm:ss}'' )"; */
		final String lstrQuery = QueryBuilder.getQuery(TICKETMASTER_INSERT_1, new Object[]{
				liTicketNo,
				pAccountNo ,
				pTransactionNo, 
				fromAddress,
				toAddress, 
				pAmount, 
				pCreatedTime.getTime()
		});
		System.out.println(lstrMethodName + "Query_Key :: " + TICKETMASTER_INSERT_1 + " Query :: " + lstrQuery);
		
		int count = stmt.executeUpdate(lstrQuery);

		if(count == 0)
		{
			throw new Exception("Error creating ticket");
		}
		
		System.out.println(lstrMethodName + "END");
	}
}
