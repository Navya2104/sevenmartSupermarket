package com.sevenmart.tests;

import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.pages.PushNotificationPage;
import com.sevenmart.utilities.ExcelUtility;

public class PushNotificationTest extends Base{
	
	PushNotificationPage pushnotification;
	LoginPage loginpage;
	ExcelUtility excelutility=new ExcelUtility();
	
	@Test
	public void pushNotificationPage()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		pushnotification=new PushNotificationPage(driver);
		excelutility.SetExcelFile("pushNotificationData", "pushMsg");
		String textMsg=excelutility.getCellData(0, 0);
		String descriptionMsg=excelutility.getCellData(0, 1);
		pushnotification.sendPushNotification(textMsg, descriptionMsg);
		
		
	}

}
