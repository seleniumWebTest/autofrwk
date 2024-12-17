package core.test.java.core.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import core.test.java.core.log.ReportPortalLogHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;


import org.openqa.selenium.WebElement;

public class DriverWrapper {

	protected static Logger logger = Logger.getLogger(DriverWrapper.class);

	public static WebDriver getSeleniumWebDriver() {
		return DriverManager.getDriver().getSeleniumWebdriver();
	}

	public static void acceptAlert() {
		getSeleniumWebDriver().switchTo().alert().accept();
	}


	/**
	 * Get current page url
	 * 
	 * @return
	 */
	public static String getCurrentUrl() {
		logger.info("Get Current Url ");
		String strURL = null;
		try {
			strURL = getSeleniumWebDriver().getCurrentUrl();
		} catch (Exception e) {
			logger.error("Error: Cannot get current URL due to '" + e.getMessage() + "'");
		}
		return strURL;
	}

	/**
	 * Get Page title
	 * 
	 * @return
	 */
	public static String getTitle() {
		String strTitle = null;
		try {
			strTitle = getSeleniumWebDriver().getTitle();
		} catch (Exception e) {
			logger.info("Error: Cannot get title due to '" + e.getMessage() + "'");
		}
		return strTitle;
	}

	/**
	 * Get page source
	 * 
	 * @return
	 */
	public static String getPageSource() {
		String strPageSource = null;
		try {
			strPageSource = getSeleniumWebDriver().getPageSource();
		} catch (Exception e) {
			logger.error("Error: Cannot get page source due to '" + e.getMessage() + "'");
		}
		return strPageSource;
	}

	/**
	 * Close current page
	 */
	public static void close() {
		logger.info("Close current page");
		try {
			getSeleniumWebDriver().close();
		} catch (Exception e) {
			logger.error("Error: Cannot find element due to '" + e.getMessage() + "'");
		}
	}

	/**
	 * Quit browser
	 */
	public static void quit() {
		logger.info("Quit browser");
		try {
			getSeleniumWebDriver().quit();
		} catch (Exception e) {
			logger.error("Error: Cannot quit due to '" + e.getMessage() + "'");
		}
	}

	/**
	 * Get windows handles
	 * 
	 * @return
	 */
	public static ArrayList<String> getWindowHandles() {
		try {
			return new ArrayList<String>(getSeleniumWebDriver().getWindowHandles());
		} catch (Exception e) {
			logger.error("Error: Cannot get window handles due to '" + e.getMessage() + "'");
			return null;
		}
	}

	/**
	 * Get windows handle
	 * 
	 * @return
	 */
	public static String getWindowHandle() {
		try {
			return getSeleniumWebDriver().getWindowHandle();
		} catch (Exception e) {
			logger.error("Error: Cannot get window handle due to '" + e.getMessage() + "'");
			return null;
		}
	}

	/**
	 * Switch handling
	 * 
	 * @return
	 */
	public TargetLocator switchTo() {
		try {
			return getSeleniumWebDriver().switchTo();
		} catch (Exception e) {
			logger.error("Error: Cannot switch due to '" + e.getMessage() + "'");
			return null;
		}
	}

	/**
	 * Switch to window
	 * 
	 * @param windowHandle
	 */
	public static void switchTo(String windowHandle) {
		getSeleniumWebDriver().switchTo().window(windowHandle);
	}

	/**
	 * @description: Switch window by title.
	 * @param title
	 */
	public static void switchWindow(String title) {

		String currentWindow = getSeleniumWebDriver().getWindowHandle();
		Set<String> availableWindows = getSeleniumWebDriver().getWindowHandles();

		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (getSeleniumWebDriver().switchTo().window(windowId).getTitle().equals(title)) {
				} else {
					getSeleniumWebDriver().switchTo().window(currentWindow);
				}
			}
		}
	}

	/**
	 * @description: Switch iFrame by id name
	 * @param iframeName
	 */
	public static void switchFrame(String iframeName) {

		if (iframeName != "default") {
			getSeleniumWebDriver().switchTo().frame(iframeName);
		} else {
			getSeleniumWebDriver().switchTo().defaultContent();
		}
	}

	/**
	 * @description: Switch iFrame by index
	 * @param index
	 */
	public static void switchFrame(int index) {
		getSeleniumWebDriver().switchTo().frame(index);
	}

	/**
	 * @description: Switch iFrame by iframe
	 */
	public static void switchParentFrame() {
		getSeleniumWebDriver().switchTo().parentFrame();
	}

	/**
	 * manage() method
	 * 
	 * @return
	 */
	public static Options manage() {
		try {
			return getSeleniumWebDriver().manage();
		} catch (Exception e) {
			logger.error("Error: Cannot navigate due to '" + e.getMessage() + "'");
			return null;
		}
	}

	/**
	 * Maximize browser windows
	 */
	public static void maximize() {
		try {
			getSeleniumWebDriver().manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: Cannot maximize due to '" + e.getMessage() + "'");
		}
	}

	/**
	 * Navigate to URL
	 * 
	 * @param url
	 */
	public static void navigateToURL(String url) {
		try {
			getSeleniumWebDriver().navigate().to(url);
		} catch (Exception e) {
			logger.error("Error: Cannot navigate to " + url + "due to '" + e.getMessage() + "'");
		}
	}

	/**
	 * @description: Switch window
	 */
	public static void closeWindow() {
		getSeleniumWebDriver().close();
	}

	public static void refresh() {
		try {
			getSeleniumWebDriver().navigate().refresh();
		} catch (Exception e) {
			logger.error("Error: Cannot refresh page due to '" + e.getMessage() + "'");
		}
	}

	public static WebElement findElement(By locator) {
		return getSeleniumWebDriver().findElement(locator);
	}

	public static void captureScreen(String path, String imgExt, String filename) throws IOException {

		TakesScreenshot scrShot = ((TakesScreenshot) getSeleniumWebDriver());
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(path + "//" + filename +".jpg");
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	public static void saveScreenShot()
	{
		if (DriverWrapper.getSeleniumWebDriver() instanceof TakesScreenshot) {
			String screenshot = ((TakesScreenshot) DriverWrapper.getSeleniumWebDriver()).getScreenshotAs(OutputType.BASE64);
			ReportPortalLogHelper.logBase64(screenshot, "Invalid page");
		}
	}
}
