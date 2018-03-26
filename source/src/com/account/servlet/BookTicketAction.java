package com.account.servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.RailAccount;
import com.account.controller.Ticket;

public class BookTicketAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		
		String lstrMobileNo = WebUtil.readParameter(pRequest, "mobileNo") + "";
		String lstrFromAddress = WebUtil.readParameter(pRequest, "from_address") + "";
		String lstrToAddress = WebUtil.readParameter(pRequest, "to_address") + "";
		
		int liAccountId;
		double liAmount; 
		try
		{
			liAccountId = RailAccount.getAccountIdFromMobile(lstrMobileNo);
			liAmount = Ticket.calculateTicketAmount(lstrFromAddress, lstrToAddress);
		}catch (Exception e) {
			System.out.println("Invalid account : "+e.getMessage());
			return;
		}
		
		pRequest.setAttribute("accountNo", liAccountId+"");
		pRequest.setAttribute("amount", liAmount+"");
		pRequest.setAttribute("from_address", lstrFromAddress);
		pRequest.setAttribute("to_address", lstrToAddress);

		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/WithdrawAccount.do");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	

}
