package com.dsalgo.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.pages.RegisterPage;
import com.dsalgo.qa.pages.SignInPage;
import com.dsalgo.qa.util.Loggerload;

import allureReports.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Listeners({AllureListener.class})

public class HomePageTest extends TestBase {
	
	GetStartedPage getStartedPage;
	HomePage homePage;
	SignInPage signInPage;
	RegisterPage registerPage;
	
	public HomePageTest() {
		super();  
	}
	@BeforeMethod
	public void setup() {
		initilization();
	
		homePage = new HomePage();		
		getStartedPage =new GetStartedPage();
		signInPage = new SignInPage();
		registerPage = new RegisterPage();

		//Clicking on get started button and getting homePage object in return
		homePage = getStartedPage.clickOnGetStarted();
	}
	@Test (priority = 1,description = "Verifying user landed on home page after clicking on Get Started button" )
	@Description("Verifying user landed on home page after clicking on Get Started button" )
	@Severity(SeverityLevel.MINOR)
	public void VerifyUserLandedHomePageTest() {
		Loggerload.info("Verifying user landed on home page after clicking on Get Started button");
		String homeURL= homePage.LandedHomePage();
		Assert.assertEquals(homeURL,prop.getProperty("homepage_url"), "User has not landed on HomePage");		
	}
	
	@Test(priority = 2,description = "Clicking on Login link on Home Page")
	@Description("Clicking on Login link on Home Page")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateUserLandedLoginPageClickingLoginBtn() {
		Loggerload.info("Clicking on Login link on Home Page");
		signInPage=homePage.ClickloginUserLnk();
		String LoginPageURL = signInPage.SignInPageURL();
		//String LoginPageTitle = testUtil.getPageTitle();
		Loggerload.info("URL of current page is : " + LoginPageURL);
		Assert.assertEquals(LoginPageURL,prop.getProperty("loginpage_url"), "URL of SignIn Page does not match");			
	}
	
	@Test(priority = 3,description="Clicking on Register Link on Home Page")
	@Description("Clicking on Register Link on Home Page")
	@Severity(SeverityLevel.CRITICAL)
	public void ValidateUserLandedRegisterPageClickingRegisterBtn() {
		Loggerload.info("Clicking on Register Link on Home Page");
		registerPage= homePage.ClickRegisterUserLnk();
		//String SignInPageTitle = testUtil.getPageTitle();
		String RegisterPageTitle = registerPage.getPageTitle();
		Loggerload.info("Title of current page is : " + RegisterPageTitle);		
		Assert.assertEquals(RegisterPageTitle, "Registration","Title of register page does not match");
	}
	
	@Test(priority = 4,description = "Verify user got Error message on clicking GetStarted button under any Data Structure without Login")
	@Description("Verify user got Error message on clicking GetStarted button under any Data Structure without Login")
	@Severity(SeverityLevel.NORMAL)
	public void UserClicksGetStartedBtnWithoutLoginGetErrorMsg() {
		homePage.ClickGetStartedBtnWithoutLogin();
		String ErrorMsg = homePage.UserNotLoggedMsg();
		Loggerload.info("User got Error message on clicking GetStarted button under any Data Structure without Login");
		Assert.assertEquals(ErrorMsg, "You are not logged in");
	}
	
	@Test(priority = 5,description="Verify user got Error message on clicking GetStarted button after selecting value from dropdown without Login ")
	@Description("Verify user got Error message on clicking GetStarted button after selecting value from dropdown without Login ")
	@Severity(SeverityLevel.NORMAL)
	public void UserSelectValueFromDropdownClickGetStartedWithoutLoginGetErrorMsg() {
		homePage.selectValueFromdropdown("Arrays");
		String ErrorMsg = homePage.UserNotLoggedMsg();
		Loggerload.info("User got Error message on clicking GetStarted button after selecting value from dropdown without Login");
		Assert.assertEquals(ErrorMsg, "You are not logged in");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
	
