/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.control;

import java.util.Vector;
import mini.project.model.FileTemporayProduct;
import mini.project.model.TemporayProduct;

/**
 *
 * @author NINI
 */
public class ChekTemporaryProduct {

    private static Vector<TemporayProduct> tproducts = new Vector<>();

    public static Vector<TemporayProduct> getTproducts() {
        return tproducts;
    }

    public static boolean isHastemporaydata() {
        FileTemporayProduct.fetch(tproducts);
        if (tproducts.size() > 0) {
            return true;
        }
        return false;
    }

    public static void reCoverdata() {
        ChekTemporaryProduct.tproducts.forEach((tp) -> {
            switch (tp.status) {
                case INSERT:
                    System.out.println("Insert " + tp.getProduct().toString());
                    InsertProduct.insert(tp.getProduct());
                    break;
                case UPDATE:
                    System.out.println("Update " + tp.getProduct().toString());
                    UpdateProduct.update(tp.getProduct().toArrayList());
                    break;
                case DELETE:
                    System.out.println("delete " + tp.getProduct().toString());
                    DeletePrduct.delete(tp.getProduct().getId());
                    break;
            }
        });
        clear();
    }

    static void clear() {
        FileTemporayProduct.clear();
        ChekTemporaryProduct.tproducts.clear();

    }

    public static int getRecord() {
        return ChekTemporaryProduct.tproducts.size();
    }
}
