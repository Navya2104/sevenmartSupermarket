package com.sevenmart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.utilities.GeneralUtility;

public class PushNotificationPage {
	WebDriver driver;
	GeneralUtility generalutility;
	
	@FindBy(xpath="(//a[@class=' nav-link'])[3]")
	private WebElement pushNoticationtField;
	@FindBy(id="title")
	private WebElement titleField;
	@FindBy(id="description")
	private WebElement descriptionField;
	@FindBy(xpath="//button[@name='create']")
	private WebElement saveButton;
	@FindBy (xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement messageArea;
	
	public PushNotificationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void clickon_element(WebElement element)
	{
		element.click();
	}
	public void enterTitle(String textmsg)
	{
		titleField.sendKeys(textmsg);
	}
	public void enterDescription(String description)
	{
		descriptionField.sendKeys(description);
	}
	public String sendPushNotification(String text,String description)
	{
		generalutility=new GeneralUtility(driver);
		clickon_element(pushNoticationtField);
		enterTitle(text);
		enterDescription(description);
		clickon_element(saveButton);
		String actualColour=generalutility.get_cssValue(messageArea, "background-color");
		return actualColour;
				
	}
	

}
