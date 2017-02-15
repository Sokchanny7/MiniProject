package mini.project.control;

import java.io.IOException;
import java.util.Scanner;

import mini.project.view.Box;
import mini.project.view.MiniTable;

public class Menu {

    /*
    Main Menu is the menu that show and the first time
     */
    public static String mainMenu() {               
    	new Box(Option.THEME);
        System.out.print("\n\nOption >");
        return ReadKey.readOption();
    }

    /*
    Update is have its individually menu show
     */
    public static char[] updateMenu() {
        MiniTable.box(Option.MENU_UPDATE);
        System.out.print("\n\nOption >");
        return ReadKey.readOption().toCharArray();
    }
}
