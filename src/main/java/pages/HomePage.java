package pages;

import java.util.Properties;

import base.PredefinedActions;
import filePaths.Constants;
import io.qameta.allure.Step;
import utils.PropertieFileUtils;

public class HomePage extends PredefinedActions {

	private static ThreadLocal<HomePage> homePageThreaded = new ThreadLocal<HomePage>();
	private Properties prop;

	private HomePage() {
		prop = PropertieFileUtils.getProperties(Constants.HOMEPAGE_ELEMENT);
	}

	public static HomePage getHomePage() {
		if (homePageThreaded.get() == null) {
			homePageThreaded.set(new HomePage());
		}
		return homePageThreaded.get();
	}

	@Step("Verify user able to login")
	public void login() {
		clickOnRegistration();
		enterUserName();
		enterPassword();
		clickLoginBtn();
		handleAlert();
	}

	private void handleAlert() {
		acceptAlert();
	}

//	@Step("User ener {0} to the text area")
//	private void enterName(String string) {
//		switch (string.toLowerCase()) {
//		case "first":
//			setTextOnElement(prop.getProperty("first"), "harsh");
//			break;
//		case "last":
//			setTextOnElement(prop.getProperty("last"), "vegada");
//			break;
//		case "email":
//			setTextOnElement(prop.getProperty("email"), "harshvegada@gmail.com");
//			break;
//		}
//	}

	private void clickLoginBtn() {
		clickOnElement(prop.getProperty("loginbtn"));
	}

	private void enterPassword() {
		setTextOnElement(prop.getProperty("pwd"), "harshvegada");
	}

	private void enterUserName() {
		setTextOnElement(prop.getProperty("username"), "harshvegada");
	}

	private void clickOnRegistration() {
		clickOnElement(prop.getProperty("registraction"));
	}

}
