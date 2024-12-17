package core.test.java.test;
import java.util.ArrayList;

import core.test.java.core.log.Logger;
import core.test.java.pageobject.HomePage;
import core.test.java.pageobject.LoginPage;
import core.test.java.pageobject.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Scenario3 extends BaseTest{
	
	@Test 
	public void VerifyListOfLikedPhotos() {
		HomePage homePage = new HomePage();
		Logger.info("1. Go to Login Page");
		LoginPage loginPage = homePage.goToLoginPage();
		
		Logger.info("2. Login with Tien's account");
		loginPage.loginWithUser("Tien");
		
		Logger.info("3. Like 3 randoms photos");
		int number_of_photo = 3;
		ArrayList<String> list_liked_photo_id = homePage.likeRandomPhotos(number_of_photo);
		
		Logger.info("4. Go to Likes Page");
		ProfilePage profilePage = homePage.gotoLikesPage();
		
		Logger.info("5. Verify if the number of like is correct");
		Assert.assertEquals(profilePage.getLikeNumber(), number_of_photo);
		
		Logger.info("6. Verify if 3 photos displayed in like section");
		Assert.assertTrue(profilePage.verifyLikeSection(list_liked_photo_id));
		
		Logger.info("7. Unlike photos with API");
		//Need to improve: Find a better way to generate auth_token
		//String auth_token = BasePage.getAuthToken();
		//UnlikePhotosWithAPI(auth_token);		
	}

	/*private void UnlikePhotosWithAPI(String authToken) {
		String currentUser = UnSplashUser.getCurrentUser(authToken);
		ArrayList<String> list_liked_photo= UnSplashUser.getListUserLikes(authToken, currentUser);
		UnSplashPhoto.unlikePhotos(authToken, list_liked_photo);	
	}*/
}
