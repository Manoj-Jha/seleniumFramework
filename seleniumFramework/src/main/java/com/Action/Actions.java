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
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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

	/**
	 * Purpose: Created Dictionary to set and get Environment data from the script, where Key and Value as string.
	 */
	public static Hashtable<String, String> envDict= new Hashtable<String,String>();
	
	public static List<String> objectProperty=null;
	public static Properties config = null;
	
	public static String objName="";						// Store Identifier object name e.g. SignIn
	public static String objIdentity="";					// Store Identifier object identity e.g. Xpath
	public static String objProperty="";					// Store Identifier object Property e.g. //input[@id='username']
	public static By byIdentifier=null;						// Store the By data type value returned by method ToBy()
	
	
	
	public static WebElement GetObject() {
		
		WebElement element=null;
		
		try {
			element=TestSetup.driver.findElement(byIdentifier);
			
			System.out.println("\n Printing ObjectName " + element);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return element;
	}
	
	public static By ToBy(String testObject) {
		try {
    		objectProperty = BreakObject(testObject);
    	
    		
    		String objectName= objectProperty.get(0).toString();
    		String objectType= objectProperty.get(1).toString();
    		String objectValue= objectProperty.get(2).toString();
    				
    		
    	if(objectType.equalsIgnoreCase("id"))
    			{ return By.id(objectValue); }
            else if(objectType.equalsIgnoreCase("name"))
              { return By.name(objectValue); }
            else if((objectType.equalsIgnoreCase("classname")) || (objectType.equalsIgnoreCase("class")))
                { return By.className(objectValue); }
            else if((objectType.equalsIgnoreCase("tagname")) || (objectType.equalsIgnoreCase("tag")))
                {return By.className(objectValue);}
            else if((objectType.equalsIgnoreCase("linktext")) || (objectType.equalsIgnoreCase("link")))
                { return By.linkText(objectValue); }
            else if(objectType.equalsIgnoreCase("partiallinktext"))
                { return By.partialLinkText(objectValue); }
            else if((objectType.equalsIgnoreCase("cssselector")) || (objectType.equalsIgnoreCase("css")))
                { return By.cssSelector(objectValue); }
            else if(objectType.equalsIgnoreCase("xpath"))
                { return By.xpath(objectValue); }
            else 
            {
            	
            	System.out.println("Invalid Object");
            //Log.error("Invalid locator");
            	}
            }
    	catch(WebDriverException ex)
    	{
    		throw ex;
    	}
    	catch (Exception e) {
    		throw e;
    	}
    	return null;
		
	}
	
	public static List<String> BreakObject(String testObject) {
		

		//String username ="username&&xpath&&//input[@id='username']";
         List<String> list =  new ArrayList<String>();
		
		String objectName = null;
		String objectType = null;
		String objectValue = null;
		try {
			StringTokenizer st = new StringTokenizer(testObject, "&&");
			while(st.hasMoreTokens()){
				objectName=st.nextToken().toString();
				objectType=st.nextToken().toString();
				objectValue=st.nextToken().toString();
			}
			System.out.println(objectName);
			System.out.println(objectType);
			System.out.println(objectValue);
			
			list.add(objectName);
			list.add(objectType);
			list.add(objectValue);
			
			System.out.println(list);
			System.out.println(list.get(0).toString());
			System.out.println(list.get(1).toString());
			System.out.println(list.get(2).toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
	
	
	
	
	/**
	 * Purpose: Clear the Local variable dictionary.
	 * @return: Void
	 */
	public static void ClearDictionary()

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
	 */
	public static void SetEnviornmentVariable(String varKey, String varValue)
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




