package com.dsalgo.qa.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dsalgo.qa.base.TestBase;

import io.qameta.allure.Allure;

public class testUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	public static boolean webElement_Click(WebElement element) {
		try {
			WebElement ele_toclick = new WebDriverWait(driver, Duration.ofSeconds(10)).
					until(ExpectedConditions.visibilityOf(element));
				
				if(ele_toclick.isEnabled()) {
					try {
						ele_toclick.click();
						return true;
					}
					catch(Exception e) {
						e.printStackTrace();
						return false;
					}	
				}
				else {
					throw new Exception("Element is not enabled");
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean webSendKeys(WebElement element, String text) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10)).
					until(ExpectedConditions.visibilityOf(element));
		
				if(ele.isEnabled()) {
					try {
						ele.clear();
						ele.sendKeys(text);	
						return true;
					}
					catch(Exception e) {
						e.printStackTrace();
						return false;
					}	
				}
				else {
					throw new Exception("Element is not enabled");
				}
			}
			
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	//Returns a random number between 0 and 1000
	public static int getRandomNum() {
		Random ran = new Random();
		 return ran.nextInt(1000);
				
	}
	
	public static String getCellData(String SheetName,int rownum,int colnum) throws IOException {
		
		String path=System.getProperty("user.dir")+"/src/test/resources/TestData/DSAlgo_Login.xlsx";
		File Excelfile= new File(path);
		
		FileInputStream Fis = new FileInputStream(Excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(Fis);
		XSSFSheet sheet = workbook.getSheet("PythonCode");
		XSSFRow row = sheet.getRow(rownum);
		XSSFCell cell = row.getCell(colnum);
	
		DataFormatter formatter=new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell);
			//System.out.println(data);
		}
		catch(Exception e)
		{
			data = "";
		}
		workbook.close();
		Fis.close();
		return data;
		}
	
	public static String CapturewrongPythonCodeError() {
		//switch focus to alert
	      Alert a = driver.switchTo().alert();
	      //get alert text
	      String s= driver.switchTo().alert().getText();
	      //System.out.println("Alert text is: " + s);
	      //accepting alert
	      a.accept();
	      return s;
	     
	}

	public static void captureScreenshot(WebDriver driver,String screenshotName)
	{

		try 
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
			System.out.println("Screenshot taken");
			Allure.attachment("ErrorScreenshot.png",new ByteArrayInputStream(((ts).getScreenshotAs(OutputType.BYTES))));
		} 
		catch (Exception e)
		{
			System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
	}	
}
