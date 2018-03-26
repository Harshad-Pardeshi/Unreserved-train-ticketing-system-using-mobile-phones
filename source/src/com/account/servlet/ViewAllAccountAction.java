package com.account.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;

public class ViewAllAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

		Vector<Account> lAccountCollection = populateAllAccounts();
		pRequest.setAttribute("rail_account_obj_collection", lAccountCollection);
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/viewAllAccount.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}

	private Vector<Account> populateAllAccounts() 
	{
		try
		{
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.populateAllAccounts();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
