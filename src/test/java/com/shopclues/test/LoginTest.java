package com.shopclues.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.shopclues.page.HomePage;

public class LoginTest extends BaseClass{
	
	HomePage hp= new HomePage(driver);
	
	@Test(priority=1)
	public void urlTest() 
	{ 
	  String title= driver.getTitle();
	  Assert.assertTrue(title.contains("Online Shopping Site India"));
	  logger.info("Application is launched");
	}
	
	@Test(priority=2)
	public void loginTest() throws InterruptedException, IOException 
	{  
	   HomePage hp= new HomePage(driver);
	   hp.clickSignin();
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   hp.switchToPopUp();
	   hp.enterUserId(userId);
	   logger.info("Entered username");
	   hp.enterPassword(password);
	   logger.info("Entered password");
	   hp.clickLogin();
	   Thread.sleep(1000);
	   logger.info("Clicked Login");
	   if((hp.isLoggedIn().equals("Hi sasikala"))) {
		 Assert.assertTrue(true); 
		 logger.info("Login successful");
		 hp.logOut();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 Assert.assertEquals(hp.isLoggedOut(),"Sign In");
		 logger.info("Logout successful");
	  }
	   else
	   {
		   logger.info("login test failed");
		   captureScreen(driver,"loginTest");
		   Assert.assertTrue(false);
		  
	   }
	   
	}
	
		
	
}
