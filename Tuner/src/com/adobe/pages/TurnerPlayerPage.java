package com.adobe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TurnerPlayerPage {

	public WebDriver	driver;
	public TurnerPlayerPage (WebDriver driver)
	{
		this.driver= driver;

	}



	public void play()

	{
		WebElement ele = driver.findElement(By.className("btn-play"));
		ele.click();	
		
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(ele.getAttribute("class"));
	}

	public void pause()

	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/button[1]"));
		
		String value = ele.getAttribute("class");
		if(value.equalsIgnoreCase("btn-play off"))
		{
		ele.click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isPaused()
	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/button[1]"));
		String value = ele.getAttribute("class");
		if(value.equalsIgnoreCase("btn-play"))
		{
		return true;
		}
		
		else {
			return false;
		}
	}
	
	
	public boolean isFullScreen()
	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/button[3]"));
		String value = ele.getAttribute("class");
		System.out.println(value);
		if(value.equalsIgnoreCase("btn-fullscreen off"))
		{
		return true;
		}
		
		else {
			return false;
		}
	}
	
	public boolean isMute()
	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/div[3]/button"));
		String value = ele.getAttribute("class");
		System.out.println(value);
		if(value.equalsIgnoreCase("btn-volume muted"))
		{
		return true;
		}
		
		else {
			return false;
		}
	}
	
	public boolean isPlaying()
	{
		WebElement ele = driver.findElement(By.className("btn-play"));
		String value = ele.getAttribute("class");
		if(value.equalsIgnoreCase("btn-play off"))
		{
		return true;
		}
		
		else {
			return false;
		}
	}

	public void mute()

	{
		WebElement VolumeBtn = driver.findElement(By.xpath("//*[@id='video-controls']/div[3]/button"));
		VolumeBtn.click();	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public Boolean getPlayerDuration(int Seconds)
	{
		WebElement ele = driver.findElement(By.className("time-display"));
		String duration = ele.getText();
		System.out.println(duration);
		String time = duration.substring(0, 5);
		System.out.println(time);
		//String time = "02:30"; //mm:ss
		String[] units = time.split(":"); //will break the string up into an array
		int minutes = Integer.parseInt(units[0]); //first element
		int seconds = Integer.parseInt(units[1]); //second element
		int durationValue = 60 * minutes + seconds; //add up our values
		
		if (Seconds>durationValue)
		{
			return true;
		}
		
		else
			
		{
			return false;
		}
	}

	public void unMute()

	{
		WebElement VolumeBtn = driver.findElement(By.xpath("//*[@id='video-controls']/div[3]/button"));
		VolumeBtn.click();	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fullScreen()

	{
		WebElement fullScreen = driver.findElement(By.className("btn-fullscreen"));
		fullScreen.click();	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public void escapeFullScreen()

	{
		WebElement fullScreenOff = driver.findElement(By.xpath("//*[@id='video-controls']/button[3]"));
		fullScreenOff.click();	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public void enableCC()

	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/button[2]"));
		String value = ele.getAttribute("class");
		if(value.equalsIgnoreCase("btn-cc active"))
		{
		ele.click();
		}
		
		
	}
	
	public void disableCC()

	{
		WebElement ele = driver.findElement(By.xpath("//*[@id='video-controls']/button[2]"));
		String value = ele.getAttribute("class");
		if(value.equalsIgnoreCase("btn-cc"))
		{
		ele.click();
		}
	}


	public void seek(String min)

	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.tagName("video"));
		js.executeScript("arguments[0].currentTime =" +min+ ";", video);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void takeScreenShotForCC(String min)

	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.tagName("video"));
		js.executeScript("arguments[0].currentTime =" +min+ ";", video);
		pause();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		
		
	}
	
	


	public void setVolume(String min)

	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement video = driver.findElement(By.tagName("video"));
		//System.out.println(js.executeScript("arguments[0].paused", video));
		js.executeScript("arguments[0].volume =" +min+ ";", video);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public void getCurrentime()

	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement video = driver.findElement(By.tagName("video"));
		
		
		System.out.println(js.executeScript("arguments[0].currentTime;", video));

	}

	public void wait(int seconds)

	{

		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}



	public void pressEscapeKey()

	{
		WebElement Captions = driver.findElement(By.tagName("video"));
				Captions.sendKeys(Keys.ESCAPE);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	public void pressSpaceBar()

	{

		WebElement Captions = driver.findElement(By.tagName("video"));
		Captions.sendKeys(Keys.SPACE);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void javaScritPlay()

	{

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js .executeScript("document.getElementByTagName(\"video\").playing");
	    
	    
	    
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	
	


}
