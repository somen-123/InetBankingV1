package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.HomePage;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.BaseClass;

import junit.framework.Assert;

public class TC_HomePageTest_001 extends BaseClass {
	
	@Test
	public void newCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLogInButton();
		Thread.sleep(2000);
		logger.info("Login success");
		HomePage hp = new HomePage(driver);
		hp.newCust();
		Thread.sleep(15000);
		//driver.switchTo().alert().accept();
		hp.rstBtn();
		hp.custName("Ram");
		Thread.sleep(3000);
		hp.gender("male");
		hp.dob("07", "30", "1990");
		hp.addrs("INDIA");
		hp.city("Mumbai");
		hp.state("Maharashtra");
		hp.pin(400001);
		hp.mobilenum("9874563210");
		String emailstring = generateRandomString() + "@gmail.com";
		hp.emailID(emailstring);
		hp.password("87546258ab#Y");
		hp.sbutton();
		Thread.sleep(10000);
		try {
		driver.getPageSource().contains("Customer Registered Successfully!!!");
		logger.info("Customer added successfully");
		Thread.sleep(5000);
		customertableDetails();
		
		}catch(Exception e) {
			logger.info("Add customer failed");
			captureScreenshot(driver, "newCustomer");
			Assert.assertTrue(false);
		}
		String custId = hp.getCustomerId();
		Thread.sleep(3000);
		TC_EditCustomer_001 ec = new TC_EditCustomer_001();
		ec.editCustomer(custId);
		Thread.sleep(3000);
		TC_DeleteCustomer_001 del = new TC_DeleteCustomer_001();
		del.deleteCustomerTest(custId);
	
	}
	
	public void customertableDetails() {
		int row = driver.findElements(By.xpath("//*[@name='customer']/tbody/tr")).size();
		for(int i = 1; i<=row-1; i++) {
			if(i<=3) {
				String customerdetails = driver.findElement(By.xpath("//*[@name='customer']/tbody/tr["+i+"]/td")).getText();
				System.out.println(customerdetails);
				continue;
			}
			else {
				for(int j = 1; j<=2; j++) {
				
					String colData = driver.findElement(By.xpath("//*[@name='customer']/tbody/tr["+i+"]/td["+j+"]")).getText();
					System.out.print(colData + " | ");
				}
				System.out.println();
			}
		}
	}
	
	
}
