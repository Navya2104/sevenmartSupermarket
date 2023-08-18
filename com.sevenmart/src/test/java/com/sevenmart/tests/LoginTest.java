package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.HomePage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.ExcelUtility;

public class LoginTest extends Base{
	LoginPage loginpage;
	HomePage homepage;
	ExcelUtility excelutility=new ExcelUtility();
	
	@Test(priority=1,groups={"smoke"})
	public void verifyAdminUserLogin()
	{
		loginpage= new LoginPage(driver);
		homepage=new HomePage(driver);
		loginpage.login();
		String actualProfileName=homepage.getProfileName();
		excelutility.SetExcelFile("loginData", "profileNameDetails");
		String expectedProfileName=excelutility.getCellData(0, 0);
		Assert.assertEquals(actualProfileName, expectedProfileName,"error message");
	}
	
	@Test(dataProvider="invalidcredentials",dataProviderClass=TestDataProviders.class)
	public void verifyInvalidUserLogin(String invalidUsername,String invalidPassword,String expectedMessage)
	{
		loginpage= new LoginPage(driver);
		loginpage.login(invalidUsername, invalidPassword);
		String expectedErrorMessage=expectedMessage;
		String actualErrorMessage=loginpage.getErrorMsg();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage,"user logged in");
	}

}
