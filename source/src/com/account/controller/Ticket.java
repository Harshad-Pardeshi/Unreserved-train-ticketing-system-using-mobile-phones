package com.account.controller;

import static com.account.model.QueryBuilder.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import com.account.model.DB;
import com.account.model.QueryBuilder;

public class Ticket 
{
	int id;
	String toAddress;
	String fromAddress;
	double amount;
	int TransactionId;
	Calendar CreatedTime;
	
	public Ticket(int pId, String pToAddress, String pFromAddress, double pAmount, int pTransactionId, Calendar pCreatedTime) {
		this.id = pId;
		this.toAddress = pToAddress;
		this.fromAddress = pFromAddress;
		this.amount = pAmount;
		this.TransactionId = pTransactionId;
		CreatedTime = pCreatedTime;
	}
	
	public static Ticket getInstance(Connection pConnection, int pId, boolean isTicketId) throws Exception
	{
		Ticket lTicket = null;
		boolean isNewConnection = false;
		try
		{
			if(pConnection == null)
			{
				pConnection = DB.getConnection();
				isNewConnection = true;
			}
			
			Statement stmt = pConnection.createStatement();
			// Query : 
			String lstrQuery = null; 
			if(isTicketId == true)
			{
				lstrQuery = QueryBuilder.getQuery(TICKETMASTER_SELECT_1, new Object[]{pId});
			}
			else
			{
				/*
				 * "SELECT ID, ACCOUNT_ID, TRANSACTION_ID, TOADDRESS, FROMADDRESS, AMOUNT, CREATED_TIME 
				 * FROM TICKETMASTER 
				 * WHERE TRANSACTION_ID={0,number,#}";
				 */
				lstrQuery = QueryBuilder.getQuery(TICKETMASTER_SELECT_2, new Object[]{pId});
			}
			System.out.println("QUERY: " +lstrQuery);
			ResultSet lResultSet = stmt.executeQuery(lstrQuery);
			if(lResultSet.next())
			{
				int liTicketNo = lResultSet.getInt("ID");
				int liTransactionId  = lResultSet.getInt("TRANSACTION_ID");
				String lstrToAddress  = lResultSet.getString("TOADDRESS");
				String lstrFromAddress  = lResultSet.getString("FROMADDRESS");
				double ldAmount = lResultSet.getDouble("AMOUNT");
				Calendar lcalCreatedTime = Calendar.getInstance();
				lcalCreatedTime.setTimeInMillis(lResultSet.getTimestamp("CREATED_TIME").getTime());
				
				lTicket = new Ticket(liTicketNo, lstrToAddress, lstrFromAddress, ldAmount, liTransactionId, lcalCreatedTime);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		finally
		{
			try {
				if(isNewConnection)
				{
					pConnection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
			
		return lTicket;
	}
	
	public static double getCurrentBalance(Connection pConnection, int pTicketId) throws Exception
	{
		String lstrMethodName = Ticket.class.getClass().getName() + " :: getCurrentBalance :: ";
		System.out.println(lstrMethodName + "START");

		double mdBalance = -1;
		//Connection pConnection = null;
		try
		{
			//pConnection = DB.getConnection();
		
			Statement stmt = pConnection.createStatement();
			// Query : 
			final String lstrQuery = QueryBuilder.getQuery(JOINQUERY_SELECT_1, new Object[]{
					pTicketId
			});
			System.out.println(lstrMethodName + "Query_Key :: " + JOINQUERY_SELECT_1 + " Query :: " + lstrQuery);
			
			ResultSet lResultSet  = stmt.executeQuery(lstrQuery);
	
			if(lResultSet.next())
			{
				mdBalance = lResultSet.getDouble("BALANCE");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
//			pConnection.close();
//			pConnection = null;
		}
		System.out.println(lstrMethodName + "END");
		return mdBalance;
	}
	
	public static double calculateTicketAmount(String pFromAddress, String pToAddress) throws Exception 
	{
		String lstrMethodName  = Ticket.class.getClass().getName() + " :: calculateTicketAmount :: ";
		System.out.println(lstrMethodName + " START .");
		double price = 0;
		Connection lConnection = null;
		try
		{
			lConnection = DB.getConnection();
			Statement stmt = lConnection.createStatement();
			// Query : 
			final String lstrQuery = QueryBuilder.getQuery(PRICEMASTER_SELECT_1, new Object[]{
					pFromAddress, 
					pToAddress, 
			});
			System.out.println(lstrMethodName + "Query_Key :: " + PRICEMASTER_SELECT_1 + " Query :: " + lstrQuery);
			
			ResultSet rs  = stmt.executeQuery(lstrQuery);
	
			if(rs.next())
			{
				price = rs.getDouble("PRICE");
			}
			else
			{
				throw new Exception("Error fetching price");
			}	
		}
		finally
		{
				lConnection.close();
				lConnection = null;
		}
		System.out.println(lstrMethodName + " END .");
		return price;
	}

	public int getId() {
		return id;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public double getAmount() {
		return amount;
	}

	public int getTransactionId() {
		return TransactionId;
	}

	public Calendar getCreatedTime() {
		return CreatedTime;
	}
	
	
}
