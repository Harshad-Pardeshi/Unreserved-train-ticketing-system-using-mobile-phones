/**
 * 
 */
package com.account.sms;

import org.smslib.Message.MessageTypes;

/**
 * @author Jay
 *
 */
public class SMSMessage
{
	public static final int INBOUND_MESSAGE = 0;
	public static final int OUTBOUND_MESSAGE = 1;
	public static final int STATUS_REPORT_MESSAGE = 2;
	public static final int UNKNOWN_MESSAGE = 3;
	
	private String originator;
	private String body;
	private int messageType;
	
	/**
	 * @return the messageType
	 */
	public int getMessageType()
	{
		return messageType;
	}
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageTypes messageType)
	{
		if( messageType == MessageTypes.INBOUND )
			this.messageType = INBOUND_MESSAGE;
		else if( messageType == MessageTypes.OUTBOUND )
			this.messageType = OUTBOUND_MESSAGE;
		else if( messageType == MessageTypes.STATUSREPORT )
			this.messageType = STATUS_REPORT_MESSAGE;
		else if( messageType == MessageTypes.UNKNOWN )
			this.messageType = UNKNOWN_MESSAGE;
	}
	/**
	 * @return the originator
	 */
	public String getOriginator()
	{
		return originator;
	}
	/**
	 * @param originator the originator to set
	 */
	public void setOriginator(String originator)
	{
		this.originator = originator;
	}
	/**
	 * @return the body
	 */
	public String getBody()
	{
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body)
	{
		this.body = body;
	}
}