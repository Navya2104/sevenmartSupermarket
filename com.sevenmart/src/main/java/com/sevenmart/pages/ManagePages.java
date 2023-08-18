package com.sevenmart.pages;

import java.io.File;
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

public class ManagePages {
	WebDriver driver;
	LoginPage loginpage;
	GeneralUtility generalutility;
	PageUtility pageutility;
	WaitUtility waitutility;
	
	@FindBy(xpath="//ul[@role='menu']//li[7]")
	private WebElement manageContentTab;
	@FindBy (xpath="//ul[@role='menu']//li[7]/ul//a")
	private WebElement managePageTab;
	@FindBy (xpath="//div[@class='col-sm-12']/a")
	private WebElement newButton;
	@FindBy(xpath="//input[@name='title']")
	private WebElement titleField;
	@FindBy (xpath="//input[@name='page']")
	private WebElement pageField;
	@FindBy (xpath="//input[@id='main_img']")
	private WebElement uploadField;
	@FindBy (xpath="//button[@name='create']")
	private WebElement saveButton;
	@FindBy(xpath="//table//tbody//tr//td[4]")
	private List<WebElement> listOfPages;
	
	
	public ManagePages(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void clickOn_element(WebElement element)
	{
		element.click();
	}
	public void uploadFile(String path)
	{
		File file= new File(path);
		uploadField.sendKeys(file.getAbsolutePath());
	}
	public boolean addCellContent(String title,String pages,String path)
	{
		pageutility=new PageUtility(driver);
		clickOn_element(manageContentTab);
		clickOn_element(managePageTab);
		clickOn_element(newButton);
		titleField.sendKeys(title);
		pageField.sendKeys(pages);
		uploadFile(path);
		pageutility.scrollAndClickUsingArrowDown(saveButton);
		return true;
	}
	public boolean verify_Field(String name)
	{
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(listOfPages);
		int position=generalutility.positionOfUser(names, name);
		if((position>0)&&(position<40))
		{
			return true;
		}
		return false;
	}
	public boolean deleteCellContent(String pageName)
	{
		pageutility=new PageUtility(driver);
		generalutility=new GeneralUtility(driver);
		waitutility= new WaitUtility(driver);
		clickOn_element(manageContentTab);
		clickOn_element(managePageTab);
		List<String> names=new ArrayList<String>();
		names=generalutility.getTextOfElements(listOfPages);
		int position=generalutility.positionOfUser(names, pageName);
		WebElement element=driver.findElement(By.xpath("//table//tbody//tr["+position+"]//td[5]/a[2]"));
		pageutility.scrollAndClickUsingArrowDown(element);
		if(waitutility.waitForAlert()==true)
		{
			pageutility.acceptAlert();
			
		}
		boolean actual=verify_Field(pageName);
		return actual;
	}
		

}
