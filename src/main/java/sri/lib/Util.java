package sri.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class Util extends ComnVab {
	

	
	
	public static  boolean click(By element) {
		
		boolean status ;
		try {
			driver.findElement(element).click();
			status =true; 
			
		}catch(Exception e) {
			status =false; 
			
			System.out.println(e);
		}
		return status;	
	}
	
	
	public static  boolean settext(By element,String value) {
		
		
		boolean status ;
		try {
			driver.findElement(element).clear();
			driver.findElement(element).sendKeys(value);
			//driver.findElement(element).sendKeys(Keys.ENTER);
			status =true; 
			
		}catch(Exception e) {
			status =false; 
			System.out.println("Un able to send value");
		}
		return status;
}
	
	
	public  WebDriver Lanchbrowser(String browser) {
		
		
		WebDriver Tempdriver = null;
		
		
		switch(browser.toLowerCase()) {
		
		case "chrome":
		{
			System.setProperty("webdriver.chrome.driver", "E:\\Resources\\chromedriver.exe");
			Tempdriver=new ChromeDriver();
			break;
		}
		
		case "firefox":
			
			Tempdriver = new FirefoxDriver();
		{
			
			break;
		}
		
		default :
		{
			System.out.println("No browser found");
			
		}
		
		}
		
		return Tempdriver;	
		
	}
	public boolean  lanchapp(String url) {
		
		boolean status;
		try {
			
			driver.get(url);
			driver.manage().window().maximize();
			status =true;
			
		}catch(Exception e) {
			status =false;
			
		}
		return status;
		
	}
	
	
	public void Logevents(boolean stepstatus ,String Passlog ,String FailLog) throws IOException {
	//	boolean stepstatus;
		if (CommVar_TakeScreenshotForEveryStep == false)
		{
			if(stepstatus)
			{
				CommVar_test.log(LogStatus.PASS, Passlog);
				System.out.println("	<<Pass>> " + Passlog);
				CommVar_test.log(LogStatus.PASS, "Pass");
				
			}
			else
			{
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotpath = CommVar_test.addScreenCapture(ScreenShotPath);
				CommVar_test.log(LogStatus.FAIL, FailLog + LoggerScreenShotpath);
				System.out.println("	<<Fail>> " + FailLog);
				
			}
		}
		else
		{
			if(stepstatus)
			{
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotpath = CommVar_test.addScreenCapture(ScreenShotPath);
				CommVar_test.log(LogStatus.PASS, Passlog + LoggerScreenShotpath);
				System.out.println("	<<Pass>> " + Passlog);
			}
			else
			{
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotpath = CommVar_test.addScreenCapture(ScreenShotPath);
				CommVar_test.log(LogStatus.FAIL, FailLog + LoggerScreenShotpath);
				System.out.println("	<<Fail>> " + FailLog);
			}
		}
		Assert.assertTrue(stepstatus);
	}
	
	public static String getscreenshot(WebDriver driver) throws IOException {
		try {
		String ScreenshotName;
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "_");
		ScreenshotName =  Current_TestCase_Name + "_"+ DateTimeStamp;
		
		TakesScreenshot ts =( TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String Dest = ScreenshotsFolderPath + "//" + ScreenshotName + ".png";
		File destination = new File(Dest);
		FileUtils.copyFile(source, destination);
		return Dest;
	 }
	  catch(Exception e)
	  {
	return e.getMessage();
	  }
		
		
	}
	
	public HashMap<String,String> elib_loadData(String TestCaseName)
	{
		HashMap<String,String> TestData = new HashMap<String,String>();
		try
		{		
			String FilePath = CommVar_DataFilesPath +"\\Data.xlsx";
			//File f1 = new File(FilePath);
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			
			XSSFSheet ws1 = wb1.getSheet("sheet1");
			
			int rowcount = ws1.getLastRowNum();
			for(int r = 0;r<=rowcount;r++)
			{
				if(ws1.getRow(r).getCell(0)!=null)
				{
					String Excel_TestCaseName = ws1.getRow(r).getCell(0).getStringCellValue();
					if(Excel_TestCaseName.equalsIgnoreCase(TestCaseName))
					{
						int datarow = r + 1;
						int valuerow = r + 2;
						int cellcount = ws1.getRow(datarow).getLastCellNum();
						for(int c = 0;c<cellcount;c++)
						{
							String ExcelFieldName="";
							String ExcelFieldValue="";
							if(ws1.getRow(datarow).getCell(c)!=null)
							ExcelFieldName = ws1.getRow(datarow).getCell(c).getStringCellValue();
							System.out.println(ExcelFieldName);
							if(ws1.getRow(valuerow).getCell(c)!=null)
							ExcelFieldValue = ws1.getRow(valuerow).getCell(c).getStringCellValue();
							if(ExcelFieldName.length()>1)
							TestData.put(ExcelFieldName, ExcelFieldValue);
						}
						break;
					}
				}
			}
			fis.close();
			wb1.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Exception occured while reading data of '" + TestCaseName + "'  TestCase ");
		}
		return TestData;
	}
	
	
	
	public void putdata(String FieldName,String FieldValue)
	{
		if(CurrentTestData.containsKey(FieldName))
		{
			CurrentTestData.put(FieldName, FieldValue);
		}
		else
		{
			System.out.println("'" + FieldName + "' Field Name Not Found");
		}
	}
			
	public String getdata(String FieldName)
	{
		if(CurrentTestData.containsKey(FieldName))
		{
			return CurrentTestData.get(FieldName);
		}
		else
		{
			System.out.println("Data Not Found For '" + FieldName + "' Field");
			return null;
		}
	}
	
	
	
	
	
}
