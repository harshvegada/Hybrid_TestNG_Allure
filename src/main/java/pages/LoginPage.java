package pages;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import base.PredefinedActions;
import filePaths.Constants;
import utils.PropertieFileUtils;

public class LoginPage extends PredefinedActions {

	private static ThreadLocal<LoginPage> loginPageThreaded = new ThreadLocal<LoginPage>();
	private Properties prop;

	private LoginPage() {
		prop = PropertieFileUtils.getProperties(Constants.LOGIN_ELEMENT);
	}

	public static LoginPage getHomePage() {
		if (loginPageThreaded.get() == null) {
			loginPageThreaded.set(new LoginPage());
		}
		return loginPageThreaded.get();
	}

	public LoginPage createAccountForNewUser(String emailAddress) {
		enterEmailAddressForNewUser(emailAddress);
		clickOnCreateAnAccount();
		return loginPageThreaded.get();
	}

	public MyAccountPage createAccount(Map<Object, Object> userDetails) {
		choseGender(userDetails.get("genderTitle").toString());
		enterPersonalInfoFirstName(userDetails.get("firstName").toString());
		enterPersonalInfoLastName(userDetails.get("lastName").toString());
		verifyUserEmailAddresPrepopulated(userDetails.get("email").toString());
		enterPasswordYouInformation(userDetails.get("password").toString());
		selectDOB(userDetails.get("dob").toString());
		enterAddressFirstName(userDetails.get("firstName").toString());
		enterAddressLastName(userDetails.get("lastName").toString());
		enterAddressCompanyName(userDetails.get("userCity").toString());
		enterAddressLine1(userDetails.get("userAddress1").toString());
		enterCityNameInAddress(userDetails.get("userCity").toString());
		selectStateName(userDetails.get("state").toString());
		enterZipCodeInYourAddress("37211");
		enterHomePhoneNumber("8905714850");
		enterMobilPhoneNumber("8905714850");
		registerAccount();
		return MyAccountPage.getMyAccountPage();
	}

	private void registerAccount() {
		clickOnElement(prop.getProperty("registerAccount"));
	}

	private void enterEmailAddressForNewUser(String emailAddress) {
		setTextOnElement(prop.getProperty("createAccount"), emailAddress);
	}

	private void clickOnCreateAnAccount() {
		clickOnElement(prop.getProperty("createAccountBtn"));
	}

	public void enterEmailAddressForExistingUser(String emailAddress) {
		setTextOnElement(prop.getProperty("alreadyhaveAccountEmailSection"), emailAddress);
	}

	public void enterPasswordForAlreadyHaveAccount(String password) {
		setTextOnElement(prop.getProperty("alreadyhaveAccountPasswordSection"), password);
	}

	public void signIn() {
		clickOnElement(prop.getProperty("signInForAlreadyAccount"));
	}

	public void clickOnForgotLink() {
		clickOnElement(prop.getProperty("forgotLink"));
	}

	private void enterMobilPhoneNumber(String mobilePhoneNumber) {
		setTextOnElement(prop.getProperty("mobileNumber"), mobilePhoneNumber);
	}

	private void enterHomePhoneNumber(String homePhoneNumber) {
		setTextOnElement(prop.getProperty("homePhone"), homePhoneNumber);
	}

	private void enterZipCodeInYourAddress(String zipCode) {
		setTextOnElement(prop.getProperty("zipCode"), zipCode);
	}

	private void selectStateName(String stateName) {
		selectElementWithVisibleText(prop.getProperty("selectState"), stateName);
	}

	private void enterCityNameInAddress(String cityName) {
		setTextOnElement(prop.getProperty("addressCity"), cityName);
	}

	private void enterAddressLine1(String address1) {
		setTextOnElement(prop.getProperty("address1"), address1);
	}

	private void enterAddressCompanyName(String companyName) {
		setTextOnElement(prop.getProperty("addressCompany"), companyName);
	}

	private void enterAddressLastName(String lastName) {
		setTextOnElement(prop.getProperty("addressLastName"), lastName);
	}

	private void enterAddressFirstName(String firstName) {
		setTextOnElement(prop.getProperty("addressFirstName"), firstName);
	}

	private void selectDOB(String dob) {
		String days = dob.split("/")[0];
		String months = dob.split("/")[1];
		String years = dob.split("/")[2];
		selectElementWithValue(prop.getProperty("dobDay"), days);
		selectElementWithValue(prop.getProperty("dobMonth"), months);
		selectElementWithValue(prop.getProperty("dobYear"), years);
	}

	private void enterPasswordYouInformation(String password) {
		setTextOnElement(prop.getProperty("passwordField"), password);
	}

	private void verifyUserEmailAddresPrepopulated(String emailAddress) {
		String actualEmail = getElementAttatibuteValue(prop.getProperty("emailFeild"));
		Assert.assertEquals(actualEmail, emailAddress);
		System.out.println("Email Address Prepopulated For new user account creation...");
	}

	private void enterPersonalInfoFirstName(String firstName) {
		setTextOnElement(prop.getProperty("firstName"), firstName);
	}

	private void enterPersonalInfoLastName(String lastName) {
		setTextOnElement(prop.getProperty("lastName"), lastName);
	}

	private void choseGender(String gender) {
		switch (gender.toUpperCase()) {
		case "FEMALE":
			clickOnElement(prop.getProperty("femaleRadio"));
			break;
		case "MALE":
			clickOnElement(prop.getProperty("maleRadio"));
			break;
		default:
			Assert.fail("Please check Excel Sheet Gender section..");
		}
	}

}
