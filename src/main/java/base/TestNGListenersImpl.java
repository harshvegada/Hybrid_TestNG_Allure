package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TestNGListenersImpl extends PredefinedActions implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] makeScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", driverThread.get());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		createBrowser("chrome");
		System.out.println("Start method " + getTestMethodName(iTestResult) + " start");
		System.out.println(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Success method " + getTestMethodName(iTestResult) + " succeed");
		terminateBrowser();
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("Failure method " + getTestMethodName(iTestResult) + " failed");
		// Allure ScreenShotRobot and SaveTestLog
		if (driverThread.get() instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
		}
		makeScreenShot(driverThread.get());
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
		terminateBrowser();
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Skipped method " + getTestMethodName(iTestResult) + " skipped");
		terminateBrowser();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	// @Override
	// public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	// createBrowser("chrome");
	// }
	//
	// @Override
	// public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	// if(testResult.getStatus()==ITestResult.FAILURE) {
	// onTestFailure(testResult);
	// }
	// }
}