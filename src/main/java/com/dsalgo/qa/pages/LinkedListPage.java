package com.dsalgo.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.util.testUtil;

public class LinkedListPage extends TestBase {
	@FindBy(xpath="//a[@class='list-group-item']") 
	WebElement IntroductionLink; //IntroductionLink
	@FindBy(xpath="//a[@class='btn btn-info']") 
	WebElement TryhereLink; //TryhereLink
	@FindBy(xpath="//a[@href=\"/linked-list/types-of-linked-list/\"]")
	WebElement TypesOfLinkedList;
	//@FindBy(xpath="//span[@class='cm-builtin']") WebElement editorInput;
	@FindBy(xpath="//textarea[@tabindex='0']") 
	WebElement editorInput;
	@FindBy(xpath="//button[@type='button']")
	WebElement runButton;
	@FindBy(xpath="//pre[@id='output']") 
	WebElement outputConsole;
	
	public LinkedListPage()
	    {
			   PageFactory.initElements(driver, this);
		}
	
	public void ClickOnIntroductionLink()
	{
		 testUtil.webElement_Click(IntroductionLink);
	}
	public void ClickOnTypesOfLinkedList()
	{
		testUtil.webElement_Click(TypesOfLinkedList);
	}
	public void ClickOnTryHere()
	 {
		testUtil.webElement_Click(TryhereLink);
	 }
	
	public boolean enterCode(String code,String result) {
		
		try {
			new Actions(driver).sendKeys(editorInput, code).perform();
		} catch(Exception e) {
			editorInput.sendKeys(code);
		}
		return true;
	}
	
	public void clickBtnRun()
	{
		 runButton.click();
	}
	public String validatePythonCodeGotPrinted()
	{
		return outputConsole.getText();
	}	
	public void deletePythoncode()
	{
		Actions action = new Actions(driver);
		action.click(editorInput).perform();
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}	
}
