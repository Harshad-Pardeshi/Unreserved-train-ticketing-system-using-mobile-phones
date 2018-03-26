package com.account.controller;

import java.util.Calendar;
import com.account.exception.*;

public abstract class Account{
	
	public abstract int getId();
	public abstract int getType();
	public abstract String getName();
	//public abstract String getMail();
	public abstract String getMobileNo();
	public abstract double getBalance();
	public abstract Calendar getCreatedTime();
	public abstract Calendar getLMTime();
	
//	public abstract void deposit(double amount);
//	public abstract void withdraw(double amount) throws InsufficientFundsException;
	
	public abstract void validateWithdraw(double amount) throws InsufficientFundsException;
}