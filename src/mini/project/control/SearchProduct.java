package mini.project.control;

import java.util.ArrayList;
import java.util.Vector;
import mini.project.model.Product;

public class SearchProduct {

    public static Product searchByid() {
        System.out.print("Input Product ID > ");
        int id = ReadKey.readInt();
        new Loading("Searing").start();
        return searchByid(id);
    }

    public static Vector<Product> search() {
        System.out.print("Input (String include name and id) > ");
        String key = ReadKey.readString();
        return SearchProduct.search(key);
    }

    static Product searchByid(int id) {
        for (Product p : Option.products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    static Vector search(String key) {
        Vector ps = new Vector();
        new Loading("Searing").start();
        Option.products.forEach((p) -> {
            if (p.hasThis(key)) {             
                ps.add(p);
            }
        });
        Option.refreshStatus(ps.size());
        ViewProduct.setProducts(ps);
        Pagination.setProducts(ps);
        return ps;
    }
}
