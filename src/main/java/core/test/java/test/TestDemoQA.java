package core.test.java.test;

import core.test.java.core.log.Logger;
import core.test.java.pageobject.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;



import pageobject.demoqa.LoginPage;

public class TestDemoQA extends BaseTest{
	@Test
	public void VerifyLoginSuccessfully()
	{
		String username = "anhtien123";
		String password = "Abcd1234@";
		Logger.info("1. Go to Login Page");
		HomePage homePage = new HomePage();
		homePage.goToLoginPage();
		//Assert.assertTrue(false,"abcd");
		Logger.info("2. Log in to the page");
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);
		
		Logger.info("3. Verify if the username label is displayed correctly");
//		Assert.assertEquals(username, homePage.get, "The user label is not displayed correctly");
	}
}
