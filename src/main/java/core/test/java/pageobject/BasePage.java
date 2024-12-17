package core.test.java.pageobject;


import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.element.Element;
import core.test.java.core.enumobject.EnumObject.ByType;
import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.helper.apihelper.UnSplashTokenGeneration;

public class BasePage {
	private Element personnalMenuButton = new Element("//div[@id = 'popover-avatar-loggedin-menu-desktop']//button");
	private String personalSubMenuFormat = Element.gerateLocatorFormat( ByType.Xpath, "//div[@role='menu']//a[.='%s']");
	
	public ProfilePage goToProfilePage()
	{
		select_personal_menu_item("View profile");
		return new ProfilePage();
	}
	
	public void goToHomePage() {
		DriverWrapper.navigateToURL(EnvironmentInfo.getApplicationURL());
	}
	private void select_personal_menu_item(String menu_item) {
		personnalMenuButton.click();
		String personal_sub_menu_xpath = String.format(personalSubMenuFormat, menu_item);
		Element personal_sub_menu = new Element(personal_sub_menu_xpath);
		personal_sub_menu.click();	
	}
	
	public ProfilePage gotoLikesPage()
	{
		ProfilePage profilePage= goToProfilePage();
		DriverWrapper.navigateToURL(DriverWrapper.getCurrentUrl() + "/likes");
		return profilePage;
	}
	

	public CollectionPage gotoCollectionPage(String collection_id) {
		DriverWrapper.navigateToURL(EnvironmentInfo.getApplicationURL() + "collections/" + collection_id);
		return new CollectionPage();
	}
	public OAuthApplicationPage gotoOAuthApplicationPage() {
		//navigateTo("https://unsplash.com/oauth/applications/221999");
		DriverWrapper.navigateToURL("https://unsplash.com/oauth/authorize?client_id=qBfG-rpsyVghf03eBLWtcxe2u2nxcTpYEPD7_K0q9cA&amp;redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&amp;response_type=code&amp;scope=public+read_user+write_user+read_photos+write_photos+write_likes+write_followers+read_collections+write_collections");
		return new OAuthApplicationPage();
	}
	
	public static String getAuthToken()
	{
		DriverWrapper.navigateToURL("https://unsplash.com/oauth/authorize?client_id=qBfG-rpsyVghf03eBLWtcxe2u2nxcTpYEPD7_K0q9cA&amp;redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&amp;response_type=code&amp;scope=public+read_user+write_user+read_photos+write_photos+write_likes+write_followers+read_collections+write_collections");
		OAuthApplicationPage oauthPage = new OAuthApplicationPage();
		return UnSplashTokenGeneration.getAuthToken(oauthPage.getAuthorizationCode());
	}
}
