package com.bkp.mumbai.booking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.Button;

public class main_menu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_new);
        
        Button btn_book = (Button)findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				Intent i = new Intent(main_menu.this,Booktkt.class);
				startActivity(i);
								
			}
		});
        
    
    	Button btn_timetable = (Button)findViewById(R.id.btn_timetable);
    	btn_timetable.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(main_menu.this,Timetable.class);
				startActivity(i);
			}
		});
   
    	Button btn_map = (Button)findViewById(R.id.map);
    	btn_map.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i =new Intent(main_menu.this,Mapview.class);
				startActivity(i);
				
				
			}
		});
    	
    	Button btn_booked = (Button)findViewById(R.id.btn_history);
    	btn_booked.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
				
			}
		});
    }
}


/* @Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
}*/