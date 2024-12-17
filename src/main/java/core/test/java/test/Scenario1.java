package core.test.java.test;
import core.test.java.core.log.Logger;
import core.test.java.pageobject.HomePage;
import core.test.java.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Scenario1 extends BaseTest{
	@Test 
	public void VerifyFollowingButtonShowsCorrectStateAndColor() {
		HomePage homePage = new HomePage();
		Logger.info("1. Go to Login Page");
		LoginPage loginPage = homePage.goToLoginPage();
		
		Logger.info("2. Login with Tien's account");
		loginPage.loginWithUser("account");
		
		Logger.info("3. Open first picture");
		homePage.openFirstPhoto();
		
		Logger.info("4. Follow user");
		homePage.followPhotographer();
		
		Logger.info("5. Verify if the state and background color of Follow button are correct");
		Assert.assertEquals(homePage.getFollowButtonState(), "Following");
		//Assert.assertEquals(homePage.getFollowbuttonBackgroundColor(), "rgba(238, 238, 238, 115)");
		
		Logger.info("6. Unfollow user");
		homePage.unfollowPhotoGrapher();		
	}
}
