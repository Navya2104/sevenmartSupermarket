package com.sevenmart.tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.AdminUserPage;
import com.sevenmart.pages.DeliveryBoyPage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.ExcelUtility;
import com.sevenmart.utilities.GeneralUtility;

public class AdminUserTest extends Base {
	LoginPage loginpage;
	AdminUserPage adminuserpage;
	ExcelUtility excelutility=new ExcelUtility();

	@Test(priority = 1,groups={"smoke"})
	public void verifyAdminUser() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		excelutility.SetExcelFile("adminProfileData", "verifyDetails");
		String expectedUserName =excelutility.getCellData(0, 0);
		String actualUserName = adminuserpage.verifyUser(expectedUserName);
		Assert.assertEquals(actualUserName, expectedUserName,"user is not found");
	}

	@Test(groups={"regression"},dataProvider = "adminUserDetails", dataProviderClass = TestDataProviders.class)
	public void createAdminUser(String userName, String password, String userType,String position) {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		int expectedPosition=Integer.parseInt(position);
		int actualPosition=adminuserpage.createNewUser(userName+" "+GeneralUtility.getRandomName(), password, userType);
		Assert.assertEquals(actualPosition, expectedPosition,"user is not created.check");
		
	}

	@Test(retryAnalyzer=com.sevenmart.listeners.RetryAnalyzer.class)
	public void deleteAdminUser() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		excelutility.SetExcelFile("adminProfileData", "deleteDetails");
		String expectedUserName =excelutility.getCellData(0, 0);
		boolean value=adminuserpage.deleteUser(expectedUserName);
		Assert.assertEquals(value, true,"user is not deleted.check");
		
	}

}
