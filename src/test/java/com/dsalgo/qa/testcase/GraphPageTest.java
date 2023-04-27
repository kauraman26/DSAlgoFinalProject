package com.dsalgo.qa.testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.GraphPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;

@Listeners({AllureListener.class})
public class GraphPageTest extends TestBase{

	GetStartedPage getStartedPage;
	HomePage homePage;	
	SignInPage signInPage;
	GraphPage graphPage;
	
	public GraphPageTest() {
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
		graphPage = new GraphPage();
		homePage = getStartedPage.clickOnGetStarted();
		homePage.ClickloginUserLnk();	
		
	}
	
	@Test(dataProvider ="getLoginData")
	public void GraphPageValidationTest(String username,String password) throws IOException {
		Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signInPage.LoginUser(username, password);
		homePage.ClickGraphGetStartedBtn();
		graphPage.clickGraphLnk();
		testUtil.getPageTitle();
		Assert.assertEquals(testUtil.getPageTitle(),"Graph");
		graphPage.clickTryHereBtnGraphPage();
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		graphPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		graphPage.clickRunBtn();		
		graphPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(graphPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ graphPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		graphPage.deletePythonCode();
		//Enter invalid Python code
		graphPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		graphPage.clickRunBtn();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		driver.navigate().back();
		graphPage.clickOnGraphRepresentationsLink();
		testUtil.getPageTitle();
		Assert.assertEquals(testUtil.getPageTitle(),"Graph Representations");
		graphPage.clickTryHereBtnGraphPage();
		graphPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		graphPage.clickRunBtn();		
		graphPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(graphPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ graphPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		graphPage.deletePythonCode();
		//Enter invalid Python code
		graphPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		graphPage.clickRunBtn();
		String outputError1 = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError1, "SyntaxError: bad input on line 1");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
