package com.bkp.mumbai.booking;

import java.security.PublicKey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.database.Cursor;

public class Getmess extends Activity {
	
	SQLiteDatabase my_db;
	private static String DBNAME = "DATA.db";
    private static String TABLE = "SMS_TABLE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       
        Bundle extras1 = getIntent().getExtras();
        String From = extras1.getString("From");
        String MESSAGE = null;
       
        
        
//        ImageView image_view=(ImageView)findViewById(R.id.imageView1);
//		try{
//		QRTest qrt= new QRTest();
//		Bitmap bm = qrt.getImage();
////		if(bm != null) {
//	        image_view.setImageBitmap(bm);
////	    }
//		}
//		catch(Exception e){
//			System.out.println("Error");
//		}
//        
        if (From.equals("Service")){
        	
        	setContentView(R.layout.getsms);
        	try{
       		 my_db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
                Cursor allrows  = my_db.rawQuery("SELECT * FROM "+  TABLE, null);
                if(allrows.moveToLast()){
                    do{
               
                   	 	String ID = allrows.getString(0);
                   	 	String SENDER= allrows.getString(1);
                   	 	MESSAGE= allrows.getString(2);
                   	 	//Toast.makeText(getApplicationContext(),"ID : "+ID,Toast.LENGTH_SHORT).show();
                   	 	//Toast.makeText(getApplicationContext(),"SENDER : "+SENDER,Toast.LENGTH_SHORT).show();
                   	 	//Toast.makeText(getApplicationContext(),"MESSAGE : "+MESSAGE,Toast.LENGTH_SHORT).show();
                   	 	TextView addresstv = (TextView) findViewById(R.id.addresstv);
                   	 	TextView messagetv = (TextView) findViewById(R.id.messagetv);
                   	 	messagetv.setText(MESSAGE);
                   	 	addresstv.setText(SENDER);
                   	 	
                   
                    }
                    while(allrows.moveToNext());
                }
                my_db.close();
       		}
       		catch(Exception e){
       			Log.d("Error ", e.toString());
       			
       		}
        	
        }
        
        
        else{
		setContentView(R.layout.getsms);
		Bundle extras = getIntent().getExtras(); 
			
	    	String address = extras.getString("address");
	    	String message = extras.getString("message");
	    	TextView addresstv = (TextView) findViewById(R.id.addresstv);
	    	TextView messagetv = (TextView) findViewById(R.id.messagetv);
	    	
	    	
	      
	    	
	        messagetv.setText(message);
	        addresstv.setText(address);
	       
	        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); 
	        long[] pattern= { 100, 1000, 100, 1000};
	        vibrator.vibrate(pattern,-1);
	        try{
				
	            my_db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
	            my_db.execSQL("INSERT INTO " + TABLE + "(SENDER,MESSAGES)VALUES('"+address+"','"+message+"')");
	            Toast.makeText(getApplicationContext(), "Data inserted succesfully", Toast.LENGTH_LONG).show();;
	            my_db.close();
	            
	        }catch(Exception e){
	        	
	            Toast.makeText(getApplicationContext(), "Error in inserting into table : "+e.toString(), Toast.LENGTH_LONG).show();
	            
	            Log.d("Error : ",e.toString());
	            //Log.e(e.toString(),"");
	        }
	        
        
        
	}
        final String mesg= MESSAGE;
        Button book_other = (Button)findViewById(R.id.button1);
        book_other.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				 ImageView image_view=(ImageView)findViewById(R.id.imageView1);
					try{
					QRTest qrt= new QRTest();
					Bitmap bm = qrt.getImage(mesg);
					if(bm != null) {
				        image_view.setImageBitmap(bm);
				    }
					}
					catch(Exception e){
						System.out.println("Error");
					}
		}
		});
	}
	
}
