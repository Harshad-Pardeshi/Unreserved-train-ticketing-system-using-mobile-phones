package com.account.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;

public class DepositAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		
		int liAccountNo = Integer.parseInt(WebUtil.readParameter(pRequest, "accountNo")+"");
		double ldAmount = Double.parseDouble(WebUtil.readParameter(pRequest, "amount")+ "");
		
		Account lAccount = depositAccount(liAccountNo, ldAmount);
		
		saveMessage(pRequest, "Deposited Rs. {0,number,#} for Customer ''{1}({2,number,#})''", new Object[]{ldAmount, lAccount.getName(), lAccount.getId()});;
		pRequest.setAttribute("account_no", liAccountNo+"");
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/ViewAccount.do");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	private Account depositAccount(int pAccountNo, double pAmount) 
	{
		try
		{
			Calendar lcalCurrentTime = Calendar.getInstance();
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.depositAccount(pAccountNo, pAmount, lcalCurrentTime);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
