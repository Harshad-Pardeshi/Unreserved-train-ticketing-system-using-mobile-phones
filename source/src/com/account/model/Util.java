package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Util 
{

	public static String getFormattedDate(Calendar pCalendar)
	{
		SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DB_DATE_PATTERN);
		 return simpleDateFormatter.format(pCalendar.getTime());
	}
	
	public static String getDisplayDate(Calendar pCalendar)
	{
		SimpleDateFormat lSimpleDateFormatter = new SimpleDateFormat(DISPLAY_DATE_PATTERN);
		return lSimpleDateFormatter.format(pCalendar.getTime());
	}
	
	/*public static int getNextFFN(Connection pConnection, int pType) throws Exception
	{
		int liRetVal = 0;
		boolean isNewConnection = false;
		
		try
		{
			if(pConnection == null)
			{
				pConnection = DB.getConnection();
				isNewConnection = true;
			}

			Statement stmt = pConnection.createStatement();
			//Query : "UPDATE NUMBERSERIES SET VALUE = VALUE+1 WHERE TYPE = {0,number,#}";
			final String lstrQuery1 = QueryBuilder.getQuery(NUMBERSERIES_UPDATE_1,  new Object[]{
					pType
			});
			
			stmt.executeUpdate(lstrQuery1);
			
			//Query : "SELECT VALUE FROM NUMBERSERIES WHERE TYPE = {0,number,#}";
			final String lstrQuery2 = QueryBuilder.getQuery(NUMBERSERIES_SELECT_1, new Object[]{
					pType
			});
			ResultSet lResultSet = stmt.executeQuery(lstrQuery2);
			if(lResultSet.next())
			{
				liRetVal =  lResultSet.getInt("VALUE");
			}
			
		}
		finally
		{
			try{
				if(isNewConnection)
				{
					pConnection.close();
					pConnection = null;
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return liRetVal;
	}*/
	
	public static int getNextFFN(Connection pConnection, int pType) throws Exception
	{
		int liRetVal = 0;
		boolean isNewConnection = false;
		//Connection lConnection = null; 
		try
		{
			if(pConnection == null)
			{
				pConnection = DB.getConnection();
				isNewConnection = true;
			}

			Statement stmt = pConnection.createStatement();
			//Query : "UPDATE NUMBERSERIES SET VALUE = VALUE+1 WHERE TYPE = {0,number,#}";
			final String lstrQuery1 = QueryBuilder.getQuery(NUMBERSERIES_UPDATE_1,  new Object[]{
					pType
			});
			System.out.println("################# commited ");
			pConnection.commit();
			System.out.println("################# commited ");
			stmt.executeUpdate(lstrQuery1);
			
			//Query : "SELECT VALUE FROM NUMBERSERIES WHERE TYPE = {0,number,#}";
			final String lstrQuery2 = QueryBuilder.getQuery(NUMBERSERIES_SELECT_1, new Object[]{
					pType
			});
			ResultSet lResultSet = stmt.executeQuery(lstrQuery2);
			if(lResultSet.next())
			{
				liRetVal =  lResultSet.getInt("VALUE");
			}
			
		}
		finally
		{
			try{
				if(isNewConnection)
				{
					pConnection.close();
					pConnection = null;
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return liRetVal;
	}
	public static boolean storeReceivedSMS(String pMobile, String pFromAddress, String pToAddress, Calendar pcalTime )
	{
		System.out.println(" STORE RECIEVED SMS");
		Connection lConnection = null;
		try
		{
			
			lConnection = DB.getConnection(); 
			
			int lSMSId = getNextFFN(lConnection, FFN_SMS_IN_ID); 
			Statement stmt = lConnection.createStatement();
			//Query : 
			final String lstrQuery = QueryBuilder.getQuery(SMSINMASTER_INSERT_1,  new Object[]{
					lSMSId,
					pMobile,
					pFromAddress,
					pToAddress,
					0,
					pcalTime.getTime()
			});
			
			System.out.println("QUERY_KEY :" + SMSINMASTER_INSERT_1 + " QUERY : " + lstrQuery );
			
			int count = stmt.executeUpdate(lstrQuery);
			if(count == 0 )
			{
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
				lConnection = null;
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	// getQueuedSMS
	public static Vector<Vector> getQueuedSMS()
	{
		Connection lConnection = null;
		Vector<Vector> lCollection = new Vector<Vector>();
		try
		{
			lConnection = DB.getConnection(); 
		
			Statement stmt = lConnection.createStatement();
			/* Query : 
				SELECT ID, MOBILE, FROMADDRESS, TOADDRESS, PROCESSED, CREATED_TIME 
				FROM SMSINMASTER 
				WHERE PROCESSED={0,number,#}";
			*/
			final String lstrQuery = QueryBuilder.getQuery(SMSINMASTER_SELECT_1,  new Object[]{
					0,
			});
			
			System.out.println("QUERY_KEY :" + SMSINMASTER_SELECT_1 + " QUERY : " + lstrQuery );
			
			ResultSet lResultSet = stmt.executeQuery(lstrQuery);
			while(lResultSet.next())
			{
				Vector lvec = new Vector();
				
				lvec.add(lResultSet.getInt("ID"));
				lvec.add(lResultSet.getString("MOBILE"));
				lvec.add(lResultSet.getString("FROMADDRESS"));
				lvec.add(lResultSet.getString("TOADDRESS"));
				
				lCollection.add(lvec);
			}
			System.out.println("lCollection size : " +lCollection.size());
			
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
				lConnection = null;
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lCollection;
	}

	public static void ticketBooked(Set<Integer> pBookedTicket, int pSuccess)
	{
		String lInqueryForBookedTickets = pBookedTicket.toString().replace('[', '(').replace(']', ')');
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection(); 
		
			Statement stmt = lConnection.createStatement();
			/* Query : 
			*/
			final String lstrQuery = QueryBuilder.getQuery(SMSINMASTER_UPDATE_1,  new Object[]{
					pSuccess,
					lInqueryForBookedTickets
			});
			
			System.out.println("QUERY_KEY :" + SMSINMASTER_UPDATE_1 + " QUERY : " + lstrQuery );
			
			stmt.executeUpdate(lstrQuery);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
				lConnection= null;
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static boolean validateUser(String pUserName, String pPassword)
	{
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection(); 
			Statement stmt = lConnection.createStatement();
			//Query : 
			final String lstrQuery = QueryBuilder.getQuery(EMPLOYEEMASTER_SELECT_1,  new Object[]{
					pUserName,
					pPassword
			});
			
			System.out.println("QUERY_KEY :" + EMPLOYEEMASTER_SELECT_1 + " QUERY : " + lstrQuery );
			
			ResultSet lResultSet = stmt.executeQuery(lstrQuery);
			if(lResultSet.next())
			{
				System.out.println("login success");
				return true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
				lConnection = null;
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}
	
	//StoreUserDetails
	public static void storeUserDetails(String pUserId, String pPassword)
	{
		
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection(); 
		
			Statement stmt = lConnection.createStatement();
			final String lstrQuery = QueryBuilder.getQuery(EMPLOYEEMASTER_INSERT_1,  new Object[]{
					pUserId,
					pPassword
			});
			
			stmt.executeUpdate(lstrQuery);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				lConnection.close();
				lConnection= null;
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}



public static Vector<String[]> getAllStationList()
{
	Connection lConnection = null;
	Vector<String[]> vec = new Vector<String[]>();
	try
	{
		lConnection = DB.getConnection(); 
		Statement stmt = lConnection.createStatement();
		//Query : 
		final String lstrQuery = QueryBuilder.getQuery(STATIONMASTER_SELECT_1,  null);
		
		System.out.println("QUERY_KEY :" + STATIONMASTER_SELECT_1 + " QUERY : " + lstrQuery );
		
		ResultSet lResultSet = stmt.executeQuery(lstrQuery);
		Map<String, String> mapAllStations = new HashMap<String, String>();
		String[] station = null;
		vec = new Vector<String[]>();
		while(lResultSet.next())
		{
			station = new String[2];
			station[0] = lResultSet.getString("NAME");
			station[1] = lResultSet.getString("DESCRIPTION");
			vec.add(station);
		}
		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	finally
	{
		try{
			lConnection.close();
			lConnection = null;
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return vec;
	}
}
