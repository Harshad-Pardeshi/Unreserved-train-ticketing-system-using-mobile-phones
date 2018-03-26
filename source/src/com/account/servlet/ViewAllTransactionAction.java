package com.account.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Accountant;
import com.account.controller.Transaction;

public class ViewAllTransactionAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

		String str = WebUtil.readParameter(pRequest, "accountNo")+"";
		int liAccountNo = Integer.parseInt(str);
		Vector<Transaction> lTransactionCollection = populateAllAccountTransaction(liAccountNo);
		pRequest.setAttribute("rail_transaction_obj_collection", lTransactionCollection);
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/viewAllTransaction.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);	
	}

	private Vector<Transaction> populateAllAccountTransaction(int pAccountNo) 
	{
		try
		{
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.populateAllTransactions(pAccountNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
