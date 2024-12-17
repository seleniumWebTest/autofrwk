package core.test.java.core.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import core.test.java.core.driver.DriverWrapper;
import core.test.java.core.utilities.DateTimeUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class TestListener implements ITestListener {

	private static Map<String, ExtentTest> testSuite = new HashMap<String, ExtentTest>();
	
	public void onTestFailure(ITestResult result) {
		
		ExtentTest detailFailed = ExtentTestManager.getTest();
		Markup m = MarkupHelper.createCodeBlock(result.getThrowable().getMessage());
		detailFailed.fail(m);
		
		/* capture pic for error or failure */
		
		try {
			String picName = DateTimeUtilities.getCurrentDateTime("hhmmssMMddyyyy");
			DriverWrapper.captureScreen(ExtentTestManager.outputFolder, "jpg", picName);
			detailFailed.addScreenCaptureFromPath("./"+picName+".jpg");
		
		} catch (IOException e) {
			e.printStackTrace();
			detailFailed.fail(e.toString());
		}
	}

	public void onTestStart(ITestResult result) {
		System.out.print("ontest start");
		ExtentTestManager.startTest(result.getMethod().getMethodName(), "", testSuite.get(result.getTestContext().getName()));
		ExtentTestManager.getTest().log(Status.INFO, String.format("TEST CASE: %s.%s", result.getTestClass().getName(), result.getName()));
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTest detailInfo = ExtentTestManager.getTest();
		detailInfo.pass("Test passed.");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	public void onStart(ITestContext context) {
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), "", null);
			testSuite.put(context.getName(), tmpSuite);
		}
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	public void onFinish(ITestContext context) {
		try {
			ExtentManager.getReporter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
