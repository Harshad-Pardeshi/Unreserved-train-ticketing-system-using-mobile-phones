package com.account.servlet;

import javax.servlet.http.HttpServletRequest;

public class WebUtil 
{
	public static Object readParameter(HttpServletRequest pRequest, String pParam)
	{
		if(pRequest == null)
			return null;
		
		Object value = pRequest.getParameter(pParam);
		if(value == null)
			value = pRequest.getAttribute(pParam);
		
		return value;
	}
	
}
