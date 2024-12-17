package core.test.java.test;

import core.test.java.core.driver.DriverManager;
import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.report.TestListener;
import core.test.java.core.testprofile.TestProfileProperties;
import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.dataobject.datastorage.DataStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


@Listeners( TestListener.class)
//@Listeners({ReportPortalListener.class})
public class BaseTest {
	
	@BeforeSuite
	public void BeforeSuite()
	{
		TestProfileProperties.loadRunConfigProps(System.getProperty("testEvn", "test"));
		DataStorage.initializeDataStorage();
	}
	
	@BeforeMethod
	@Parameters({"browser", "application"})
	public void beforeMethod(@Optional("chrome.local") String browser, @Optional("[Your application]") String application) {
		DriverManager.initBrowser(browser);
		DriverWrapper.maximize();
		DriverWrapper.navigateToURL(EnvironmentInfo.getApplicationURL());
	}

	@AfterMethod
	public void afterMethod() {
		DriverWrapper.quit();
	}
	
	@AfterSuite
	public void afterSuite()
	{
		DataStorage.cleanTestData();
	}
}
