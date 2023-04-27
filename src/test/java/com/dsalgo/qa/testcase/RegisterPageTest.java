package com.dsalgo.qa.testcase;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.RegisterPage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.excelTestData;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;

@Listeners({AllureListener.class})

public class RegisterPageTest extends TestBase{
	
	GetStartedPage getStartedPage;
	HomePage homePage;
	RegisterPage registerPage;

	
	public RegisterPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initilization();
	
		homePage = new HomePage();		
		getStartedPage =new GetStartedPage();	
		registerPage = new RegisterPage();

		//Clicking on get started button and getting homePage object in return
		homePage = getStartedPage.clickOnGetStarted();
		registerPage = homePage.ClickRegisterUserLnk();
}
	 @Test
	  public void VerifyUserLandedRegisterPageTest() {
	     Loggerload.info("Executing test to validate if user landed on Register Page.");
	     String RegisterTitle = registerPage.getPageTitle();
	     Loggerload.info("Title of the page is : "+RegisterTitle);
	     Assert.assertEquals(RegisterTitle, "Registration", "User has landed on Register page.");
	    }
	 	 
	 @DataProvider
		public Object[][] getLoginData() throws Exception {
			Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Register");
			return dataTable;		
		}	
	 
	@Test(dataProvider = "getLoginData")
	public void RegisterNewUserTest(String name,String passwrd,String cnfrmpasswrd) {	
		
		registerPage.enterUserName(name);
		Loggerload.info("Entered Username in username textbox : "+ name);
		
		registerPage.enterPassword(passwrd);
		Loggerload.info("Entered Password in password text box : "+passwrd);
		
		registerPage.enterConfirmPassword(cnfrmpasswrd);
		Loggerload.info("Entered confirm password in confirm password textbox : "+ cnfrmpasswrd);
		
		Loggerload.info("Clicking on Register button");
		registerPage.clickRegisterButton();	
		
		String registermsg = registerPage.registerSucessMessageRegisterPage();
		Loggerload.info("Register Success message :"+registermsg);
		Assert.assertTrue(registermsg.contains(prop.getProperty("RegisterSuccessMsg")));
	}	
	
	@Test
	public void ClickRegisterBtnWithEmptyFieldsTest() {
		Loggerload.info("Clicking on Register button with all field empty");
		registerPage.clickRegisterButton();	
		String errorEmptyFields =registerPage.getEmptyfieldErrormsgUser();
		Loggerload.info("Error message is displayed under Username text box: " + errorEmptyFields);
		Assert.assertEquals(errorEmptyFields, prop.getProperty("expRegisterErrormsg"));
	}
	
	
	@Test(dataProvider = "getLoginData")
	public void RegisterErrorEmptyfieldBelowPasswordTxtbox(String name,String passwrd,String cnfrmpasswrd) {	
		
		registerPage.enterUserName(name);
		Loggerload.info("Entered Username in username textbox : "+ name);	
		
		Loggerload.info("Clicking on Register button");
		registerPage.clickRegisterButton();	
		
		String errorEmptyFields =registerPage.getEmptyfieldErrormsgPassword();
		Loggerload.info("Error message displayed under Password text box is: " + errorEmptyFields);
		Assert.assertEquals(errorEmptyFields, prop.getProperty("expRegisterErrormsg"));
	}	
	
	@Test(dataProvider = "getLoginData")
	public void ClickRegisterBtnWithDifferentPasswordsTest(String name,String passwrd,String cnfrmpasswrd) {
		
		registerPage.enterUserName(name);
		Loggerload.info("Entered Username in username textbox : "+ name);
		
		registerPage.enterPassword(passwrd);
		Loggerload.info("Entered Password in password text box : "+passwrd);
			
		registerPage.enterConfirmPassword(cnfrmpasswrd+ testUtil.getRandomNum());
		Loggerload.info("Entered confirm password in confirm password textbox : "+ cnfrmpasswrd+ testUtil.getRandomNum());
		
		Loggerload.info("Clicking on Register button");
		registerPage.clickRegisterButton();	

		String errorPwdMismatch= registerPage.passwordMismatchErrormsg();
		Loggerload.info("Error Message displayed on entering different password in confirmation field: " + errorPwdMismatch);
		Assert.assertTrue(errorPwdMismatch.contains (prop.getProperty("pwd_mismatch")));		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}	
}
             