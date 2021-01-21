package com.shopclues.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shopclues.page.SearchPage;
import com.shopclues.utilities.XLUtils;

public class RobotClassTest extends BaseClass{
	
	SearchPage sp= new SearchPage(driver);
	
	@Test(dataProvider="ProductData")
	public void RobotSearchTest(String product) throws AWTException  {
		sp= new SearchPage(driver);
		sp.clearSearchItem();
		sp.enterSearchItem(product);
		logger.info("Entered Product as "+ product+ " in the search field.");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Point coordinates =sp.getSearchCoordinates();
		Robot robot = new Robot();
		robot.mouseMove(coordinates.getX(),(coordinates.getY()+5) +120);
		//robot.keyPress(KeyEvent.VK_ENTER);
		//robot.keyRelease(KeyEvent.VK_ENTER);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		logger.info("Clicked enter");
		String count= sp.getCount();
		Assert.assertNotEquals(count,0);
		logger.info(count+" items found.");	
		
	}
	
	 @DataProvider(name="ProductData")
		String[] getData() throws IOException
		{
			String path=System.getProperty("user.dir")+"/src/test/java/com/shopclues/testData/Data.xlsx";
			int rownum=XLUtils.getRowCount(path,"Sheet2");
			String searchdata[]=new String[rownum-1];
			
			for(int i=0;i<rownum-1;i++)
			{
				searchdata[i]=XLUtils.getCellData(path,"Sheet2",i+1,0);
			}
		
		return searchdata;
		}


	
}
