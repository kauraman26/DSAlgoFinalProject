package com.dsalgo.qa.testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
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

public class SignInPageTest extends TestBase {
	
	GetStartedPage getStartedPage;
	HomePage homePage;
	RegisterPage registerPage;
	SignInPage signInPage;
	
	public SignInPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		initilization();
		getStartedPage =new GetStartedPage();
		homePage = new HomePage();
		registerPage = new RegisterPage();
		homePage = getStartedPage.clickOnGetStarted();
		signInPage = homePage.ClickloginUserLnk();

	}
	
	@DataProvider
	public Object[][] getLoginData() throws Exception {
		Object dataTable[][]= excelTestData.getDataFromSheet("src/test/resources/TestData/DSAlgo_Login.xlsx", "Credentials");
		return dataTable;		
	}	
	
	@Test(priority = 1,description = "Verifying SignIn Page Link test")
	@Severity(SeverityLevel.NORMAL)
	@Step("Validate user is on Signin page")
	@Description("Test Case Description:Verifying SignIn Page Link is displayed test ")
	public void VerifyUserLogin() {				
		String SignInPageURL = driver.getCurrentUrl();
		Assert.assertEquals(SignInPageURL,"https://dsportalapp.herokuapp.com/logins" );	
	}
		
	@Test(dataProvider = "getLoginData",priority = 1,description = "Verifying user is able to login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description:Verifying user is able to login with valid credentials ")	
	@Step("Login Step with username:{0},password:{1},into application")
	public void LoginUserwithValidCredentialsValidationTest(String username,String password) {
		signInPage.LoginUser(username, password);
		Loggerload.info("User enters valid username :"+username+ ", password :"+ password +" and clicks on Login button");
		String loginSucesstxt=homePage.loginSucessMessage();
		Loggerload.info("User gets the message after sucessful Login : "+loginSucesstxt);
		Assert.assertEquals(loginSucesstxt, prop.getProperty("loginSucessMsg"));	
	}
	
	@Test(dataProvider = "getLoginData",priority =2,description="Verifying User fails to login on entering invalid username,password and clicking on Login button")
	@Severity(SeverityLevel.BLOCKER)
	@Step("Login Step with  invalid username:{0},password:{1},into application")
	@Description("Verifying User fails to login on entering invalid username,password and clicking on Login button")
	public void LoginUserWithInvalidCredentialsValidationTest(String username,String password){
		Loggerload.info("User enters invalid username,password and clicks on Login button");
		signInPage.LoginUser(username, password+testUtil.getRandomNum());
		String Invalid_Loginmsg = signInPage.AlertMessageLoginPage();
		Loggerload.info("User gets alert Message : "+Invalid_Loginmsg);
		Assert.assertEquals(Invalid_Loginmsg, "Invalid Username and Password", "Username and Password does not match");	
	}	
	
	@Test
	@Step("Validating clicking on Register Link")
	@Description("User is clicking on Register link on SignIn Page and validating title of the page")
	public void ClickRegisterLnkSignInPageValidationTest() {
		Loggerload.info("User is clicking on Register link on SignIn Page and validating title of the page");
		signInPage.clickRegisterLnkLoginPage();
		String reg_title = registerPage.getPageTitle();
		Loggerload.info("Title of the Page is : " + reg_title);
		Assert.assertEquals(reg_title, "Registration", "Title do not match");		
	}
	
	@Test(dataProvider = "getLoginData")
	public void ClickSignoutLnkValidationTest(String username,String password) {
		Loggerload.info("User enters valid username :"+username+ ", password :"+ password +" and clicks on Login button");
		signInPage.LoginUser(username, password);
		Loggerload.info("User is clicking on Signout button");
		homePage.ClickSignoutBtn();
		String logoutmsg= homePage.loginSucessMessage();
		Loggerload.info("User gets logout success message :"+logoutmsg );
		Assert.assertEquals(logoutmsg, "Logged out successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}


