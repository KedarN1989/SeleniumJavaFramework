package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
	
	public static String readProperties(Env env, String propertyName) {
		File propFile = new File(System.getProperty("user.dir") + "\\config\\"+ env + ".properties");
		FileReader fileReader = null;
		Properties properties = new Properties();
		String value = null;
		try {
			fileReader = new FileReader(propFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		value = properties.getProperty(propertyName.toUpperCase());
		return value;
	}

}
