package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;

import com.account.controller.Account;
import com.account.controller.RailAccount;

public class OpenNewAccountProcesser extends Processer {

	public OpenNewAccountProcesser(int pAccountNo,
			double pAmount,
			Calendar pCurrentTime)
	{
		super(pAccountNo, pAmount, TRANSACTION_TYPE_OPEN_ID, pCurrentTime);
	}
	
	public Account processSub(Connection pConnection) throws Exception
	{
		String lstrName = null;
		//String lstrMail = null;
		String lstrMobile = null;
		int liAccountType = 0;
		
		if(mmapProcess != null)
		{	
			lstrName = (String) mmapProcess.get(MAP_CUSTOMER_NAME);
			lstrMobile = (String) mmapProcess.get(MAP_CUSTOMER_MOBILE);
			liAccountType = Integer.parseInt(mmapProcess.get(MAP_ACCOUNT_TYPE) + "");
		}
		return saveAccount(pConnection, mdAmount, liAccountType, lstrName, lstrMobile, mcalTransactionTime);
	}
	
	@Override
	protected void saveTransactionDetails(Connection pConnection,
			int pTransactionId) throws Exception {
		// Do nothing
	} 
	
	public Account saveAccount(Connection pConnection, double pAmount, int pAccountType, String pName, String pMobile, Calendar pCreatedTime) throws Exception
	{
		String lstrMethodName = getClass().getName() + " :: saveAccount :: ";
		System.out.println(lstrMethodName + "START");
		Account account = null;
		
		int liAccountNo = Util.getNextFFN(pConnection, FFN_ACCOUNT_ID);
		
		Statement stmt = pConnection.createStatement();
		// Query :
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_INSERT_1, new Object[]{liAccountNo ,
				pName, 
				pAccountType, 
				pAmount, 
//				pMail,
				pMobile,
				1,
				pCreatedTime.getTime(), 
				pCreatedTime.getTime()
		});
		System.out.println(lstrMethodName + "Query_Key :: " + ACCOUNTMASTER_INSERT_1 + " Query :: " + lstrQuery);
		
		int count = stmt.executeUpdate(lstrQuery);

		if(count == 0)
		{
			throw new Exception("Error creating account");
		}
		
		account = RailAccount.getAccountInstance(pConnection, liAccountNo);
		
		System.out.println(lstrMethodName + "END");
		return account;
	}
}
