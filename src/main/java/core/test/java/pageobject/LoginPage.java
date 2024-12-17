package core.test.java.pageobject;

import core.test.java.core.element.Element;
import core.test.java.dataobject.Account;
import org.openqa.selenium.By;

public class LoginPage extends HomePage {
	private Element input_username = new Element("//input[@name=\"email\"]");
	private Element input_password = new Element("//input[@name=\"password\"]");
	private Element btn_login = new Element("//button[@value='Login']");

	public void loginWithUser(String user) {
		Account account = Account.loadFromJSON(user);
		input_username.sendKeys(account.getUserName());
		input_password.sendKeys(account.getPassword());
		input_username.waitForVisibility(5);
		btn_login.click();
	}

}
