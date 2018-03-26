package com.account.controller;

import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.account.model.QueryBuilder;

public class SMS 
{
	String mstrMobile;
	String mstrSubject;
	String mstrBody;
	
	public static SMS getSMS(Connection pConnection, int pSMSId) throws Exception
	{
		SMS lSMS = null;

		Statement stmt = pConnection.createStatement();
		//Query : 
		final String lstrQuery = QueryBuilder.getQuery(SMSOUTMASTER_SELECT_1, new Object[]{
				pSMSId
		});
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		if(lResultSet.next())
		{
			lSMS = new SMS();
			lSMS.setMobile(lResultSet.getString("MOBILE_NO"));
			lSMS.setSubject(lResultSet.getString("SUBJECT"));
			lSMS.setBody(lResultSet.getString("BODY"));
		}
		
		return lSMS;
	}

	public String getMobile() {
		return mstrMobile;
	}

	public void setMobile(String toAddress) {
		mstrMobile = toAddress;
	}

	public String getSubject() {
		return mstrSubject;
	}

	public void setSubject(String subject) {
		mstrSubject = subject;
	}

	public String getBody() {
		return mstrBody;
	}

	public void setBody(String body) {
		mstrBody = body;
	}
	
	
}
