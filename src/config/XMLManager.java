package config;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class XMLManager {
	
	
	public static Config getConfig(String path){
		Config obj=null;
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Config.class);
			Unmarshaller jaxbUnmarshaller=jaxb.createUnmarshaller();
			obj=(Config) jaxbUnmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return obj;
	}
/*
	public static void putXMLData(){
		Config obj=new Config();
		obj.setURL("localhost:");
		obj.setPort("5432");
		obj.setDBName("productdb");
		obj.setUser("postgres");
		obj.setPwd("1234");
		try {
			JAXBContext jaxb=JAXBContext.newInstance(Config.class);
			Marshaller jaxbMarshaller=jaxb.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(obj, new File("configDB/config.xml"));
			jaxbMarshaller.marshal(obj, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
}
