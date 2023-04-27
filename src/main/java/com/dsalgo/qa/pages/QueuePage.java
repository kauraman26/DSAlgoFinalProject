package com.dsalgo.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.util.testUtil;

public class QueuePage  extends TestBase {
	
	@FindBy(xpath = "//a[normalize-space()='Implementation of Queue in Python']")
	WebElement ImplementationQueuePythonLink;
	@FindBy(xpath = "//a[@class='btn btn-info']")
	WebElement TryhereLink;
	@FindBy(xpath = "//a[@href='/queue/implementation-collections/']")
	WebElement ImpCollectionsDequeLink;
	@FindBy(xpath="//textarea[@tabindex='0']") 
	WebElement editorInput;
	@FindBy(xpath="//button[@type='button']")
	WebElement runButton;
	@FindBy(xpath="//pre[@id='output']") 
	WebElement outputConsole;
	
	public QueuePage()
    {
		   PageFactory.initElements(driver, this);
	}
	public void ClickOnImplementationQueuePythonLink()
	{
		 testUtil.webElement_Click(ImplementationQueuePythonLink);
	}
	public void ClickOnImpCollectionsDequeLink()
	{
		 testUtil.webElement_Click(ImpCollectionsDequeLink);
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
	
	public void deletePythonCode() {
		Actions actions = new Actions(driver);
		actions.click(editorInput).perform();
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}
		
}
