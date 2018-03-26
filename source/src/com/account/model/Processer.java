package com.account.model;

import static com.account.controller.CommonConstants.*;
import static com.account.model.QueryBuilder.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Map;

import com.account.controller.Account;
import com.account.sms.SMSHelper;

public abstract class Processer 
{
	protected int miAccountNo;
	protected double mdAmount;
	protected int miTransactionType;
	protected Calendar mcalTransactionTime;
	protected Map<String, Object> mmapProcess;
	Processer(int pAccountNo,
			double pAmount,
			int pTransactionType,
			Calendar pCurrentTime)
	{
		this.miAccountNo = pAccountNo;
		this.mdAmount = pAmount;
		this.miTransactionType = pTransactionType;
		this.mcalTransactionTime = pCurrentTime;
	}
	
	//absract method
	protected abstract Account processSub(Connection pConnection) throws Exception;
	protected abstract void saveTransactionDetails(Connection pConnection, int pTransactionId) throws Exception;
	
	public Account process(Map<String, Object> mmapProcess)
	{
		this.mmapProcess = mmapProcess;
		Connection lConnection = null;
		Account lAccount = null;
		try{
			lConnection = DB.getConnection();
			lConnection.setAutoCommit(false);

			lAccount = processSub(lConnection);
			
			int liTransactionId = saveTransaction(lConnection, lAccount, miTransactionType, mdAmount, mcalTransactionTime);
			saveTransactionDetails(lConnection, liTransactionId);

			if(miTransactionType == TRANSACTION_TYPE_WITHDRAW_ID)
			{
				String lstrSMSSubject = ""; //SMSHelper.prepareSMSSubject(miTransactionType);
				String lstrSMSBody = SMSHelper.prepareSMSBody(lConnection, liTransactionId);
				
				int liSMSId = saveSMS(lConnection, lAccount, liTransactionId, lstrSMSSubject, lstrSMSBody, mcalTransactionTime);
				SMSHelper.sendSMS(lConnection, liSMSId);
			}
			lConnection.commit();
			return lAccount;
		}catch (Exception e) {
			try{
			e.printStackTrace();
			lConnection.rollback();
			}catch (SQLException esql) {
				esql.printStackTrace();
			}
		}finally{
			try{
			lConnection.close();
			lConnection = null;
			}catch (SQLException esql) {
				esql.printStackTrace();
			}
		}
		return lAccount;
	
	}
	public static int saveTransaction(Connection pConnection,
			Account pAccount, 
			int pTransactionType, 
			double pTransactionAmount, 
			Calendar pCreatedTime) throws Exception
	{
		int liTransactionNo = Util.getNextFFN(pConnection, FFN_TRANSACTION_ID);
		
		Statement stmt = pConnection.createStatement();
		// Query : "INSERT INTO TRANSACTIONMASTER (ID, TYPE, ACCT_NO, AMOUNT, CREATED_TIME) VALUES({0,number,#}, {1,number,#}, {2,number,#}, {3,number,#}, ''{4}'')";
		final String lstrQuery = QueryBuilder.getQuery(TRANSACTIONMASTER_INSERT_1, new Object[]{ liTransactionNo ,
				pTransactionType, 
				pAccount.getId(), 
				pTransactionAmount, 
				pCreatedTime.getTime() 
		});
		
		int count = stmt.executeUpdate(lstrQuery);
		if(count == 0)
		{
			throw new Exception("Transaction Error : ");
		}
		
		return liTransactionNo;
	}
	
	public static int saveSMS(Connection pConnection,
			Account pAccount,
			int pTransactionId,
			String pSubject,
			String pBody,
			Calendar pCreatedTime) throws Exception
	{
		
		int liSMSId = Util.getNextFFN(pConnection, FFN_SMS_OUT_ID);
		
		Statement stmt = pConnection.createStatement();
		//Query : 
		final String lstrQuery = QueryBuilder.getQuery(SMSOUTMASTER_INSERT_1, new Object[]{ liSMSId ,
				pAccount.getId(),
				pTransactionId,
				pAccount.getMobileNo(),
				SENDER_SMS_CENTER,
				pSubject,
				pBody,
				0,
				pCreatedTime.getTime(),
				pCreatedTime.getTime()
		});
		
		int count = stmt.executeUpdate(lstrQuery);
		if(count == 0)
		{
			throw new Exception("Transaction Error : ");
		}
		
		return liSMSId;
	}
	
	
}
