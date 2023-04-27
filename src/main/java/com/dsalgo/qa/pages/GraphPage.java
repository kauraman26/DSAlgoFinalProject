package com.dsalgo.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.dsalgo.qa.base.TestBase;

public class GraphPage extends TestBase{
	
	@FindBy (xpath="//a[@href='graph']")
	WebElement graphLnk;
	@FindBy (xpath ="//a[@href='/tryEditor']")
	WebElement TryHereBtnGraph;
	@FindBy(xpath = "//a[normalize-space()='Graph Representations']")
	WebElement GraphRepresentationslink;
	@FindBy(xpath = "//a[@class='btn btn-info']")
	WebElement GraphTryherelink;
	@FindBy(xpath = "//a[@class='list-group-item list-group-item-light text-info']")
	WebElement GraphPracQuesLink;
	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement editorInput;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runButton;	
	@FindBy(id = "output")
	WebElement outputConsole;

	
	public GraphPage(){
		PageFactory.initElements(driver, this); 
	} 
	
	public void clickGraphLnk() {
		graphLnk.click();
		//TestUtil.webElement_Click(graphLnk);
	}
	
	public void clickTryHereBtnGraphPage() {
		TryHereBtnGraph.click();
	}
	
	public void clickRunBtn() {
		runButton.click();
	}
	
	public void clickOnGraphRepresentationsLink() {
		GraphRepresentationslink.click();
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
