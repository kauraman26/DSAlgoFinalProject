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
import com.dsalgo.qa.pages.StackPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

@Listeners({AllureListener.class})
public class StackPageTest extends TestBase {
	
	GetStartedPage getStartedPage;
    HomePage homePage;
    SignInPage signinPage;
    StackPage stackPage;
    
    public StackPageTest(){
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
    	stackPage= new StackPage();
}
    @DataProvider
	public Object[][] getLoginData() throws Exception {
		Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Credentials");
		return dataTable;		
	}
    
    @Test(priority=1,dataProvider = "getLoginData",description="Stack page verification")
    @Description("Stack page verification")
    @Step("Verifying all links in StackPage")
    @Severity(SeverityLevel.NORMAL)
    public void VerifyUserLandedStackPage(String username,String password) throws IOException {
    	
    	Loggerload.info("User is entering username :"+ username + " and password :" + password);
		signinPage.LoginUser(username, password);
		Loggerload.info("User is clicking on Get Started button under Stack");
		//Clicking on Stack GetStarted Page in homePage
		homePage.ClickStackGetStartedBtn();
		Loggerload.info("Validating if user landed on Stack Page.");
		Assert.assertEquals(testUtil.getPageTitle(), "Stack","User has not landed on Stack Page");
		//Clicking on Operations in stack Link
		stackPage.ClickOnOperationsInStackLnk();
		Loggerload.info("Validating if user landed on operations in Stack Page.");
		Assert.assertEquals(testUtil.getPageTitle(), "Operations in Stack");
		stackPage.ClickOnTryHere();
		Loggerload.info("Validating if user landed on Tryhere Page on StackPage.");
		Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
		excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "PythonCode");
		stackPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		stackPage.clickBtnRun();
		Loggerload.info(" Validating if python code got printed sucessfully");
		stackPage.validatePythonCodeGotPrinted();		
		Assert.assertEquals(stackPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
		Loggerload.info("Python Code - "+ stackPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
		//delete python code
		stackPage.deletePythoncode();
		//Enter invalid Python code
		Loggerload.info("Entering invalid python code ");
		stackPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
		stackPage.clickBtnRun();
		String outputError = testUtil.CapturewrongPythonCodeError();
		Assert.assertEquals(outputError, "SyntaxError: bad input on line 1");
		Loggerload.info(outputError+" invalid python code entered by user");
		//Navigating back and and clicking on Implementations
		driver.navigate().back();
		stackPage.ClickOnImplementationLnk();
		Loggerload.info("Validating user landed on Implementations Page on Stack Page");
		Assert.assertEquals(testUtil.getPageTitle(), "Implementation");
		stackPage.ClickOnTryHere();
		Loggerload.info("Validating user landed on Tryhere Page on StackPage.");
		Assert.assertEquals(testUtil.getPageTitle(), "Assessment");
		//excelTestData.getDataFromSheet("src/test/resources/ExcelSheetData/DSAlgo_Login.xlsx", "Pythoncode Sheet");
		stackPage.enterCode(testUtil.getCellData("PythonCode", 1, 0), testUtil.getCellData("PythonCode", 1, 1));
		stackPage.clickBtnRun();
		Loggerload.info("Validating if python code got printed sucessfully");
        stackPage.validatePythonCodeGotPrinted();
        Assert.assertEquals(stackPage.validatePythonCodeGotPrinted(), "Hello DSAlgo");
        Loggerload.info("Python Code - "+ stackPage.validatePythonCodeGotPrinted() +" got printed sucessfully");
      //delete python code
      	stackPage.deletePythoncode();
      	//Enter invalid Python code
      	Loggerload.info("Entering invalid python code ");
      	stackPage.enterCode(testUtil.getCellData("PythonCode", 2, 0), testUtil.getCellData("PythonCode", 1, 1));
      	stackPage.clickBtnRun();
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