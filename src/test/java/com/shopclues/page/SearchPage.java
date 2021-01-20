package com.shopclues.page;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

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
	
	@FindBy(xpath="//*[@id=\"product_list\"]/div[3]/div[1]/a")
	WebElement firstProduct;
	
	@FindBy(css=".prd_mid_info > h1:nth-child(2)")
	WebElement productName;
	
			
	@FindBy(xpath="//*[@id=\"main_data\"]/div[2]/div[2]/div[2]/span[1]")
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
	
	public void clickFirstProduct(){
		firstProduct.click();
    }
	
	public void switchToNewWindow(){
		String parent_handle= this.ldriver.getWindowHandle();
		   Set<String> handles = this.ldriver.getWindowHandles();
		   for(String handle1:handles)
		       if(!parent_handle.equals(handle1))
		       {
		    	   this.ldriver.switchTo().window(handle1);
		       }
		}
	
	public void closeTab() {
		String originalHandle = this.ldriver.getWindowHandle();

	    

	    for(String handle : this.ldriver.getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	        	this.ldriver.switchTo().window(handle);
	        	this.ldriver.close();
	        }
	    }

	    this.ldriver.switchTo().window(originalHandle);
	}
    
    public String getProductName() {
    	this.ldriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	return productName.getText();
    }
    
    public String getPrice() {
    	return productPrice.getText();
    }
    
    public String getProductPrice() {
    	String priceText=productPrice.getText();
    	String[] priceArray= priceText.split("\u20B9");
    	String price=priceArray[1];
    	return price;
    }
}
