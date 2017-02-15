package manipulate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import mini.project.control.Option;
import mini.project.model.Product;
import share.ConnectionManager;

public final class DAO {

	private static ResultSet rs=null;
	private static CallableStatement cs=null;
	private static Product pro=null;
	private static Connection con=null;
	
	
	public static void inserts(int record){
		try{
			con=ConnectionManager.getConnection();
			cs=con.prepareCall("{call insert_product(?,?,?,?)}");
			pro=new Product();
			for(int i=0;i<record;i++){
				cs.setString(1,pro.getName());
				cs.setFloat(2,pro.getUnitprice());
				cs.setInt(3,pro.getStockquantity());
				cs.setString(4,pro.getImporteddate());
				cs.executeUpdate();
				cs.clearParameters();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionManager.close();
		}

	}
	
	public static int insert(final Product obj){
		try{
			if(obj!=null){
				con=ConnectionManager.getConnection();
				cs=con.prepareCall("{call insert_product(?,?,?,?)}");
				cs.setString(1, obj.getName());
				cs.setFloat(2, obj.getUnitprice());
				cs.setInt(3, obj.getStockquantity());
				cs.setString(4, obj.getImporteddate());
				return cs.executeUpdate();
			}else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			ConnectionManager.close();
		}

	}
	
	public static int update(final Product obj){
		try{
			if(obj!=null){
				con=ConnectionManager.getConnection();
				cs=con.prepareCall("{call update_product(?,?,?,?,?)}");
				cs.setInt(1, obj.getId());
				cs.setString(2, obj.getName());
				cs.setFloat(3, obj.getUnitprice());
				cs.setInt(4, obj.getStockquantity());
				cs.setString(5, obj.getImporteddate());
				return cs.executeUpdate();
			}else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			ConnectionManager.close();
		}
		
	}
	
	public static int delete(final int key){
		try{
			if(key>-1){
				con=ConnectionManager.getConnection();
				cs=con.prepareCall("{call delete_product(?)}");
				cs.setInt(1,key);
				return cs.executeUpdate();				
			}else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			ConnectionManager.close();
		}
		
	}
	
	public static void selecte(){
		try{
			con=ConnectionManager.getConnection();
			cs=con.prepareCall("{call select_products()}");
			rs=cs.executeQuery();
			while(rs.next())
				Option.products.addElement(new Product(rs.getInt("pro_id"),rs.getString("pro_name"),
						rs.getFloat("pro_price"),rs.getShort("pro_qty"),rs.getString("pro_date")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
