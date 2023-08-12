package com.sevenmart.tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.AdminUserPage;
import com.sevenmart.pages.DeliveryBoyPage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.GeneralUtility;

public class AdminUserTest extends Base {
	LoginPage loginpage;
	AdminUserPage adminuserpage;

	@Test(priority = 1,groups="regression")
	public void user() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		String expectedUserName = "Thara";
		String actualUserName = adminuserpage.verifyUser(expectedUserName);
		Assert.assertEquals(actualUserName, expectedUserName,"user is not found");
	}

	@Test(dataProvider = "adminUserDetails", dataProviderClass = TestDataProviders.class)
	public void createAdminUser(String userName, String password, String userType) {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		int expectedPosition=1;
		int actualPosition=adminuserpage.createNewUser(userName+" "+GeneralUtility.getRandomName(), password, userType);
		Assert.assertEquals(actualPosition, expectedPosition,"user is not created");
		
	}

	@Test(groups={"smoke test","regression"},retryAnalyzer=com.sevenmart.listeners.RetryAnalyzer.class)
	public void deleteAdminUser() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		String expectedUserName = "tomika.sanford";
		boolean value=adminuserpage.deleteUser(expectedUserName);
		Assert.assertEquals(value, true,"user is not deleted");
		
	}

}
