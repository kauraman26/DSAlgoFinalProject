package com.dsalgo.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dsalgo.qa.base.TestBase;
import com.dsalgo.qa.util.testUtil;



public class TreePage extends TestBase{
	
	@FindBy(xpath = "//a[@href='data-structures-introduction']")
	WebElement getstart_datastructures;
	@FindBy(xpath = "//a[@href='tree']")
	WebElement getstart_tree;
	@FindBy(xpath = "//*[@id=\"navbarCollapse\"]//a[contains(text(), 'Data Structures')]")
	WebElement dropdown;
	@FindBy(xpath = "//*[@id='navbarCollapse']//a[contains(@href, '/tree')]")
	WebElement dropdown_tree;
	@FindBy(xpath = "//*[@href='overview-of-trees']")
	WebElement overviewOfTreesLink;
	@FindBy(xpath = "//a[@href='/tree/terminologies/']")
	WebElement terminologiesLink;
	@FindBy(xpath = "//*[@href='types-of-trees']")
	WebElement typesoftreesLink;
	@FindBy(xpath = "//*[@href='tree-traversals']")
	WebElement treetraversalsLink;
	@FindBy(xpath = "//*[@href='traversals-illustration']")
	WebElement trav_illustrationsLink;
	@FindBy(xpath = "//*[@href='binary-trees']")
	WebElement binarytreesLink;
	@FindBy(xpath = "//*[@href='types-of-binary-trees']")
	WebElement typesofBinaryTreesLink;
	@FindBy(xpath = "//a[@href='implementation-in-python']")
	WebElement implementationInPythonLink;
	@FindBy(xpath = "//a[@href='binary-tree-traversals']")
	WebElement binaryTreeTraversalsLink;
	@FindBy(xpath = "//a[@href='implementation-of-binary-trees']")
	WebElement implementationOfBinaryTreesLink;
	@FindBy(xpath = "//a[@href='applications-of-binary-trees']")
	WebElement applicationsOfBinaryTreesLink;
	@FindBy(xpath = "//a[@href='binary-search-trees']")
	WebElement binarySearchTreesLink;
	@FindBy(xpath = "//a[@href='implementation-of-bst']")
	WebElement implementationOfBSTLink;
	@FindBy(xpath = "//a[@href='/tree/practice']")
	WebElement practiceQuestionLink;
	@FindBy(xpath = "//a[@href='/tryEditor']")
	WebElement TryHereLink;
	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement editorInput;
	@FindBy(xpath = "//*[@id='answer_form']/button")
	WebElement runButton;
	@FindBy(id = "output")
	WebElement outputConsole;
	
	public TreePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void dropdown_Tree() {
		dropdown.click();
		dropdown_tree.click();
	}

	public String getTreePageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public void clickOnoverviewOfTreesPage() {
		overviewOfTreesLink.click();
	}
	
	public void clickOnTerminologiesLink() {
		testUtil.webElement_Click(terminologiesLink);
		//terminologiesLink.click();
	}

	public void click_Tryhere() {
		TryHereLink.click();
	}
	public void clickOnRun() {
		runButton.click();
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
