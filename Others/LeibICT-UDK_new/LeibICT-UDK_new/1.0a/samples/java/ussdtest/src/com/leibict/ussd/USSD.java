/*
 * USSD.java
 *
 * Created on November 24, 2008, 12:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.leibict.ussd;

import java.sql.ResultSet;

/**
 *
 * @author Alejandro Leib
 */
public class USSD {
    
    static ResultSet rest ;
    
    static String a = "Hiiiii.... Test";
    /** Creates a new instance of USSD */
     public static native int initialize();
     public static native int connect(String addr,int port);
     public static native int ussd_request(int dialogId,String prompt);
     public static native int ussd_end(int dialogId,String info);
     private static native int pong();

     private static void cb_ussd_service(int dialogId,String shortCode,String phoneNumber) throws Exception{
         
         rest = DBconnection.data();
         
         String bal;
         
         System.out.println(dialogId + " " + shortCode + " " + phoneNumber);
         int res;
         if(shortCode.equals("1"))
         {
             bal = rest.getObject(2).toString();
             int res1;
         res1 = ussd_end(dialogId,"Balance is "+bal);
         System.out.println(phoneNumber);
         }
         res = ussd_end(dialogId,a);
         System.out.println(res);
     }
     private static void cb_ussd_response(int dialogId,String str) {

         int res;
         res = ussd_end(dialogId,"Heloo");
         System.out.println(res);

         System.out.println("cb_response");
     }
     private static void cb_ussd_end(int dialogId){
	 int res;
         res = ussd_end(dialogId,"Heloo1");
         System.out.println(res);

         System.out.println("cb_end"); 
     }
     private static void cb_disconnected(){
	 
         
         System.out.println("cb_disconnected"); 
     }
     private static void cb_connected(){
	 System.out.println("cb_connected"); 
     }
     private static void cb_ping(){
	 System.out.println("cb_ping"); 
	 pong();
     }

     static {
         System.loadLibrary("ussdjni");
     }
}
