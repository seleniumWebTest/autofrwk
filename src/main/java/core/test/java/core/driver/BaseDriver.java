package core.test.java.core.driver;

import org.openqa.selenium.WebDriver;

public abstract class BaseDriver {
	protected DriverProperty property;
	protected WebDriver webDriver;

	public BaseDriver(DriverProperty property) {
		this.property = property;
	}

	public abstract void createWebDriver();

	public  WebDriver getSeleniumWebdriver() {
		return this.webDriver;

	}

}