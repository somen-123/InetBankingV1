package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.HomePage;
import com.inetBanking.utilities.BaseClass;

import junit.framework.Assert;

public class TC_DeleteCustomer_001 extends BaseClass{
	
	
	
	@Test
	public void deleteCustomerTest(String custId) throws InterruptedException, IOException {
		HomePage hp = new HomePage(driver);
		hp.deleteCustomerLink();
		hp.deleteCustomerID(custId);
		System.out.println(custId);
		Thread.sleep(2000);
		hp.submitBtnForDeleteCust();
		Thread.sleep(2000);
		String st2;
		if(isAlert1Present() == true) {
			//driver.switchTo().alert().accept();
			st2 = driver.switchTo().alert().getText();
			
			if(st2.equalsIgnoreCase("Customer does not Exist!!!")) {
				logger.info("Customer ID not exist");
				captureScreenshot(driver, "customerIDNotExists");
				driver.switchTo().alert().accept();
			}
			else {
				driver.switchTo().alert().accept();
				//WE SHOULD NOT USE THREAD WHEN THERE IS CONSECUTIVE ALERTS ELSE WILL THROW EXCEPTION AS NO SUCH ALERT
				//Thread.sleep(4000);
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				logger.info("Customer deleted");
				//Assert.assertTrue(true);
			}
		}else {
			logger.info("Delete customer TC failed");
			Assert.assertTrue(false);
			captureScreenshot(driver, "deleteCustomerfailed");
		}
		
		
		
		
		
	}
	
	public  boolean isAlert1Present() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

}
