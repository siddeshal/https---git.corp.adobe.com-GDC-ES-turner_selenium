package com.adobe.tests;
import java.io.File;
import java.io.FileOutputStream;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;;

@SuppressWarnings("deprecation")
public class MonitorNetworkTrafic {

	public static void main(String[] args) throws Exception {

		String strFilePath = System.getProperty("user.dir") +"//NetworkTraffic//networkTraffic.har";

		// start the proxy
		ProxyServer server = new ProxyServer(5555);
		WebDriver driver;
		

		try{

			File file = new File(System.getProperty("user.dir") +"//NetworkTraffic//networkTraffic.har");

			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			}

		}catch(Exception e){

			e.printStackTrace();

		}

		try
		{

			server.start();
			//captures the moouse movements and navigations
			server.setCaptureHeaders(true);
			server.setCaptureContent(true);

			// get the Selenium proxy object
			Proxy proxy = server.seleniumProxy();

			// configure it as a desired capability
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.PROXY, proxy);

			// start the browser up
			driver = new FirefoxDriver(capabilities);

			// create a new HAR with the label "apple.com"
			server.newHar("T.com");

			// open yahoo.com
			driver.get("http://localhost:8887/");

			Thread.sleep(10000);

			// get the HAR data
			Har har = server.getHar();
			FileOutputStream fos = new FileOutputStream(strFilePath);
			har.writeTo(fos);
		}

		finally
		{
			server.stop();
		}
		driver.quit();

	}}





