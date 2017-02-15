package mini.project.control;

import java.util.ArrayList;
import java.util.Vector;
import mini.project.model.FileTemporayProduct;
import mini.project.model.Product;
import mini.project.model.SaveProduct;
import mini.project.model.TemporayProduct;

/**
 *
 * @author NINI
 */
public class InsertProduct {

    /*
    insert new product one by one
     */
	private static int id;
    public static void insert() {
        ReadKey.clearConsole();
        ArrayList<Object> data = new ArrayList();
        if (Pagination.getProducts() != Option.products) {
            Pagination.setProducts(Option.products);
            Option.refreshStatus(Option.products.size());
        }
      /*  if (Option.RECORD_SIZE != 0) {
            id = Option.products.get(Option.RECORD_SIZE - 1).getId() + 1;
        } else {
            id = 1;
        }*/
        id=Option.products.lastElement().getId()+1;
        System.out.println("Product ID : " +id);
        System.out.print("Name           >");
        data.add(ReadKey.readString());
        System.out.print("Unit price     >");
        data.add(ReadKey.readDouble());
        System.out.print("Stock Quantity >");
        data.add(ReadKey.readLong());

        /*
        Adding new producnt into products of list
         */
        InsertProduct.insert(data);
    }

    static void insert(ArrayList<Object> data) {
        if (Pagination.getProducts() != Option.products) {
            Pagination.setProducts(Option.products);
            Option.refreshStatus(Option.products.size());
        }
       /* if (Option.RECORD_SIZE != 0) {
            id = Option.products.get(Option.RECORD_SIZE - 1).getId() + 1;
        } else {
            id = 1;
        }*/
        try {
            ReadKey.clearConsole();
            Product p = new Product(id, data.get(0).toString(),
                    Float.parseFloat(data.get(1).toString()),
                    Integer.parseInt(data.get(2).toString()));
            ViewProduct.viewRecord(p);
            if (ReadKey.information("Are you sure you want to save this record ? [y/n] > ")) {
                Option.products.add(p);
                
                /**
                 * Saving new product into temporary file
                 */
                FileTemporayProduct.saveTemporary(new TemporayProduct(p, "insert"));
                Option.RECORD_SIZE += 1;
                Option.refreshMaxpage();
            } else {
                return;
            }
            ReadKey.clearConsole();
        } catch (Exception ex) {
            // no implement.
        }
    }

    public static void insert(Product p) {
        Option.products.add(p);
        FileTemporayProduct.saveTemporary(new TemporayProduct(p, "insert"));
        Option.RECORD_SIZE += 1;
        Option.refreshMaxpage();
    }
}
