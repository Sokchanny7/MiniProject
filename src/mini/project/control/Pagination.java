package mini.project.control;

import java.util.ArrayList;
import java.util.Vector;

import mini.project.model.Product;


public class Pagination {

    private static Vector<Product> products;

    public static void setProducts(Vector<Product> products) {
        Pagination.products = products;
    }

    public static Vector<Product> getProducts() {
        return Pagination.products;
    }

    /**
     * Change number of record in each page
     */
    public static void setRecrdnumber() {
        do {
            System.out.print("Input record number (1-100) > ");
            int n = ReadKey.readInt();
            if (setRecrdnumber(n)) {
                break;
            }
        } while (true);
    }

    static boolean setRecrdnumber(int n) {
        if (Validation.isInrank(0, 101, n)) {
            ReadKey.clearConsole();
            if (ReadKey.information("Are yur sure you want to dispay " + n + " in each page ? [y/n] > ")) {
                Option.RECORD_NUMBER = n;
                Option.refreshStatus(Option.RECORD_SIZE);
            }
            return true;
        }
        return false;
    }

    /**
     * Pagination > go next page
     */
    public static void nextPage() {
        if (Option.PAGE_NUMBER < Option.MAX_PAGE) {
            Option.PAGE_NUMBER += 1;
        } else {
            System.out.println("It is the last page.");
        }
        ViewProduct.displayProductList();
    }

    /**
     * Pagination > go previous page
     */
    public static void previousPage() {
        if (Option.PAGE_NUMBER > 1) {
            Option.PAGE_NUMBER -= 1;
        } else {
            System.out.println("It is the first page.");
        }
        ViewProduct.displayProductList();
    }

    /**
     * Go to first page
     */
    public static void firstPage() {
        if (Option.PAGE_NUMBER > 1) {
            Option.PAGE_NUMBER = 1;
        } else {
            System.out.println("It is the first page.");
        }
        ViewProduct.displayProductList();
    }

    /**
     * Go to last page
     */
    public static void lastPage() {
        if (Option.PAGE_NUMBER < Option.MAX_PAGE) {
            Option.PAGE_NUMBER = Option.MAX_PAGE;
        } else {
            System.out.println("It is the last page.");
        }
        ViewProduct.displayProductList();
    }

    /**
     * Go to page ?
     */
    public static void gotoPage() {
        while (true) {
            System.out.print("goto page number (1-" + Option.MAX_PAGE + ") > ");
            int n = ReadKey.readInt();
            if (gotoPage(n)) {
                break;
            }
        }
    }

    static boolean gotoPage(int number) {
        if (Validation.isInrank(0, Option.MAX_PAGE + 1, number)) {
            Option.PAGE_NUMBER = number;
            ViewProduct.displayProductList();
            return true;
        }                    
        return false;
    }
}
