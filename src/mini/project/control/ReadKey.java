/*
Get user input
 */
package mini.project.control;

import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadKey {

    private static Scanner sc = new Scanner(System.in);

    public static String readOption() {
        String str;
        while (true) {
            str = readString();
            if (!(str.equals(""))) {
                ReadKey.clearConsole();
                return str;
            }
            System.out.print("Input again > ");
        }
    }

    public static String readString() {
        String str;
        while (true) {
            try {
                str = sc.nextLine();
                if (!(str.equals(""))) {
                    return str;
                }
            } catch (Exception ex) {

            }
        }
    }

    public static char readChar() {
        String str = readString();
        return str.charAt(0);
    }

    public static double readDouble() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception ex) {
                sc.nextLine();
                System.out.print("Input again(*only number)  >");
            }
        }
    }
    public static float readFloat() {
        while (true) {
            try {
                return sc.nextFloat();
            } catch (Exception ex) {
                sc.nextLine();
                System.out.print("Input again(*only number)  >");
            }
        }
    }

    public static long readLong() {
        while (true) {
            try {
                return sc.nextLong();
            } catch (Exception ex) {
                sc.nextLine();
                System.out.print("Input again(*only number)  >");
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception ex) {
                sc.nextLine();
                System.out.print("Input again(*only number)  >");
            }
        }
    }

    public static String readDate() {
        return readString();
    }

    public static void clearConsole() {
   
        for (int i = 0; i < 70; i++) {
            System.out.println();
        }
    }

    public static boolean information(String message) {
        return information(message, "Completed...", "Canceled...");
    }

    public static boolean information(String message, String complete, String cancel) {
        while (true) {
            System.out.print(message);
            char ch = readChar();
            if (ch == 'y' || ch == 'Y') {
                System.out.println(complete);
                return true;
            } else if (ch == 'n' || ch == 'N') {
                System.out.println(cancel);
                return false;
            }
        }
    }

    public static void load(String message) {
        int i = 0;
        System.out.print(message);
        while (i < 10) {
            try {
                sleep(0);
                i++;
                System.out.print(".");
            } catch (InterruptedException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println();
    }
}
