package com.purplle.genericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass implements IAutoConstants {
	public String readDataFromProperty(String Key)   {
		File file= new File(PROPERTIES_PATH);
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
		} catch (Exception e) {
		}
		Properties prop=new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
		}
		String value=prop.getProperty(Key);
		return value;
	}
}

