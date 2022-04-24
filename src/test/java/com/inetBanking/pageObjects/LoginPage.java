package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver ldriver;
	
	 public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	 
	@FindBy(name = "uid")
	WebElement txtusername;
	
	@FindBy(name = "password")
	WebElement txtpwd;
	
	@FindBy(name = "btnLogin")
	WebElement logbutton;
	
	@FindBy(name = "btnReset")
	WebElement restBtn;
	
	@FindBy(xpath="//*[@class='menusubnav']/li[15]/a")
	WebElement logout;
	//absoulte xpath /html/body/div[3]/div/ul/li[15]/a
	
	public void setUsername(String username) {
		txtusername.sendKeys(username);
	}
	
	public void setPassword(String pwd) {
		txtpwd.sendKeys(pwd);
	}
	
	public void clickLogInButton() {
		logbutton.click();
	}
	
	public void logOut() {
		logout.click();
	}
	
	public void clickResetButton() {
		restBtn.click();
	}
	
	
	

}
