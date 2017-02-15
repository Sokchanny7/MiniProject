/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


import manipulate.DAO;
import mini.project.control.Loading;
import mini.project.control.Option;
import mini.project.control.Pagination;
import mini.project.control.ReadKey;
import mini.project.control.Validation;

/**
 *
 * @author NINI
 */
public final class RetrieveProduct {

    private static ObjectInputStream ois = null;
    private static long start = 0;
    
    public static void fetch() {
        fetch("Please wait.\nProgram is loading record from database.\nloading record");
        
    }

    private static void fetch(String message) {
        new Loading(message).start();
        try {
            DAO.selecte();
            if(Option.products.isEmpty())
            	System.out.println("No record from database were loading.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        Option.refreshStatus(Option.products.size());
        Pagination.setProducts(Option.products);
    }

    public static void restore() {
    	  File[] bakuplist = new File("Backup/").listFiles();
          if (bakuplist.length > 0) {
              System.out.println("Restorable file");
              for (int i = 1; i <= bakuplist.length; i++) {
                  System.out.println("\t" + i + " > " + bakuplist[i - 1].getName());
              }
              int i;
              System.out.print("Input number of restorable file name (1-" + (bakuplist.length) + ") > ");
                  i = ReadKey.readInt();
                  if (Validation.isInrank(0, bakuplist.length + 1, i)) {
                	  if(!Option.products.isEmpty())Option.products.clear();
                   	  readBackUp("Please wait.\nProgram is restoring data from file ( "
                              + bakuplist[i - 1].getName() + " )\n\nReading",
                              ("Backup/" + bakuplist[i - 1].getName()));
                
              }
          }else
        	  System.out.println("No Backup file");
    }
     private static void readBackUp(String message,String path){
    	 ObjectInputStream in=null;
    	 try{
    		 new Loading(message).start();
    		 in=new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
         	  while(true){
         		   Option.products.add((Product) in.readObject());
         	   }
            }catch(Exception e){
         	   
            }finally{
            	Option.refreshStatus(Option.products.size());
                Pagination.setProducts(Option.products);
         	   if(in!=null)
     			try {
     				in.close();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
            }
     }
      /* File f=null;
      
       try{
    	   f=new File(Option.FILE_BACKUP_URL);
    	   in=new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
    	   if(!Option.products.isEmpty())Option.products.clear();
    	   while(true){
    		   Option.products.addElement((Product) in.readObject());
    	   }
       }catch(Exception e){
    	   
       }finally{
    	   if(in!=null)
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }*/
        
    
}
