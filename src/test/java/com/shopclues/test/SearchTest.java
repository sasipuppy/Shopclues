package com.shopclues.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.shopclues.page.SearchPage;
import com.shopclues.utilities.XLUtils;

public class SearchTest extends BaseClass{
	
	 SearchPage sp= new SearchPage(driver);
	
	 @Test(dataProvider="ProductData")
	public void searchProductTest(String product) throws InterruptedException {
		sp= new SearchPage(driver);
		sp.clearSearchItem();
		sp.enterSearchItem(product);
		logger.info("Entered Product as "+ product+ " in the search field.");
		sp.clickSearch();
		logger.info("Clicked search button.");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		sp.clickFirstProduct();
		logger.info("Clicked the first product.");
		sp.switchToNewWindow();
		logger.info("Switching to a new window");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		logger.info("Product name: "+ sp.getProductName());
		Thread.sleep(2000);
		logger.info("Product price in rupees : "+ sp.getPrice());
		sp.closeTab();
		
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
