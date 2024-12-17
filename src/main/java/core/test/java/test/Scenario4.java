package core.test.java.test;
import core.test.java.core.log.Logger;
import core.test.java.core.utilities.RandomUtilities;
import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.dataobject.datastorage.DataStorage;
import core.test.java.helper.apihelper.UnSplashUser;
import core.test.java.pageobject.CollectionPage;
import core.test.java.pageobject.HomePage;
import core.test.java.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Scenario4 extends BaseTest{
	
	@Test 
	public void VerifyUpdateUserUrlInProfilePage() {
		HomePage homePage = new HomePage();
		Logger.info("1. Go to Login Page");
		LoginPage loginPage = homePage.goToLoginPage();
		
		Logger.info("2. Login with Tien's account");
		loginPage.loginWithUser("Tien");
		
		Logger.info("3. Create a new private collection");
		String new_collectionName = RandomUtilities.generateRandomString(5);
		homePage.createNewPrivateCollection(new_collectionName);
		String collection_id = UnSplashUser.getCollectionIdByName(new_collectionName, EnvironmentInfo.getAccessToken());
		DataStorage.addCreatedCollection(collection_id);
		
		Logger.info("3. Add 2 random photo to collection");
		int number_of_photo = 2;
		homePage.addRandomPhotosToCollection(number_of_photo, new_collectionName);
		
		Logger.info("4. Remove a photo from collect");
		homePage.removeAPhotoFromCollection(new_collectionName);
		
		Logger.info("5. Go to new collection page");
		CollectionPage collectionPage = homePage.gotoCollectionPage(collection_id);
		
		Logger.info("6. Verify if there is only 1 photo in this collection");
		Assert.assertEquals(collectionPage.getNumberOfPhoto(), number_of_photo - 1);
		
		//Logger.info("7. Delete the created collection with API");
		//UnSplashCollection.deleteCollection(auth_token, collection_id);
	}	
}
