package com.account.servlet;

import static com.account.controller.CommonConstants.*;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.controller.Account;
import com.account.controller.Accountant;

public class NewAccountAction extends BaseAction {

	protected void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		
		int liAccountType = ACCOUNT_TYPE_SMARTCARD;
		String lstrName = WebUtil.readParameter(pRequest, "name")+"";
		String lstrMobile = WebUtil.readParameter(pRequest, "mobile")+"";
		double ldAmount = Double.parseDouble(WebUtil.readParameter(pRequest, "amount")+"");
		
		int liAccountNo = saveAccount(lstrName, lstrMobile, liAccountType, ldAmount);
		
		saveMessage(pRequest, "Account created successfully : ''{0}({1,number,#})''" ,new Object[]{lstrName, liAccountNo});
		pRequest.setAttribute("accountNo", liAccountNo+"");
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/ViewAccount.do");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
	
	private int saveAccount(String pName, String pMobile, int pAccountType, double pAmount) 
	{
		int liRetVal=0;
		try
		{
			String lstrName = pName;
//			String lstrMail = pMail;
			int liAccountType = pAccountType;
			double ldAmount = pAmount;
			Calendar lcalCurrentTime = Calendar.getInstance();
			
			Accountant lAccountant = Accountant.getAccountant();
			Account lCustomer = lAccountant.openAccount(ldAmount, liAccountType, lstrName, pMobile, lcalCurrentTime);
			liRetVal = lCustomer.getId();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return liRetVal;
	}

}
