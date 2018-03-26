package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.model.Util;

public class RegistrationAction extends HttpServlet {

	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

		String lstrName = WebUtil.readParameter(pRequest, "name") + "";		
		String lstrPwd = WebUtil.readParameter(pRequest, "pwd") + "";
		
		HttpSession session = null;

		try
		{
			Util.storeUserDetails(lstrName, lstrPwd);
		}
		catch (Exception e) 
		{}
		
		pRequest.setAttribute("message", "Registration success for " + lstrName);
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("logout.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
}
