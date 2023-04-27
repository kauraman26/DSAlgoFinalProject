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
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.pages.TreePage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;

@Listeners({AllureListener.class})

public class TreePageTest extends TestBase{
	GetStartedPage getStartedPage;
	HomePage homePage;	
	SignInPage signInPage;
	TreePage treePage;
	
	public TreePageTest() {
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
		treePage = new TreePage();
		homePage = getStartedPage.clickOnGetStarted();
		homePage.ClickloginUserLnk();			
	}
	
	@Test(dataProvider ="getLoginData")
	public void TreePageValidationTest(String username,String password) throws IOException {
		Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signInPage.LoginUser(username, password);
		Loggerload.info("User is selecting Tree option from the dropdown");
		treePage.dropdown_Tree();
		testUtil.getPageTitle();
		Loggerload.info("User is clicking on overview of Trees");
		treePage.clickOnoverviewOfTreesPage();
		testUtil.getPageTitle();
		treePage.click_Tryhere();
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		treePage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		treePage.clickOnRun();
		Loggerload.info("Validating Python Code got printed sucessfully");
		treePage.validatePythonCodeGotPrinted();
		Assert.assertEquals(treePage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		//delete python code
		treePage.deletePythonCode();
		//Enter invalid Python code
		treePage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		treePage.clickOnRun();
		String outputError =testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		driver.navigate().back();
		Loggerload.info("User is clicking on Terminologies Link");
		treePage.clickOnTerminologiesLink();
		testUtil.getPageTitle();
		Assert.assertEquals(testUtil.getPageTitle(),"Terminologies");
		treePage.click_Tryhere();
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		treePage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		treePage.clickOnRun();
		Loggerload.info("Validating Python Code got printed sucessfully");
		treePage.validatePythonCodeGotPrinted();
		Assert.assertEquals(treePage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		//delete python code
		treePage.deletePythonCode();
		//Enter invalid Python code
		treePage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		treePage.clickOnRun();
		String outputError1 =testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError1, "SyntaxError: bad input on line 1");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
