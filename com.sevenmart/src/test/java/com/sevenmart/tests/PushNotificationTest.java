package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.pages.PushNotificationPage;
import com.sevenmart.utilities.ExcelUtility;

public class PushNotificationTest extends Base{
	
	PushNotificationPage pushnotification;
	LoginPage loginpage;
	ExcelUtility excelutility=new ExcelUtility();
	
	@Test(groups={"smoke"},dataProvider = "pushNotificationDetails", dataProviderClass = TestDataProviders.class)
	public void pushNotificationPage(String title, String description, String expectedColour)
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		pushnotification=new PushNotificationPage(driver);
		String descriptionMessage=description;
		String actualColour=pushnotification.sendPushNotification(title, descriptionMessage);
		Assert.assertEquals(actualColour, expectedColour,"push message not send ");
		
	}

}
