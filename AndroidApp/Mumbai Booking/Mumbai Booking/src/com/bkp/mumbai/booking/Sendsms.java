package com.bkp.mumbai.booking;

import android.app.Activity;
//import android.app.AlertDialog;
import android.os.Bundle;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Sendsms extends Activity {

	
//This Will be act as the service number. Message to be sent	
//private String phoneno ;
   

@Override
  public void onCreate(Bundle savedInstanceState) 
  {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.sendsms);        
      
      Bundle recieveddata = getIntent().getExtras();
      final String Source = recieveddata.getString("Source");
      final String Destination = recieveddata.getString("Destination");
      
      TextView tv = (TextView)findViewById(R.id.requesttkt);
      tv.setText("Requesting Ticket From "+Source+" to "+Destination);
      
      final TextView serviceno = (TextView)findViewById(R.id.serviceno);
      
      
      
      //Toast.makeText(this,"Buy ticket from "+Source+ " to "+ Destination, Toast.LENGTH_LONG).show();
      
      
      Button btnbuy = (Button)findViewById(R.id.btnbuy);
      btnbuy.setOnClickListener(new View.OnClickListener() 
      {
          public void onClick(View v) 
          {        
        	  		//This Function Send mess to Service number by calling the function "sendSMS"
                  	sendSMS(serviceno.getText().toString(),"BOOK TICKET "+Source+" to "+Destination);                
                  	finish();
          }
      });
     
      
  }    

  private void sendSMS(String phoneNumber, String message)
  {        
        
      SmsManager sms = SmsManager.getDefault();
      sms.sendTextMessage(phoneNumber, null, message, null, null);
      Toast.makeText(getBaseContext(),"Booking Request Sent Succesfully You will Shortly Recieve your Ticket", Toast.LENGTH_LONG).show();
  } 
}
