package mini.project.control;

import java.util.ArrayList;
import java.util.Vector;

import manipulate.DAO;
import mini.project.model.FileTemporayProduct;
import mini.project.model.Product;
import mini.project.model.TemporayProduct;

/**
 *
 * @author NINI
 */
public class UpdateProduct {

    public static void update() {
        Product p;
        /**
         * calling search product by its id.
         */
        Product p1 = SearchProduct.searchByid();
        if (p1 != null) {
            p = new Product();
            p1.clone(p);
            ViewProduct.viewRecord(p);
            /*
            updateMenu returning arrayChar
             */
            char[] ch = Menu.updateMenu();
            for (int i = 0; i < ch.length; i++) {
                switch (ch[i]) {
                    case 'a' | 'A':
                        updateAll(p);
                        break;
                    case 'n' | 'N':
                        updateName(p);
                        break;
                    case 'u' | 'U':
                        updateUnitprice(p);
                        break;
                    case 's' | 'S':
                        updateStockquantity(p);
                        break;
                    case 'i' | 'I':
                        updateImporteddate(p);
                        break;
                    case 'b' | 'B':
                        if (ReadKey.information("Are your sure you want to cancel ? [y/n] > ")) {
                            return;
                        }
                        break;
                }
            }

            ReadKey.clearConsole();
            ViewProduct.viewRecord(p);
            if (ReadKey.information("Are you sure you want to Update this record ? [y/n] > ")) {
            	p.clone(p1);
                FileTemporayProduct.saveTemporary(new TemporayProduct(p1, "update"));            
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     *
     * Update for temporary products
     */
    public static void updateAll(Product p) {
        updateName(p);
        updateUnitprice(p);
        updateStockquantity(p);
        updateImporteddate(p);
    }

    public static void updateName(Product p) {
        System.out.print("New Name              > ");
        p.setName(ReadKey.readString());
    }

    public static void updateUnitprice(Product p) {
        System.out.print("New Unitprice         > ");
        p.setUnitprice(ReadKey.readFloat());
    }

    public static void updateStockquantity(Product p) {
        System.out.print("New Stock Quantity    > ");
        p.setStockquantity(ReadKey.readInt());
    }

    public static void updateImporteddate(Product p) {
        System.out.print("New Imported Date     > ");
        p.setImporteddate(ReadKey.readDate());
    }

    /**
     * Update date by giving string array // expression and temporary
     */
    static void update(ArrayList<Object> data) {
        Product p = SearchProduct.searchByid((int) data.get(0));
        ReadKey.clearConsole();
        if (p != null) {
            Product p1 = new Product();
            p.clone(p1);
            p1.setName((String) data.get(1));
            p1.setUnitprice((float) data.get(2));
            p1.setStockquantity((int) data.get(3));
            p1.setImporteddate((String) data.get(4));
            ViewProduct.viewRecord(p1);
            if (ReadKey.information("Are you sure you want to Update this record ? [y/n] > ")) {
                p1.clone(p);
                FileTemporayProduct.saveTemporary(new TemporayProduct(p1, "update"));
            }
        } else {
            System.out.println("Product not found.");
        }
    }
}
