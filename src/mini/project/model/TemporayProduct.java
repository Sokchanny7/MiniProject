/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.project.model;

import java.io.Serializable;

/**
 *
 * @author NINI
 */
public class TemporayProduct extends Product implements Serializable {

	 public enum STATUS{
	    	INSERT{
	    		@Override
	    		public String toString() {
	    			
	    			return "insert";
	    		}
	    	},
	    	UPDATE{
	    		@Override
	    		public String toString() {
	    			
	    			return "update";
	    		}
	    	},
	    	DELETE{
	    		@Override
	    		public String toString() {
	    		
	    			return "delete";
	    		}
	    	}
	 }
	private Product product;
    public STATUS status;
   
    public TemporayProduct(Product product,String status) {
        this.product = product;
        this.setStatus(status);
        this.id=product.getId();
        this.name=product.getName();
        this.unitprice=product.getUnitprice();
        this.stockquantity=product.getStockquantity();
        this.importeddate=product.getImporteddate();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String getStatus() {
        return status.toString();
    }
    
    @Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getImporteddate() {
		// TODO Auto-generated method stub
		return this.importeddate;
	}

	@Override
	public float getUnitprice() {
		// TODO Auto-generated method stub
		return this.unitprice;
	}

	@Override
	public int getStockquantity() {
		// TODO Auto-generated method stub
		return this.stockquantity;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.setId(id);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.setName(name);
	}

	@Override
	public void setImporteddate(String date) {
		// TODO Auto-generated method stub
		this.setImporteddate(date);
	}

	@Override
	public void setUnitprice(float unitprice) {
		// TODO Auto-generated method stub
		this.setUnitprice(unitprice);
	}

	@Override
	public void setStockquantity(int stockquantity) {
		// TODO Auto-generated method stub
		this.setStockquantity(stockquantity);
	}

	/**
     * Product status
     *
     * @param status
     */
    private void setStatus(String status) {
        if(status.equalsIgnoreCase("insert"))
        	this.status=STATUS.INSERT;
        else if(status.equalsIgnoreCase("update"))
        	this.status=STATUS.UPDATE;
        else
        	this.status=STATUS.DELETE;
    }

    public void delete() {
        setStatus("delete");
    }

    public void insert() {
        setStatus("insert");
    }

    public void update() {
        setStatus("update");
    }
}
