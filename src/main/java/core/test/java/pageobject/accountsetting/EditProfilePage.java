package core.test.java.pageobject.accountsetting;

import core.test.java.core.element.Element;
import org.openqa.selenium.By;



public class EditProfilePage extends AccountSettingPage {
	private Element inputUsername = new Element(By.id("user_username"));
	private Element btnUpdateAccount = new Element("//input[@value='Update account']");
	private Element inputFirstName = new Element(By.id("user_first_name"));
	private Element inputLastName = new Element(By.id("user_last_name"));

	public void updateUserName(String username) {
		inputUsername.enter(username);
		btnUpdateAccount.click();
	}

	public String getProfileFullName() {
		return inputFirstName.getAttribute("value") + " " + inputLastName.getAttribute("value");
	}
}
