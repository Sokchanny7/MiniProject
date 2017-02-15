package share;

import java.sql.Connection;
import java.sql.DriverManager;

import config.Config;
import config.XMLManager;
import mini.project.control.Loading;

public final class ConnectionManager {

	private static Connection connect=null;
	private static Config obj=null;
	private static String url=null;
	
	public static void open(){
		try {
			new Loading("Loading configuration.\nInitialize connection configuration \nInitializing").start();
			loadConfig();			
			Class.forName("org.postgresql.Driver").newInstance();
			new Loading("Please Wait.Start connecting").start();
			connect=DriverManager.getConnection(url,obj.getUser(),obj.getPwd());
			System.out.println("Connection Sucuessed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try{
			if(connect!=null&&connect.isClosed())
				connect.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isConnected(){
		try{
			if(connect!=null&&!connect.isClosed())
				return true;
			else 
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static Connection getConnection(){
		try{
			if(connect==null||connect.isClosed())
				return connect=DriverManager.getConnection(url,obj.getUser(),obj.getPwd());
			else 
				return connect;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private static void loadConfig(){
		obj=XMLManager.getConfig("configDB/config.xml");
		url="jdbc:postgresql://"+obj.getURL()+obj.getPort()+"/"+obj.getDBName();
	}
	
}
