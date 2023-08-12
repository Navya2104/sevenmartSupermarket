package com.sevenmart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmart.base.Base;
import com.sevenmart.pages.ExpensePage;
import com.sevenmart.pages.LoginPage;

public class ExpenseTest extends Base{
	
	LoginPage loginpage;
	ExpensePage expensepage;
	
	@Test
	public void add_VerifyExpenseCategory()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		expensepage= new ExpensePage(driver);
		String expenseName=expensepage.createExpenseCategory();
		boolean actual=expensepage.verifyExpenseCategory(expenseName);
		Assert.assertEquals(actual, true,"element not found");
	}
	@Test
	public void deleteExpenseCategory()
	{
		loginpage=new LoginPage(driver);
		loginpage.login();
		expensepage= new ExpensePage(driver);
		String expenseName=expensepage.createExpenseCategory();
		expensepage.verifyExpenseCategory(expenseName);
		expensepage.deleteExpenseEntry(expenseName);
		
		
		
		
		
	}
	

}
