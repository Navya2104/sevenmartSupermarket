package com.sevenmart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.utilities.GeneralUtility;
import com.sevenmart.utilities.PageUtility;
import com.sevenmart.utilities.WaitUtility;

public class DeliveryBoyPage {
	WebDriver driver;
	WaitUtility waitutility;
	PageUtility pageutility;
	GeneralUtility generalutility;
	
	@FindBy(xpath="//ul[@role='menu']/li[13]")
	private WebElement deliveryBoyButton;
	@FindBy(xpath="//a[@onclick='click_button(1)']")
	private WebElement newButton;
	@FindBy(xpath="//div[@class='col-md-12']/div/input")
	private List<WebElement> inputFields;
	@FindBy(id="name")
	private WebElement nameField;
	@FindBy(id="email")
	private WebElement mailField;
	@FindBy(id="phone")
	private WebElement phoneField;
	@FindBy(id="username")
	private WebElement userNameField;
	@FindBy(id="password")
	private WebElement passwordField;
	@FindBy(id="address")
	private WebElement addressField;
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;
	@FindBy(xpath="//div[@class='row-sm-12']/div/h5")
	private WebElement successAlert;
	@FindBy(xpath="//table/tbody/tr/td[5]")
	private List<WebElement> userNames;
	
	public DeliveryBoyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickOn_element(WebElement element)
	{
		element.click();
	}
	public void enterInputFields(String name,String mail,String phoneNumber,String address,String userName,String password)
	{
		newButton.click();
		nameField.sendKeys(name);
		mailField.sendKeys(mail);
		phoneField.sendKeys(phoneNumber);
		addressField.sendKeys(address);
		userNameField.sendKeys(userName);
		passwordField.sendKeys(password);	
	}
	public void clickonSaveButton()
	{
		pageutility= new PageUtility(driver);
		pageutility.clickButton(saveButton);
	}
	public String createDeliveryBoyField(String name,String mail,String phoneNumber,String address,String userName,String password)
	{
		pageutility= new PageUtility(driver);
		waitutility=new WaitUtility(driver);
		generalutility=new GeneralUtility(driver);
		clickOn_element(deliveryBoyButton);
		enterInputFields(name, mail, phoneNumber, address, userName, password);
		pageutility.scrollDown(saveButton);
		waitutility.waitForClickable(saveButton);
		clickonSaveButton();
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(userNames);
		int position=generalutility.positionOfUser(names,name);
		String pos=Integer.toString(position);
		return pos;
	}
	public String verifyAndEditUser(String expectedUserName) {
		
		return null;
	}
	public String verifyUser(String name)
	{
		generalutility=new GeneralUtility(driver);
		clickOn_element(deliveryBoyButton);
		List<String>names=new ArrayList<String>();
		names=generalutility.getTextOfElements(userNames);
		int position=generalutility.positionOfUser(names,name);
		return name;
		
	}
	
	
	

}
