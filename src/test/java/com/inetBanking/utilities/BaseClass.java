package com.inetBanking.utilities;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

//import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseurl = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
		logger = Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		driver = new ChromeDriver();
		}
		else {
			System.out.println("Browser launching failed");
		}
		driver.manage().window().maximize();
		
		driver.get(baseurl);
		
		//This will applicable to all statements in test class also
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver, String testname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File scrfile=ts.getScreenshotAs(OutputType.FILE);
		//After capturing screenshot storing in Screenshot folder of project directory
		File targetfile = new File(System.getProperty("user.dir")+"/Screenshots/"+testname+".png");
		FileUtils.copyFile(scrfile, targetfile);
		System.out.println("Screenshot taken");

	}
	
	public String generateRandomString() {
		String randomstring = RandomStringUtils.randomAlphabetic(4);
		return randomstring;
	}
	
	public boolean checkAlert() {
		try {
		driver.switchTo().alert();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
