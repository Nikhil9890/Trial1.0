package com.TestingShastraLogic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.TestingShatra_utility.Fileutility;

public class TestBase extends KeyWord{

	@BeforeMethod
	public static void launchBrowser() {
		String env=System.getProperty("env");
		KeyWord.openbrowser("chrome");
		KeyWord.launchurl(Fileutility.getappurl(env));
	}
	
	@AfterMethod
	public static void closebrowser() {
		KeyWord.driver.quit();
	}
}
