package testScript;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import dataProvider.UserDataProvider;
import io.qameta.allure.Description;

@Listeners(base.TestNGListenersImpl.class)
public class HomePageTest extends TestBase {

	// @Description("Test Description: Submit Form username and password and email")
	// @Test(dataProvider="dataSupplierMap")
	// public void test1(String genderTitle, String firstName, String lastName,
	// String emailAddress, String password,
	// String dob, String userCity, String userAddress1, String zipCode, String
	// userCountry, String homePhone,
	// String mobilePhone) {
	// System.out.println("Test Data");
	// }

	@Description("Test Description: Submit Form username and password and email")
	@Test(dataProvider = "dataSupplierMap")
	public void test1(Map<Object, Object> userDetails) {
		commonFunction.signIn().createAccountForNewUser(userDetails.get("email").toString()).
		createAccount(userDetails).
		verifyMyAccountPageDisplay().
		clickOnHomeButtonOnMyAccountPage().
		gotoCommonFunction().
		mouseHoverToWomenSectionAndClickOn(userDetails.get("productName").toString()).
		displayTotalNumberResult().
		choseFirstAvailableProduct().enterQuantity("10").
		clickOnProceedToCheckout().
		verifyTotalBillAmt().
		verifyDeliveryAddress(userDetails).
		clickOnProcessAddress().
		clickOnProcessAddress();
		
	}

	// @Description("Test Description: Submit Form username and password")
	// @Test(description = "Submit Form username and password")
	// public void test2() {
	// }
	//
	// @Description("Test Description: Submit form with username and password exp")
	// @Test(description = "Submit form with username and password exp")
	// public void test3() {
	// }

	@DataProvider(name = "dataSupplierMap")
	public Object[][] getTestData() {
		return UserDataProvider.dataSupplierMap();
	}

}
