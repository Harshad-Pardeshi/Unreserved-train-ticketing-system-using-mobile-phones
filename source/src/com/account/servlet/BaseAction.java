package com.account.servlet;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseAction extends HttpServlet 
{
	protected void service(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		HttpSession session = pRequest.getSession();
		if(session == null)
		{
			String path = pRequest.getContextPath();
			pResponse.sendRedirect(path + "login.jsp");
		}
		process(pRequest, pResponse);
	}

	protected abstract void process(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException;
	
	protected void saveMessage(HttpServletRequest pRequest, String msg, Object[] pParams)
	{
		
		String lstr = MessageFormat.format(msg, pParams);
	
		pRequest.setAttribute("message", lstr);
	}
	
	protected void saveError(HttpServletRequest pRequest, String msg, Object[] pParams)
	{
		
		String lstr = MessageFormat.format(msg, pParams);
	
		pRequest.setAttribute("error", lstr);
	}
}
