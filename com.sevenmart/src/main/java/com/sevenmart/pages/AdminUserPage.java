package com.sevenmart.pages;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sevenmart.utilities.GeneralUtility;
import com.sevenmart.utilities.PageUtility;

public class AdminUserPage {
	WebDriver driver;
	GeneralUtility generalutility;
	Select select;
	PageUtility pageutility;
	
	@FindBy(xpath="//table/tbody/tr/td[1]")
	private List<WebElement> userNames;
	@FindBy(xpath="//p[text()='Admin Users']")
	WebElement adminUserTab;
	@FindBy(xpath="//a[@onclick='click_button(1)']")
	WebElement newButton;
	@FindBy(id="username")
	WebElement userNameField;
	@FindBy(id="password")
	WebElement passwordField;
	@FindBy(id="user_type")
	WebElement userTypeField;
	@FindBy(xpath="//button[@name='Create']")
	WebElement saveButton;
	
	//By deleteButtonElement=By.xpath("//table/tbody/tr/td["+pos+"]/a[3]");	
	
	
	public AdminUserPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickOn_element(WebElement element)
	{
		element.click();
	}
	public void enterAdminDetails(String username,String password,String userType)
	{
		userNameField.sendKeys(username);
		passwordField.sendKeys(password);
		clickOn_element(userTypeField);
		select=new Select(userTypeField);
		if(userType.equals("Staff"))
		{
		select.selectByIndex(1);
		}
		if(userType.equals("Admin"))
		{
		select.selectByIndex(2);
		}
		if(userType.equals("Partner"))
		{
		select.selectByIndex(3);
		}
		if(userType.equals("Delivery Boy"))
		{
		select.selectByIndex(4);
		}
		clickOn_element(saveButton);
		
	}
	public void createNewUser(String userName,String password,String userType)
	{
		clickOn_element(adminUserTab);
		clickOn_element(newButton);
		enterAdminDetails(userName,password,userType);
	}
	public List<String> getCellContents()
	{
		generalutility=new GeneralUtility(driver);
		return generalutility.getTextOfElements(userNames);
	}
	public String verifyUser(String expectedPersonName)
	{
		generalutility=new GeneralUtility(driver);
		List<String> names=new ArrayList<String>();
		clickOn_element(adminUserTab);
		names=getCellContents();
		String actualPersonName=" ";
		for(String name:names)
		{
			if(expectedPersonName.equals(name))
			{
				actualPersonName=name;
				break;
			}
		}
		return actualPersonName;
	}
	public void deleteUser(String personName) 
	{
		generalutility=new GeneralUtility(driver);
		pageutility=new PageUtility(driver);
		List<String> names=new ArrayList<String>();
		clickOn_element(adminUserTab);
		int pos;
		names=generalutility.getTextOfElements(userNames);
		
		for(pos=0;pos<names.size();pos++)
		{
			if(personName.equals(names.get(pos)))
			{
				pos++;
				break;
			}
		}
		System.out.println(pos);
		WebElement deleteButton=driver.findElement(By.xpath("//table/tbody/tr["+pos+"]/td[5]/a[3]"));
		pageutility.scrollAndClickUsingArrowDown(deleteButton);
	}
		
}
