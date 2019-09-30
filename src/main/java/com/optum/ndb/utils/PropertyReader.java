package com.optum.ndb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader 
{
	Properties properties = new Properties();
    InputStream inputStreamConfig = null;

    public static String filePath=System.getProperty("user.dir")+"//src//main//resources//config.properties" ;
	
    public PropertyReader() {
        loadProperties();
    }

    private void loadProperties() {
        try {
        	
            inputStreamConfig = new FileInputStream(filePath);
            properties.load(inputStreamConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
