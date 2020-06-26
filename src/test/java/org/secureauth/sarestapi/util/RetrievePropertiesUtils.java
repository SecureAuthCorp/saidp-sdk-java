package org.secureauth.sarestapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RetrievePropertiesUtils {


	private final static String PROPERTIES_FILE_PATH = "test.properties";
	private Map<String, String> propertiesMap;

	public RetrievePropertiesUtils(){
		propertiesMap = new HashMap<>();
		getAndFieldProperties();
	}

	public RetrievePropertiesUtils(String path){
		getAndFieldProperties(path);
	}

	private void getAndFieldProperties(){
		propertiesMap = new HashMap<>();
		getAndFieldProperties(PROPERTIES_FILE_PATH);
	}

	public Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public String getValueFromProperty(Property property){
		return propertiesMap.get(property.getValue());
	}

	public void getAndFieldProperties(String path){
		Properties properties = new Properties();
		try (InputStream inputStream = RetrievePropertiesUtils.class.getClassLoader().getResourceAsStream(path)) {
			properties.load(inputStream);
			for(String name: properties.stringPropertyNames()){
				propertiesMap.put(name, properties.getProperty(name));
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot load " + PROPERTIES_FILE_PATH + ": " + e.getMessage());
		}
	}
}
