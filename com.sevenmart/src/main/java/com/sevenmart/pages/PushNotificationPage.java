package com.sevenmart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PushNotificationPage {
	WebDriver driver;
	
	@FindBy(xpath="(//a[@class=' nav-link'])[4]")
	WebElement pushNoticationtField;
	@FindBy(id="title")
	WebElement titleField;
	@FindBy(id="description")
	WebElement descriptionField;
	@FindBy(xpath="//button[@name='create']")
	WebElement signButton;
	
	public PushNotificationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickon_PushNotificationTab()
	{
		pushNoticationtField.click();
	}
	public void enterTitle(String textmsg)
	{
		titleField.sendKeys(textmsg);
	}
	public void enterDescription(String description)
	{
		descriptionField.sendKeys(description);
	}
	public void clickonSignButton()
	{
		signButton.click();
	}
	public void sendPushNotification(String text,String description)
	{
		clickon_PushNotificationTab();
		enterTitle(text);
		enterDescription(description);
		clickonSignButton();
		
		
	}
	

}
