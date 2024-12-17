package core.test.java.core.driver.selenium.chrome;

import core.test.java.core.driver.BaseDriver;
import core.test.java.core.driver.DriverProperty;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalChromeDriver extends BaseDriver {

	public LocalChromeDriver(DriverProperty property) {
		super(property);
	}


	@Override
	public void createWebDriver() {
		String driverVerion = (String) property.getCapabilities().getCapability("driverVersion");
		WebDriverManager.chromedriver().browserVersion(driverVerion).setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments(property.getArguments());
		ops.merge(property.getCapabilities());
		webDriver = new ChromeDriver(ops);
	}
}
