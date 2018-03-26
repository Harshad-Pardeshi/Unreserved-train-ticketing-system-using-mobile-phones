package com.example.qrtest;



import java.util.EnumMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

public class QRTESt {

	
	public Bitmap getImage() throws Exception{
	 QRCodeWriter writer = new QRCodeWriter();
   
         EnumMap<EncodeHintType, Object> hint = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
         hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");
         BitMatrix bitMatrix = writer.encode("content", BarcodeFormat.QR_CODE, 150, 150, hint);
         int width = bitMatrix.getWidth();
         int height = bitMatrix.getHeight();
         int[] pixels = new int[width * height];
         for (int y = 0; y < height; y++)
         {
             int offset = y * width;
             for (int x = 0; x < width; x++)
             {
                  pixels[offset + x] = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
//                 pixels[offset + x] = bitMatrix.get(x, y) ? colorBack : colorFront;
             }
         }

         Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
         bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
         return bitmap;
	
     }

//	// this is a small sample use of the QRCodeEncoder class from zxing
//	try {
//	    // generate a 150x150 QR code
//		
//		String barcode_content= new String("Heloo");
//	    Bitmap bm = encodeAsBitmap(barcode_content, BarcodeFormat.QR_CODE, 150, 150);
//
//	    if(bm != null) {
//	        image_view.setImageBitmap(bm);
//	    }
//	} catch (WriterException e) { System.out.print("Error"); 
//	}

         
         
         
//	
//	private static void testCODE39(Canvas canvas) throws Exception
//	{
//	    QRCode barcode = new QRCode();
//	    
//	    /*
//	       Code39 Valid data char set:
//	            0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
//	            A - Z (Uppercase letters)
//	            - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)
//	    
//	       Code39 extension Valid data char set:
//	            All ASCII 128 characters
//	    */
//	    // Code39 encodes upper case chars only, for lower case chars, use Code 39 extension
//	    barcode.setData("123456789012");
//	    
//	    barcode.setExtension(false);
//	    
//	    barcode.setAddCheckSum(true);
//	    
//	    // Code 39 Wide Narrow bar Ratio
//	    // Valid value is from 2.0 to 3.0 inclusive.
//	    barcode.setN(3.0f);
//	    // The space between 2 characters in code 39; This a multiple of X; The default is 1.;
//	    // Valid value is from 1.0 (inclusive) to 5.3 (exclusive)
//	    barcode.setI(1.0f);
//	    barcode.setShowStartStopInText(true);
//	    
//	    // Unit of Measure, pixel, cm, or inch
//	    barcode.setUom(IBarcode.UOM_PIXEL);
//	    // barcode bar module width (X) in pixel
//	    barcode.setX(1f);
//	    // barcode bar module height (Y) in pixel
//	    barcode.setY(75f);
//	    
//	    // barcode image margins
//	    barcode.setLeftMargin(10f);
//	    barcode.setRightMargin(10f);
//	    barcode.setTopMargin(10f);
//	    barcode.setBottomMargin(10f);
//	    
//	    // barcode image resolution in dpi
//	    barcode.setResolution(72);
//	    
//	    // disply barcode encoding data below the barcode
//	    barcode.setShowText(true);
//	    // barcode encoding data font style
//	    barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
//	    // space between barcode and barcode encoding data
//	    barcode.setTextMargin(6);
//	    barcode.setTextColor(AndroidColor.black);
//	    
//	    // barcode bar color and background color in Android device
//	    barcode.setForeColor(AndroidColor.black);
//	    barcode.setBackColor(AndroidColor.white);
//	    
//	    RectF bounds = new RectF(30, 30, 0, 0);
//	    barcode.drawBarcode(canvas, bounds);
//	    
//	    GeneratedBarcodeInfo barInfo = barcode.getBarcodeInfo();
//	}
}
