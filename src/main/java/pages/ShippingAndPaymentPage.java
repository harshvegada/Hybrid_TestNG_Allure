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
		prop = PropertieFileUtils.getProperties(Constants.COMMON_ELEMENT);
	}

	public static ShippingAndPaymentPage getShippingAndPaymentPage() {
		if (shippingAndPaymentPageThreaded.get() == null) {
			shippingAndPaymentPageThreaded.set(new ShippingAndPaymentPage());
		}
		return shippingAndPaymentPageThreaded.get();
	}

	public ShippingAndPaymentPage verifyTotalBillAmt() {
		String totalAmt = getElementText(prop.getProperty("totalBillAmt"));
		Assert.assertEquals(totalAmt, ProductDetails.getTotal_bill());
		System.out.println("Total Amount matched");
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage verifyDeliveryAddress(Map<Object, Object> userDetails) {
		getElementText(prop.getProperty("deliveryAddress")).trim();
		getElementText(prop.getProperty("cityStateAndZipCode")).trim();
		getElementText(prop.getProperty("country")).trim();
		getElementText(prop.getProperty("mobilePhoneNumber")).trim();

		String fistNameAndLastName = userDetails.get("firstName").toString() + " "
				+ userDetails.get("lastName").toString();

		Assert.assertEquals("First Name & Last Mismatch", "",
				getElementText(prop.getProperty("firstNameAndLastName")).trim());

		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

	public ShippingAndPaymentPage clickOnProcessAddress() {
		clickOnElement(prop.getProperty("processAddress"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}

}
