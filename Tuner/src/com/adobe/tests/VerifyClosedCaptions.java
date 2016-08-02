package com.adobe.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.frontendtest.components.ImageComparison;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.adobe.common.TestBase;
import com.adobe.pages.TurnerPlayerPage;

public class VerifyClosedCaptions extends TestBase {
	
	public WebDriver driver;
	public static String currentDir = System.getProperty("user.dir");
	public static String filePath = currentDir+"\\ScreenShots\\";
	@BeforeClass
	@Parameters({ "browserType", "appURL" })
	public void setUp() throws InterruptedException {
		driver=getDriver();
		Thread.sleep(3000);
		driver.manage().window().maximize();
		
	}
	
	@Test(priority=1)
	public void selectMediaType() throws InterruptedException {
		
		//driver.get("http://localhost:8887/");
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		Thread.sleep(8000);
		WebElement video = driver.findElement(By.tagName("video"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].play();", video);
		playerActions.pause();
		Assert.assertTrue(playerActions.isPaused());
		playerActions.play();
		Select select = new Select(driver.findElement(By.xpath("//*[@id='video-sources']")));
	    select.selectByVisibleText("HLS - LongTailVideo 608CC");
	    Thread.sleep(5000);
	              
	}  
	
	
	@Test(priority=2)
	public  void VerifyCCEnableDisable() throws IOException {
		String imgOriginal = filePath+ "\\image1.png";
		String imgToCompareWithOriginal = filePath+ "\\image2.png";
		String imgOutputDifferences = filePath+ "\\image3.png";
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.tagName("video"));
		WebElement ele = driver.findElement(By.className("btn-play"));
		ele.click();
		playerActions.enableCC();
		js.executeScript("arguments[0].currentTime = 0;", video);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].currentTime = 15;", video);
		//js.executeScript("arguments[0].pause();", video);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerActions.pause();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(imgOriginal));
		
		
		ele.click();
		playerActions.enableCC();
		js.executeScript("arguments[0].currentTime = 0;", video);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		js.executeScript("arguments[0].currentTime = 15;", video);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//js.executeScript("arguments[0].pause();", video);
		playerActions.pause();
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(imgToCompareWithOriginal));
		
		
		
		
		
		
		
		
		ImageComparison imageComparison = new ImageComparison(10,10,0.05);
	
			if(imageComparison.fuzzyEqual(imgOriginal,imgToCompareWithOriginal,imgOutputDifferences))
			System.out.println("Images are fuzzy-equal.");
		else
			System.out.println("Images are not fuzzy-equal.");
	}
	

}
