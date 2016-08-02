package com.adobe.common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects extends TestBase {
	
	public PageObjects(WebDriver driver) {
	}

	private static WebElement element = null;

	public static WebElement playBtn(WebDriver driver){

		element = driver.findElement(By.className("btn-play"));
		return element;

	}

	public static WebElement pauseBtn(WebDriver driver){

		element = driver.findElement(By.className("btn-play"));
		return element;

	}
	
	public static WebElement volumeBtn(WebDriver driver){

		element = driver.findElement(By.xpath("//*[@id='video-controls']/div[3]/button"));
		return element;

	}
	
	public static WebElement fullScreenBtn(WebDriver driver){

		element = driver.findElement(By.className("btn-fullscreen"));
		return element;

	}
	
	public static WebElement fullScreenOffBtn(WebDriver driver){

		element = driver.findElement(By.xpath("//*[@id='video-controls']/button[3]"));
		return element;

	}

}


