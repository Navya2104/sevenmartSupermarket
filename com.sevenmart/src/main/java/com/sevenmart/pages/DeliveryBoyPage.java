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
	
	@FindBy(xpath="(//a[@class=' nav-link'])[8]")
	WebElement deliveryBoyButton;
	@FindBy(xpath="//a[@onclick='click_button(1)']")
	WebElement newButton;
	@FindBy(xpath="//div[@class='col-md-12']/div/input")
	List<WebElement> inputFields;
	@FindBy(id="name")
	WebElement nameField;
	@FindBy(id="email")
	WebElement mailField;
	@FindBy(id="phone")
	WebElement phoneField;
	@FindBy(id="username")
	WebElement userNameField;
	@FindBy(id="password")
	WebElement passwordField;
	@FindBy(id="address")
	WebElement addressField;
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveButton;
	@FindBy(xpath="//div[@class='row-sm-12']/div/h5")
	WebElement successAlert;
	
	public DeliveryBoyPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickon_ManageDeliveryBoy()
	{
		deliveryBoyButton.click();
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
	public void createDeliveryBoyField(String name,String mail,String phoneNumber,String address,String userName,String password)
	{
		pageutility= new PageUtility(driver);
		waitutility=new WaitUtility(driver);
		clickon_ManageDeliveryBoy();
		enterInputFields(name, mail, phoneNumber, address, userName, password);
		pageutility.scrollDown(saveButton);
		waitutility.waitForClickable(saveButton);
		clickonSaveButton();
	}
	public String alertMessage()
	{
		generalutility=new GeneralUtility(driver);
		return generalutility.getTextOfElement(successAlert);
	}
	
	
	
	

}
