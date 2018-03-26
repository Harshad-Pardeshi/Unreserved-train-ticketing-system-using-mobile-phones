package com.account.controller;
import static com.account.controller.CommonConstants.*;

import java.util.Calendar;

import com.account.exception.InsufficientFundsException;

public final class SmartCardAccount extends RailAccount{

	
	public SmartCardAccount(int pId, String pName, double pAmount, String pMobile, Calendar pCalendar, Calendar pLMTime){
		miId = pId;
		mstrName = pName;
		miType = ACCOUNT_TYPE_SMARTCARD;
		mdBalance = pAmount;
		//mstrMailId = pMail;
		mstrMobileNo = pMobile;
		mcalCreatedTime = pCalendar;
		mcalLastmodifiedTime = pLMTime; 
	}	

	public void validateWithdraw(double amount) throws InsufficientFundsException{
		if(mdBalance - amount < MINIMUM_CARD_BALANCE)
		{	
			System.out.println(miId + " : " + mdBalance + "-" + amount);
			throw new InsufficientFundsException();
		}
	}
}








