package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class PredefinedActions {

	static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	protected void createBrowser(String browser) {
		switch (browser.toUpperCase()) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driverThread.set(new ChromeDriver());
			driverThread.get().manage().window().maximize();
			driverThread.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driverThread.set(new InternetExplorerDriver());
			driverThread.get().manage().window().maximize();
			driverThread.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		EventFiringWebDriver event = new EventFiringWebDriver(driverThread.get());
		BrowserEventListeners eventListener = new BrowserEventListeners();
		event.register(eventListener);
		driverThread.get().get("http://automationbykrishna.com/");
	}

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driverThread.get(), 30);

		String[] arr = locator.split("]:-");
		String locatorType = arr[0].substring(1).toLowerCase(); // xpath //24
		String locatorValue = arr[1];

		try {
			switch (locatorType) {
			case "xpath":
				if (isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))); // 29
				else
					element = driverThread.get().findElement(By.xpath(locatorValue));
				break; // 30

			case "id":
				if (isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				else
					element = driverThread.get().findElement(By.id(locatorValue));
				break;

			case "name":
				if (isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				else
					element = driverThread.get().findElement(By.name(locatorValue));
				break;

			case "css":
				if (isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				else
					element = driverThread.get().findElement(By.cssSelector(locatorValue));
				break;

			case "linktext":
				if (isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				else
					element = driverThread.get().findElement(By.linkText(locatorValue));
				break;

			default:
				System.out.println("Please select correct locatorType, locatorType " + locatorType + " is invalid");
			}
		} catch (ElementNotVisibleException e) {
			System.out.println(e.getMessage());
		} catch (InvalidSelectorException ise) {
			System.out.println(ise.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return element;
	}

	protected void setTextOnElement(String locator, String keysToSend) {
		WebElement element = getElement(locator, true);
		element.clear();
		element.sendKeys(keysToSend);
	}

	protected void clickOnElement(String locator) {
		WebElement element = getElement(locator, true);
		element.click();
	}

	protected void terminateBrowser() {
		System.out.println(Thread.currentThread().getName() + " : " + driverThread.get());
		driverThread.get().close();
	}

	public byte[] takeScreenShot() {
		TakesScreenshot ts = (TakesScreenshot) driverThread.get();
		byte[] src = ts.getScreenshotAs(OutputType.BYTES);
		return src;
	}

	public void switchToWindow(String winName) {
		try {
			driverThread.get().switchTo().window(winName);
		} catch (NoSuchWindowException e) {
			System.out.println(e.getMessage());
		}
	}

	public void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driverThread.get(), 10);
		wait.until(ExpectedConditions.alertIsPresent());
		try {
			driverThread.get().switchTo().alert().accept();
		} catch (NoAlertPresentException e) {
			System.out.println("An exceptional case");
		}
	}

}
