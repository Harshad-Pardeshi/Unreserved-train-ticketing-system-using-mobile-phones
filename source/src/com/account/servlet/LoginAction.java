package com.account.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.model.Util;

public class LoginAction extends HttpServlet {

	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {

		String lstrName = WebUtil.readParameter(pRequest, "name") + "";		
		String lstrPwd = WebUtil.readParameter(pRequest, "pwd") + "";
		
		HttpSession session = null;

		try
		{
			if(Util.validateUser(lstrName, lstrPwd))
			{
				session = pRequest.getSession(true);
				session.setAttribute("login_name", lstrName);
			}
		}
		catch (Exception e) 
		{
		}
		
		RequestDispatcher lRequestDispatcher = pRequest.getRequestDispatcher("/blank.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);
	
	}
}
