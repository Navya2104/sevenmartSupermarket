package com.sevenmart.tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.AdminUserPage;
import com.sevenmart.pages.DeliveryBoyPage;
import com.sevenmart.pages.LoginPage;

public class AdminUserTest extends Base {
	LoginPage loginpage;
	AdminUserPage adminuserpage;

	@Test(priority = 1)
	public void user() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		String expectedUserName = "mike1234";
		String actualUserName = adminuserpage.verifyUser(expectedUserName);
		Assert.assertEquals(actualUserName, expectedUserName);
	}

	@Test(dataProvider = "adminUserDetails", dataProviderClass = TestDataProviders.class)
	public void createAdminUser(String userName, String password, String userType) {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		adminuserpage.createNewUser(userName, password, userType);
		// adminuserpage.activateUser(userName);
	}

	@Test()
	public void deleteAdminUser() {
		loginpage = new LoginPage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		String expectedUserName = "mike1234";
		adminuserpage.deleteUser(expectedUserName);
	}

}
