package com.adobe.utils;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.adobe.common.TestBase;

public class ScreenshotListener extends TestListenerAdapter {

	WebDriver driver=null;
	public static String currentDir = System.getProperty("user.dir");
	public static String filePath = currentDir+"\\ScreenShots\\";


	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error "+result.getName()+" test has failed *****");
		String methodName=result.getName().toString().trim();
		takeScreenShot(methodName);
	}


	public void takeScreenShot(String methodName) {
		//get the driver
		driver=TestBase.getDriver();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with test method name 
		try {
			FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
			System.out.println("***Placed screen shot in "+filePath+" ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onFinish(ITestContext context) {}

	public void onTestStart(ITestResult result) {   }

	public void onTestSuccess(ITestResult result) {

		System.out.println("Test Pass->"+result.getName());
	}

	public void onTestSkipped(ITestResult result) { 

		System.out.println("Test Skipped->"+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

	public void onStart(ITestContext context) {   }
}  

