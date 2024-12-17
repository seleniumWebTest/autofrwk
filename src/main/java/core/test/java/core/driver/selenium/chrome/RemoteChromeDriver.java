package core.test.java.core.driver.selenium.chrome;

import core.test.java.core.driver.BaseDriver;
import core.test.java.core.driver.DriverProperty;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


public class RemoteChromeDriver extends BaseDriver {

	public RemoteChromeDriver(DriverProperty property) {
		super(property);
	}

	@Override
	public void createWebDriver() {
		ChromeOptions ops = new ChromeOptions();

		ops.addArguments(property.getArguments());
		ops.merge(property.getCapabilities());

		webDriver = new RemoteWebDriver(property.getRemoteUrl(), ops);
	}
}
