package mini.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import mini.project.control.Option;

public class Product implements Serializable{

    protected int id;
    protected String name;
    protected float unitprice;
    protected int stockquantity;
    protected String importeddate = Option.date;

    public String toString() {
        return "ID > " + getId() + " > " + getName() + " > " + getUnitprice() + " > " + getStockquantity() + " > " + getImporteddate();
    }

    /*
    Accessor
     */
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImporteddate() {
        return this.importeddate;
    }

    public float getUnitprice() {
        return this.unitprice;
    }

    public int getStockquantity() {
        return this.stockquantity;
    }

    /*
    Implementor
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImporteddate(String date) {
        this.importeddate = date;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public void setStockquantity(int stockquantity) {
        this.stockquantity = (short) stockquantity;
    }

    /*
    Constructor
     */
    public Product() {
        this.id = 0;
        this.name = "N/A";
        this.unitprice = 0.0F;
        this.stockquantity = 0;
    }

    public Product(int id,String name, float unitprice, int stockquantity) {
    	this.id=id;
        this.name = name;
        this.unitprice = unitprice;
        this.stockquantity = stockquantity;
    }
    
    public Product(int id, String name, float unitprice, int stockquantity, String importeddate) {
        this.id = id;
        this.name = name;
        this.unitprice = unitprice;
        this.stockquantity = stockquantity;
        this.importeddate = importeddate;
    }

    /*
    this method use for searching by id
     */
    public Object isMe(int id) {
        if (this.id == id) {
            return this;
        }
        return null;
    }

    /**
     * clone product
     */
    public void clone(Product p) {
        if (p == null) {
            p = new Product();
        }
        p.setId(this.getId());
        p.setName(this.getName());
        p.setUnitprice(this.getUnitprice());
        p.setStockquantity(this.getStockquantity());
        p.setImporteddate(this.getImporteddate());
    }

    public boolean hasThis(String match) {
        String str = getId() + getName();// + getUnitprice() + getStockquantity() + getImporteddate();
        return str.matches("(?i).*" + match + ".*");
    }

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> data = new ArrayList<>();
        data.add(getId());
        data.add(getName());
        data.add(getUnitprice());
        data.add((int) getStockquantity());
        data.add(getImporteddate());
        return data;
    }
}
