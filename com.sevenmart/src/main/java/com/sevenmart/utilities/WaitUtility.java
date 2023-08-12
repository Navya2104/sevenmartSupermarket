package com.sevenmart.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	
		
		WebDriver driver;
		public static final long IMPLICIT_WAIT=10;
		public static final long EXPLICIT_WAIT=5;
		WebDriverWait wait;
		
		public WaitUtility(WebDriver driver)
		{
			this.driver=driver;
		}
		public void waitForClickable(WebElement element)
		{
			wait=new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
		}
		public void waitForVisibility(String xpath)
		{
			wait=new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}
		public void waitForInvisibility(String xpath)
		{
			wait=new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		}
		public boolean waitForAlert()
		{
			wait=new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
					
		}
		public void waitForVisibility(By by)
		{
			wait=new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		
		

	}


