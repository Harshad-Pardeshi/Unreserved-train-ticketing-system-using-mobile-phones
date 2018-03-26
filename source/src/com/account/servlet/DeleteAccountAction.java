package com.account.servlet;


import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;

public class DeleteAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		Account lAccount = null;
		
		String lstrAccountNo = WebUtil.readParameter(pRequest, "accountNo") + "";
		
		int liAccountNo = Integer.parseInt(lstrAccountNo);
		lAccount = loadAccount(liAccountNo);
		lAccount = deleteAccount(lAccount);
		
		saveMessage(pRequest, "Account Deleted for Customer ''{0}({1,number,#})''", new Object[]{lAccount.getName(), lAccount.getId()});
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("preDeleteAccount.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	Account loadAccount(int pAccountNo)
	{
		try{
		Accountant lAccountant = Accountant.getAccountant();
		return lAccountant.populateAccounts(null, pAccountNo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Account deleteAccount(Account pAccount) 
	{
		try
		{
			Calendar lCurrentTime = Calendar.getInstance();
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.deleteAccount(pAccount.getId(), pAccount.getBalance(), lCurrentTime);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
