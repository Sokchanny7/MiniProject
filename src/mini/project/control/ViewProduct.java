/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.control;

import java.util.ArrayList;
import java.util.Vector;
import mini.project.model.Product;
import mini.project.view.MiniTable;

public final class ViewProduct {

    private static Vector<Product> products = null;

    public static void setProducts(Vector<Product> products) {
        ViewProduct.products = products;
    }

    public static Vector<Product> getProducts() {
        return ViewProduct.products;
    }

    public static void view() {
        Product p = SearchProduct.searchByid();
        if (p != null) {
            viewRecord(p);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void viewRecord(Product p) {
        System.out.println("Product Detail");
        System.out.println("Imported Date       : " + p.getImporteddate());
        System.out.println("Product ID          : " + p.getId());
        System.out.println("Product Name        : " + p.getName());
        System.out.println("Unit price          : " + p.getUnitprice() + Option.CURRENCY);
        System.out.println("Stock Quantity      : " + p.getStockquantity());
    }

    public static void displayProductList() {
        ReadKey.clearConsole();
        if (Pagination.getProducts() != products) {
            Pagination.setProducts(products);
            Option.refreshStatus(products.size());
            System.out.println("LISRADA");
        }
        MiniTable.tableHeader(Option.HEADER);
        MiniTable.tableBody(products);
        MiniTable.tableFooter();
    }

    public static void view(ArrayList<Object> data) {
        Product p = SearchProduct.searchByid(Integer.parseInt((String)data.get(0)));
        if (p != null) {
            viewRecord(p);
        } else {
            System.out.println("Product not found.");
        }
    }
}
