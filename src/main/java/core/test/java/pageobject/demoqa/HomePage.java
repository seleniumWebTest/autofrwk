package core.test.java.pageobject.demoqa;
import core.test.java.core.element.Element;
import core.test.java.pageobject.LoginPage;
import org.openqa.selenium.By;



public class HomePage {
	private Element loginButton = new Element(By.id("login"));
	private Element usernameLabel = new Element(By.id("userName-value"));
	
	public LoginPage goToLoginPage() {
		loginButton.click();
        return null;
    }
	
	public String getUsernameLabel() {
		return usernameLabel.getText();
	}
	
	
}
