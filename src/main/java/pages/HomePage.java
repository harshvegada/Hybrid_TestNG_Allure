package pages;

import java.util.Properties;

import base.PredefinedActions;
import filePaths.Constants;
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
	
	public CommonFunction gotoCommonFunction() {
		return CommonFunction.getCommonFunction();
	}

}
