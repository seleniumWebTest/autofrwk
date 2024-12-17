package core.test.java.test;
import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.log.Logger;
import core.test.java.core.utilities.RandomUtilities;
import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.pageobject.HomePage;
import core.test.java.pageobject.LoginPage;
import core.test.java.pageobject.ProfilePage;
import core.test.java.pageobject.accountsetting.EditProfilePage;
import core.test.java.projectconst.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Scenario2 extends BaseTest{
	
	@Test 
	public void VerifyUpdateUserUrlInProfilePage() {
		HomePage homePage = new HomePage();
		Logger.info("1. Go to Login Page");
		LoginPage loginPage = homePage.goToLoginPage();
		
		Logger.info("2. Login with Tien's account");
		loginPage.loginWithUser("Tien");
		
//		Logger.info("3. Go to Profile page");
//		ProfilePage profilePage = homePage.goToProfilePage();
//
//		Logger.info("4. Go to Edit Profile page");
//		EditProfilePage editProfilePage = profilePage.gotoEditProfilePage();
//		String currentAccountFullName = editProfilePage.getProfileFullName();
//
//		Logger.info("5. Update user name");
//		String new_user_name = "anhtien0613" + RandomUtilities.generateRandomString(5);
//		editProfilePage.updateUserName(new_user_name);
//
//		Logger.info("6.Navigate to new profile page");
//		DriverWrapper.navigateToURL(EnvironmentInfo.getApplicationURL() + "@" + new_user_name);
////
//		Logger.info("7. Verify if current page is profile page");
//		Assert.assertTrue(DriverWrapper.getTitle().contains(Constant.USER_PROFILE_PAGE_SUFFIX));
		
//		Logger.info("8. Verify if current full name is correct");
//		Assert.assertEquals(profilePage.isFullnameDislayedCorrectly(currentAccountFullName), true);
	}
		

}
