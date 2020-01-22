package com.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Driver.TestSetup;
import com.Reports.Report;
import com.Resuables.RE_Login;



/**
 * Unit test for simple App.
 */
public class TC_Login

{
	
/*	public static void TC_Login_1234() {
		
		System.out.println("TC_Login_1234");
		Report.TestCaseStarts("TC_Login_1234", " Login in BRB with Invalid scenario");
		//Invalid scenario
		RE_Login.doLogin("xyz", "password12");
		
	}*/
	
	
    public static void TC_Login_2345() {
		
    	try {
			TestSetup.openBrowser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("TC_Login_2345");
		Report.TestCaseStarts("TC_Login_2345", " Login in BRB with valid scenario");
		//valid scenario
		RE_Login.doLogin("QAadminmanoj", "Test@123");
		
		TestSetup.closeAllBrowser();
	}
	
	
	
	
	
	
	
	
	
	
	
   
	/*public static void TC_Login_123() {

		String expected = "Google";
		String actual = null;
		try {

			System.out.println("TC_Login_123()");
			Report.TestCaseStarts("TC_Login_123", "Verify page Title.");

			actual = TestSetup.driver.getTitle();
			Assert.assertEquals(expected, actual);
			Report.Remarks("Expected title :" + expected + " ", "Pass", "matches with Actual title: " + actual);
		} catch (Exception e) {
			Report.Remarks("Expected title :" + expected + " ", "Pass", "matches with Actual title: " + actual);
			e.printStackTrace();
		}

	}
  
	public static void TC_Login_122() {

		String expected = "Google";
		String actual = null;
		try {

			System.out.println("TC_Login_122()");
			Report.TestCaseStarts("TC_Login_122", "Verify page Title.");

			actual = TestSetup.driver.getTitle();
			Assert.assertEquals(expected, actual);
			Report.Remarks("Expected title :" + expected + " ", "Pass", "matches with Actual title: " + actual);
		} catch (Exception e) {
			Report.Remarks("Expected title :" + expected + " ", "Pass", "matches with Actual title: " + actual);
			e.printStackTrace();
		}

	}*/
}
