package filePaths;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Client {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("IFrame Demo")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(1);
		driver.findElement(By.id("dropdownButton")).click();
		driver.switchTo().parentFrame();
		System.out.println(driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).getText());

	}
}
