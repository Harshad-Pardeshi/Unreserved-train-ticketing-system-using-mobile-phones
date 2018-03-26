/*
 * USSD.java
 *
 * Created on November 24, 2008, 12:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.leibict.ussd;

/**
 *
 * @author Alejandro Leib
 */
public class USSD {
    
    /** Creates a new instance of USSD */
     public static native int initialize();
     public static native int connect(String addr,int port);
     public static native int ussd_request(int dialogId,String prompt);
     public static native int ussd_end(int dialogId,String info);
     private static native int pong();

     private static void cb_ussd_service(int dialogId,String shortCode,String phoneNumber) {
         System.out.println(dialogId + " " + shortCode + " " + phoneNumber);
         int res;
         res = ussd_end(dialogId,"bye!");
         System.out.println(res);
     }
     private static void cb_ussd_response(int dialogId,String str) {
         System.out.println("cb_response");
     }
     private static void cb_ussd_end(int dialogId){
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
