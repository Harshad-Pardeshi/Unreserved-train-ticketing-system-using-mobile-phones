package com.bkp.mumbai.booking;

//import android.app.AlertDialog;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;
 
public class SmsReceiver extends BroadcastReceiver
{ 	
	
	@Override
    public void onReceive(Context ctx, Intent intent) 
    {
		Bundle bundle = intent.getExtras();        
		Object[] pdus = (Object[]) bundle.get("pdus");
		SmsMessage[] messages = new SmsMessage[pdus.length];
	    
		for (int i = 0; i < messages.length; i++)
		{
			messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
	    	Log.v("SMSFun","Body: " + messages[i].getDisplayMessageBody());
	    	Log.v("SMSFun","Address: " + messages[i].getDisplayOriginatingAddress());
	    	
	    	
	    	if (messages[i].getDisplayOriginatingAddress().contains("+919867792770"))
	    	{
	    			this.abortBroadcast();
	    			//Toast.makeText(ctx,messages[i].getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
	    			Intent newintent = new Intent(ctx,Getmess.class);
	    			newintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);         // Pass in data
	    			newintent.putExtra("From","SmsReceiver");
	    			newintent.putExtra("address", messages[i].getDisplayOriginatingAddress());
	    			newintent.putExtra("message", messages[i].getDisplayMessageBody());
	    			ctx.startActivity(newintent);         
	    	}
		}	      
    } 
	
	
	  
}