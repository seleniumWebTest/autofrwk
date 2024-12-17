package core.test.java.core.element;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.enumobject.EnumObject.ByType;
import core.test.java.core.log.Logger;
import core.test.java.projectconst.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Element implements WebElement {

	protected WebElement _webElement;
	protected Logger logger = new Logger();
	protected By by;
	private static String locatorDelimiter = "@@@@";

	// INSTRUCTORS //
	public Element() {
		_webElement = null;
	}

	public Element(By by) {
		this.by = by;
		this._webElement = findElement(by);
	}

	public Element(String locatorFormat) {
		if (locatorFormat.contains(locatorDelimiter)) {
			 ByType locatorType = converStringToByType(locatorFormat.split(locatorDelimiter)[0]);
			String locatorValue = locatorFormat.split(locatorDelimiter)[1];
			switch (locatorType) {
			case Id:
				by = By.id(locatorValue);
				break;
			case Name:
				by = By.name(locatorValue);
				break;
			case CssSelector:
				by = By.cssSelector(locatorValue);
				break;
			case LinkText:
				by = By.linkText(locatorValue);
				break;
			case PartialLinkText:
				by = By.partialLinkText(locatorValue);
				break;
			case TagName:
				by = By.tagName(locatorValue);
				break;
			case Xpath:
				by = By.xpath(locatorValue);
				break;
			}
		}
		else {
			by = By.xpath(locatorFormat);
		}
	}

	public Element(WebElement webElement) {
		this._webElement = webElement;
	}

	// METHODS //

	public By getLocator() {
		return by;
	}

	/**
	 * Get Element attribute.
	 */
	public String getAttribute(String name) {
		try {
			waitForVisibility(Constant.SHORT_TIMEOUT);
			return _webElement.getAttribute(name);
		} catch (Exception e) {
			logger.printMessage(e.getMessage());
			return null;
		}
	}

	/**
	 * Get Element text
	 */
	public String getText() {
		try {
			waitForVisibility(Constant.SHORT_TIMEOUT);
			return _webElement.getText();
		} catch (Exception e) {
			logger.printMessage(e.getMessage());
			return null;
		}
	}

	/**
	 * Check if Element is selected
	 */
	public boolean isSelected() {
		try {
			waitForVisibility(Constant.SHORT_TIMEOUT);
			return _webElement.isSelected();
		} catch (Exception e) {
			logger.printMessage(String.format("IsSelected: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
			return false;
		}
	}

	/**
	 * Check if Element is enabled
	 */
	public boolean isEnabled() {
		try {
			waitForVisibility(Constant.SHORT_TIMEOUT);
			return _webElement.isEnabled();
		} catch (Exception e) {
			logger.printMessage(String.format("IsEnabled: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
			return false;
		}
	}

	/**
	 * Check if Element is displayed
	 */
	public boolean isDisplayed() {
		return isDisplayed(Constant.SHORT_TIMEOUT);
	}

	/**
	 * Check if Element is displayed with timeout value.
	 */
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			waitForVisibility(timeOutInSeconds);
			return _webElement.isDisplayed();
		} catch (Exception e) {
			logger.printMessage(String.format("IsDisplayed: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
			return false;
		}
	}

	/**
	 * @return
	 */
	public boolean isExisted() {
		boolean result = false;
		try {
			if (DriverWrapper.getSeleniumWebDriver().findElements(this.getLocator()).size() != 0) {
				result = true;
			}
		} catch (Exception e) {
			logger.printMessage(String.format("isExisted: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
		}

		return result;
	}

	/**
	 * Return TRUE if element is invisible. Otherwise, return FALSE
	 * 
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean isInvisible(int timeOutInSeconds) {
		boolean result = false;
		try {
			waitForInvisibility(timeOutInSeconds);
			if (!isDisplayed(0)) {
				result = true;
			}
		} catch (Exception e) {
			logger.printMessage(String.format("IsDisplayed: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
		}

		return result;
	}

	/**
	 * Wait for Element clickable.
	 * 

	 * @param timeOutInSeconds
	 */
	public void waitForClickable(int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverWrapper.getSeleniumWebDriver(),Duration.ofSeconds(timeOutInSeconds));
			wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(this.by),
					ExpectedConditions.visibilityOfElementLocated(this.by)));
			_webElement = wait.until(ExpectedConditions.elementToBeClickable(this.by));
		} catch (Exception e) {
			logger.printMessage(String.format("waitForClickable: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	/**
	 * Wait for visibility
	 * 
	 * @param timeOutInSeconds
	 */
	public void waitForVisibility(int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverWrapper.getSeleniumWebDriver(), Duration.ofSeconds(timeOutInSeconds));
			_webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator()));
		} catch (Exception e) {
			logger.printMessage(String.format("waitForVisibility: Has error with control '%s': %s",
					getLocator().toString(), e.getMessage()));
		}
	}

	/**
	 * Wait for the invisibility of element.
	 * 
	 * @param timeOutInSeconds
	 */
	public void waitForInvisibility(int timeOutInSeconds) {
		logger.printMessage("Wait for element disappears");
		Wait<WebDriver> wait = new FluentWait<>(DriverWrapper.getSeleniumWebDriver())
				.withTimeout(Duration.ofSeconds(timeOutInSeconds)).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(this.by));
	}

	public <X> X getScreenshotAs(OutputType<X> target) {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getScreenshotAs(target);
	}

	public void submit() {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		_webElement.submit();
	}

	public void enter(CharSequence... keysToSend) {
		try {
			if (!getAttribute("value").equals(keysToSend.toString())) {
				_webElement.click();
				_webElement.clear();
				_webElement.sendKeys(keysToSend);
			}
		} catch (Exception e) {
			logger.printMessage(String.format("sendKeys: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
		}
	}

	public void clear() {
		try {
			waitForVisibility(Constant.SHORT_TIMEOUT);
			_webElement.clear();
		} catch (Exception e) {
			logger.printMessage(
					String.format("clear: Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
	}

	public String getTagName() {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getTagName();
	}

	public List<Element> findElements(By by, Integer timeOutInSeconds) {
		try {
			List<Element> listElement = new ArrayList<Element>();
			this._webElement = new WebDriverWait(DriverWrapper.getSeleniumWebDriver(), Duration.ofSeconds(timeOutInSeconds))
					.until(ExpectedConditions.visibilityOfElementLocated(getLocator()));

			List<WebElement> listWebElement = this._webElement.findElements(by);
			for (WebElement webElement : listWebElement) {
				Element element = new Element(webElement);
				element.by = by;
				listElement.add(new Element(webElement));
			}

			return listElement;
		} catch (Exception e) {
			logger.printMessage(String.format("findElements: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
			return null;
		}
	}

	public List<Element> findElements(String xPath) {
		return this.findElements(By.xpath(xPath), Constant.SHORT_TIMEOUT);
	}

	public Element findElement(By by) {
		List<Element> listElement = findElements(by, Constant.SHORT_TIMEOUT);
		if (listElement != null && listElement.size() >= 0) {
			return listElement.get(0);
		} else {
			return null;
		}
	}

	public Point getLocation() {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getLocation();
	}

	public Dimension getSize() {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getSize();
	}

	public Rectangle getRect() {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getRect();
	}

	public String getCssValue(String propertyName) {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		return _webElement.getCssValue(propertyName);
	}

	public void set(boolean selected) {
		try {
			waitForClickable(Constant.SHORT_TIMEOUT);
			if (_webElement.isSelected() != selected) {
				_webElement.click();
			}
		} catch (Exception e) {
			logger.printMessage(
					String.format("set: Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
		}
	}

	/**
	 * Click element with default timeout.
	 */
	public void click() {
		click(Constant.SHORT_TIMEOUT);
	}

	/**
	 * Click element.
	 * 
	 * @param timeOutInSeconds
	 */
	public void click(int timeOutInSeconds) {
		if (timeOutInSeconds > 0) {
			try {
				waitForClickable(timeOutInSeconds);
				_webElement.click();
			} catch (Exception e) {
				logger.printMessage(String.format("click: Has error with control '%s': %s", getLocator().toString(),
						e.getMessage()));
			}
		} else {
			logger.printMessage("Time out cannot be less than 0");
		}
	}

	public void moveMouse() {
		moveMouse(Constant.SHORT_TIMEOUT);
	}

	public void moveMouse(int timeOutInSeconds) {
		try {
			waitForVisibility(timeOutInSeconds);
			Actions builder = new Actions(DriverWrapper.getSeleniumWebDriver());
			Action actMoveMouse = builder.moveToElement(_webElement).build();
			actMoveMouse.perform();
		} catch (Exception e) {
			logger.printMessage(String.format("moveMouse: Has error with control '%s': %s", getLocator().toString(),
					e.getMessage()));
		}
	}

	/**
	 * Scroll element to the top.
	 */
	public void scrollToElement(String xpath) {
		((JavascriptExecutor) DriverWrapper.getSeleniumWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
				DriverWrapper.getSeleniumWebDriver().findElement(By.xpath(xpath)));
	}

	/**
	 * Scroll the element into the middle of screen.
	 * 
	 * @param xpath
	 */
	public void scrollElementIntoMiddle(String xpath) {
		String script = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
				+ "var elementTop = arguments[0].getBoundingClientRect().top;"
				+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";

		((JavascriptExecutor) DriverWrapper.getSeleniumWebDriver()).executeScript(script,
				DriverWrapper.getSeleniumWebDriver().findElement(By.xpath(xpath)));
	}

	/**
	 * Wait for Presence of Elements defined by By object
	 * 
	 * @param by
	 */
	public void waitForElementsPresence(By by) {
		new WebDriverWait(DriverWrapper.getSeleniumWebDriver(), Duration.ofSeconds(Constant.SHORT_TIMEOUT))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	@Override
	public List<WebElement> findElements(By by) {
		throw new UnsupportedOperationException("Not support this function yet!");
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		waitForVisibility(Constant.SHORT_TIMEOUT);
		_webElement.sendKeys(keysToSend);
	}

	public static String gerateLocatorFormat(ByType byType, String format) {
		return byType + locatorDelimiter + format;
	}

	private ByType converStringToByType(String type) {
		try {
			return ByType.valueOf(type);
		} catch (Exception e) {
			logger.printMessage(String.format("Don't allow the '%s'. Please use %s for your configuration", type,
					ByType.asString()));
			return null;
		}
	}
}
