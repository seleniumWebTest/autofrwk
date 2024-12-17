package core.test.java.test;

import core.test.java.core.driver.DriverManager;
import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.report.TestListener;
import core.test.java.core.testprofile.TestProfileProperties;
import core.test.java.dataobject.EnvironmentInfo;
import core.test.java.dataobject.datastorage.DataStorage;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;


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
	public void beforeMethod(@Optional("chrome.remote") String browser, @Optional("web_app") String application) {
		DriverManager.initBrowser(browser);
		DriverWrapper.maximize();
		DriverWrapper.navigateToURL(EnvironmentInfo.getApplicationURL());
		Allure.addAttachment("go to url: ",EnvironmentInfo.getApplicationURL());

	}

	@AfterMethod
	public void afterMethod() {
		final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		Allure.addAttachment(Arrays.toString(screenshot),"the result");
		DriverWrapper.quit();
	}



	@AfterSuite
	public void afterSuite()
	{
		DataStorage.cleanTestData();
	}
}
