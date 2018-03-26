package com.bkp.mumbai.booking;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Services extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
    
        Button book_your = (Button)findViewById(R.id.book_self);
        book_your.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent i = new Intent(Services.this,Booktkt.class);
				startActivity(i);
			}
		});

        Button book_other = (Button)findViewById(R.id.book_other);
        book_other.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent i = new Intent(Services.this,Bookothers.class);
				
				startActivity(i);
		}
		});

        Button last_tkt = (Button)findViewById(R.id.view_last_tkt);
        last_tkt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Services.this,Getmess.class);
				Bundle b1 = new Bundle();
				b1.putString("From", "Service");
				i.putExtras(b1);
				startActivity(i);
				
			}
		});
        
        Button help =(Button)findViewById(R.id.Help);
        help.setOnClickListener(new View.OnClickListener() {
        	
        	
			public void onClick(View arg0) {
        		 sendSMS("+919867792770","TKT HELP");    		            
     	        }
        	
        });
        
        Button balance =(Button)findViewById(R.id.Balance);
        balance.setOnClickListener(new View.OnClickListener() {
        	
        
			public void onClick(View arg0) {
				 sendSMS("+919867792770","TKT BAL"); 
			}		
        });
      }
    private void sendSMS(String phoneNumber, String message)
    {        
          
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
       Toast.makeText(getBaseContext(),"Request Sent Succesfully You will Shortly Recieve Response", Toast.LENGTH_LONG).show();
       
    } 
}