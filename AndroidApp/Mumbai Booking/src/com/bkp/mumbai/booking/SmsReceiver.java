package com.bkp.mumbai.booking;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;
 
public class SmsReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) 
    {
    	
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = ""; 
        String Address = null;
        if (bundle != null)
        {
           
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);  
                Address = msgs[i].getOriginatingAddress();
                str += "SMS from " + msgs[i].getOriginatingAddress();                     
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";        
            }
            
           if(Address.equals("15555215556")){
        	this.abortBroadcast();  
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
           }
        }                         
    }
}