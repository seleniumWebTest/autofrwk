package core.test.java.core.report;

import core.test.java.core.driver.DriverWrapper;
import org.testng.ITestResult;

import com.epam.reportportal.testng.ReportPortalTestNGListener;


public class ReportPortalListener extends ReportPortalTestNGListener{
	@Override
	public void onTestFailure(ITestResult testResult) {
		DriverWrapper.saveScreenShot();
		super.onTestFailure(testResult);
	}
}
