package pages;

import java.util.Properties;

import org.testng.Assert;

import base.PredefinedActions;
import filePaths.Constants;
import utils.PropertieFileUtils;

public class MyAccountPage extends PredefinedActions {

	private static ThreadLocal<MyAccountPage> myAccountPageThreaded = new ThreadLocal<MyAccountPage>();
	private Properties prop;

	private MyAccountPage() {
		prop = PropertieFileUtils.getProperties(Constants.MYACCOUNT_ELEMENT);
	}

	public static MyAccountPage getMyAccountPage() {
		if (myAccountPageThreaded.get() == null) {
			myAccountPageThreaded.set(new MyAccountPage());
		}
		return myAccountPageThreaded.get();
	}

	public MyAccountPage verifyMyAccountPageDisplay() {
		String act = getElementText(prop.getProperty("myAccountName"));
		Assert.assertEquals(act, Constants.MY_ACCOUNT_TITLE);
		System.out.println("My Account Page title verified");
		return MyAccountPage.getMyAccountPage();
	}

	public HomePage clickOnHomeButtonOnMyAccountPage() {
		clickOnElement(prop.getProperty("homePageBtn"));
		return HomePage.getHomePage();
	}

}
