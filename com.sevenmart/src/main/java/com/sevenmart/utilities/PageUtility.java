package com.sevenmart.utilities;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	
	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	
	public PageUtility(WebDriver driver)
	{
		this.driver=driver;
		
	}
	public void select_ByIndex(WebElement element, int index)
	{
		select=new Select(element);
		select.selectByIndex(index);
	}
	public void select_ByValue(WebElement element, String value)
	{
		select=new Select(element);
		select.selectByValue(value);
	}
	public void select_ByVisibleText(WebElement element, String text)
	{
		select=new Select(element);
		select.selectByVisibleText(text);;
	}
	public void moveToElement(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	public void dragAndDrop(WebElement source, WebElement destination)
	{
		Actions action=new Actions(driver);
		action.dragAndDrop(source, destination).build().perform();
	}
	public void doubleClick(WebElement element)
	{
		Actions action=new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	public void contextClick(WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).build().perform();
	}
	public void acceptAlert()
	{
		driver.switchTo().alert().accept();
	}
	public void dismissAlert()
	{
		driver.switchTo().alert().dismiss();
	}
	public String getTextAlert()
	{
		return driver.switchTo().alert().getText();
	}
	public void enterTextAlert(String text)
	{
		driver.switchTo().alert().sendKeys(text);;
	}
	public void switchToWindow(String windowId)
	{
		driver.switchTo().window(windowId);
	}
	public void scrollToField(WebElement button)
	{
		js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", button);
	}
	public void scrollDown(WebElement button)
	{
		js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)", button);
	}
	public void clickButton(WebElement button)
	{
		js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",button);
	}
	public void clickButtonCoordinate()
	{
		js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
	}
	public void scrollAndClickUsingArrowDown(WebElement element) 
	{
		
		try {
			Robot robot=new Robot();
			while(isNotClicked(element))
			 {
				robot.keyPress(KeyEvent.VK_DOWN);//down arrown
				robot.keyRelease(KeyEvent.VK_DOWN);
			 }
			
		} catch (Exception e) {
			
		}
		
	}
	public void scrollAndClick(WebElement element)
	{
		int y=0;
		JavascriptExecutor js=(JavascriptExecutor) driver; 
		 while(isNotClicked(element))
		 {
			 js.executeScript("window.scrollBy(0," + y + ")");
				y = y + 2;
		 }
	}
	public boolean isNotClicked(WebElement element)
	{
		try {
			element.click();
			return false;
		}
		catch (Exception e) {
			return true;
		}
		
	}
}
