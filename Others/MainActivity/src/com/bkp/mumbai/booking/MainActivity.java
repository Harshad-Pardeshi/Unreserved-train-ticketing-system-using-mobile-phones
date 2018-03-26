package com.bkp.mumbai.booking;



import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	SQLiteDatabase my_db;
	private static String DBNAME = "DATA.db";
    private static String TABLE = "SMS_TABLE";
    
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createtable();
        Button btn_welcome = (Button)findViewById(R.id.btn_welcome);
        btn_welcome.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent (MainActivity.this,main_menu.class);
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
	    CreateMenu(menu);
	    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	return MenuChoice(item);
    }

    private void CreateMenu(Menu menu)
    {
	    MenuItem mnu1 = menu.add(0, 0, 0, "About");
	    {
		    mnu1.setAlphabeticShortcut('a');
		    mnu1.setIcon(R.drawable.ic_launcher);
	    }
	    MenuItem mnu2 = menu.add(0, 1, 0, "Exit");
	    {
		    mnu1.setAlphabeticShortcut('b');
		    mnu1.setIcon(R.drawable.ic_launcher);
	    }
	    
	    
    }

    @SuppressWarnings("deprecation")
	private boolean MenuChoice(MenuItem item)
    {
	    switch (item.getItemId()) 
	    {
	    case 0:
		    AlertDialog ad = new AlertDialog.Builder(MainActivity.this).create();
			ad.setTitle("About.. ");
			ad.setIcon(R.drawable.ic_launcher);
			ad.setCanceledOnTouchOutside(true);
			ad.setCancelable(true);
			ad.setMessage("This App was developed By Bhavin Panchal, Tushar Patil, Kiran Shinde. This app is being used for Booking Ticket ");
			ad.show();
			return true;
	    case 1:
	    	AlertDialog ad1 = new AlertDialog.Builder(MainActivity.this).create();
			ad1.setTitle("Do You Really want to Exit ? ");
			ad1.setIcon(R.drawable.ic_launcher);
			ad1.setCancelable(true);
			ad1.setButton("OK",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					finish();	
				}
			});
			ad1.show();
	   
		}
	return false;
	}
     
    public void createtable(){
      	 try{
      	        my_db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
      	        my_db.execSQL("CREATE TABLE IF  NOT EXISTS "+ TABLE +" (ID INTEGER PRIMARY KEY, SENDER INTEGER, MESSAGES TEXT );");
      	        my_db.close();
      	        //Toast.makeText(getApplicationContext(), "TABLE CREATED", Toast.LENGTH_LONG).show();
      	        }
      	 catch(Exception e){
      		 	Toast.makeText(getApplicationContext(), "Error in creating table", Toast.LENGTH_LONG).show();;
      		 	Log.d("Error : ",e.toString());
      	 }
      	}
       
}
