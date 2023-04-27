package com.dsalgo.qa.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.pages.GetStartedPage;
import com.dsalgo.qa.pages.HomePage;
import com.dsalgo.qa.util.Loggerload;
import com.dsalgo.qa.util.testUtil;

import allureReports.AllureListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({AllureListener.class})
public class GetStartedTest extends TestBase{
	
	GetStartedPage getStartedPage;
	HomePage homePage;
	
	public GetStartedTest() 
	{
		super();//call super class constructor
	}
	
	
	@BeforeMethod	
	public void setUp() 
	{	
		initilization();	
		getStartedPage = new GetStartedPage();	
	}
	
	@Test(priority = 1,description = "Clicking on Get Started button")
	@Description ("Clicking on Get Started button")
	@Severity(SeverityLevel.BLOCKER)
	public void getStartedButtonClickTest()
	{		
		Loggerload.info("User is clicking on GetStarted button on Main Page");
		homePage= getStartedPage.clickOnGetStarted();
	}
	
	@Test(priority = 2, description="Verify user landed on Home Page after clicking Get Started button")
	@Description("Verify user landed on Home Page after clicking Get Started button")
	@Severity(SeverityLevel.NORMAL)
	public void validateUserLandingHomePageTest() 
	{
		//String HomePageTitle = getStartedPage.HomePageTitle();
		String HomePageTitle =  testUtil.getPageTitle();
		Loggerload.info("Title of current page is : " + HomePageTitle + " ****");
		Assert.assertEquals(HomePageTitle, "Numpy Ninja");	
	}
			
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
