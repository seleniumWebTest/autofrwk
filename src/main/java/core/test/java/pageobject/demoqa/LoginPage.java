package pageobject.demoqa;

import core.test.java.core.element.Element;
import org.openqa.selenium.By;

public class LoginPage {
	private Element loginButton = new Element(By.id("login"));
	private Element usernameTextBox = new Element(By.id("userName"));
	private Element passwordTextBox = new Element(By.id("password"));
	
	public void login(String username, String password) {
		usernameTextBox.enter(username);
		passwordTextBox.enter(password);
		loginButton.click();
	}
	
}
