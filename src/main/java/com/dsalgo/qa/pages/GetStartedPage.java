package com.dsalgo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.util.testUtil;

public class GetStartedPage extends TestBase{
	
	//Page Factory-OR:	
		@FindBy(xpath="//a[@href='/home']")
		WebElement getStartedBtn;
				
	//Constructor ,initializing the PageObjects
		public GetStartedPage() 
		{
			PageFactory.initElements(driver, this); 
		} 
	
	//Click on Get Started button method
	public HomePage clickOnGetStarted() {			
			getStartedBtn.click();
			return new HomePage();
		}
	
	public String HomePageTitle() {
		String Home_Title = driver.getTitle();
		return Home_Title;
	}
	
	
	
}