package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.TestUtil;

/*ProjectName: Classic CRM Application Framework
 * Description: TestBase contains functions to load Config properties, 
 * 				intitialize Webdriver which is used by all pages
 * Created By:Aswathy
 * Date of Creation: 06-June-2021
 * Version: V1.1
 */

public class TestBase  {
	
		public static WebDriver driver;
		public static Properties prop;
	
	/*
	 * TestBase Class Constructor : used to initialize the Properties object to 
	 * fetch config(env) variables from config.proprties file
	 */
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(TestUtil.configPath);
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",TestUtil.driverPath+"chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Wait,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

}
