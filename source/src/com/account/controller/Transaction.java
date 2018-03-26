package com.account.controller;

import java.util.Calendar;
import static com.account.controller.CommonConstants.*;
public class Transaction 
{
	int miId;
	int miType;
	int miAccountNo;
	double miAmount;
	Calendar mcalCreatedTime;
	
	public Transaction(int pId, int pType, int pAccountNo, double pAmount, Calendar pCreatedTime) {
		this.miId = pId;
		this.miType = pType;
		this.miAccountNo = pAccountNo;
		this.miAmount = pAmount;
		this.mcalCreatedTime = pCreatedTime;
	}
	
	public int getId() {
		return miId;
	}
	public int getType() {
		return miType;
	}
	public int getAccountNo() {
		return miAccountNo;
	}
	public double getAmount() {
		return miAmount;
	}
	public Calendar getCreatedTime() {
		return mcalCreatedTime;
	}

	public String getDisplayType()
	{
		return getDisplayType(miType);
	}
	
	public static String getDisplayType(int pType)
	{
		if(pType == TRANSACTION_TYPE_DELETE_ID)
			return TRANSACTION_TYPE_DELETE_NAME;
		
		if(pType == TRANSACTION_TYPE_DEPOSIT_ID)
			return TRANSACTION_TYPE_DEPOSIT_NAME;
		
		if(pType == TRANSACTION_TYPE_INVALID_ID)
			return TRANSACTION_TYPE_INVALID_NAME;
		
		if(pType == TRANSACTION_TYPE_OPEN_ID)
			return TRANSACTION_TYPE_OPEN_NAME;
		
		if(pType == TRANSACTION_TYPE_WITHDRAW_ID)
			return TRANSACTION_TYPE_WITHDRAW_NAME;
		
		return "";
	}
}
