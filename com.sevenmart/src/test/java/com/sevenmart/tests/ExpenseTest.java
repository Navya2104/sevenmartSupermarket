package com.sevenmart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.pages.ExpensePage;
import com.sevenmart.pages.LoginPage;
import com.sevenmart.utilities.ExcelUtility;
import com.sevenmart.utilities.GeneralUtility;

public class ExpenseTest extends Base{
	
	LoginPage loginpage;
	ExpensePage expensepage;
	ExcelUtility excelutility=new ExcelUtility();
	
	@Test(groups={"regression"})
	public void add_VerifyExpenseCategory()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		expensepage= new ExpensePage(driver);
		String expenseName=GeneralUtility.getRandomName();
		boolean actual=expensepage.createExpenseCategory(expenseName);
		Assert.assertEquals(actual, true,"expense not found");
	}
	@Test
	public void verifyExpenseCategory()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		expensepage= new ExpensePage(driver);
		excelutility.SetExcelFile("expenseData", "verifyExpenseDetails");
		String expenseName=excelutility.getCellData(0, 0);
		boolean actual=expensepage.verifyExpenseCategory(expenseName);
		Assert.assertEquals(actual, true,"expense field not found");
		
	}
	@Test
	public void deleteAndVerifyExpenseCategory()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		expensepage= new ExpensePage(driver);
		excelutility.SetExcelFile("expenseData", "deleteExpenseDetails");
		String expenseName=excelutility.getCellData(0, 0);
		boolean actual=expensepage.deleteExpenseEntry(expenseName);
		Assert.assertEquals(actual, false,"field not deleted");
			
	}
	

}
