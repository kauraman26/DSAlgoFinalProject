package com.dsalgo.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dsalgo.qa.base.TestBase;



public class ArrayPage extends TestBase{
	
	@FindBy(xpath="//a[@href='arrays-in-python']")
	 WebElement arraysInPythonLnk;
	@FindBy (xpath="//a[@href='/tryEditor']")
	 WebElement tryHereBtn;
	@FindBy(xpath ="//button[@type ='button' and text ()='Run']")
	 WebElement runBtn;
	@FindBy(xpath="//a[@href='/array/arrays-using-list/']")
	 WebElement arraysUsingListLnk;	
	@FindBy(xpath="//a[@href='basic-operations-in-lists']")
	 WebElement basicOperationInListsLnk;
	@FindBy(xpath="//a[@href='applications-of-array']")
	 WebElement applicationOfArrayLnk;
	//@FindBy(xpath="//pre[@class=' CodeMirror-line ']")
	@FindBy(xpath="//textarea[@tabindex='0']")
	WebElement editorInput;
	@FindBy(xpath="//pre[@id ='output']")
	WebElement outputConsole;
	
	public ArrayPage(){
		PageFactory.initElements(driver, this); 
	} 
	
	public void clickOnArraysUsingListLink() {
			arraysUsingListLnk.click();
		}
	public void clickOnArraysInPythonLink() {
		arraysInPythonLnk.click();
	}
	
	public void clickTryHereBtn() {
		tryHereBtn.click();	
	}
	public void clickRunBtn() {
		runBtn.click();
	}
	
	public boolean enterCode(String code,String result) {
		
		try {
			new Actions(driver).sendKeys(editorInput, code).perform();
		} catch(Exception e) {
			editorInput.sendKeys(code);
		}
		return true;
	}
	
	public String validatePythonCodeGotPrinted() {
		return outputConsole.getText();
	}
	
	public void deletePythonCode() {
		Actions actions = new Actions(driver);
		actions.click(editorInput).perform();
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}
	
}
