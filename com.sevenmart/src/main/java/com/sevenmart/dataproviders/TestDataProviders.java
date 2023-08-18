package com.sevenmart.dataproviders;

import org.testng.annotations.DataProvider;

import com.sevenmart.utilities.ExcelUtility;

public class TestDataProviders {
	ExcelUtility excelutility = new ExcelUtility();

	@DataProvider(name = "invalidcredentials")
	public Object[][] invalid_Credentials() {
		return new Object[][] { { "admina", "adm","Alert!" }, { "case", "tv","Alert!" } };
	}

	@DataProvider(name = "validCredentials")
	public Object[][] valid_Credentials() {
		return new Object[][] { { "Navya", "ifice", }, { "case", "tv" } };
	}

	@DataProvider(name = "deliveryBoynames")
	public Object[][] deliveryBoyNames() {
		return new Object[][] { { "Navya", "navya@gmail.com", "12345", "house", "navya", "navya123", "1" },
				{ "dhanya", "dhanya@gmail.com", "123456", "house", "dhanyas", "dhanya123", "1" } };
	}

	@DataProvider(name = "deliveryBoyProfileDetails")
	public Object[][] deliveryBoyProfileDetails() {
		excelutility.SetExcelFile("manageDeliveryBoyData", "deliveryBoyProfileDetails");
		Object data[][] = excelutility.getMultiDimensionalData(3, 6);
		return data;
	}
	@DataProvider(name = "adminUserDetails")
	public Object[][] adminUserDetails() {
		excelutility.SetExcelFile("adminProfileData", "adminProfileDetails");
		Object data[][] = excelutility.getMultiDimensionalData(3, 4);
		return data;
	}
	@DataProvider(name = "manageContentDetails")
	public Object[][] amanageContentDetails() {
		excelutility.SetExcelFile("manageContentData", "manageContentDetails");
		Object data[][] = excelutility.getMultiDimensionalData(3, 3);
		return data;
	}
	@DataProvider(name = "pushNotificationDetails")
	public Object[][] pushNotificationDetails() {
		excelutility.SetExcelFile("pushNotificationData", "pushMsg");
		Object data[][] = excelutility.getMultiDimensionalData(2, 3);
		return data;
	}
	

}
