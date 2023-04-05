package com.TestingShastra.API.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static ConfigManager manager;
	private static final Properties prop = new Properties();

	private ConfigManager() {
		String basedir = System.getProperty("user.dir");
		InputStream inputStream = ConfigManager.class
				.getResourceAsStream(basedir+ "\\src\\main\\resources\\config.properties");
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ConfigManager getInstance() {
		if (manager == null) {
			synchronized (ConfigManager.class) {
				manager = new ConfigManager();
			}
		}
		return manager;
	}

	public String getString(String Key) {
		return System.getProperty(Key,prop.getProperty(Key));
	}

}
