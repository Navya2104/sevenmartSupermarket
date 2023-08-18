package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.DeliveryBoyPage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.ExcelUtility;
import com.sevenmart.utilities.GeneralUtility;
import com.sevenmart.utilities.PageUtility;

public class DeliveryBoyPageTest extends Base{
	DeliveryBoyPage deliveryboypage;
	LoginPage loginpage;
	ExcelUtility excelutility=new ExcelUtility();
	
	
	@Test(groups={"smoke"},dataProvider="deliveryBoynames",dataProviderClass = TestDataProviders.class)
	public void verifyDeliveryBoyNameAdded(String name,String mail,String phoneNumber,String address,String userName,String password,String expectedPosition)
	{
		loginpage= new LoginPage(driver);
		deliveryboypage= new DeliveryBoyPage(driver);
		loginpage.login();
		String uniqueUserName=userName+" "+GeneralUtility.getRandomName();
		String actualPosition=deliveryboypage.createDeliveryBoyField(name, mail, phoneNumber, address, uniqueUserName, password);
		Assert.assertEquals(actualPosition, expectedPosition,"deliveryBoy is not created");
		
	}
	@Test
	public void verifyDeliveryBoyGivenByUser()
	{
		loginpage= new LoginPage(driver);
		deliveryboypage= new DeliveryBoyPage(driver);
		loginpage.login();
		excelutility.SetExcelFile("manageDeliveryBoyData", "verifyDetails");
		String expectedUserName = excelutility.getCellData(0, 0);
		String actualUserName = deliveryboypage.verifyUser(expectedUserName);
		Assert.assertEquals(actualUserName, expectedUserName,"user is not found");
	}


}
