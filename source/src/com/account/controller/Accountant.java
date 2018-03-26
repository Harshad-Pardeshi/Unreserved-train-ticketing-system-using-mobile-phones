package com.account.controller;

import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.account.model.DeleteAccountProcesser;
import com.account.model.DepositAccountProcesser;
import com.account.model.OpenNewAccountProcesser;
import com.account.model.Processer;
import com.account.model.WithdrawAccountProcesser;

import static com.account.controller.CommonConstants.*;

public class Accountant{

	private static Accountant sSingelton;
	
	private Accountant(){}
	
	public static Accountant getAccountant(){
		if(sSingelton == null)
			sSingelton = new Accountant();
		return sSingelton;
	}

	/**
		Opens a new Savings or Current account
		@param pAmount Initial deposit
		@param pAccountType true for SavingsAccount and false for CurrentAccount
		@return The newly opened account
	*/
	public final Account openAccount(double pAmount, int pAccountType, String pCustName, String pCustMobile, Calendar pCreatedTime) throws Exception{
	
		Map<String, Object> mmapParams = new HashMap<String, Object>();
		mmapParams.put(MAP_CUSTOMER_NAME, pCustName);
		//mmapParams.put(MAP_CUSTOMER_MAIL, pCustMail);
		mmapParams.put(MAP_CUSTOMER_MOBILE, pCustMobile);
		mmapParams.put(MAP_ACCOUNT_TYPE, pAccountType);
		
		Processer p = new OpenNewAccountProcesser(0, pAmount, pCreatedTime);
		Account lAccount = p.process(mmapParams);
		return  lAccount;
	}

	public final Account depositAccount(int pAccountNo, double pAmount, Calendar pCreatedTime) throws Exception{
		
		Processer p = new DepositAccountProcesser(pAccountNo, pAmount, pCreatedTime);
		return p.process(null);
	}
	
	public final Account withdrawAccount(int pAccountNo, double pAmount, String from, String to, Calendar pCreatedTime) throws Exception{
	
		Map<String, Object> mmapParams = new HashMap<String, Object>();
		mmapParams.put(MAP_FROM_ADDRESS, from);
		mmapParams.put(MAP_TO_ADDRESS, to);
		
		Processer p = new WithdrawAccountProcesser(pAccountNo, pAmount, pCreatedTime);
		return p.process(mmapParams);
	}

	public final Vector<Account> populateAllAccounts() throws Exception{
		
		return RailAccount.loadAllAccount();
	}
	
	public final Vector<Transaction> populateTransaction(int pAccountNo) throws Exception{
		
		return RailAccount.loadTransaction(pAccountNo);
	}
	
	public final Vector<Transaction> populateAllTransactions(int pAccountNo) throws Exception{
		
		return RailAccount.loadTransaction(pAccountNo);
	}

	public final Account populateAccounts(Connection pConnection, int AccountNo) throws Exception{
		
		return RailAccount.getAccountInstance(pConnection, AccountNo);
	}
	
	public Account deleteAccount(int pAccountNo, double pAmount, Calendar pCurrentTime)
	{
		Processer p = new DeleteAccountProcesser(pAccountNo, pAmount, pCurrentTime);
		return p.process(null);
	}
	
}

