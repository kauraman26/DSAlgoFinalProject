package com.dsalgo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dsalgo.qa.base.TestBase;
import io.qameta.allure.Step;

public class SignInPage extends TestBase{
	

	@FindBy (xpath="//*[@id='id_username']")
	WebElement usernametxtbox;	
	@FindBy (xpath="//*[@id='id_password']")
	WebElement passwordtxtbox;
	@FindBy (xpath="//*[@value='Login']")
	WebElement loginBtn;
	@FindBy (xpath="//div[@class='alert alert-primary']")
	WebElement alertmsg;
	@FindBy (xpath="//a[@href='/register']")
	WebElement lnkregister;
	@FindBy (xpath="//a[@href='/logout']")
	WebElement Btnsignout;
	
	@FindBy(xpath="//a[@href='/register']")
	WebElement RegisterLnk;
	
	//Constructor ,initializing the PageObjects
	public SignInPage() {
		PageFactory.initElements(driver, this); 
	} 
		
	//Actions		
	public void enterUsername(String username) {
		usernametxtbox.clear();
		usernametxtbox.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordtxtbox.clear();
		passwordtxtbox.sendKeys(password);
	}
	
	public HomePage clickLoginBtnLoginPage(){
		loginBtn.click();
		return  new HomePage();	
	}
	
	public void LoginUser(String username,String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginBtnLoginPage();			
	}
	
	public RegisterPage clickRegisterLnkLoginPage() {
		lnkregister.click();
		return new RegisterPage();
	}
	
	public String AlertMessageLoginPage() {
		return alertmsg.getText();
	}
	
	public String SignInPageURL() {
		return driver.getCurrentUrl();	
	}
}
