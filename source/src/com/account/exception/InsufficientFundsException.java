package com.account.exception;

public class InsufficientFundsException extends Exception
{
	public String getMessage() {
		return "Insufficient Fund in your account";
	}
}