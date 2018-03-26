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
import android.widget.TextView;
import android.widget.Toast;

public class Bookothers extends Activity {
	
	public void onCreate(Bundle savedInstanceState) 
	  {
		
		 super.onCreate(savedInstanceState);
				setContentView(R.layout.bookothers);

		 	
	    final AutoCompleteTextView auto_src = (AutoCompleteTextView)findViewById(R.id.auto_src);
	      ArrayAdapter<CharSequence> source = ArrayAdapter.createFromResource(this,R.array.Central, android.R.layout.simple_spinner_item);
			auto_src.setThreshold(1);
			auto_src.setAdapter(source);
			
			
		 final AutoCompleteTextView auto_dest = (AutoCompleteTextView)findViewById(R.id.auto_dest);
		 ArrayAdapter<CharSequence> desti = ArrayAdapter.createFromResource(this,R.array.Central, android.R.layout.simple_spinner_item);
			auto_dest.setThreshold(1);
			auto_dest.setAdapter(desti);
			
			final AutoCompleteTextView auto_other = (AutoCompleteTextView)findViewById(R.id.auto_others_number);
			
	      Button book = (Button)findViewById(R.id.btn_book_others);
	      book.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				String sel_source= auto_src.getEditableText().toString();
				String sel_destination= auto_dest.getEditableText().toString();
				String blank = "";
				if (sel_source.equals(blank) || sel_destination.equals(blank)){
					
					Toast t2 = Toast.makeText(Bookothers.this, "Enter All Fields", Toast.LENGTH_SHORT);
					t2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t2.show();
				}
				else if(sel_source.equals(sel_destination))	{
					Toast t1 = Toast.makeText(Bookothers.this, "Source & Destination Cannot be Same", Toast.LENGTH_SHORT);
					t1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t1.show();
				}
				
				
				/*else if (sel_source.equals(blank) && sel_destination.equals(blank) && other_no.equals(blank)){
					
					Toast t3 = Toast.makeText(Booktkt.this, "Enter All Fields", Toast.LENGTH_SHORT);
					t3.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					t3.show();
				}*/
				else  {
					Toast.makeText(getBaseContext(), sel_source+" " + " "+sel_destination, Toast.LENGTH_SHORT).show();
					Intent i = new Intent(Bookothers.this,Sendsms.class);
					Bundle senddata = new Bundle();
					senddata.putString("Source", sel_source);
					senddata.putString("Destination", sel_destination);
					i.putExtras(senddata);
					startActivity(i);
				}
				
			}
		});
	  }
}