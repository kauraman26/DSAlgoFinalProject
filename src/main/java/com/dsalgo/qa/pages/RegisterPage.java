package com.dsalgo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.util.testUtil;

public class RegisterPage extends TestBase {
	
	
	@FindBy(xpath="//input[@type='submit' and @value='Register']")
	WebElement Btnregister;
	@FindBy(xpath="//input[@name='username']")
	WebElement txtboxusername;
	@FindBy (xpath="//*[@id='id_password1']") 
	WebElement txtboxpassword;
	@FindBy (xpath="//*[@id='id_password2']")
	WebElement txtboxconfirmpwd;
	@FindBy (xpath="//*[@class='alert alert-primary']")
	WebElement alertMsg;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement accountCreatedSuccess;
	
	
	//Constructor ,initializing the PageObjects
		public RegisterPage() {
			PageFactory.initElements(driver, this); 
		} 

	
	public String getPageTitle() {
		String Reg_Title = driver.getTitle();
		return Reg_Title;
	}
	
	public void clickRegisterButton()  {
		Btnregister.click();	
	}
	
	public void enterUserName(String uname) {	
		testUtil.webSendKeys(txtboxusername, testUtil.getRandomNum()+uname);
	}
	
	public void enterPassword(String pswrd) {
		testUtil.webSendKeys(txtboxpassword, pswrd);
	}
	
	public void enterConfirmPassword(String Confirmpswrd) {	
		testUtil.webSendKeys(txtboxconfirmpwd, Confirmpswrd);
	}
	
	public String registerSucessMessageRegisterPage() {
		return accountCreatedSuccess.getText();		
	}
	
	public String getEmptyfieldErrormsgUser() {
		return txtboxusername.getAttribute("validationMessage");
	}
	
	public String getEmptyfieldErrormsgPassword() {
		return txtboxpassword.getAttribute("validationMessage");
	}
	
	public String passwordMismatchErrormsg() {
		return alertMsg.getText();
	}	
}
