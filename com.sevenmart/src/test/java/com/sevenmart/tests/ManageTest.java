package com.sevenmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.pages.ManagePages;

public class ManageTest extends Base{
	ManagePages managepage;
	LoginPage loginpage;
	
	@Test
	public void verifyTableFirstCell()
	{
		managepage=new ManagePages(driver);
		loginpage= new LoginPage(driver);
		loginpage.login();
		String expectedCell_Content="vdj";
		managepage.clickOnManageBox();
		String actualCell_Content=managepage.getRequiredCellContent(expectedCell_Content);
		Assert.assertEquals(actualCell_Content, expectedCell_Content);
	}
}
