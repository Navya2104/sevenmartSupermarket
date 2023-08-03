package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.DeliveryBoyPage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.PageUtility;

public class DeliveryBoyPageTest extends Base{
	DeliveryBoyPage deliveryboypage;
	LoginPage loginpage;
	
	
	@Test(dataProvider="deliveryBoynames",dataProviderClass = TestDataProviders.class)
	public void verifyDeliveryBoyNameAdded(String name,String mail,String phoneNumber,String address,String userName,String password,String expectedMessage)
	{
		loginpage= new LoginPage(driver);
		deliveryboypage= new DeliveryBoyPage(driver);
		loginpage.login();
		deliveryboypage.createDeliveryBoyField(name, mail, phoneNumber, address, userName, password);
		String actualMessage=deliveryboypage.alertMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
		
	}
	@Test(dataProvider="deliveryBoynames",dataProviderClass = TestDataProviders.class)
	public void verify(String name,String mail,String phoneNumber,String address,String userName,String password,String expectedMessage)
	{
		loginpage= new LoginPage(driver);
		deliveryboypage= new DeliveryBoyPage(driver);
		loginpage.login();
		deliveryboypage.createDeliveryBoyField(name, mail, phoneNumber, address, userName, password);
		String actualMessage=deliveryboypage.alertMessage();
		Assert.assertEquals(actualMessage, expectedMessage,"error");
		
	}


}
