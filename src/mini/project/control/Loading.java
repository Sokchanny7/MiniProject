/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.control;

import static java.lang.Thread.sleep;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mini.project.model.Product;

public class Loading{

    String message;

    public Loading(String message) {      
        this.message = message;
    }

    //@Override
    public void start() {
        int i = 0;
        while (i<5) {
            try {                                
                if (i == 0) {
                    ReadKey.clearConsole();
                    System.out.print(message);
                }
                sleep(250);
                i++;
                load();
            } catch (InterruptedException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println();
    }

    public void load() {
        System.out.print(".");
    }
}
