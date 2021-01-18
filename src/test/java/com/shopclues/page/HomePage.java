package com.shopclues.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{
	
WebDriver ldriver;
	
	public HomePage(WebDriver rdriver)
	{
	   ldriver=rdriver;
	   PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/button[1]")
	WebElement btnDontAllow;
	
	@FindBy(xpath="//*[@id=\"sc_uname\"]/a")
	WebElement loginText;
	
	@FindBy(css= "#sign-in > a")
	WebElement linkSignIn;
	
	@FindBy(xpath="//*[@id=\"sign-in\"]/a")
	WebElement signInText;
	
	@FindBy(xpath="/html/body/div[4]/div/div/div[4]/ul/li[6]/div/div/div/a[1]")
	WebElement fieldRegister;
		
	@FindBy(id="common_popupModel")
	WebElement loginPopUp;
	
	@FindBy(id="main_user_login")
	WebElement txtUserId;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(id="login_button")
	WebElement btnLogin;
	
	@FindBy(id="login_via_otp")
	WebElement btnLoginOtp;
	
	@FindBy(xpath="/html/body/div[4]/div/div/div[4]/ul/li[5]/div/ul/li[10]/a")
	WebElement btnLogOut;
	
	@FindBy(xpath="//*[@id=\"login\"]/form/fieldset/div[2]/div[1]/span")
	WebElement loginError;
	
	@FindBy(xpath="//*[@id=\"login\"]/form/fieldset/div[3]/div[1]/span")
	WebElement msgError;
	
	@FindBy(id="a_close_id")
	WebElement btnLoginClose;
	
	 Actions action;
	 
	public void clickBtnDontAllow() 
	{
	   btnDontAllow.click();
	}
		 
	public void clickSignin()
	{  
	   action=new Actions(this.ldriver);//instantiating object of actions class
	   action.moveToElement(linkSignIn).click().build().perform();
	   action.moveToElement(fieldRegister).click().build().perform();
	}
	
	public void logOut()
	{
	   action=new Actions(this.ldriver);
	   action.moveToElement(loginText).click().build().perform();
	   action.moveToElement(btnLogOut).click().build().perform();
	}
	
	public void switchToPopUp()
	{
	  action=new Actions(this.ldriver);
	  action.moveToElement(loginPopUp);
		
	}
		
	public void enterUserId(String uname)
	{
	   txtUserId.sendKeys(uname);
	}
	
	public void enterPassword(String pwd)
	{
	   txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
	   btnLogin.click();
	}
	
	public String getError() {
		return loginError.getText();
	}
	
	public String msgError() {
		return msgError.getText();
	}
	
	public String isLoggedIn() {
		String name=loginText.getText();
		return name;
	}
			
	public String isLoggedOut() {
		String signin=signInText.getText();
		return signin;
	}
	
	public void clickLoginOtp()
	{
		btnLogin.click();
	}
	
	public void clickCloseButton()
	{
		btnLoginClose.click();
	}
	
}
