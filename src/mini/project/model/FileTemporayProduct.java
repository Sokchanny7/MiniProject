/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.model;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import mini.project.control.Option;

/**
 *
 * @author NINI
 */
public class FileTemporayProduct {

    public static void saveTemporary(TemporayProduct tproducts) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                ObjectOutputStream os = null;
                try {
                    if (!(new File(Option.FILE_TEMPORARY_URL).exists())) {                        
                        os = new ObjectOutputStream(new FileOutputStream(new File(Option.FILE_TEMPORARY_URL)));
                       // os.writeObject(new Product());
                        os.writeObject(tproducts);
                        Option.map.put(tproducts.getId(),tproducts);
                        os.close();
                    } else {                        
                        os = new ObjectOutputStream(new FileOutputStream(new File(Option.FILE_TEMPORARY_URL), true)) {
                            protected void writeStreamHeader() throws IOException {
                                reset();
                            }
                        };
                       // os.writeObject(new Product());
                        os.writeObject(tproducts);
                        Option.map.put(tproducts.getId(),tproducts);
                        os.close();
                    }
                } catch (IOException ex) {

                }
            }
        };
        thread.start();
    }

    public static void fetch(Vector<TemporayProduct> tproducts) {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(Option.FILE_TEMPORARY_URL));
           TemporayProduct obj;
            while ((obj=(TemporayProduct) is.readObject())!=null) {
            	tproducts.add(obj);
            	 Option.map.put(obj.getId(),obj);
            }
            
        } catch (Exception ex) {
       
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public static void clear() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                File f = new File(Option.FILE_TEMPORARY_URL);
                while (f.exists()) {
                    try {
                        sleep(500);
                        f.delete();
                    } catch (InterruptedException ex) {                     
                    }
                }
            }
        };
        thread.start();
    }

    /*public static void main(String[] args) throws IOException {
        saveTemporary(new TemporayProduct(new Product(), "ww"));
        saveTemporary(new TemporayProduct(new Product(), "ww"));
        saveTemporary(new TemporayProduct(new Product(), "ww"));
        Vector<TemporayProduct> v = new Vector<>();
        fetch(v);
        System.out.println("mini.project.model.FileTemporayProduct.main()" + v.size());
    }*/
}
