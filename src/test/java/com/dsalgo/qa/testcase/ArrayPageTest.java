package com.dsalgo.qa.testcase;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.ArrayPage;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;

@Listeners({AllureListener.class})
public class ArrayPageTest extends TestBase {
	
	GetStartedPage getStartedPage;
	HomePage homePage;	
	SignInPage signInPage;
	ArrayPage arrayPage;
	
	public ArrayPageTest() {
		super();
	}
	@DataProvider
	public Object[][] getLoginData() throws Exception {
		Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Credentials");
		return dataTable;		
	}
		
	@BeforeMethod	
	public void setUp() 
	{	
		initilization();
		getStartedPage =new GetStartedPage();
		homePage = new HomePage();	
		signInPage = new SignInPage();
		arrayPage = new ArrayPage();
		homePage = getStartedPage.clickOnGetStarted();
		homePage.ClickloginUserLnk();
	}
	
	@Test(dataProvider ="getLoginData")
	public void ArrayPageValidationTest(String username,String password) throws IOException {
		Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signInPage.LoginUser(username, password);
		Loggerload.info("User is selecting Array option from dropdown");
		homePage.ClickOnDropdown();
		homePage.selectValueFromdropdown("Arrays");
		testUtil.getPageTitle();
		Assert.assertEquals(testUtil.getPageTitle(),"Array");
		Loggerload.info("User landed on "+ testUtil.getPageTitle()+" page after selecting array option from dropdown");
		arrayPage.clickOnArraysInPythonLink();
		testUtil.getPageTitle();
		Loggerload.info("User landed on "+ testUtil.getPageTitle() +" page after clicking on Arrays in Python Link");
		arrayPage.clickTryHereBtn();
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		arrayPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		arrayPage.clickRunBtn();		
		arrayPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(arrayPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ arrayPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		arrayPage.deletePythonCode();
		//Enter invalid Python code
		arrayPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		arrayPage.clickRunBtn();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError+" invalid python code entered by user");
		driver.navigate().back();
		arrayPage.clickOnArraysUsingListLink();
		testUtil.getPageTitle();
		Loggerload.info("User landed on "+ testUtil.getPageTitle() +" page after clicking on Arrays Using List Link");
		arrayPage.clickTryHereBtn();
		//ExcelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		arrayPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		arrayPage.clickRunBtn();		
		String outputArray= arrayPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(outputArray, "Hello DSAlgo");
		Loggerload.info("Python Code - "+ outputArray +" got printed sucessfully");
		//delete python code
		arrayPage.deletePythonCode();
		//Enter invalid Python code
		arrayPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		arrayPage.clickRunBtn();
		String outputError1 = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError1, "SyntaxError: bad input on line 1");	
		Loggerload.info(outputError1+" invalid python code entered by user");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
