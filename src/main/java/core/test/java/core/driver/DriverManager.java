package core.test.java.core.driver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import core.test.java.core.enumobject.EnumObject.*;
import core.test.java.core.driver.*;
import core.test.java.projectconst.Constant;
import org.apache.log4j.Logger;

import com.google.common.base.Throwables;



public class DriverManager {
    private static ThreadLocal<BaseDriver> driver = new ThreadLocal<BaseDriver>();
	//static Map<String, BaseDriver> driverController = new HashMap<String, BaseDriver>();
	private static Logger logger = Logger.getLogger(DriverManager.class);
	
	public static void initBrowser(String browser) {
		DriverProperty property = BrowserSettingHelper.getDriverProperty(Constant.BROWSER_SETTING_FILE, browser);
		BaseDriver newDriver = createDriver(property);
		if (newDriver != null)
		{
			driver.set(newDriver);
		}
	}


	public static BaseDriver createDriver(DriverProperty property) {
		RunningMode mode = property.getMode();
		String browserPackage = Constant.BROWSERSTRING;
		String browserClass = "%s%sDriver";
		String packageName = String.format(browserPackage, property.getProvider().toLowerCase(), property.getDriverType().toString().toLowerCase());
		String className = String.format(browserClass, mode, property.getDriverType().toString());
		try {
			Method method;
			String fullClassName = packageName + "." + className;
			Class<?> clzz = Class.forName(fullClassName);//core.DRIVER.selenium.chrome.localChromeDriver
			Constructor<?> cons = clzz.getDeclaredConstructor(DriverProperty.class);
			Object obj = cons.newInstance(property);
			// Create Web Driver
			method = clzz.getDeclaredMethod("createWebDriver"); //nameDeclareMethod = "createWebDriver"
			method.invoke(obj);
			return (BaseDriver) obj;
		} catch (Exception e) {
			logger.error("Could not create new Driver instance. " + Throwables.getStackTraceAsString(e));
			return null;
		}
	}
	
	/*public static void addDriver(BaseDriver driver) {
		driverController.put(String.valueOf(Thread.currentThread().getId()), driver);
	}
	
	public static void removeDriver() {
		driverController.remove(String.valueOf(Thread.currentThread().getId()));
	}*/
	
	public static BaseDriver getDriver() {
		return driver.get();
//		return driverController.get(String.valueOf(Thread.currentThread().getId()));
	}
}
