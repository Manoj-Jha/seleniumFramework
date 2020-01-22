package com.ExecutionTestNG;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Action.Actions;
import com.Driver.TestSetup;
import com.Reports.Report;
import com.TestCases.TC_Login;

public class SuiteExecutionSetup {

	
	
	@BeforeTest(alwaysRun = true)
	public static void InitializeAutomation() {
		
		
		try{
			
			//TestSetup.openBrowser();
			
			if (Actions.getOSName().toLowerCase().indexOf("win") >= 0) 
			{
				
				Actions.load("src/main/java/com/Constants/GlobalParametersWindows");
			}
			else
			{
				Actions.load("src/parameters/GlobalParametersMac");
			}

			//RE_PreRequisites.SetupEnvironmentFiles(); // SetUp environments file like qa1, qa2,qa3, stage etc. 

			String TempFolderPath = System.getProperty("java.io.tmpdir");
			
			String varTimeStemp = Actions.getSplitedCurrentTime();

			Actions.SetEnviornmentVariable("ReportStartExecutionTime", Actions.getCurrentTime());
			
			Report.getMachineName();
			
			String MachineName = Actions.GetEnvironmentVariable("ReportHostName");
			
			String ReportTempFolderPath = TempFolderPath + "\\Report -"+MachineName +"-" + varTimeStemp ;
			
			String ReportTempFilesFolderPath = ReportTempFolderPath + "\\Report-"+MachineName +"-"+  varTimeStemp ;
			
			String ReportTempXMLFilePath= ReportTempFilesFolderPath + "\\Report-" +MachineName +"-"+ varTimeStemp + ".xml";

			if (Actions.getOSName().toLowerCase().indexOf("win") >= 0) 
			{
				
			} 
			else 
			{
				ReportTempFolderPath = ReportTempFolderPath.replace("\\", "/");
				ReportTempXMLFilePath = ReportTempXMLFilePath.replace("\\", "/");
				ReportTempFilesFolderPath = ReportTempFilesFolderPath.replace("\\", "/");
			}
			
			Actions.SetEnviornmentVariable("ReportTempFolderPath", ReportTempFolderPath);
			Actions.SetEnviornmentVariable("ReportTempXMLFilePath", ReportTempXMLFilePath);
			Actions.SetEnviornmentVariable("ReportTempFilesFolderPath", ReportTempFilesFolderPath);
			com.Reports.Report.CreateFolder(ReportTempFolderPath);
			com.Reports.Report.CreateFolder(ReportTempFilesFolderPath);
			com.Reports.Report.CreateFile("",ReportTempXMLFilePath);
					
			
		}

		catch (WebDriverException ex)
		{
			Report.Remarks("Exception inside Initialize Automation ", "Fail", ex.getMessage());
		}

		catch(Exception ex)
		{
			Report.Remarks("Exception inside Initialize Automation ", "Fail", "Please check the Intialialize function through TestNG");
		}
		Actions.Wait(1000);

	} 
	
	
	@Test
	public static void LoginSuite(){
		
		//TC_Login.TC_Login_1234();
		TC_Login.TC_Login_2345();
		
		//TC_Login.TC_Login_123();
		
		//TC_Login.TC_Login_122();
	}
	
	
	
	public void tearDown() {
		//TestSetup.closeAllBrowser();
		//TestSetup.closeBrowser();
	}
	
	@AfterTest(alwaysRun =true)
	public static void createReport()
	{
		System.out.println("Report generation started");
		com.Reports.Report.CreateReportFromXML();
	}
}
