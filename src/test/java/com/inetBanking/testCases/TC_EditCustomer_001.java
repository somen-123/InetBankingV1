package com.inetBanking.testCases;

import org.openqa.selenium.JavascriptExecutor;

import com.inetBanking.pageObjects.HomePage;
import com.inetBanking.utilities.BaseClass;

public class TC_EditCustomer_001 extends BaseClass {
	
	public void editCustomer(String custId) throws InterruptedException {
		HomePage hp2 = new HomePage(driver);
		//String custId = hp2.getCustomerId();
		hp2.clickOnEditLink();
		Thread.sleep(3000);
		hp2.deleteCustomerID(custId);
		hp2.submitBtnForDeleteCust();
		Thread.sleep(2000);
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("document.getElementByName('name').value='Selenium'");
		hp2.rstBtn();
		Thread.sleep(2000);
		hp2.addrs("UK");
		hp2.city("CR");
		hp2.state("ManU");
		hp2.pin(987456);
		hp2.mobilenum("9874125630");
		hp2.clearEmailId();
		String email = generateRandomString() + "@gmail.com";
		hp2.emailID(email);
		Thread.sleep(3000);
		hp2.sbutton();
		if(checkAlert() == true) {
			String s=driver.switchTo().alert().getText();
			logger.info(s);
			driver.switchTo().alert().accept();
		}else {
			System.out.println("Edit customer alert not generated");
			
		}
		
		
		
	}
	
	
	

}
