package com.bkp.mumbai.booking;

import android.app.Activity;
import android.content.Intent;
//import android.app.AlertDialog;
import android.os.Bundle;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Booktkt extends Activity {
	
	public void onCreate(Bundle savedInstanceState) 
	  {
		
		 super.onCreate(savedInstanceState);
		 		setContentView(R.layout.booktkt);
		  	
	    final AutoCompleteTextView auto_src = (AutoCompleteTextView)findViewById(R.id.auto_src);
	      ArrayAdapter<CharSequence> source = ArrayAdapter.createFromResource(this,R.array.Central, android.R.layout.simple_spinner_item);
			auto_src.setThreshold(1);
			auto_src.setAdapter(source);
			
			
		 final AutoCompleteTextView auto_dest = (AutoCompleteTextView)findViewById(R.id.auto_dest);
		 ArrayAdapter<CharSequence> desti = ArrayAdapter.createFromResource(this,R.array.Central, android.R.layout.simple_spinner_item);
			auto_dest.setThreshold(1);
			auto_dest.setAdapter(desti);
			
		
			
	      Button book = (Button)findViewById(R.id.btn_book_new);
	      book.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Spinner no_of_pass = (Spinner)findViewById(R.id.no_of_pass);
				String pass = no_of_pass.getSelectedItem().toString();
				String sel_source= auto_src.getEditableText().toString();
				String sel_destination= auto_dest.getEditableText().toString();
				String blank = "";
				if (sel_source.equals(blank) || sel_destination.equals(blank)){
					
					Toast t2 = Toast.makeText(Booktkt.this, "Enter All Fields", Toast.LENGTH_SHORT);
					t2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t2.show();
				}
				else if(sel_source.equals(sel_destination))	{
					Toast t1 = Toast.makeText(Booktkt.this, "Source & Destination Cannot be Same", Toast.LENGTH_SHORT);
					t1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t1.show();
				}
				
				
				/*else if (sel_source.equals(blank) && sel_destination.equals(blank) && other_no.equals(blank)){
					
					Toast t3 = Toast.makeText(Booktkt.this, "Enter All Fields", Toast.LENGTH_SHORT);
					t3.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t3.show();
				}*/
				else  {
					//Toast.makeText(getBaseContext(), sel_source+" " + " "+sel_destination, Toast.LENGTH_SHORT).show();
					Intent i = new Intent(Booktkt.this,Sendsms.class);
					String Booktkt="TKT BOOK "+sel_source.substring(0, 4)+" "+sel_destination.substring(0, 4)+" "+pass;
					Toast.makeText(Booktkt.this,Booktkt, Toast.LENGTH_SHORT).show();
					Bundle senddata = new Bundle();
					senddata.putString("book",Booktkt);
					senddata.putString("Source", sel_source);
					senddata.putString("Destination", sel_destination);
					senddata.putString("Pass",pass);
					i.putExtras(senddata);
					startActivity(i);
				}
				
			}
		});
	  }
}