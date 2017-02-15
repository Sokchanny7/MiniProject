/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mini.project.control.Option;

/**
 *
 * @author NINI
 */
public class FileOption {

    public static void save() {
        try (FileWriter fw = new FileWriter(new File(Option.FILE_OPTION))) {
            fw.write(Option.RECORD_NUMBER + "");
            fw.write("-");
            fw.write(Option.PAGE_NUMBER + "");
            fw.write("-");
            fw.write(Option.THEME + "");
        } catch (IOException ex) {
            Logger.getLogger(FileOption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void recover() {    
        try (FileReader fr = new FileReader(new File(Option.FILE_OPTION))) {
            int ch;
            String temp = "";
            while (-1 != (ch = fr.read())) {
                temp += ((char) ch);
            }
            String[] tmp=temp.split("-");
            Option.RECORD_NUMBER=Integer.parseInt(tmp[0]);
            Option.PAGE_NUMBER=Integer.parseInt(tmp[1]);
            Option.THEME=Integer.parseInt(tmp[2]);
        } catch (IOException ex) {
        	
        }
    }
}
