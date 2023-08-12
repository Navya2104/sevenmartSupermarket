package com.sevenmart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.utilities.GeneralUtility;

public class ExpensePage {
	WebDriver driver;
	GeneralUtility generalutility;
	
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
	
	public ExpensePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickon_element(WebElement element)
	{
		element.click();
	}
	public String createExpenseCategory()
	{
		clickon_element(manageExpenseField);
		clickon_element(expenseCategory_check);
		clickon_element(newButton);
		String randomName=GeneralUtility.getRandomName();
		titleField.sendKeys(randomName);
		clickon_element(saveButton);
		return randomName;
	
	}
	public boolean verifyExpenseCategory(String name)
	{
		generalutility=new GeneralUtility(driver);
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(expenseListField);
		int i;
		for(i=0;i<expenseListField.size();i++)
		{
			if(name.equals(names.get(i)))
			{
				i++;
				break;
			}
		}
		
		return true;
	}
	public void deleteExpenseEntry(String name)
	{ 
		generalutility=new GeneralUtility(driver);
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(expenseListField);
		int i;
		for(i=0;i<expenseListField.size();i++)
		{
			if(name.equals(names.get(i)))
			{
				i++;
				break;
			}
		}
		System.out.println(i);
		WebElement deleteButton=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]/a[2]"));
		deleteButton.submit();
	}
	

}
