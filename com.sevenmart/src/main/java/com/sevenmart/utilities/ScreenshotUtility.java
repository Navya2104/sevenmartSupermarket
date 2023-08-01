package com.sevenmart.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.sevenmart.constants.Constants;

public class ScreenshotUtility {
	TakesScreenshot takescreenshot;//TakesScreenshot is an interface
	
	public void takeScreenShot(WebDriver driver, String imageName)
	{
		try {
			takescreenshot=(TakesScreenshot) driver;
			File screenShot= takescreenshot.getScreenshotAs(OutputType.FILE);//captures screenshot
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
			String destination=Constants.SCREENSHOT_PATH+ imageName+timeStamp+".png";
			File finalDestination= new File(destination);
			FileHandler.copy(screenShot, finalDestination);//moves the image from clipboard to destination
			
		} catch (Exception e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

}
