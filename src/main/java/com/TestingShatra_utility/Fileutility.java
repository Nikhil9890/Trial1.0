package com.TestingShatra_utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Fileutility {

	public static String getappurl(String env) {
		String basedir = System.getProperty("user.dir");
		String urlvalue = "";
		FileInputStream fis = null;
		Properties prp = null;
		try {
			fis = new FileInputStream(basedir + "src\\main\\resources\\weburl.properties");
			prp = new Properties();
			prp.load(fis);
			urlvalue = prp.getProperty("app" + env + "url");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlvalue;
	}
}
