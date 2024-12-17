package core.test.java.test;
import core.test.java.core.log.Logger;
import core.test.java.core.utilities.FileUtilities;
import core.test.java.pageobject.HomePage;
import core.test.java.pageobject.LoginPage;
import core.test.java.projectconst.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Scenario5 extends BaseTest{
	
	@Test 
	public void VerifyDownloadImageSuccessfully() {	
		HomePage homePage = new HomePage();
		Logger.info("1. Go to Login Page");
		LoginPage loginPage = homePage.goToLoginPage();
		
		Logger.info("2. Login with Tien's account");
		loginPage.loginWithUser("Tien");
		
		Logger.info("3. Open a random photo");
		homePage.openARandomPhoto();
		
		Logger.info("4. Download picture");
		homePage.downloadPicture();
		
		Logger.info("5. Verify if image is downloadable");
		Assert.assertTrue(homePage.isImageDownloaded());
		
		
		Logger.info("6. Verify if image is downloaded correctly");
		String image_name = String.format(Constant.IMAGE_NAME_FORMAT, homePage.getPhotographerName(), homePage.getPhotoID());
		String file_path = Constant.DOWNLOAD_FOLDER + "\\" + image_name;
		Assert.assertEquals(FileUtilities.doesFileExist(file_path, Constant.IMAGE_DOWNLOAD_TIMEOUT), true);
		
		Logger.info("7. Delete downloaded image");
		FileUtilities.deleteFiles(file_path);
	}		
}
