package com.account.model;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilder 
{
	
	private static Map<String, String> mResourceBundle = null;
	static
	{
		mResourceBundle = new HashMap<String, String>();
	}
	/** ACCOUNTMASTER */
	//SELECT
	public static final String ACCOUNTMASTER_SELECT_1 = "SELECT_ACCOUNTMASTER_1";
	private static final String ACCOUNTMASTER_SELECT_1_VALUE = "SELECT ID, TYPE, NAME, BALANCE, MOBILE_NO, CREATED_TIME, LM_TIME FROM ACCOUNTMASTER WHERE STATUS=1";
	
	public static final String ACCOUNTMASTER_SELECT_2 = "ACCOUNTMASTER_SELECT_2";
	private static final String ACCOUNTMASTER_SELECT_2_VALUE = "SELECT ID, TYPE, NAME, BALANCE, MOBILE_NO, CREATED_TIME, LM_TIME FROM ACCOUNTMASTER WHERE ID={0,number,#} AND STATUS=1";
	
	public static final String ACCOUNTMASTER_SELECT_3 = "ACCOUNTMASTER_SELECT_3";
	private static final String ACCOUNTMASTER_SELECT_3_VALUE = "SELECT ID, TYPE, NAME, BALANCE, MOBILE_NO, CREATED_TIME, LM_TIME FROM ACCOUNTMASTER WHERE ID={0,number,#} AND STATUS=0";
	
	public static final String ACCOUNTMASTER_SELECT_4 = "ACCOUNTMASTER_SELECT_4";
	private static final String ACCOUNTMASTER_SELECT_4_VALUE = "SELECT ID FROM ACCOUNTMASTER WHERE MOBILE_NO=''{0}'' AND STATUS=1";
	
	//INSERT
	public static final String ACCOUNTMASTER_INSERT_1 = "INSERT_ACCOUNTMASTER_1"; 
	private static final String ACCOUNTMASTER_INSERT_1_VALUE = "INSERT INTO ACCOUNTMASTER (ID, NAME, TYPE, BALANCE, MOBILE_NO, STATUS, CREATED_TIME, LM_TIME) VALUES({0,number,#}, ''{1}'', {2,number,#}, {3,number,#}, ''{4}'', {5,number,#}, ''{6,date,yyyy-MM-dd HH:mm:ss}'', ''{7,date,yyyy-MM-dd HH:mm:ss}'' )";
	
	//UPDATE
	public static final String ACCOUNTMASTER_UPDATE_1 = "ACCOUNTMASTER_UPDATE_1"; 
	private static final String ACCOUNTMASTER_UPDATE_1_VALUE = "UPDATE ACCOUNTMASTER SET BALANCE=BALANCE+{1,number,#}, LM_TIME=''{2,date,yyyy-MM-dd HH:mm:ss}'' WHERE ID={0,number,#}";
	
	public static final String ACCOUNTMASTER_DELETE_2 = "ACCOUNTMASTER_DELETE_2"; 
	private static final String ACCOUNTMASTER_DELETE_2_VALUE = "UPDATE ACCOUNTMASTER SET STATUS={1,number,#}, BALANCE=BALANCE-{2,number,#}, LM_TIME=''{3,date,yyyy-MM-dd HH:mm:ss}'' WHERE ID={0,number,#}";
	
	/** NUMBERSERIES */
	//UPDATE
	public static final String  NUMBERSERIES_UPDATE_1 = "UPDATE_NUMBERSERIES_1";
	private static final String NUMBERSERIES_UPDATE_1_VALUE = "UPDATE NUMBERSERIES SET VALUE = VALUE+1 WHERE TYPE = {0,number,#}";

	//SELECT
	public static final String  NUMBERSERIES_SELECT_1 = "SELECT_NUMBERSERIES_1";
	private static final String NUMBERSERIES_SELECT_1_VALUE = "SELECT VALUE FROM NUMBERSERIES WHERE TYPE = {0,number,#}";
	
	/** TRANSACTIONMASTER */
	//SELECT
	public static final String TRANSACTIONMASTER_SELECT_1 = "TRANSACTIONMASTER_SELECT_1";
	private static final String TRANSACTIONMASTER_SELECT_1_VALUE = "SELECT ID, TYPE, ACCT_NO, AMOUNT, CREATED_TIME FROM TRANSACTIONMASTER WHERE ACCT_NO={0,number,#}";
	
	//UPDATE
	public static final String  TRANSACTIONMASTER_INSERT_1 = "INSERT_TRANSACTIONMASTER_1";
	private static final String TRANSACTIONMASTER_INSERT_1_VALUE = "INSERT INTO TRANSACTIONMASTER (ID, TYPE, ACCT_NO, AMOUNT, CREATED_TIME) VALUES({0,number,#}, {1,number,#}, {2,number,#}, {3,number,#}, ''{4,date,yyyy-MM-dd HH:mm:ss}'')";
	
	/** SMSINMASTER*/
	//UPDATE
	public static final String  SMSINMASTER_INSERT_1 = "SMSINMASTER_INSERT_1";
	private static final String SMSINMASTER_INSERT_1_VALUE = "INSERT INTO SMSINMASTER (ID, MOBILE, FROMADDRESS, TOADDRESS, PROCESSED, CREATED_TIME) VALUES({0,number,#}, ''{1}'', ''{2}'', ''{3}'', {4,number,#}, ''{5,date,yyyy-MM-dd HH:mm:ss}'')";
	
	public static final String  SMSINMASTER_UPDATE_1 = "SMSINMASTER_UPDATE_1";
	private static final String SMSINMASTER_UPDATE_1_VALUE = "UPDATE SMSINMASTER SET PROCESSED= {0,number,#} WHERE ID IN {1}";
	
	//SELECT
	public static final String  SMSINMASTER_SELECT_1 = "SMSINMASTER_SELECT_1";
	private static final String SMSINMASTER_SELECT_1_VALUE = "SELECT ID, MOBILE, FROMADDRESS, TOADDRESS, PROCESSED, CREATED_TIME FROM SMSINMASTER WHERE PROCESSED={0,number,#}";
	
	/** SMSOUTMASTER*/	
	//UPDATE
	public static final String  SMSOUTMASTER_INSERT_1 = "SMSOUTMASTER_INSERT_1";
	private static final String SMSOUTMASTER_INSERT_1_VALUE = "INSERT INTO SMSOUTMASTER (ID, ACCOUNT_NO, TRANSACTION_ID, TOADDRESS, FROMADDRESS, SUBJECT, BODY, SMS_COUNT, SENT_TIME, CREATED_TIME) VALUES({0,number,#}, {1,number,#}, {2,number,#}, ''{3}'', ''{4}'', ''{5}'', ''{6}'', {7,number,#}, ''{8,date,yyyy-MM-dd HH:mm:ss}'', ''{9,date,yyyy-MM-dd HH:mm:ss}'')";
	
	//SELECT
	public static final String  SMSOUTMASTER_SELECT_1 = "SMSOUTMASTER_SELECT_1";
	private static final String SMSOUTMASTER_SELECT_1_VALUE = "SELECT ACC.MOBILE_NO, SUBJECT, BODY FROM SMSOUTMASTER SMS , ACCOUNTMASTER ACC WHERE ACC.ID = SMS.ACCOUNT_NO AND SMS.ID={0,number,#}";	
	/** TICKETMASTER */
	//SELECT
	public static final String TICKETMASTER_SELECT_1 = "TICKETMASTER_SELECT_1";
	private static final String TICKETMASTER_SELECT_1_VALUE = "SELECT ID, ACCOUNT_ID, TRANSACTION_ID, TOADDRESS, FROMADDRESS, AMOUNT, CREATED_TIME FROM TICKETMASTER WHERE ID={0,number,#}";
	
	public static final String TICKETMASTER_SELECT_2 = "TICKETMASTER_SELECT_2";
	private static final String TICKETMASTER_SELECT_2_VALUE = "SELECT T.ID ID, ACCOUNT_ID , TRANSACTION_ID, SM1.DESCRIPTION TOADDRESS, SM2.DESCRIPTION FROMADDRESS, AMOUNT, CREATED_TIME FROM TICKETMASTER T, STATIONMASTER SM1, STATIONMASTER SM2 WHERE T.TOADDRESS=SM1.name AND T.FROMADDRESS=SM2.name AND TRANSACTION_ID={0,number,#}";
		// "SELECT ID, ACCOUNT_ID, TRANSACTION_ID, TOADDRESS, FROMADDRESS, AMOUNT, CREATED_TIME FROM TICKETMASTER WHERE TRANSACTION_ID={0,number,#}";
	
	//INSERT
	public static final String TICKETMASTER_INSERT_1 = "TICKETMASTER_INSERT_1";
	private static final String TICKETMASTER_INSERT_1_VALUE = "INSERT INTO TICKETMASTER (ID, ACCOUNT_ID, TRANSACTION_ID, FROMADDRESS, TOADDRESS, AMOUNT, CREATED_TIME) VALUES({0,number,#}, {1,number,#}, {2,number,#}, ''{3}'', ''{4}'', {5,number,#}, ''{6,date,yyyy-MM-dd HH:mm:ss}'' )";

	/** PRICEMASTER*/
	//SELECT
	public static final String  PRICEMASTER_SELECT_1 = "PRICEMASTER_SELECT_1";
	private static final String PRICEMASTER_SELECT_1_VALUE = "SELECT PRICE FROM PRICEMASTER WHERE FROM_ADDRESS=''{0}'' AND TO_ADDRESS=''{1}''";
	
	/**EmployeeMaster*/
	///SELECT 
	public static final String  EMPLOYEEMASTER_SELECT_1 = "EMPLOYEEMASTER_SELECT_1";
	private static final String EMPLOYEEMASTER_SELECT_1_VALUE = "SELECT USER_ID, PASSWORD FROM EMPLOYEEMASTER WHERE USER_ID=''{0}'' AND PASSWORD=''{1}''";
	
	public static final String  EMPLOYEEMASTER_INSERT_1 = "EMPLOYEEMASTER_INSERT_1";
	private static final String EMPLOYEEMASTER_INSERT_1_VALUE = "INSERT INTO EMPLOYEEMASTER (USER_ID, PASSWORD ) VALUES (''{0}'', ''{1}'')";
	
	//STATIONMASTER
	public static final String  STATIONMASTER_SELECT_1 = "STATIONMASTER_SELECT_1";
	private static final String STATIONMASTER_SELECT_1_VALUE = "SELECT NAME, DESCRIPTION FROM STATIONMASTER ORDER BY NAME";
	
	
	public static final String  JOINQUERY_SELECT_1 = "JOINQUERY_SELECT_1";
	private static final String JOINQUERY_SELECT_1_VALUE = "select top 1 balance BALANCE from accountmaster where id=(select acct_no from transactionmaster tsm join ticketmaster tkm on tkm.transaction_id = tsm.id where tkm.id={0,number,#})";

	static
	{
		mResourceBundle.put(ACCOUNTMASTER_SELECT_1, ACCOUNTMASTER_SELECT_1_VALUE);
		mResourceBundle.put(ACCOUNTMASTER_SELECT_2, ACCOUNTMASTER_SELECT_2_VALUE);
		mResourceBundle.put(ACCOUNTMASTER_SELECT_3, ACCOUNTMASTER_SELECT_3_VALUE);
		mResourceBundle.put(ACCOUNTMASTER_SELECT_4, ACCOUNTMASTER_SELECT_4_VALUE);
		
		mResourceBundle.put(ACCOUNTMASTER_INSERT_1, ACCOUNTMASTER_INSERT_1_VALUE);
		mResourceBundle.put(ACCOUNTMASTER_UPDATE_1, ACCOUNTMASTER_UPDATE_1_VALUE);
		mResourceBundle.put(ACCOUNTMASTER_DELETE_2, ACCOUNTMASTER_DELETE_2_VALUE);
		
		mResourceBundle.put(NUMBERSERIES_UPDATE_1, NUMBERSERIES_UPDATE_1_VALUE);
		mResourceBundle.put(NUMBERSERIES_SELECT_1, NUMBERSERIES_SELECT_1_VALUE);
		
		mResourceBundle.put(TRANSACTIONMASTER_SELECT_1, TRANSACTIONMASTER_SELECT_1_VALUE);
		mResourceBundle.put(TRANSACTIONMASTER_INSERT_1, TRANSACTIONMASTER_INSERT_1_VALUE);
		
		mResourceBundle.put(SMSOUTMASTER_INSERT_1, SMSOUTMASTER_INSERT_1_VALUE);
		mResourceBundle.put(SMSOUTMASTER_SELECT_1, SMSOUTMASTER_SELECT_1_VALUE);

		
		mResourceBundle.put(SMSINMASTER_INSERT_1, SMSINMASTER_INSERT_1_VALUE);
		mResourceBundle.put(SMSINMASTER_UPDATE_1, SMSINMASTER_UPDATE_1_VALUE);
		mResourceBundle.put(SMSINMASTER_SELECT_1, SMSINMASTER_SELECT_1_VALUE);

		
		mResourceBundle.put(TICKETMASTER_SELECT_1, TICKETMASTER_SELECT_1_VALUE);
		mResourceBundle.put(TICKETMASTER_SELECT_2, TICKETMASTER_SELECT_2_VALUE);
		mResourceBundle.put(TICKETMASTER_INSERT_1, TICKETMASTER_INSERT_1_VALUE);
		
		mResourceBundle.put(PRICEMASTER_SELECT_1, PRICEMASTER_SELECT_1_VALUE);
		mResourceBundle.put(EMPLOYEEMASTER_SELECT_1, EMPLOYEEMASTER_SELECT_1_VALUE);
		mResourceBundle.put(EMPLOYEEMASTER_INSERT_1, EMPLOYEEMASTER_INSERT_1_VALUE);
		
		mResourceBundle.put(STATIONMASTER_SELECT_1, STATIONMASTER_SELECT_1_VALUE);
		
		mResourceBundle.put(JOINQUERY_SELECT_1, JOINQUERY_SELECT_1_VALUE);

	}
	
	public static String getQuery(String pQueryKey, Object[] pParams)
	{
		if(pQueryKey == null || pQueryKey.length() == 0)
			return pQueryKey;
		
		String lstrText = mResourceBundle.get(pQueryKey);
		
		if(pParams == null || pParams.length == 0)
			return lstrText;
		
		String lstrSql = MessageFormat.format(lstrText, pParams);
		System.out.println("####### ####### ####### #######");
		System.out.println("QUERY_KEY : " + pQueryKey + " QUERY : " + lstrSql);
		System.out.println("####### ####### ####### #######");
		return lstrSql;
	}
	
	
	
}
