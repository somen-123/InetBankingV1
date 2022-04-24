package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.HomePage;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.BaseClass;
import com.inetBanking.utilities.ExcelUtils;

import junit.framework.Assert;

public class TC_LoginTestDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String uname, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(uname);
		Thread.sleep(3000);
		lp.setPassword(pwd);
		Thread.sleep(3000);
		lp.clickLogInButton();
		Thread.sleep(3000);
		
		if(isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			//Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else {
			Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
			logger.info("Login Success");
			lp.logOut();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); //for logout alert window
			driver.switchTo().defaultContent();
			lp.clickResetButton();
		}
		//Below is my implementation for positive and negative scenario of login test 
		/*try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			Assert.assertTrue(true);
		}catch(Exception e) {
			System.out.println(driver.getTitle());
			Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
			logger.info("Login success");
			HomePage hp = new HomePage(driver);
			hp.logOut();
			driver.switchTo().alert().accept(); //close logout alert window
			
			lp.clickResetButton();
		}*/
	
	}
	//Below is the general method to check if alert window is present or not, it can be reused to check for alert window presence and can 
	//be move to base class as well
			
			public boolean isAlertPresent() {
				try {
					driver.switchTo().alert();
					return true;
				}
				catch(Exception e){
					return false;
				}
			}
	
	@DataProvider(name = "LoginData")
	String[][] getLoginData() throws IOException{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rownum = ExcelUtils.getRowCount(path, "Sheet1");
		int colcount = ExcelUtils.getCellCount(path, "Sheet1", 1);
		
		String[][] loginData = new String[rownum][colcount];
		
		for(int i =1; i<=rownum ; i++) {
			for(int j =0; j<colcount; j++) {
				loginData[i-1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	
}
