package mini.project.control;

import java.util.ArrayList;
import mini.project.model.FileTemporayProduct;
import mini.project.model.Product;
import mini.project.model.TemporayProduct;

public class DeletePrduct {

    public static void delete() {
        System.out.print("Input Product ID (to delete) > ");
        int id = ReadKey.readInt();
        delete(id);
    }

    static void delete(int id) {
        new Loading("Searching").start();
        for (int i = 0; i < Option.RECORD_SIZE; i++) {
            if (Option.products.get(i).getId() == id) {
                ViewProduct.viewRecord(Option.products.get(i));
                if (ReadKey.information("Are sure you want to delete this record ? [y/n] > ")) {
                    FileTemporayProduct.saveTemporary(new TemporayProduct(Option.products.get(i), "delete"));
                    Option.products.remove(i);
                    Option.RECORD_SIZE -= 1;
                    Option.refreshMaxpage();
                }
                return;
            }
        }
        System.out.println("Product not found..");
    }
}
