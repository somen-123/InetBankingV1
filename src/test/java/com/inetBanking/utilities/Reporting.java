package com.inetBanking.utilities;
//Listener class used to generate Extent reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentReporter;
//this extends is needed to use below methods onStart etc. otherwise will give runtime 
//error com.inetBanking.utilities.Reporting cannot be cast to class org.testng.ITestNGListener
public class Reporting extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-report-"+timestamp+".html";
		//Saving the report using system timestamp so that every time we run previous report doesn't replace with new
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("user", "Somen");
		
		htmlReporter.config().setDocumentTitle("INetBanking Test report");
		htmlReporter.config().setReportName("Function test report");
		
	}
	public void onTestSuccess(ITestResult tr) {
		//tr.getname() will return the test method name
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	
	}
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		System.out.println(screenshotpath);
		File f = new File(screenshotpath);
		//if file added to Screenshot folder, check if exists then add to extent report
		if(f.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void onTestSkip(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	public void onFinish(ITestContext tc) {
		extent.flush();
	}
	
	
}
