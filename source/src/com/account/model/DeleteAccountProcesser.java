package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;

import com.account.controller.Account;
import com.account.controller.RailAccount;

public class DeleteAccountProcesser extends Processer {

	public DeleteAccountProcesser(int pAccountNo,
			double pAmount,
			Calendar pCurrentTime)
	{
		super(pAccountNo, pAmount, TRANSACTION_TYPE_DELETE_ID, pCurrentTime);
	}
	
	public Account processSub(Connection pConnection) throws Exception
	{
		return deleteAccount(pConnection, miAccountNo, mdAmount, mcalTransactionTime);
	}
	
	@Override
	protected void saveTransactionDetails(Connection pConnection,
			int pTransactionId) throws Exception {
		// Do nothing 
	}
	public Account deleteAccount(Connection pConnection, int pAccountNo, double pAmount, Calendar pCreatedTime) throws Exception
	{
		String lstrMethodName = getClass().getName() + " :: deleteAccount :: ";
		System.out.println(lstrMethodName + "START");
		Account account = null;
		
		Statement stmt = pConnection.createStatement();
		// Query : 
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_DELETE_2, new Object[]{pAccountNo ,
				0,
				pAmount, 
				pCreatedTime.getTime()
		});
		System.out.println(lstrMethodName + "Query_Key :: " + ACCOUNTMASTER_DELETE_2 + " Query :: " + lstrQuery);
		
		int count = stmt.executeUpdate(lstrQuery);

		if(count == 0)
		{
			throw new Exception("Error deleting account");
		}
		
		account = RailAccount.getDeletedAccountInstance(pConnection, pAccountNo);
		
		System.out.println(lstrMethodName + "END");
		return account;
	}
}
