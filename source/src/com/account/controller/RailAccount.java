package com.account.controller;

import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import com.account.exception.InsufficientFundsException;
import com.account.model.DB;
import com.account.model.QueryBuilder;

public class RailAccount extends Account{
	
	int miId;
	String mstrName;
	int miType;
	//String mstrMailId;
	String mstrMobileNo;
	Calendar mcalCreatedTime;
	Calendar mcalLastmodifiedTime;
	protected double mdBalance;

	public static Account getAccountInstance(Connection pConnection, int pAccountNo) throws Exception
	{
		Account lAccount = null;
		if(pConnection == null)
		{
			pConnection = DB.getConnection();
		}
		
		Statement stmt = pConnection.createStatement();
		// Query : 
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_SELECT_2, new Object[]{pAccountNo});
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		int liAccountType = 0;
		String lstrName = null;
		double liAmount = 0;
		//String lstrMail = null;
		String lstrMobileNo = null;
		Calendar lcalCreatedTime = Calendar.getInstance();
		Calendar lcalLMTime = Calendar.getInstance();
		if(lResultSet.next())
		{
			liAccountType = lResultSet.getInt("TYPE");
			lstrName  = lResultSet.getString("NAME");
			liAmount = lResultSet.getDouble("BALANCE");
			//lstrMail  = lResultSet.getString("MAIL_ID");
			lstrMobileNo = lResultSet.getString("MOBILE_NO");
			lcalCreatedTime.setTimeInMillis(lResultSet.getTimestamp("CREATED_TIME").getTime());
			lcalLMTime.setTimeInMillis(lResultSet.getTimestamp("LM_TIME").getTime());
		}
		
		lAccount = new SmartCardAccount(pAccountNo, lstrName, liAmount, lstrMobileNo, lcalCreatedTime, lcalLMTime);

		return lAccount;
	}

	public static int getAccountIdFromMobile(String pMobileNo) throws Exception
	{
		int liAccountId = 0;
		Statement stmt = null;
		ResultSet lResultSet = null;
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection();
			
			stmt = lConnection.createStatement();
			// Query : 
			final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_SELECT_4, new Object[]{pMobileNo});
			
			lResultSet = stmt.executeQuery(lstrQuery);
			if(lResultSet.next())
			{
				liAccountId = lResultSet.getInt("ID");
			}
			else{
				System.out.println("Invalid mobile no : " + pMobileNo);
				throw new Exception("Invalid mobile no : " + pMobileNo );
			}	
		}
		finally
		{
			lConnection.close();
			lConnection = null;
			
		}
		return liAccountId;
	}
	
	public static Account getDeletedAccountInstance(Connection pConnection, int pAccountNo) throws Exception
	{
		Account lAccount = null;
		if(pConnection == null)
		{
			pConnection = DB.getConnection();
		}
		
		Statement stmt = pConnection.createStatement();
		// Query : 
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_SELECT_3, new Object[]{pAccountNo});
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		int liAccountType = 0;
		String lstrName = null;
		double liAmount = 0;
		//String lstrMail = null;
		String lstrMobile = null;
		Calendar lcalCreatedTime = Calendar.getInstance();
		Calendar lcalLMTime = Calendar.getInstance();
		if(lResultSet.next())
		{
			liAccountType = lResultSet.getInt("TYPE");
			lstrName  = lResultSet.getString("NAME");
			liAmount = lResultSet.getDouble("BALANCE");
//			lstrMail  = lResultSet.getString("MAIL_ID");
			lstrMobile = lResultSet.getString("MOBILE_NO");
			lcalCreatedTime.setTimeInMillis(lResultSet.getTimestamp("CREATED_TIME").getTime());
			lcalLMTime.setTimeInMillis(lResultSet.getTimestamp("LM_TIME").getTime());
		}
		
		
		lAccount = new SmartCardAccount(pAccountNo, lstrName, liAmount, lstrMobile, lcalCreatedTime, lcalLMTime);
		
		return lAccount;
	}
	
	public static Vector<Account> loadAllAccount() throws Exception
	{
		//ACCOUNTMASTER_SELECT_1
		Account lAccount = null;
		Vector<Account> lAccountCollection = new Vector<Account>();
		Connection lConnection = DB.getConnection();
		Statement stmt = lConnection.createStatement();
		// Query : 
		final String lstrQuery = QueryBuilder.getQuery(ACCOUNTMASTER_SELECT_1, null);
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		int liAccountNo = 0;
		int liAccountType = 0;
		String lstrName = null;
		double liAmount = 0;
//		String lstrMail = null;
		String lstrMobile;
		Calendar lcalTime = null; 
		Calendar lcalLMTime = null;
		
		while(lResultSet.next())
		{
			liAccountNo = lResultSet.getInt("ID");
			liAccountType = lResultSet.getInt("TYPE");
			lstrName  = lResultSet.getString("NAME");
			liAmount = lResultSet.getDouble("BALANCE");
//			lstrMail  = lResultSet.getString("MAIL_ID");
			lstrMobile = lResultSet.getString("MOBILE_NO");
			lcalTime = Calendar.getInstance();
			lcalTime.setTimeInMillis(lResultSet.getTimestamp("CREATED_TIME").getTime());
			lcalLMTime = Calendar.getInstance();
			lcalLMTime.setTimeInMillis(lResultSet.getTimestamp("LM_TIME").getTime());
			
			
			lAccount = new SmartCardAccount(liAccountNo, lstrName, liAmount, lstrMobile, lcalTime, lcalLMTime);
			
			lAccountCollection.add(lAccount);
		}
		
		return lAccountCollection;
	}
	
	public int getId(){
		return miId;
	}

	public double getBalance(){
		return mdBalance;
	}	
	
	public String getName() {
		return mstrName;
	}

	public int getType() {
		return miType;
	}
	
	/*public String getMail() {
		return mstrMailId;
	}*/
	
	public String getMobileNo() {
		return mstrMobileNo;
	}
	
	public Calendar getCreatedTime() {
		return mcalCreatedTime;
	}
	
	
	public Calendar getLMTime() {
		return mcalLastmodifiedTime;
	}
//	public void deposit(double amount){	/* mdBalance += amount;*/ }
//
//	public void withdraw(double amount) throws InsufficientFundsException {}
	
	public void validateWithdraw(double amount) throws InsufficientFundsException{}
	
	public static Vector<Transaction> loadTransaction(int pAccountNo) throws Exception
	{
		Transaction lTransaction = null;
		Vector<Transaction> lTransactionCollection = new Vector<Transaction>();
		Connection lConnection = DB.getConnection();
		Statement stmt = lConnection.createStatement();
		// Query : "SELECT ID, TYPE, ACCT_NO, AMOUNT, CREATED_TIME WHERE ACCT_NO={0,number,#}"
		final String lstrQuery = QueryBuilder.getQuery(TRANSACTIONMASTER_SELECT_1, new Object[]{pAccountNo});
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		int liTransactionId = 0;
		int liTransactionType = 0;
		int liAccountNo = 0;
		double liAmount = 0;
		Calendar lcalTime = null; 
		
		while(lResultSet.next())
		{
			liTransactionId = lResultSet.getInt("ID");
			liTransactionType = lResultSet.getInt("TYPE");
			liAccountNo  = lResultSet.getInt("ACCT_NO");
			liAmount = lResultSet.getDouble("AMOUNT");
			
			lcalTime = Calendar.getInstance();
			lcalTime.setTimeInMillis(lResultSet.getTimestamp("CREATED_TIME").getTime());

			lTransaction = new Transaction(liTransactionId, liTransactionType, liAccountNo, liAmount, lcalTime);
			lTransactionCollection.add(lTransaction);
		}
		
		return lTransactionCollection;
	}
	
}









