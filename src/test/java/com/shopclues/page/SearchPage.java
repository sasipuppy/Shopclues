package com.shopclues.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
WebDriver ldriver;
	
	public SearchPage(WebDriver rdriver)
	{
	   ldriver=rdriver;
	   PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="autocomplete")
	WebElement txtSearch;
	
	@FindBy(linkText="Search")
	WebElement btnSearch;
	
	@FindBy(xpath="//*[@id=\"product_list\"]/div[3]/div[1]/a/h2")
	WebElement productName;
	
	@FindBy(xpath="//*[@id=\"product_list\"]/div[3]/div[1]/a/div[2]/div[1]/span[1]")
	WebElement productPrice;
	
	public void enterSearchItem(String product){
		txtSearch.sendKeys(product);
	}
	
	public void clearSearchItem()
	{
		txtSearch.sendKeys(Keys.CONTROL + "a");
		txtSearch.sendKeys(Keys.DELETE);
	}
	
	public void clickSearch(){
		btnSearch.click();
    }
    
    public String getProductName() {
    	return productName.getText();
    }
    
    public String getProductPrice() {
    	String priceText=productPrice.getText();
    	String[] priceArray= priceText.split("\u20B9");
    	String price=priceArray[1];
    	return price;
    }
}
