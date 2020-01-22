package com.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringSubstitutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.Driver.TestSetup;

import com.Reports.Report;





public class Actions {
	
	/**
	 * Purpose: Created Dictionary to set and get local data from the script, where Key and Value as string.
	 */
	public static Hashtable<String, String> dict =new Hashtable<String, String>();
	
	// Create TestCases Variable dictionary in key-value pair.
	public static Hashtable<String, String> TcVariabledict =new Hashtable<String, String>();

	/**
	 * Purpose: Created Dictionary to set and get Environment data from the script, where Key and Value as string.
	 */
	public static Hashtable<String, String> envDict= new Hashtable<String,String>();
	
	//public static List<String> objectProperty1=null;
	public static Properties config = null;
	public static String[] objectProperty= new String[3];   //// Store an array returned by ToBy() method.
	public static String objName="";						// Store testObject object name e.g. SignIn
	public static String objIdentity="";					// Store testObject object identity e.g. Xpath
	public static String objProperty="";					// Store testObject object Property e.g. //input[@id='username']
	public static  By byTestObject=null;						// Store the By data type value returned by method ToBy()
	public static WebElement element=null;
	
	
	public static By GetSelectionMethod(String testObject) {
		try {
    		objectProperty = breakObject(testObject);
    	
    		
    		objName= objectProperty[0].toString();
    		objIdentity= objectProperty[1].toString();
    		objProperty= objectProperty[2].toString();
    		
    		System.out.println("Obj name in By method :" +objName);	
    		System.out.println("objIdentity in By method :" +objIdentity);	
    		System.out.println("objProperty in By method :" +objProperty);	
    	
    		switch(objIdentity.toLowerCase().trim()) {
    		
    		case "xpath":
				return By.xpath(objProperty);
    		case "id":
    			return By.id(objProperty);
    		case "name":
    			return By.name(objProperty);
    		case "cssselector":
    			return By.cssSelector(objProperty);
    		case "linktext":
    			return By.linkText(objProperty);
    		case "partiallinktext":
    			return By.partialLinkText(objProperty);
    		case "tagname":
    			return By.tagName(objProperty);
    		case "classname":
    			return By.className(objProperty);
    		default:
    			return null;
    		   }
    		
            }
    	catch(WebDriverException ex)
    	{
    		throw ex;
    		
    	}
    	catch (Exception e) {
    		throw e;
    	}
		
	}
	
	public static WebElement GetElement(String testObject) {

		By objBy = GetSelectionMethod(testObject);

		
			try {
				element = (WebElement) TestSetup.driver.findElement(objBy);

				if(element !=null) {
					HightlightObject(element);
					
					return element;
				}
				System.out.println("\"\\n Printing whole WebObject : " + element);

			} catch (Exception e) {

				//element = null;
				 Report.Remarks("Object Not found", "Fail", "Given object is not found"  +element);
			}
		
		return null;
		}
		
	

	public static void SwitchToMostRecentBrowser(String OriginalHandle)
    {
		String parenthandle=TestSetup.driver.getWindowHandle();
        for(String handle:TestSetup.driver.getWindowHandles())
        {
            if (handle != OriginalHandle)
            {
            	TestSetup.driver.switchTo().window(handle);
                break;
            }

        }
    }
	
	 public static WebElement GetElementFromFrame(By objBy)
     {
		 /* IEnumerable<IWebElement> frames = driver.FindElements(By.TagName("frame"));
         IEnumerable<IWebElement> iframes = driver.FindElements(By.TagName("iframe"));
         // IEnumerable<IWebElement> openiframe = driver.FindElements(By.CssSelector("#open_iframe"));
         IEnumerable<IWebElement> elements = frames.Union(iframes);
         foreach (IWebElement element in elements)
         {
             try
             {
                 driver.SwitchTo().Frame(element);
                 IWebElement foundObject = driver.FindElement(objBy);
                 return foundObject;
             }
             catch
             {
                 continue;
             }
         } */
         return null; 
     }
	
	
	
	public static String[] breakObject(String testObject) {

		// public static String
		// username="username=xpath=//input[@id='username']||//input[@name='myusername']";
		// public static String username="username=xpath=//input[@id='username']";
		String objectName = null; // username
		String objectIdentity = null; // xpath
		String objectProperty = null; //// input[@id='username'] or if user use Pipe:
										//// //input[@id='username']||//input[@name='myusername']

		String[] objectCollective = new String[3];
		try {
			String[] oBjbreak = testObject.split("=", 3);

			// int oBjLength=oBjbreak.length;
			objectName = oBjbreak[0].toString().trim();

			//System.out.println(objectName);
			objectIdentity = oBjbreak[1].toString().trim();

			//System.out.println(objectIdentity);

			objectProperty = oBjbreak[2].toString().trim();

			//System.out.println(objectProperty);

			if ((objectName.isEmpty()) || (objectName == null)) {
				objectName = objectProperty;
			}

			objectCollective[0] = objectName;
			objectCollective[1] = objectIdentity;
			objectCollective[2] = objectProperty;

		} catch (Exception ex) {

			System.out.println(ex);

		}
		return objectCollective;
	}
	
	
	public static String breakObject(String testObject, String objectField) {

		// public static String
		// username="username=xpath=//input[@id='username']||//input[@name='myusername']";
		// public static String username="username=xpath=//input[@id='username']";
		String objectName = null; // username
		String objectIdentity = null; // xpath
		String objectProperty = null; //// input[@id='username'] or if user use Pipe:
										//// //input[@id='username']||//input[@name='myusername']

		
		try {
			String[] oBjbreak = testObject.split("=", 3);

			// int oBjLength=oBjbreak.length;
			objectName = oBjbreak[0].toString().trim();

			//System.out.println(objectName);
			objectIdentity = oBjbreak[1].toString().trim();

			//System.out.println(objectIdentity);

			objectProperty = oBjbreak[2].toString().trim();

		//	System.out.println(objectProperty);

			if ((objectName.isEmpty()) || (objectName == null)) {
				objectName = objectProperty;
			}
             String field=objectField.toLowerCase().trim();
             
			switch(field)
			{
			case "objectname":
			     return objName;
			case "objectproperty":
			     return objProperty;
			case "objectidentity":
			     return objIdentity;
			default:
				break;
			}

		} catch (Exception ex) {

			System.out.println(ex);

		}
		return "";
	}
	
	
 public static void Click(String testObject)
		{
	        String objName=breakObject(testObject, "objectname");
	        element=GetElement(testObject);
			int ErrorMessageCount= 0;

			try{

				if (element.equals(objName))
				{
					Report.Remarks("Clicked on " + objName, "Fail", "Unable to locate element on page with property: " + testObject + "");
				}
				else
				{
					element.click();
				}
			}
			catch (NoSuchElementException e ) 
			{
				ErrorMessageCount = 1;
			}
			catch (Exception ex)
			{
				ErrorMessageCount = 1;
			}

			if (ErrorMessageCount == 0)
			{
				Report.Remarks("Clicked on '" + objName + "' object", "Pass", "");
			} else {
				Report.Remarks("Clicked on " + objName, "Fail", "Unable to locate element on page with property: " + element + "");
			}

		}
 public static void EnterData(String testObject, String data)
	{
		
		String objName=breakObject(testObject, "objectname");
		element= GetElement(testObject);

		int ErrorMessageCount = 0;
		try
		{
			if (element.equals(objName))
			{
				Report.Remarks("Entered data on " + objName, "Fail", "Unable to locate element on page with property: " + testObject + "");
			}
			else
			{
			//	String Data = Actions.ReplaceTestCaseVariables(data);
				element.sendKeys(data.trim());
			}
		}
		catch (Exception e ) 
		{
			ErrorMessageCount = 1;
		}
		if (ErrorMessageCount == 0)
		{
			Report.Remarks("Entered text '" + data + "' in '" + objName + "'", "Pass", "");
		} 
		else 
		{
			Report.Remarks("Unable to locate element on page with property: " + objName + "", "Fail", "Object not found Error");
			//SetTestCaseVariable("FailedCount","0");
		}
		pageSync();
	}
 public static void HightlightObject(WebElement element ) {
	 
	 for (int i = 0; i <2; i++) 
     {
         try {
             JavascriptExecutor js = (JavascriptExecutor)TestSetup.driver;
             js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: green; border: #00ff00 solid 3px;");
             Thread.sleep(50);
             js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
             Thread.sleep(50);
         } catch (InterruptedException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }

     }
 }
 
 /**
	 * Purpose: Synchronize the page when switches from one page to another.
	 * @throws Exception 
	 */
	public static void pageSync() 
	{
		try {	
			Thread.sleep(1000);		
		} 
		catch (Exception ex) {

		}

	}
	
/**
 * Purpose: Clear the Local variable dictionary.
 * @return: Void
 * @throws Exception 
 */
	public static void ClearDictionary() throws Exception

	{
		try 
		{
			dict.clear();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Purpose: Get the Environment variable value from data dictionary for particular key.
	 * @param varKey: Key for which data to be fetch from data dictionary.
	 * @return : Returns value for a key if it exist, else return empty string.
	 */
	public static String GetEnvironmentVariable(String varKey)
	{
		try{
			varKey = varKey.toString().toLowerCase();
			if (envDict.containsKey(varKey))
			{
				if (envDict.get(varKey).isEmpty()){
					return null;
				}
				else
				{
					String VarValue=envDict.get(varKey);

					return VarValue.toString();
				}
			}  

			else
			{
				return "";
			}
		}
		catch(Exception ex)
		{

		}
		return varKey;
	}
	
	/**
	 * Purpose: Set the Environment variable value or parameter file variables for particular key in a data dictionary
	 * @param varKey: Key in data dictionary.
	 * @param varValue: Value for particular key in data dictionary.
	 * @throws Exception 
	 */
	public static void SetEnviornmentVariable(String varKey, String varValue) throws Exception
	{
		try{
			varKey= varKey.toString().toLowerCase().trim();
			varValue= varValue.toString().toLowerCase().trim();

			if (varValue==null)
			{
				varValue = "";			
			}
			if(envDict.containsKey(varKey))
			{
				envDict.remove(varKey);
				envDict.put(varKey, varValue);			
			}
			else
				envDict.put(varKey,varValue);

		}
		catch(Exception ex)
		{
			throw ex;
		}

	}
	/*
	 * Load TestCases variable dictionary text file in key value pair and return the dictionary.
	 * 
	 */
	
	public static Hashtable<String, String> loadTestCaseVariableFile(String filePath) {
		
		try {
			
						
			BufferedReader brReader = new BufferedReader(new FileReader(filePath));
			String arrKey="";
			String arrValue="";
			String strLine=null;
			while((strLine=brReader.readLine())!=null) {
				
				String[] objects=strLine.split("=",2);
				
				if(objects.length>=2)
				{
					arrKey=objects[0].toString().toLowerCase().trim();
				   arrValue = objects[1].toString().toLowerCase().trim();
				   TcVariabledict.put(arrKey,arrValue);
			} else {
				System.out.println("ignoring line: " + strLine);
			}
				
			}
			
			brReader.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println(Actions.TcVariabledict.values());
		return TcVariabledict;
		}
		
	/**
	 * Purpose: Replace the variable from string if that variable is present in the local dictionary.
	 * @get parameter from variables key from 'TcVariabledict' and replace all existing key with their values.
	 * @param txtString: String Text from which text to replace.
	 * @return: Returns replaced text.
	 * @throws IOException 
	 * Variable placed within the text line of Individual field like as ${Username}. 
	 * The Username get replaced with actual name as "Manoj" both Username and "Manoj" coming from TestcaseVariable.txt file.
	 */
	public static String ReplaceTestCaseVariables(String txtString) {
		String txtToReplace=null;
		try {
			StringSubstitutor sub = new StringSubstitutor(TcVariabledict);
			 txtToReplace = sub.replace(txtString.toLowerCase());
			System.out.println(txtToReplace);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
        return txtToReplace;
	}
	
	
	/**
	 * Purpose: Load Parameters file in data dictionary
	 * @param filePath: Pass the File Path for the file to be loaded.
	 * @return : Returns the dictionary with loaded Key value pair.
	 * @throws IOException 
	 */
	public static Hashtable<String, String> load(String filePath)
	{
		try {
			BufferedReader bReader= new BufferedReader(new FileReader(filePath));
			String strLine;
			while((strLine=bReader.readLine())!=null)
			{
				String ArrValue="";
				String[] objects=strLine.split("=");

				for (int i=1;i<objects.length; i++ )
				{
					ArrValue = ArrValue + "=" + (String)objects[i];
				}
				ArrValue = ArrValue.substring(1);
				envDict.put((String)objects[0].toLowerCase(),ArrValue);	
			}
			bReader.close();
		}
		catch(IOException ex)
		{

		}
		catch(Exception ex)
		{

		}
		System.out.println(Actions.envDict.values());
		return envDict;
	}
	
	/**
	 * Purpose: Replace the variable from string if that variable is present in the local dictionary.
	 * @param txtString: String Text from which text to replace.
	 * @return: Returns replaced text.
	 * @throws IOException
	 */
	//public static String ReplaceTestCaseVariables(String txtString) {
		
		//String Variablevalue = Actions.GetTestCaseVariable(VarName);
	//}
	
	
	

	/*
	 Makes the .properties data file readable
	 */
	public static void LoadConfigProperty(String fileName) {
		config = new Properties();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//"+fileName+".properties");
		    config.load(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public static String getSplitedCurrentTime()
	{
		try{
			String CurrentTime = getCurrentTime();
			CurrentTime = CurrentTime.replace("/", "-");
			CurrentTime = CurrentTime.replace(":", "-");
			CurrentTime = CurrentTime.replace(" ", "-");
			return CurrentTime;
		}
		catch(Exception ex)
		{

		}
		return "";
	}

	public static String getCurrentTime()
	{
		try{
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH); // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			return String.valueOf(month+1) + "/" + String.valueOf(day) + "/" + String.valueOf(year)+" " 
			+ String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);
		}
		catch(Exception ex)
		{

		}
		return "";
	}

	public static String getOSName()
	{
		String OSName= "";
		try
		{
			OSName = System.getProperty("os.name");
		} 
		catch (Exception e )
		{
			OSName = "unknown";
		}
		return OSName;
	}

	public static void Wait(int timeInMilliSec)
	{
		try
		{	
			Thread.sleep(timeInMilliSec);
			Report.Remarks("\nWait '" + timeInMilliSec/1000 + "' Seconds", "Pass", "");
		}
		catch(Exception ex)
		{
         
		}

	}
	
	public static void captureImage(String ReportScreenShotName) throws IOException  {
		File scrFile = ((TakesScreenshot)TestSetup.driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File(ReportScreenShotName));
	}
}




