package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class BrowserEventListeners extends PredefinedActions implements WebDriverEventListener {

	static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("Alert Accepted");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("Alert Dismissed");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] charSeq) {
		String elementValue = element.getAttribute("value");
		System.out.println("After change element value : " + elementValue);
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='0px'", element);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='0px'", element);
		System.out.println("Clicked on " + element.getText());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println(element.getText() + " Element found");
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='0px'", element);
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> argument, X type) {
		System.out.println("Screen shot capatured");
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String string) {
		System.out.println("Element Text capatured");
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='0px'", element);
	}

	@Override
	public void afterNavigateBack(WebDriver deiver) {

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {

	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {

	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {

	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Alert Message : " + driverThread.get().switchTo().alert().getText());
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] chaeSeq) {
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {

	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driverThread.get();
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {

	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {

	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		try {
			System.out.println("Exception Occurs");
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}
