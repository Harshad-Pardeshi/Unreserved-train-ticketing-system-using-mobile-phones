/*
 * Main.java
 *
 * Created on November 24, 2008, 12:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ussdtest;

import com.leibict.ussd.*;

/**
 *
 * @author Alejandro Leib
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
	try{
                int res;
		res = USSD.initialize();
		res = USSD.connect("localhost",5454);
		while(true){
			//do statistics, compare counters, ping the, etc
			Thread.sleep(1000);
		}
	}
	catch (InterruptedException x) {
				
	}
    }
}
