package com.shopclues.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.shopclues.utilities.ReadConfig;


public class BaseClass {
			
	ReadConfig readConfig=new ReadConfig();
	
	public String baseURL=readConfig.getApplicationURL();
	public String userId=readConfig.getUsername();
	public String password=readConfig.getPassword();
	public String product=readConfig.getProduct();
	public static WebDriver driver;
	public static Logger logger;
	
	String Node="http://192.168.1.80:4444/wd/hub";
	String Node1="http://192.168.56.101:4444/wd/hub";
	
	@Parameters("node")
	@BeforeClass
	public void setup(String node) throws MalformedURLException{
		logger= Logger.getLogger("Shopclues");
		PropertyConfigurator.configure("Log4j.properties");
		
		 if(node.equalsIgnoreCase("local")) {
			 DesiredCapabilities capabilities =DesiredCapabilities.chrome(); 
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(Platform.WINDOWS);
				driver= new RemoteWebDriver(new URL(Node), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
		}
		 else if (node.equalsIgnoreCase("external")) {
			 DesiredCapabilities capabilities =DesiredCapabilities.firefox(); 
				capabilities.setBrowserName("firefox");
				capabilities.setPlatform(Platform.LINUX);
				driver= new RemoteWebDriver(new URL(Node1), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
		 }
		 
		 else if (node.equalsIgnoreCase("local1")) {
			 DesiredCapabilities capabilities =DesiredCapabilities.firefox(); 
				capabilities.setBrowserName("firefox");
				capabilities.setPlatform(Platform.WINDOWS);
				driver= new RemoteWebDriver(new URL(Node), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
		 }
		 driver.get(baseURL);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
	driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
}
