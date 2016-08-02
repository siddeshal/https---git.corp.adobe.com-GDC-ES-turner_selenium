package com.adobe.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.adobe.common.TestBase;
import com.adobe.pages.TurnerPlayerPage;

public class VerifyProtectedVodMedia extends TestBase {
	
	public WebDriver driver;



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
		Thread.sleep(5000);
		WebElement video = driver.findElement(By.tagName("video"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].play();", video);
		playerActions.pause();
		Assert.assertTrue(playerActions.isPaused());
		playerActions.play();
		Select select = new Select(driver.findElement(By.xpath("//*[@id='video-sources']")));
	    select.selectByVisibleText("HLS - Protected VOD");
	    Thread.sleep(5000);
	              
	}  
	
	@Test(priority=2)
	public void verifyPlayerLoading() throws InterruptedException {
		System.out.println("First Selenium Test");
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		WebElement video = driver.findElement(By.tagName("video"));
		js.executeScript("arguments[0].play();", video);
		playerActions.pause();
		System.out.println("Video paused");
		Assert.assertTrue(playerActions.isPaused());
		playerActions.play();
		Assert.assertTrue(playerActions.isPlaying());
	}
	
	@Test(priority=4)
	public void verifyPlayerSeek() throws InterruptedException {
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		Thread.sleep(5000);
		System.out.println("seek back to start");
		playerActions.seek("0");
		System.out.println("seek to specified position");
		playerActions.seek("15");
		Assert.assertTrue(playerActions.getPlayerDuration(15));
		System.out.println("seek to specified position");
		playerActions.seek("120");
		Assert.assertFalse(playerActions.getPlayerDuration(120));
		playerActions.pause();
		playerActions.seek("15");
		playerActions.play();
		playerActions.pause();
		playerActions.seek("200");
		playerActions.play();
		playerActions.seek("15");
		playerActions.fullScreen();
		playerActions.seek("200");
		playerActions.pause();
		playerActions.seek("70");
		playerActions.play();
		playerActions.seek("0");
		playerActions.pause();
		playerActions.seek("250");
		playerActions.play();
		playerActions.pressEscapeKey();
	
	}
	

	@Test(priority=3)
	public void verifyPlayerFullScreenAndEscapeFullScreen() throws InterruptedException {
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		Thread.sleep(5000);
		playerActions.pause();
		playerActions.play();	
		playerActions.fullScreen();
		Assert.assertTrue(playerActions.isFullScreen());
		playerActions.escapeFullScreen();
		Assert.assertFalse(playerActions.isFullScreen());
		playerActions.fullScreen();
		playerActions.pause();
		playerActions.play();
		playerActions.pause();
		playerActions.play();
		playerActions.pressEscapeKey();
		
	}
	
	
	@Test(priority=5)
	public void verifyPlayerVolume() throws InterruptedException {
		TurnerPlayerPage playerActions = new TurnerPlayerPage(driver);
		Thread.sleep(5000);
		playerActions.setVolume("0");
		playerActions.setVolume("0.5");
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.pause();
		playerActions.setVolume("0");
		playerActions.setVolume("0.5");
		playerActions.mute();
		Assert.assertTrue(playerActions.isMute());
		playerActions.unMute();
		Assert.assertFalse(playerActions.isMute());
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.fullScreen();
		playerActions.setVolume("0");
		playerActions.setVolume("0.5");
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.mute();
		playerActions.unMute();
		playerActions.setVolume("0.1");
		playerActions.setVolume("0.5");
		playerActions.pause();
		playerActions.play();
		playerActions.escapeFullScreen();
	}
	

}

