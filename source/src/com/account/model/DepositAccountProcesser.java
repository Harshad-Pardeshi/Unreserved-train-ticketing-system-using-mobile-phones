package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;

import com.account.controller.Account;
import com.account.controller.RailAccount;

public class DepositAccountProcesser extends Processer {

	public DepositAccountProcesser(int pAccountNo,
			double pAmount,
			Calendar pCurrentTime)
	{
		super(pAccountNo, pAmount, TRANSACTION_TYPE_DEPOSIT_ID, pCurrentTime);
	}
	
	public Account processSub(Connection pConnection) throws Exception
	{		
		return depositAccount(pConnection, miAccountNo, mdAmount, mcalTransactionTime);
	}
	
	protected void saveTransactionDetails(Connection pConnection,
			int pTransactionId) throws Exception {
		// Do nothing	
	}
	
	public Account depositAccount(Connection pConnection, int pAccountNo, double pAmount, Calendar pCreatedTime) throws Exception
	{
		String lstrMethodName = getClass().getName() + " :: depositAccount :: ";
		System.out.println(lstrMethodName + "START");
		Account account = null;
		
		Statement stmt = pConnection.createStatement();
		// Query :
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_UPDATE_1, new Object[]{pAccountNo ,
				pAmount,
				pCreatedTime.getTime()
		});
		System.out.println(lstrMethodName + "Query_Key :: " + ACCOUNTMASTER_UPDATE_1 + " Query :: " + lstrQuery);
		int count = stmt.executeUpdate(lstrQuery);

		if(count == 0)
		{
			throw new Exception("Error while deposit account");
		}
		
		account = RailAccount.getAccountInstance(pConnection, pAccountNo);
	
		System.out.println(lstrMethodName + "END");
		return account;
	}
}
