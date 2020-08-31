package pages;

import java.util.Properties;

import org.testng.Assert;

import base.PredefinedActions;
import filePaths.Constants;
import utils.PropertieFileUtils;

public class CommonFunction extends PredefinedActions {

	private static ThreadLocal<CommonFunction> commonFunctionPageThreaded = new ThreadLocal<CommonFunction>();
	private Properties prop;

	private CommonFunction() {
		prop = PropertieFileUtils.getProperties(Constants.COMMON_ELEMENT);
	}

	public static CommonFunction getCommonFunction() {
		if (commonFunctionPageThreaded.get() == null) {
			commonFunctionPageThreaded.set(new CommonFunction());
		}
		return commonFunctionPageThreaded.get();
	}

	public LoginPage signIn() {
		clickOnElement(prop.getProperty("signIN"));
		return LoginPage.getHomePage();
	}

	public void searchBar(String productNameToSearch) {
		setTextOnElement(prop.getProperty("searchBar"), productNameToSearch);
		clickOnElement(prop.getProperty("searchIcon"));
	}

	public void clickOnContactUs() {
		clickOnElement(prop.getProperty("contactUS"));
	}

	public void clickOnCart() {
		clickOnElement(prop.getProperty("cartIcon"));
	}

	public void gotoWomentSection() {
		clickOnElement(prop.getProperty("womenSection"));
	}

	public void gotoDreessesScetion() {
		clickOnElement(prop.getProperty("dressess"));
	}

	public void gotoTShirtSection() {
		clickOnElement(prop.getProperty("tShirtSection"));
	}

	public CommonFunction mouseHoverToWomenSectionAndClickOn(String productName) {
		mouseHoveOnElement(prop.getProperty("womenSectionMenu"));
		switch (productName.toUpperCase()) {
		case "T-SHIRTS":
			mouseHoveOnElement(prop.getProperty("womenTShirts"));
			clickOnElement(prop.getProperty("womenTShirts"));
			break;
		case "BLOUSES":
			mouseHoveOnElement(prop.getProperty("womenBlowse"));
			clickOnElement(prop.getProperty("womenBlowse"));
			break;
		case "TOPS":
			mouseHoveOnElement(prop.getProperty("womenTops"));
			clickOnElement(prop.getProperty("womenTops"));
			break;
		default:
			Assert.fail("No prodcut name on switch added : " + productName);
		}
		return CommonFunction.getCommonFunction();
	}

	public CommonFunction displayTotalNumberResult() {
		int result = getListOfWebElements(prop.getProperty("listOfResult")).size();
		System.out.println("Total produc result : " + result);
		return CommonFunction.getCommonFunction();
	}
	
	public ProductPage choseFirstAvailableProduct() {
		getListOfWebElements(prop.getProperty("listOfResult")).get(0).click();;
		return ProductPage.getProductPage();
	}

}
