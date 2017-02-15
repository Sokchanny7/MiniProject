package config;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CONFIG")
public final class Config {
	
	private String url;
	private String port;
	private String dbName;
	private String usa;
	private String pwd;
	
	public Config(){
		super();
	}
	@XmlElement(name="URL")
	public void setURL(final String value){
		this.url=value;
	}
	
	public String getURL(){
		return this.url;
	}
	@XmlElement(name="PORT")
	public void setPort(final String value){
		this.port=value;
	}
	
	public String getPort(){
		return this.port;
	}
	
	@XmlElement(name="DBNAME")
	public void setDBName(final String value){
		this.dbName=value;
	}
	
	public String getDBName(){
		return this.dbName;
	}
	
	@XmlElement(name="USER")
	public void setUser(final String value){
		this.usa=value;
	}
	
	public String getUser(){
		return this.usa;
	}
	
	@XmlElement(name="PassWord")
	public void setPwd(final String value){
		this.pwd=value;
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
	@Override
	public String toString() {
		return "Config [url=" + url + ", port=" + port + ", dbName=" + dbName + ", usa=" + usa + ", pwd=" + pwd + "]";
	}


}

