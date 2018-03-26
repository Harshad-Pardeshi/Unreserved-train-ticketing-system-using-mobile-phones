package com.example.qrtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView image_view=(ImageView)findViewById(R.id.imageView1);
		try{
		QRTESt qrt= new QRTESt();
		Bitmap bm = qrt.getImage();
		if(bm != null) {
	        image_view.setImageBitmap(bm);
	    }
		}
		catch(Exception e){
			System.out.println("Error");
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
		
	}

	
	
}
