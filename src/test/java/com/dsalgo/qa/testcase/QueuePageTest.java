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
import com.dsalgo.qa.pages.QueuePage;
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
public class QueuePageTest extends TestBase 
{
	GetStartedPage getStartedPage;
    HomePage homePage;
    SignInPage signinPage;
    QueuePage queuePage;
    
    public QueuePageTest()
    {
    	super();
    }
    
    @BeforeMethod
    public void setup()
    {
    	initilization();
    	signinPage = new SignInPage();
    	getStartedPage = new GetStartedPage();
    	homePage = new HomePage();
    	homePage = getStartedPage.clickOnGetStarted();//clicking on get started btn and getting homepage in return
    	homePage.ClickloginUserLnk();	
    	queuePage= new QueuePage();
     }
    
    @DataProvider
	public Object[][] getLoginData() throws Exception {
		Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Credentials");
		return dataTable;		
	}
    
    @Test(priority=1,dataProvider = "getLoginData",description="Queue page verification")
    @Description("Queue page verification")
    @Step("Verifying all links in QueuePage")
    @Severity(SeverityLevel.NORMAL)
    
    public void VerifyUserLandedPage(String username,String password) throws IOException {	
    	Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signinPage.LoginUser(username, password);
		Loggerload.info("User is clicking on Get Started button under Queue");
		homePage.ClickQueueGetStartedBtn();   
		Loggerload.info("Validating user landed on Queue Page.");
		Assert.assertEquals(testUtil.getPageTitle(), "Queue","User has not landed on QueuePage");
		//Clicking on Implementation of Queue in Python Link
		queuePage.ClickOnImplementationQueuePythonLink();
		Loggerload.info("Validating user landed on Implementation of Queue in Python");
		Assert.assertEquals(testUtil.getPageTitle(), "Implementation of Queue in Python");
		queuePage.ClickOnTryHere();
		Loggerload.info("Executing test to validate if user landed on Tryhere Page on QueuePage.");
		Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		queuePage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		queuePage.clickBtnRun();
		Loggerload.info("Validating Python Code got printed sucessfully");
		queuePage.validatePythonCodeGotPrinted();
		Assert.assertEquals(queuePage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ queuePage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		queuePage.deletePythonCode();
		//Enter invalid Python code
		queuePage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		queuePage.clickBtnRun();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError+" invalid python code entered by user");
		//Navigating back and and clicking on TypesOfQueueLink
		driver.navigate().back();
		Loggerload.info("User is clicking on Implementation using collections.deque");
		queuePage.ClickOnImpCollectionsDequeLink();
		Loggerload.info("Validating user landed on Implementation using collections.deque");
		Assert.assertEquals(testUtil.getPageTitle(), "Implementation using collections.deque");
		queuePage.ClickOnTryHere();
		Loggerload.info("Validating user landed on Tryhere Page on StackPage.");
		Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
		//Entering Python code
		queuePage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		queuePage.clickBtnRun();
		Loggerload.info("Validating Python Code got printed sucessfully");
		queuePage.validatePythonCodeGotPrinted();
		Assert.assertEquals(queuePage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ queuePage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		queuePage.deletePythonCode();
		//Enter invalid Python code
		queuePage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		queuePage.clickBtnRun();
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