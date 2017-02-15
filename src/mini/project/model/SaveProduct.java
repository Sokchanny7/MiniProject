package mini.project.model;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import manipulate.DAO;
import mini.project.control.Loading;
import mini.project.control.Option;

public final class SaveProduct {

    private static FileChannel s, d;
    private static ObjectOutputStream out = null;
    private static TemporayProduct pro=null;
    
    public static void saveOverride() {
        //long a = System.currentTimeMillis();
        try {
        	for(Map.Entry<Integer, TemporayProduct>obj:Option.map.entrySet()){
        		pro=obj.getValue();
        		switch(pro.status){
        		case INSERT:
        			new Loading("Please wait.\nInsert record into database\nInserting").start();
        			DAO.insert(pro);break;
        		case UPDATE:
        			new Loading("Please wait.\nUpdate record into database\nUpdating").start();
        			DAO.update(pro);break;
        		case DELETE:
        			DAO.delete(pro.getId());
        			new Loading("Please wait.\nDelete record from database\nDeleting").start();
        			break;
        		}
           	}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           
              
            
        }

       // System.out.println((System.currentTimeMillis() - a) / 1000 + "Secods");
    }

   
   /* public static void Export() {
        long a = System.currentTimeMillis();
        try {
            File f = new File(Option.FILE_URL);
            if (f.exists()) {
                return;
            }
            new Loading("Please wait.\nSaving data in to file\nWriting").start();
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            for (int i = 0; i < 10_000_000; i++) {
                out.writeObject(new Product(i + 1, "IMPORTED PRODUCT", 500, 100, Option.date));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();

                } catch (IOException ex) {
                    Logger.getLogger(SaveProduct.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println((System.currentTimeMillis() - a) / 1000 + "Secods");
    }
*/
    public static void backUp() {
    	File f=null;
    	ObjectOutputStream out=null;
    	new Loading("Start Backup data from application into file.\nStarting").start();
        try {
             f= new File(Option.FILE_BACKUP_URL);
            out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            for(Product obj:Option.products){
            	out.writeObject(obj);
            }
           // Files.copy(f.toPath(), new File(copy).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            //Logger.getLogger(SaveProduct.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Backup failed.");
        }finally{
        	if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        System.out.println("Back up completed.\nInto path "+f.getAbsolutePath());
    }
}
