package com.account.servlet;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;

public class WithdrawAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		
		int liAccountNo = Integer.parseInt(WebUtil.readParameter(pRequest, "accountNo") + "");
		double ldAmount = Double.parseDouble(WebUtil.readParameter(pRequest,"amount")+ "");
		String fromAddress = WebUtil.readParameter(pRequest, "from_address") +"";
		String toAddress = WebUtil.readParameter(pRequest,"to_address") + "";
		
		Account lAccount = withdrawAccount(liAccountNo, ldAmount,fromAddress, toAddress);
		
		saveMessage(pRequest, "Withdrawn Rs. {0,number,#} for Customer ''{1}({2,number,#})''", new Object[]{ldAmount, lAccount.getName(), lAccount.getId()});;
		pRequest.setAttribute("account_no", liAccountNo+"");
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/ViewAccount.do");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	private Account withdrawAccount(int pAccountNo, double pAmount,String fromAddress, String toAddress) 
	{
		try
		{
			Calendar lcalCurrentTime = Calendar.getInstance();
			Accountant lAccountant = Accountant.getAccountant();
			return lAccountant.withdrawAccount(pAccountNo, pAmount, fromAddress, toAddress, lcalCurrentTime);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
