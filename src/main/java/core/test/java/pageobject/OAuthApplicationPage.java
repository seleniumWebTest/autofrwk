package core.test.java.pageobject;


import core.test.java.core.element.Element;

public class OAuthApplicationPage {
	private Element code = new Element("//code");
	
	public String getAuthorizationCode() {
		return code.getText().strip();
	}
}
