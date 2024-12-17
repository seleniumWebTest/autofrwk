package core.test.java.pageobject;
import core.test.java.core.element.Element;
import core.test.java.core.enumobject.EnumObject.*;
import core.test.java.pageobject.accountsetting.EditProfilePage;


import java.util.ArrayList;



public class ProfilePage extends BasePage{
	private Element editProfile = new Element("//a[.='Edit profile']");
	private String profileFullNameFormat = Element.gerateLocatorFormat(ByType.Xpath, "//div[contains(.,'%s')]") ;
	private Element likeNumber = new Element("//span[.='Likes']/following-sibling::span");
	private String likeSectionGridPath ="//div[@data-test='users-likes-route']";
	private Element likeSectionGrid = new Element(likeSectionGridPath);
	private String likeSectionImageGridXpath = "//figure[@itemprop = 'image']";

	public EditProfilePage gotoEditProfilePage()
	{		
		editProfile.click();
		return new EditProfilePage();
	}
	
	public int getLikeNumber() {
		return Integer.parseInt(likeNumber.getText().strip());
	}

	public boolean verifyLikeSection(ArrayList<String> list_liked_photo_id) {
		boolean result = false;
		int list_image_element = likeSectionGrid.findElements(likeSectionImageGridXpath ).size();
		if (list_image_element == list_liked_photo_id.size()) {
			for (int i = 0; i < list_liked_photo_id.size(); i++) {
				String specific_like_section_image_xpath_format = likeSectionGridPath + "//a[contains(@href,'"+ list_liked_photo_id.get(i)+"')]";
				Element img_element = new Element(specific_like_section_image_xpath_format);
				if (!img_element.isExisted()){
					break;
				}
			}		
			
			result = true;
		}
		
		return result;
	}

	public boolean isFullnameDislayedCorrectly(String currentAccountFullName) {
		Element fullNameElement = new Element(String.format(profileFullNameFormat, currentAccountFullName));
		return fullNameElement.isDisplayed();
	}
}
