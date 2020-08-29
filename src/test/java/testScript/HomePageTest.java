package testScript;

import static pages.HomePage.getHomePage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;

@Listeners(base.TestNGListenersImpl.class)
public class HomePageTest extends TestBase {

	@Description("Test Description: Submit Form username and password and email")
	@Test(description = "Submit Form username and password and email")
	public void test1() {
		getHomePage().login();
	}

	@Description("Test Description: Submit Form username and password")
	@Test(description = "Submit Form username and password")
	public void test2() {
		getHomePage().login();
	}

	@Description("Test Description: Submit form with username and password exp")
	@Test(description = "Submit form with username and password exp")
	public void test3() {
		getHomePage().login();
	}

}
