package com.sevenmart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.utilities.GeneralUtility;
import com.sevenmart.utilities.PageUtility;
import com.sevenmart.utilities.WaitUtility;

public class ExpensePage {
	WebDriver driver;
	GeneralUtility generalutility;
	PageUtility pageutility;
	WaitUtility waitutility;
	
	@FindBy (xpath="(//a[@class='nav-link'])[5]")
	private WebElement manageExpenseField;
	@FindBy(xpath="//li[@class='nav-item has-treeview menu-open']//ul[1]")
	private WebElement expenseCategory_check;
	@FindBy (xpath="//div[@class='col-sm-12']//a[1]")
	private WebElement newButton;
	@FindBy (id="name")
	private WebElement titleField;
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement saveButton;
	@FindBy(xpath="//table/tbody/tr/td[1]")
	private List<WebElement>expenseListField;
	@FindBy(xpath="//input[@name='name']")
	private WebElement nameField;
	
	public ExpensePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickon_element(WebElement element)
	{
		element.click();
	}
	public boolean verify_Field(String name)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(expenseListField);
		int position=generalutility.positionOfUser(names, name);
		if((position>0)&&(position<40))
		{
			return true;
		}
		return false;
	}
	
	public boolean createExpenseCategory(String randomName)
	{
		generalutility= new GeneralUtility(driver);
		clickon_element(manageExpenseField);
		clickon_element(expenseCategory_check);
		clickon_element(newButton);
		titleField.sendKeys(randomName);
		clickon_element(saveButton);
		boolean value=verify_Field(randomName);
		return value;
	}
	public boolean verifyExpenseCategory(String name)
	{
		generalutility=new GeneralUtility(driver);
		clickon_element(manageExpenseField);
		clickon_element(expenseCategory_check);
		boolean value=verify_Field(name);
		return value;
	}
	public boolean deleteExpenseEntry(String name)
	{ 
		generalutility=new GeneralUtility(driver);
		pageutility=new PageUtility(driver);
		waitutility= new WaitUtility(driver);
		clickon_element(manageExpenseField);
		clickon_element(expenseCategory_check);
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(expenseListField);
		int position=generalutility.positionOfUser(names, name);
		WebElement deleteButton=driver.findElement(By.xpath("//table/tbody/tr["+position+"]/td[2]/a[2]"));
		pageutility.scrollAndClickUsingArrowDown(deleteButton);
		if(waitutility.waitForAlert()==true)
		{
			pageutility.acceptAlert();
			
		}
		boolean value=verify_Field(name);
		return value;
	}
	

}
