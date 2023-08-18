package com.sevenmart.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.constants.Constants;
import com.sevenmart.utilities.GeneralUtility;
import com.sevenmart.utilities.WaitUtility;

public class LoginPage {
	WebDriver driver;
	Properties properties=new Properties();
	FileInputStream fileinputstream;
	GeneralUtility generalutility;
	WaitUtility waitutility;
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement userNameField;
	@FindBy (xpath="//input[@name='password']")
	private WebElement passwordField;
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	private WebElement signButton;
	@FindBy(xpath="//input[@id='remember']")
	private WebElement rememberMeButton;
	@FindBy (xpath="//div[@class='alert alert-danger alert-dismissible']/h5")
	private WebElement errormsgfield;

	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		try {
			fileinputstream = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fileinputstream);
		} catch (Exception e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
	}
	public void enterUserName(String userName)
	{
		userNameField.sendKeys(userName);
		
	}
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	public void clickonSignButton()
	{
		waitutility=new WaitUtility(driver);
		waitutility.waitForClickable(signButton);
		signButton.click();
		
	}
	public String getErrorMsg()
	{
		generalutility=new GeneralUtility(driver);
		return generalutility.getTextOfElement(errormsgfield);
		
	}
	public void login()
	{
		String userName=properties.getProperty("username");
		String password=properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickonSignButton();
	}
	public void login(String username, String password)
	{
		
		enterUserName(username);
		enterPassword(password);
		clickonSignButton();	
			
	}
}
