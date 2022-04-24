package com.inetBanking.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.BaseClass;



public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		logger.info("URL launched");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		Thread.sleep(5000);
		lp.setPassword(password);
		Thread.sleep(5000);
		lp.clickLogInButton();
		//logger.info("login success");
		Thread.sleep(5000);
		
		//USE BELOW STEPS TO VERIFY PAGE TITLE WHEN CONTAINS SPACES
		System.out.println(driver.getTitle());
		//Assert.assertEquals(driver.getTitle(),"Guru99 Bank Manager HomePage");
		
		String actual = driver.getTitle();
		String expected = "Guru99 Bank Manager HomePage";
		if(actual.equalsIgnoreCase(expected)) {
			Assert.assertTrue(true);
			logger.info("Login success");
		}
		else {
			captureScreenshot(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
		
		
		
		
	}

}
