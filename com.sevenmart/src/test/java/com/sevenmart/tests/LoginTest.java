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
	
	@Test(priority=1,groups="smoke test")
	public void verifyAdminUserLogin()
	{
		loginpage= new LoginPage(driver);
		homepage=new HomePage(driver);
		loginpage.login();
		String actualProfileName=homepage.getProfileName();
		String expectedProfileName="Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName,"error message");
	}
	
	/*public void verifyInvalidUserLogin()
	{
		loginpage= new LoginPage(driver);
		excelutility.SetExcelFile("loginData", "InvalidLoginCredentials");
		String invalidUserName=excelutility.getCellData(0, 0);
		String invalidPassword=excelutility.getCellData(0, 1);
		//String actualErrorMsg=loginpage.login("navya", "asgjdh");
		String actualErrorMsg=loginpage.login(invalidUserName, invalidPassword);
		System.out.println(actualErrorMsg);
		String expectedErrorMsg="Alert!";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}*/
	@Test(dataProvider="invalidcredentials",dataProviderClass=TestDataProviders.class)
	public void verifyInvalidUserLogin(String invalidUsername,String invalidPassword)
	{
		loginpage= new LoginPage(driver);
		loginpage.login(invalidUsername, invalidPassword);
		String actualErrorMessage=loginpage.getErrorMsg();
		String expectedErrorMsg="Alert!";
		Assert.assertEquals(actualErrorMessage, expectedErrorMsg);
	}

}
