package com.inetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	
	public WebDriver ldriver;
	
	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//*[@class='menusubnav']/li[2]/a")
	// /html/body/div[3]/div/ul/li[2]/a
	WebElement newCustomerlink;
	
	@FindBy(xpath = "//input[@name='res']")
	WebElement restbtn;
	
	@FindBy(name = "name")
	WebElement custname;
	
	// () Xpath in bracket is used when 2 xpaths properties and query is same and want to chose 1 of them like (xpath)[index no.]
	//mainly used for male and female checkboxes //or //input[@name='rad1' and @value = 'm']
	@FindBy(xpath = "(//input[@name='rad1'])[1]") 
	WebElement gendermale;
	
	@FindBy(xpath = "//input[@name='rad1' and @value = 'f']")
	WebElement genderfemale ;
	
	@FindBy(name = "addr")
	WebElement address;
	
	@FindBy(name = "city")
	WebElement cityfield;
	
	@FindBy(name = "state")
	WebElement statefiled;
	
	@FindBy(name = "pinno")
	WebElement pinnum;
	
	@FindBy(name = "telephoneno")
	WebElement telnum;
	
	@FindBy(name = "emailid")
	WebElement email;
	
	@FindBy(name = "password")
	WebElement paswd ;
	
	@FindBy(name = "sub")
	WebElement submitbtn;
	
	@FindBy(name = "dob")
	WebElement dtofBirth;
	
	@FindBy(xpath="//*[@class='menusubnav']/li[15]/a")
	WebElement logout;
	//absoulte xpath /html/body/div[3]/div/ul/li[15]/a
	
	
	public void logOut() {
		logout.click();
	}
	
	public void newCust() {
		newCustomerlink.click();
	}
	
	public void rstBtn() {
		restbtn.click();
	}
	
	public void custName(String name) {
		custname.sendKeys(name);
	}
	
	public void gender(String gender) {
		if(gender.equalsIgnoreCase("male")) {
			gendermale.click();
		}
		else if(gender.equalsIgnoreCase("female")) {
			genderfemale.click();
		}
		else {
			Assert.assertTrue(false, "Enter correct gender");
		}
		
	}
	
	//use below approach for date, when there is slash and for every input cursor is moving to other part of date
	public void dob(String mm, String day, String year) {
		dtofBirth.sendKeys(mm);
		dtofBirth.sendKeys(day);
		dtofBirth.sendKeys(year);
	}
	
	public void addrs(String addrss) {
		address.sendKeys(addrss);
	}

	public void city(String city) {
		cityfield.sendKeys(city);
	}

	public void state(String state) {
		statefiled.sendKeys(state);
	}

	public void pin(long pin) {
		pinnum.sendKeys(String.valueOf(pin));
	}
	
	public void mobilenum(String mobnum ) {
		telnum.sendKeys(mobnum);
	}
	public void clearEmailId() {
		email.clear();
	}
	
	public void emailID(String email1) {
		email.sendKeys(email1);
	}
	
	public void password(String pwd) {
		paswd.sendKeys(pwd);
	}
	
	public void sbutton() {
		submitbtn.click();
	}
	
	@FindBy(xpath = "//*[@name='customer']/tbody")
	WebElement newCustomerTable;
	
	@FindBy(xpath = "//*[@id=\"customer\"]/tbody/tr[4]/td[2]")
	WebElement customerId;
	
	public String getCustomerId() {
		return customerId.getText();
	}
	
	@FindBy(xpath = "/html/body/div[2]/div/div/ul/li[4]/a")
	WebElement delCustomerlink;
	
	public void deleteCustomerLink() {
		delCustomerlink.click();
	}
	
	@FindBy(name = "cusid")
	WebElement delCustomer;
	
	public void deleteCustomerID(String custId) {
		delCustomer.sendKeys(custId);
	}
	
	@FindBy(name = "AccSubmit")
	WebElement delSubmitbtn;
	
	public void submitBtnForDeleteCust() {
		delSubmitbtn.click();
	}
	
	@FindBy(xpath = "/html/body/div[3]/div/ul/li[3]/a")
	WebElement editLink;
	
	public void clickOnEditLink() {
		editLink.click();
	}
	
	
	
}
