/**
 * 
 */
package com.account.sms;

 import static com.account.controller.CommonConstants.*;

import java.util.ArrayList;

import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Message.MessageTypes;
import org.smslib.modem.SerialModemGateway;

/**
 * @author Jay
 *
 */
public class SMSHandler
{

	public static void sendSMS(String pMobile, String pHeading , String pMessage) throws Exception
	{
		System.out.println("FFFFFF inal send sms" );
		//SMSHandler sender = new SMSHandler();
		//initialize(SMS_IN_PORT, SMS_IN_BAUD_RATE, "", "");
		
		sendMessage(pMobile, pMessage);
		//stopService();
	}
	
	public class OutboundNotification implements IOutboundMessageNotification
	{
		public void process(String gatewayId, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gatewayId);
			System.out.println(msg);
		}
	}

	private static Service service;
	private static SerialModemGateway gateway;

	/**
	 * @param comPortNo
	 * @param baudRate
	 * @param mobileMake
	 * @param phoneModelNo
	 */
	public static void initialize(
			String comPortNo,
			long baudRate,
			String mobileMake,
			String phoneModelNo) throws Exception
	{
		System.out.println("@@@@@ SMSHandler init");
		SMSHandler lSMSHandler = new SMSHandler();
		
		InboundNotification inboundNotification = lSMSHandler.new InboundNotification();
		
		CallNotification callNotification =  lSMSHandler.new CallNotification();
		
		GatewayStatusNotification statusNotification = lSMSHandler.new GatewayStatusNotification();
		
		OrphanedMessageNotification orphanedMessageNotification = lSMSHandler.new OrphanedMessageNotification();

		OutboundNotification outboundNotification = lSMSHandler.new OutboundNotification();

		service = new Service();
		gateway = new SerialModemGateway(
				"modem."+comPortNo, 
				comPortNo, 
				(int)baudRate, 
				mobileMake, 
				phoneModelNo);

		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");		
		
		service.setOutboundMessageNotification(outboundNotification);
		service.setInboundMessageNotification(inboundNotification);
		service.setCallNotification(callNotification);
		service.setGatewayStatusNotification(statusNotification);
		service.setOrphanedMessageNotification(orphanedMessageNotification);

		// Add the Gateway to the Service object.
		service.addGateway(gateway);
		service.startService();

	}
	
	/**
	 * @param destinationMobileNo
	 * @param message
	 * @throws Exception
	 */
	public static void sendMessage(
			String destinationMobileNo,
			String message
			) throws Exception
	{
		OutboundMessage msg  = 
			new OutboundMessage(
					destinationMobileNo, 
					message);
		service.sendMessage(msg);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<SMSMessage> getUnReadMessages()
		throws Exception
	{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ get unread messages STarT");
		ArrayList <SMSMessage> smsMessageList = new ArrayList<SMSMessage>();
		/*
		 * We assume here that the device has already been started.
		 * The method to do that is startEngine().
		 * Every call to this method will get the latest un read messages.
		 */
		try
		{
			ArrayList<InboundMessage> msgList = 
				new ArrayList<InboundMessage>();
			
			service.readMessages(msgList, MessageClasses.UNREAD);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ service unread messages");
			for (InboundMessage msg : msgList)
			{
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ populating sms");
				SMSMessage smsMessage = new SMSMessage();
				smsMessage.setBody(msg.getText());
				smsMessage.setOriginator(msg.getOriginator());
				smsMessage.setMessageType(msg.getType());
				smsMessageList.add(smsMessage);
			}
		}
		catch(Exception e)
		{
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ exeption");
			throw e;
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ get unread messages end ");
		return smsMessageList;
	}

	
	/**
	 * 
	 */
	public static void stopService() throws Exception
	{
		gateway.stopGateway();
		service.stopService();
	}
	
	public class InboundNotification implements IInboundMessageNotification
	{
		public void process(String gatewayId, MessageTypes msgType, InboundMessage msg)
		{
		}
	}

	public class CallNotification implements ICallNotification
	{

		public void process(String gatewayId, String callerId)
		{
//			System.out.println(">>> New call detected from Gateway: " + gatewayId + " : " + callerId);
		}
	}

	public class GatewayStatusNotification implements IGatewayStatusNotification
	{
		
		public void process(String gatewayId, GatewayStatuses oldStatus, GatewayStatuses newStatus)
		{
//			System.out.println(">>> Gateway Status change for " + gatewayId + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}
		
	}

	public class OrphanedMessageNotification implements IOrphanedMessageNotification
	{
		public boolean process(String gatewayId, InboundMessage msg)
		{
			return true;
		}
	}

}
