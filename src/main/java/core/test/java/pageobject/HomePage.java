package core.test.java.pageobject;
import java.util.ArrayList;

import core.test.java.core.element.Element;
import core.test.java.core.enumobject.EnumObject.ByType;
import core.test.java.core.utilities.RandomUtilities;
import core.test.java.dataobject.datastorage.DataStorage;
import core.test.java.projectconst.Constant;
import org.openqa.selenium.Keys;




public class HomePage extends BasePage{

	public Element buttonLogin = new Element("//a[.='Log in']");
	private Element photographerIcon = new Element("//div[@class='ReactModalPortal']//img[contains(@alt,'Go to')]");
	private Element buttonDownload = new Element("//div[@class='ReactModalPortal']//a[.='Download']");
	private Element buttonFollow = new Element("//button[contains(@title,'Follow')]");
	private String imageGridPath = "//div[@data-test= 'masonry-grid-count-three']";
	private String imageXpathFormat = Element.gerateLocatorFormat(ByType.Xpath, imageGridPath + "/div[%s]/child::*[%s]");
	private Element imageGrid = new Element(imageGridPath);
	private Element thanksMessage = new Element("//h3[contains(.,'Say thanks')]");
	private Element photoPopup = new Element("//div[contains(@class,'ReactModal__Content')]");
	private Element btnLikePhoto = new Element("//div[@class='ReactModalPortal']//button[@title='Like photo']");
	private Element btnAddToCollection = new Element("//div[@class='ReactModalPortal']//button[@title='Add to collection']");
	private Element btnCreateNewCollection = new Element("//button[.='Create a new collection']");
	private Element inputCollectionTitle = new Element("//input[@name='title']");
	private Element checkboxPrivacy = new Element("//input[@name='privacy']");
	private Element btnSubmitNewCollection = new Element("//button[.='Create collection']");
	private String collectionItemXpathFormat = Element.gerateLocatorFormat(ByType.Xpath, "//h4[.='%s']");
	private String photoNumberLabelXpathFormat = Element.gerateLocatorFormat(ByType.Xpath, "//h4[.='%s']/preceding-sibling::p");
	private Element btnCloseCollectionPopup = new Element("//h3[.='Add to Collection']/ancestor::div[@class='ReactModalPortal']//button[.='']");
	private Element photographerLink = new Element("//span[contains(., 'Photo by')]/a[1]");

	private ArrayList<String> listLikedPhotoId;
	private ArrayList<String> choosenPhotos;
	
	public LoginPage goToLoginPage()
	{
//		buttonLogin.click();
		return new LoginPage();
	}
	
	public void openFirstPhoto() {
		clickOnPhoto(1, 1);
	}
	
	public void clickOnPhoto(int col, int row) {
		String image_xpath = String.format(imageXpathFormat, col, row);
		Element image = new Element(image_xpath);
		image.click();
	}

	public String getFollowButtonState() {
		return buttonFollow.getAttribute("title");
	}

	public void followPhotographer() {
		photographerIcon.moveMouse();
		if (buttonFollow.getAttribute("title").equals("Follow")) {
			buttonFollow.click();
		}		
	}
	
	public void unfollowPhotoGrapher() {
		if (buttonFollow.getAttribute("title").equals("Following")) {
			buttonFollow.click();
		}		
	}

	public Object getFollowbuttonBackgroundColor() {
		return buttonFollow.getCssValue("background-color");
	}

	public void openARandomPhoto() {
		String random_photo = chooseARandomPhoto();
		clickOnPhoto (Integer.parseInt(random_photo.split("-")[0]), Integer.parseInt(random_photo.split("-")[1]));				
	}
	
	public void downloadPicture() {
		buttonDownload.click();
	}
	
	public String getPhotographerName()
	{
		return photographerLink.getText().trim().toLowerCase().replace(" ", "-");
	}
	
	public String getPhotoID() {
		//Eg: https://unsplash.com/photos/Z1yEXp8KTko/download?force=true
		String result = buttonDownload.getAttribute("href").split("/")[4];
		return result;
	}
	
	public boolean isImageDownloaded()
	{
		boolean result = thanksMessage.isDisplayed();
		return result;
	}
	

	public ArrayList<String> likeRandomPhotos(int numberOfPhotos) {
		//Another solution: Can use API to choose Random photo and like photos
		listLikedPhotoId = new ArrayList<String>();
		choosenPhotos = new ArrayList<String>();
		for (int i = 0; i < numberOfPhotos; i++)
		{
			String chosenPhoto = chooseARandomPhoto();
			while (choosenPhotos.contains(chosenPhoto))
			{
				chosenPhoto = chooseARandomPhoto();
			}
			
			choosenPhotos.add(chosenPhoto);
			likePhoto(chosenPhoto);
		}
		
		return listLikedPhotoId;
	}

	private void likePhoto(String chosenPhoto) {
		int column = Integer.parseInt(chosenPhoto.split("-")[0]);
		int row = Integer.parseInt(chosenPhoto.split("-")[1]);
		clickOnPhoto(column, row);
		String currentPhotoID = getPhotoID();
		listLikedPhotoId.add(currentPhotoID);
		DataStorage.addLikedPhoto(currentPhotoID);
		btnLikePhoto.click();
		photoPopup.sendKeys(Keys.ESCAPE);
	}

	private String chooseARandomPhoto() {
		int total_column = imageGrid.findElements(imageGridPath + "/div").size();
		int random_colum = RandomUtilities.randomInt(1, total_column);
		int total_row = imageGrid.findElements(imageGridPath+ "/div[" + random_colum + "]/child::*").size();
		int random_row = RandomUtilities.randomInt(1, total_row);
		return random_colum + "-" + random_row;
	}

	public void createNewPrivateCollection(String newCollectionName) {
		//Another solution: Can use API to create a new collection
		openFirstPhoto();	
		btnAddToCollection.click();
		btnCreateNewCollection.click();
		inputCollectionTitle.sendKeys(newCollectionName);
		checkboxPrivacy.click();
		btnSubmitNewCollection.click();
		
		//Remove currently photo from the collection
		//Need to wait for the text changed for stable script
		Element photoNumberLabel = new Element(String.format(photoNumberLabelXpathFormat, newCollectionName));
		String photoNumber = photoNumberLabel.getText();
		Element new_collection_item = new Element(String.format(collectionItemXpathFormat, newCollectionName));
		new_collection_item.click();
		waitForNumberOfPhotoChanged(photoNumber, newCollectionName);
		btnCloseCollectionPopup.click();
		btnCloseCollectionPopup.waitForInvisibility(Constant.SHORT_TIMEOUT);
		photoPopup.sendKeys(Keys.ESCAPE);
		photoPopup.waitForInvisibility(Constant.SHORT_TIMEOUT);
	}
    

	private void waitForNumberOfPhotoChanged(String photoNumber, String newCollectionName) {
		Element photoNumberLabel = new Element(String.format(photoNumberLabelXpathFormat, newCollectionName));
		while(true) {
			String currentPhotoNumber = photoNumberLabel.getText();
			if (!currentPhotoNumber.equals(photoNumber))
			{
				break;
			}
		}
	}

	public void addRandomPhotosToCollection(int number_of_photo, String collectionName) {
		//Another solution: can use API to choose random photos and add them to collection
		choosenPhotos = new ArrayList<String>();
		for (int i = 0; i < number_of_photo; i++)
		{
			String chosenPhoto = chooseARandomPhoto();
			while (choosenPhotos.contains(chosenPhoto))
			{
				chosenPhoto = chooseARandomPhoto();
			}
			
			choosenPhotos.add(chosenPhoto);
			addOrRemovePhotoOfCollection(chosenPhoto, collectionName);
		}
	}

	private void addOrRemovePhotoOfCollection(String chosenPhoto, String collectionName) {
		clickOnPhoto (Integer.parseInt(chosenPhoto.split("-")[0]), Integer.parseInt(chosenPhoto.split("-")[1]));				
		btnAddToCollection.click();	
		//Remove currently photo from the collection
		//Need to wait for the text changed for stable script
		Element photoNumberLabel = new Element(String.format(photoNumberLabelXpathFormat, collectionName));
		String photoNumber = photoNumberLabel.getText();
		Element new_collection_item = new Element(String.format(collectionItemXpathFormat, collectionName));
		new_collection_item.click();
		waitForNumberOfPhotoChanged(photoNumber, collectionName);
		btnCloseCollectionPopup.click();
		btnCloseCollectionPopup.waitForInvisibility(Constant.SHORT_TIMEOUT);
		photoPopup.sendKeys(Keys.ESCAPE);
		photoPopup.waitForInvisibility(Constant.SHORT_TIMEOUT);
	}

	public void removeAPhotoFromCollection(String collectionName) {
		//Another solution: can use API to remove a photo from the collection
		int random_photo = RandomUtilities.randomInt(0, choosenPhotos.size()-1);	
		addOrRemovePhotoOfCollection(choosenPhotos.get(random_photo), collectionName);
		choosenPhotos.remove(choosenPhotos.get(random_photo));
	}
}
