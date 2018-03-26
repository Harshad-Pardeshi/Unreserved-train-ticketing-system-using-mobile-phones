package com.account.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;
import com.account.model.DB;

public class ViewAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		Account lAccount = null;
		
		String lstrAccountNo = WebUtil.readParameter(pRequest,"accountNo")+"";
		
		int liAccountNo = Integer.parseInt(lstrAccountNo);
		lAccount = loadAccount(liAccountNo);
		
		pRequest.setAttribute("rail_account_obj", lAccount);
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("viewAccount.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	private Account loadAccount(int pAccountNo) 
	{
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection();
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.populateAccounts(lConnection, pAccountNo);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
			}catch (Exception e) {
			}
		}
		return null;
	}

}
