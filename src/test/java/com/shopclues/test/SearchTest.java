package com.shopclues.test;

import org.testng.annotations.Test;


import com.shopclues.page.SearchPage;

public class SearchTest extends BaseClass{
	
	 SearchPage sp= new SearchPage(driver);
	
	 @Test
	public void searchProductTest() {
		sp= new SearchPage(driver);
		sp.clearSearchItem();
		sp.enterSearchItem(product);
		logger.info("Entered Product as "+ product+ " in the search field.");
		sp.clickSearch();
		logger.info("Clicked search button.");
		logger.info("Product name: "+ sp.getProductName());
		logger.info("Product price in rupees : "+ sp.getProductPrice());
		
	}

}
