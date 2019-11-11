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

public class TestSetup {

	public static WebDriver driver = null;
	
	

	public static void initialize() throws Exception {
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
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				driver.get(Constant.url);

			}

		}
	}

	public static void closeAllBrowser() {

		System.out.println("Quitting all browser");
		driver.quit();
		driver = null;

	}

	public static void closeBrowser() {

		System.out.println("Closing browser");
		driver.close();
		driver = null;

	}
	
	

}
