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
import com.dsalgo.qa.pages.LinkedListPage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

@Listeners({AllureListener.class})
public class LinkedListPageTest extends TestBase {
	
	 GetStartedPage getStartedPage;
	 HomePage homePage;
	 SignInPage signInPage;
	 LinkedListPage linkedlistPage;
		    
	public LinkedListPageTest()
	{
		super();
	}
		    
	@BeforeMethod
	public void setup()
	{
		initilization();
		signInPage = new SignInPage();
    	getStartedPage = new GetStartedPage();
    	homePage = new HomePage();
    	homePage = getStartedPage.clickOnGetStarted();//clicking on get started btn and getting homepage in return
		homePage.ClickloginUserLnk();
    	linkedlistPage = new LinkedListPage();
	}
	
	@DataProvider
	public Object[][] getLoginData() throws Exception {
		Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Credentials");
		return dataTable;		
	}
		   
	@Test(priority=1,dataProvider = "getLoginData",description="Linked list page verification")
	@Description("Linked list page verification")
	@Step("Verifying all links in linkedlist")
	@Severity(SeverityLevel.NORMAL)
	
	public void VerifyUserLandedLinkedListPage(String username,String password) throws IOException {
		Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signInPage.LoginUser(username, password);
		Loggerload.info("User is clicking on get started button under linked list");
	    //Clicking on linked List GetStarted Page in homePage
	    homePage.ClickLinkedListGetStartedBtn();
		Loggerload.info("Validating user landed on LinkedList Page.");
	    Assert.assertEquals(testUtil.getPageTitle(), "Linked List","User has not landed on LinkedListPage");
	    //Clicking on introduction Link
	    linkedlistPage.ClickOnIntroductionLink();
		Loggerload.info("Validating user landed on Intoduction Page on Linked ListPage.");
	    Assert.assertEquals(testUtil.getPageTitle(), "Introduction");
	    linkedlistPage.ClickOnTryHere();
		Loggerload.info("Executing test to validate if user landed on Tryhere Page on LinkedListPage.");
	    Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
	    //Enetring Python Code
	    excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
	    linkedlistPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
	    linkedlistPage.clickBtnRun();		
	    linkedlistPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(linkedlistPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ linkedlistPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		linkedlistPage.deletePythoncode();
		//Enter invalid Python code
		linkedlistPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		linkedlistPage.clickBtnRun();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError+" invalid python code entered by user");
		driver.navigate().back();    
		//Clicking on Types of Linked Lists
	    linkedlistPage.ClickOnTypesOfLinkedList();
	    Loggerload.info("Validating user landed on Intoduction Page on LinkedListPage.");
	    Assert.assertEquals(testUtil.getPageTitle(), "Types of Linked List");
	    linkedlistPage.ClickOnTryHere();
		Loggerload.info("Validating user landed on Try here Page on LinkedListPage.");
	    Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
	    //Entering Python code
	    excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
	    linkedlistPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
	    linkedlistPage.clickBtnRun();		
	    linkedlistPage.validatePythonCodeGotPrinted();
		Assert.assertEquals(linkedlistPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ linkedlistPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		linkedlistPage.deletePythoncode();
		//Enter invalid Python code
		linkedlistPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		linkedlistPage.clickBtnRun();
		String outputError1 = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError1, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError1+" invalid python code entered by user");
	}
	
	 @AfterMethod
	 public void teardown()
	{
		driver.quit();
    }
	 
}