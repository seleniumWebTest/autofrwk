package core.test.java.pageobject;


import core.test.java.core.element.Element;

public class CollectionPage {
	private Element numberOfPhoto = new Element("//div/span[contains(., 'photo')]");
	public int getNumberOfPhoto() {
		return Integer.parseInt(numberOfPhoto.getText().split(" ")[0]);
	}
}
