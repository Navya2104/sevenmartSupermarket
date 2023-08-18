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
import com.sevenmart.utilities.WaitUtility;

public class AdminUserPage {
	WebDriver driver;
	GeneralUtility generalutility;
	PageUtility pageutility;
	WaitUtility waitutility;
	
	@FindBy(xpath="//table/tbody/tr/td[1]")
	private List<WebElement> userNames;
	@FindBy(xpath="//p[text()='Admin Users']")
	private WebElement adminUserTab;
	@FindBy(xpath="//a[@onclick='click_button(1)']")
	private WebElement newButton;
	@FindBy(id="username")
	private WebElement userNameField;
	@FindBy(id="password")
	private WebElement passwordField;
	@FindBy(id="user_type")
	private WebElement userTypeField;
	@FindBy(xpath="//button[@name='Create']")
	private WebElement saveButton;
	
		
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
		pageutility=new PageUtility(driver);
		userNameField.sendKeys(username);
		passwordField.sendKeys(password);
		clickOn_element(userTypeField);
		int option=Integer.parseInt(userType);
		pageutility.select_ByIndex(userTypeField, option);
		clickOn_element(saveButton);
		
	}
	public int createNewUser(String userName,String password,String userType)
	{
		generalutility=new GeneralUtility(driver);
		clickOn_element(adminUserTab);
		clickOn_element(newButton);
		List<String> names=new ArrayList<String>();
		enterAdminDetails(userName,password,userType);
		names=generalutility.getTextOfElements(userNames);
		int position=generalutility.positionOfUser(names, userName);
		return position;
	}
	public String verifyUser(String expectedPersonName)
	{
		generalutility=new GeneralUtility(driver);
		List<String> names=new ArrayList<String>();
		clickOn_element(adminUserTab);
		names=generalutility.getTextOfElements(userNames);
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
	public boolean deleteUser(String personName) 
	{
		generalutility=new GeneralUtility(driver);
		pageutility=new PageUtility(driver);
		waitutility= new WaitUtility(driver);
		List<String> names=new ArrayList<String>();
		clickOn_element(adminUserTab);
		names=generalutility.getTextOfElements(userNames);
		int position=generalutility.positionOfUser(names,personName);
		WebElement deleteButton=driver.findElement(By.xpath("//table/tbody/tr["+position+"]/td[5]/a[3]"));
		pageutility.scrollAndClickUsingArrowDown(deleteButton);
		if(waitutility.waitForAlert()==true)
		{
			pageutility.acceptAlert();
			
		}
		return true;
		
	}
		
}
