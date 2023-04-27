package com.dsalgo.qa.testcase;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.DataStructurePage;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;

@Listeners({AllureListener.class})
public class DataStructurePageTest extends TestBase{
	
	GetStartedPage getStartedPage;
	HomePage homePage;	
	SignInPage signInPage;
	DataStructurePage dataStructurePage;
	
	public DataStructurePageTest() {
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
		dataStructurePage = new DataStructurePage();
		homePage = getStartedPage.clickOnGetStarted();
		homePage.ClickloginUserLnk();		
	}
		
	@Test(dataProvider ="getLoginData")
	public void DataStructureValidationTest(String username,String password) throws IOException {
		Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signInPage.LoginUser(username, password);
		Loggerload.info("User is clicking on Get Started button under Data Structure");
		homePage.ClickDSGetStartedBtn();
		Loggerload.info("User is clicking on Time Complexity");
		dataStructurePage.clickOnTimeComplexitylink();
		//String tit_TimeComp = dataStructurePage.DSIntoductionPageTitle();		
		testUtil.getPageTitle();
		Loggerload.info("The title of the page is :"+ testUtil.getPageTitle());
		Assert.assertEquals(testUtil.getPageTitle(), "Time Complexity");	
		dataStructurePage.clickTryHereButton();
		//String tit_Assesment =dataStructurePage.DSIntoductionPageTitle();
		testUtil.getPageTitle();
		Loggerload.info("Title of the page is :" + testUtil.getPageTitle());
		Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		dataStructurePage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		dataStructurePage.clickBtnRun();
		Loggerload.info("Validating Python Code got printed sucessfully");
		dataStructurePage.validatePythonCodeGotPrinted();
		Assert.assertEquals(dataStructurePage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info(dataStructurePage.validatePythonCodeGotPrinted()+" got printed sucessfully");
		//delete python code
		dataStructurePage.deletePythonCode();
		//Enter invalid Python code
		dataStructurePage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		dataStructurePage.clickBtnRun();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError+" invalid python code entered by user");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}	
}	
	