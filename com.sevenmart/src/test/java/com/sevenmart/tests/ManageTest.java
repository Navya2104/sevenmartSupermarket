package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.dataproviders.TestDataProviders;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.pages.ManagePages;
import com.sevenmart.utilities.ExcelUtility;
import com.sevenmart.utilities.GeneralUtility;

public class ManageTest extends Base{
	ManagePages managepage;
	LoginPage loginpage;
	ExcelUtility excelutility=new ExcelUtility();
	
	@Test(dataProvider = "manageContentDetails",dataProviderClass = TestDataProviders.class)
	public void addField_ContentPage(String title, String page,String path)
	{
		managepage=new ManagePages(driver);
		loginpage= new LoginPage(driver);
		loginpage.login();
		boolean actualValue=managepage.addCellContent(title, page+" "+GeneralUtility.getRandomName(), path);
		Assert.assertEquals(actualValue, true,"content not created");
	
	}
	@Test
	public void deleteAndVerifyField_ContentPage()
	{
		managepage=new ManagePages(driver);
		loginpage= new LoginPage(driver);
		loginpage.login();
		excelutility.SetExcelFile("manageContentData", "deleteContentDetails");
		String expectedContent =excelutility.getCellData(0, 0);
		boolean actualContent=managepage.deleteCellContent(expectedContent);
		Assert.assertEquals(actualContent, false,"Content field not deleted");
		
		
	}
}
