package com.bkp.mumbai.booking;

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

public class QRTest {

	
	public Bitmap getImage(String message ) throws Exception{
	 QRCodeWriter writer = new QRCodeWriter();
   
         EnumMap<EncodeHintType, Object> hint = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
         hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");
         if(message.equalsIgnoreCase("")||message== null || message.equalsIgnoreCase(" ") )
         {
        	 message="No Last Ticket";
         }
         BitMatrix bitMatrix = writer.encode(message, BarcodeFormat.QR_CODE, 150, 150, hint);
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
}
