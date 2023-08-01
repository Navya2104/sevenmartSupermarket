package com.sevenmart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmart.utilities.GeneralUtility;

public class ManagePages {
	WebDriver driver;
	LoginPage loginpage;
	GeneralUtility generalutility;
	
	@FindBy(xpath="//div[@class='inner']/p[text()='Manage Pages']")
	WebElement manageBox;
	@FindBy(xpath="//table/tbody//tr//td[1]")
	List<WebElement> listFirstCellContents;
	By arrowButton=By.xpath("//a[@class='small-box-footer']");
	
	public ManagePages(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void clickOnManageBox()
	{
	manageBox.findElement(arrowButton).click();
	}
	public List<String> getAllCellContents()
	{
		generalutility=new GeneralUtility(driver);
		return generalutility.getTextOfElements(listFirstCellContents);
	}
	public String getRequiredCellContent(String expectedContent)
	{
		List<String>data=new ArrayList<String>();
		data=getAllCellContents();
		String actualContent=" ";
		for(String dat:data)
		{
			if(expectedContent.equals(dat))
			{
				actualContent=dat;
				break;				
			}
		}return actualContent;
	
	}

}
