package testScript;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import dataProvider.UserDataProvider;

//@Listeners(base.TestNGListenersImpl.class)
public class HomePageTest extends TestBase {

	@Test(dataProvider = "dataSupplierMap")
	public void test1(Map<Object, Object> userDetails) {
		commonFunction.signIn().createAccountForNewUser(userDetails.get("email").toString()).createAccount(userDetails)
				.verifyMyAccountPageDisplay().clickOnHomeButtonOnMyAccountPage().gotoCommonFunction()
				.mouseHoverToWomenSectionAndClickOn(userDetails.get("productName").toString())
				.displayTotalNumberResult().choseFirstAvailableProduct().enterQuantity("10").clickOnAddToCartButton()
				.clickOnProceedToCheckout().verifyTotalBillAmt().verifyDeliveryAddress(userDetails)
				.clickOnProcessOnSummary().clickOnProcessAddress().agressToTermsAndConditions()
				.clickOnProcessOnCarrier().payBillUsingCheck().confirmOrder().backToOrder();
	}

	@DataProvider(name = "dataSupplierMap")
	public Object[][] getTestData() {
		return UserDataProvider.dataSupplierMap();
	}

}
