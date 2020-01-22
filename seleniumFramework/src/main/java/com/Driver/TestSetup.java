package com.Driver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.Action.Actions;
import com.Constants.Constant;
import com.Reports.Report;

public class TestSetup {

	public static WebDriver driver = null;
	
	

	public static void openBrowser() throws Exception {
		if (Actions.getOSName().toLowerCase().indexOf("win") >= 0) {

			// Singleton pattern.

			if (driver == null) {
				if (Constant.browserName.equalsIgnoreCase("chrome")) {

					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/drivers/chromedriver.exe");
					driver = new ChromeDriver();

				} else if (Constant.browserName.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + "/drivers/geckodriver.exe");
					driver = new FirefoxDriver();

				}
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.get(Constant.url);

			}

		}
	}

	public static void closeAllBrowser() {

		System.out.println("Quitting all browser");
		try {
			driver.quit();
			Report.Remarks("Closing all browser", "Pass", "All browser successfully get closed");
			driver = null;
		} catch (Exception e) {
			 e.printStackTrace();
			Report.Remarks("The browser didn't get close.", "Pass", "Getting error while closing the browser"   + e +"");
			
		}
		

	}
    public static WebDriver getDriver()
    {
	return driver;
    }
	public static void closeBrowser() {

		System.out.println("Closing browser");
		try {
			driver.close();
			Report.Remarks("Closing browser", "Pass", "Browser successfully get closed");
		} catch (Exception e) {
			Report.Remarks("The browser didn't get close..", "Pass", "Getting error while closing the browser"   + e +"");
			e.printStackTrace();
		}
		//driver = null;

	}
	
	

}
