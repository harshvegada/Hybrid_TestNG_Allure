package pages;

import java.util.Properties;

import base.PredefinedActions;
import entities.ProductDetails;
import filePaths.Constants;
import utils.PropertieFileUtils;

public class ProductPage extends PredefinedActions {

	private static ThreadLocal<ProductPage> productPageThreaded = new ThreadLocal<ProductPage>();
	private Properties prop;
	private ProductDetails productDetails;

	private ProductPage() {
		productDetails = new ProductDetails();
		prop = PropertieFileUtils.getProperties(Constants.PRODUCT_DETAILS_ELEMENT);
	}

	public static ProductPage getProductPage() {
		if (productPageThreaded.get() == null) {
			productPageThreaded.set(new ProductPage());
		}
		return productPageThreaded.get();
	}

	public ProductPage enterQuantity(String numberOfQuantity) {
		setTextOnElement(prop.getProperty("quantity"), numberOfQuantity);
		return ProductPage.getProductPage();
	}

	public ProductPage addQuantityByOne() {
		clickOnElement(prop.getProperty("addQuntityByOne"));
		return ProductPage.getProductPage();
	}

	public ProductPage removeQuantityByOne() {
		clickOnElement(prop.getProperty("removeQuntityByOne"));
		return ProductPage.getProductPage();
	}

	public ProductPage clickOnAddToCartButton() {
		clickOnElement(prop.getProperty("addToCartBytton"));
		return ProductPage.getProductPage();
	}

	public ShippingAndPaymentPage clickOnProceedToCheckout() {
		clickOnElement(prop.getProperty("proceedtocheckout"));
		return ShippingAndPaymentPage.getShippingAndPaymentPage();
	}
	
	public ProductPage clickOnContinueButton() {
		clickOnElement(prop.getProperty("continueShopping"));
		return ProductPage.getProductPage();
	}
	

}
