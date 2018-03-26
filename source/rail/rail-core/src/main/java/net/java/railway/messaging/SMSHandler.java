package net.java.railway.messaging;

import static net.java.railway.Constants.DEMO_SMS_BODY;
import static net.java.railway.Constants.DEMO_SMS_SENDER;
import static net.java.railway.Constants.SMS_FLAG;
import static net.java.railway.Constants.SMS_IN_BAUD_RATE;
import static net.java.railway.Constants.SMS_IN_PORT;

import java.util.ArrayList;
import java.util.List; 
import java.util.Set;

import net.java.railway.SMS;
import net.java.railway.core.RailwayProcesser;

import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SMSHandler {

    static RailwayProcesser railwayProcesser = new RailwayProcesser();
    private static Service service;
    private static SerialModemGateway gateway;

    static {
        try {
            if (SMS_FLAG) {
                System.out.println("**** static initialise **** START ");
                initialize(SMS_IN_PORT, SMS_IN_BAUD_RATE);
                System.out.println("**** static initialise **** END ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendSMS(String pMobile, String pMessage) {
        System.out.println("Sending sms.... ");
        System.out.println(pMobile + " >> " + pMessage);
        SMSHandler sender = new SMSHandler();
        try {
            if (SMS_FLAG) {
                sender.sendMessage(pMobile, pMessage);
            }
        } catch (Exception e) {
            System.out.println("SMS Sending failed :(  ");
            e.printStackTrace();
        }
        System.out.println("SMS Sent Successfully :)  ");
    }

    public synchronized void sendMessage(String destinationMobileNo, String message) throws Exception {
        OutboundMessage msg = new OutboundMessage(destinationMobileNo, message);
        service.sendMessage(msg);
    }

    public static void initialize(String comPortNo, long baudRate) throws Exception {

        service = Service.getInstance();
        gateway = new SerialModemGateway("modem." + comPortNo, comPortNo, (int) baudRate, "", "");

        gateway.setInbound(true);
        gateway.setOutbound(true);
        gateway.setSimPin("0000");

        // Add the Gateway to the Service object.
        service.addGateway(gateway);
        service.startService();

    }

    public static void stopService() {
        try {
            gateway.stopGateway();
            service.stopService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SMSMessage> getUnReadMessages() {
        // System.out.println("<<<< get unread messages Start");
        ArrayList<SMSMessage> smsMessageList = new ArrayList<SMSMessage>();
        try {
            ArrayList<InboundMessage> msgList = new ArrayList<InboundMessage>();
            if (SMS_FLAG) {
                service.readMessages(msgList, MessageClasses.UNREAD);
            } else {
                SMSMessage smsMessage = new SMSMessage();
                smsMessage.setBody(DEMO_SMS_BODY);
                smsMessage.setOriginator(DEMO_SMS_SENDER);
                smsMessageList.add(smsMessage);

                PollingEngine.globalPollingStatus = PollingEngine.POLLING_STATUS_PAUSED;
            }
            // System.out.println("---- service unread messages");
            for (InboundMessage msg : msgList) {

                // System.out.println("---- populating sms");
                SMSMessage smsMessage = new SMSMessage();
                smsMessage.setBody(msg.getText());
                smsMessage.setOriginator(msg.getOriginator());
                smsMessageList.add(smsMessage);
            }
        } catch (Exception e) {
            System.out.println("ERROR : ---- exeption : " + e.getMessage());
            e.printStackTrace();
        }
        // System.out.println("---- get unread messages end ");
        return smsMessageList;
    }

    public static void sendSMS(Set<SMS> setOutSMS) {

        for (SMS sms : setOutSMS) {
            try {
                sendSMS(sms.getMobile() + "", sms.getBody());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void readSMS() {

        while (true) {

            if (PollingEngine.globalPollingStatus == PollingEngine.POLLING_STATUS_PAUSED) {
                return;
            }

            List<SMSMessage> unReadMessages = SMSHandler.getUnReadMessages();
            if (unReadMessages.size() > 0) {
                System.out.println("unReadMessages : " + unReadMessages.size());
            }

            for (int i = 0; i < unReadMessages.size(); i++) {
                SMSMessage sms = unReadMessages.get(i);

                System.out.println(sms);
                String smsBody = sms.getBody();
                String mobileNo = sms.getOriginator();

                if ((smsBody == null) || (mobileNo == null)) {
                    System.out.println("@@@@ Invalid sms");
                    continue;
                }

                System.out.println("@@@@ VALIDATION SUCCESS");

                if (railwayProcesser.storeReceivedSMS(mobileNo, smsBody) == true) {
                    System.out.println("$$$$ Stored new sms");
                    // isNewSMS = true;
                    try {
                        System.out.println("sleeping now");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Start:");
        // sendSMS("9930783001", "Hello");
        readSMS();
    }

}
