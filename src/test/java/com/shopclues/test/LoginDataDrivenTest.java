package com.shopclues.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.shopclues.page.HomePage;
import com.shopclues.utilities.XLUtils;

public class LoginDataDrivenTest extends BaseClass{
	HomePage hp= new HomePage(driver);
	
	@Test(dataProvider="LoginData")
	public void loginTest(String userId,String password) throws InterruptedException, IOException 
	{  
	   hp= new HomePage(driver);
	   hp.clickSignin();
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   hp.switchToPopUp();
	   hp.clearUserId();
	   hp.enterUserId(userId);
	   logger.info("Entered username");
	   hp.clearPassword();
	   hp.enterPassword(password);
	   logger.info("Entered password");
	   Thread.sleep(1000);
	   hp.clickLogin();
	   Thread.sleep(1000);
	   logger.info("Clicked Login");
	   if((hp.isLoggedIn().equals("Hi sasikala"))) {
		 Assert.assertTrue(true); 
		 logger.info("Login successful");
		 Thread.sleep(1000);
		 hp.logOut();
		 Thread.sleep(1000);
		 logger.info("Logout successful");
	  }
	   else 
	   {  
		   if(hp.isDisplayedLoginError())
			   logger.info(hp.getLoginError());
		   else if(hp.isDisplayedPasswordError())
			   logger.info(hp.getPasswordError());
		   else if(hp.isDisplayedUserIdError())
			   logger.info(hp.getUserIdError());
		   logger.info("login test failed");
		   captureScreen(driver,"loginDataDrivenTest");
		   Assert.assertTrue(false);
		   hp.clickCloseButton();
	   }
	  	   	     
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/shopclues/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);
			}
				
		}
	return logindata;
	}

}
