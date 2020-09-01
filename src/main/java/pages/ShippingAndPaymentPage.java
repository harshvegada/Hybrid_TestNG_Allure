package pages;

import java.util.Map;
import java.util.Properties;

import base.PredefinedActions;
import entities.ProductDetails;
import filePaths.Constants;
import junit.framework.Assert;
import utils.PropertieFileUtils;

public class ShippingAndPaymentPage extends PredefinedActions {

	private static ThreadLocal<ShippingAndPaymentPage> shippingAndPaymentPageThreaded = new ThreadLocal<ShippingAndPaymentPage>();
	private Properties prop;

	private ShippingAndPaymentPage() {
		prop = PropertieFileUtils.getProperties(Constants.SHIPPING_AND_PAYMENT_ELEMENT);
	}

	public static ShippingAndPaymentPage getShippingAndPaymentPage() {
		if (shippingAndPaymentPageThreaded.get() == null) {
			shippingAndPaymentPageThreaded.set(new ShippingAndPaymentPage());
		}
		return shippingAndPaymentPageThreaded.get();
	}

	public ShippingAndPaymentPage verifyTotalBillAmt() {
		String totalAmt = getElementText(prop.getProperty("totalBillAmt"));
		ProductDetails.setTotal_bill(totalAmt);
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage verifyDeliveryAddress(Map<Object, Object> userDetails) {
		String uiFirstNameAndLastName = getElementText(prop.getProperty("firstNameAndLastName")).trim();
		String uiaddress = getElementText(prop.getProperty("deliveryAddress")).trim();
		String uicityStateAndZipCode = getElementText(prop.getProperty("cityStateAndZipCode")).trim();
		String country = getElementText(prop.getProperty("country")).trim();

		System.out.println(userDetails);

		String fistNameAndLastName = userDetails.get("firstName").toString() + " "
				+ userDetails.get("lastName").toString();

		String userCountry = userDetails.get("userCountry").toString();
		String userAddress1 = userDetails.get("userAddress1").toString();
		String userCity = userDetails.get("userCity").toString() + ", " + userDetails.get("userCity") + " 37211";

		Assert.assertEquals("Address mismatch", uiaddress, userAddress1);
		Assert.assertEquals("Name mismatch", uiFirstNameAndLastName, fistNameAndLastName);
		Assert.assertEquals("city & Zip mismatch", uicityStateAndZipCode, userCity);
		Assert.assertEquals("Country mismatch", country, userCountry);

		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage clickOnProcessOnSummary() {
		clickOnElement(prop.getProperty("processAddressOnSummary"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage clickOnProcessAddress() {
		clickOnElement(prop.getProperty("processShippingAddress"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage clickOnProcessOnCarrier() {
		clickOnElement(prop.getProperty("processCarrierButton"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage agressToTermsAndConditions() {
		clickOnElement(prop.getProperty("termsAndConditions"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage getTotalAmt() {
		Assert.assertEquals(ProductDetails.getTotal_bill(), getElementText(prop.getProperty("totalAmt")));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage confirmOrder() {
		clickOnElement(prop.getProperty("confirmOrder"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public void getFinalBillVerify() {
		Assert.assertEquals(ProductDetails.getTotal_bill(), getElementText(prop.getProperty("finalBillAmt")));
	}

	public MyAccountPage backToOrder() {
		clickOnElement(prop.getProperty("backToOrder"));
		return MyAccountPage.getMyAccountPage();
	}

	public ShippingAndPaymentPage payBillUsingCheck() {
		clickOnElement(prop.getProperty("payByCheck"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}
}
