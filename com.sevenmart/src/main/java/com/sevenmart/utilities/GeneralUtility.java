package com.sevenmart.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

public class GeneralUtility {
	
	WebDriver driver;
	public GeneralUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	public String getTextOfElement(WebElement element)
	{
		return element.getText();
		
	}
	public String getTextOfElement(String xpath)
	{
		return driver.findElement(By.xpath(xpath)).getText();
		
	}
	public List<String> getTextOfElements(String xpath)
	{
		List<String> data= new ArrayList<String>();
		List<WebElement> elements= driver.findElements(By.xpath(xpath));
		for(WebElement element: elements)
		{
			data.add(element.getText());
			
		}
		return data;
	}
	public List<String> getTextOfElements(List<WebElement>element)
	{
		List<String> data= new ArrayList<String>();
		for(WebElement itr: element)
		{
			data.add(itr.getText());
			
		}
		return data;
	}
	public String get_attribute(WebElement element, String attribute)
	{
		return element.getAttribute(attribute);
	}
	public String get_cssValue(WebElement element, String cssValue)
	{
		return element.getAttribute(cssValue);
	}
	public Boolean is_Displayed(WebElement element)
	{
		return element.isDisplayed();
	}
	public Boolean is_Enabled(WebElement element)
	{
		return element.isEnabled();
	}
	public Boolean is_Selected(WebElement element)
	{
		return element.isSelected();
	}
	public static String getRandomName()
	{
		Faker faker = new Faker();

		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String streetAddress = faker.address().streetAddress();
		return name;
	}
	public static String getTimeStamp()
	{
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		return timeStamp;
	}
	public int positionOfUser(List<String>elements, String element)
	{
		int pos=0;
		for(pos=0;pos<elements.size();pos++)
		{
			if(element.equals(elements.get(pos)))
			{
				pos++;
				break;
			}
		}
		return pos;
	}
	
}
