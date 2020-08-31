package testScript;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import base.TestNGListenersImpl;
import pages.CommonFunction;
import pages.LoginPage;
import utils.PropertieFileUtils;

public class TestBase extends TestNGListenersImpl {

	public static String browsername;
	LoginPage loginPage = LoginPage.getHomePage();
	CommonFunction commonFunction = CommonFunction.getCommonFunction();
	

	@BeforeSuite
	@Parameters("browser")
	public void deleteAllAllureHistoryFiles(@Optional("chrome") String browserName) {
		TestBase.browsername = browserName;
		String path = System.getProperty("user.dir") + File.separator + "allure-results";
		File file = new File(path);
		File files[] = file.listFiles();
		for (File currentFile : files) {
			currentFile.delete();
		}
		System.out.println("History Files deleted from the Allure Folder");
		getEnvironmentInformationForExecution();
	}

	private void getEnvironmentInformationForExecution() {
		String path = System.getProperty("user.dir") + File.separator + "allure-results";
		Map<String, String> sysProp = new HashMap<>();
		sysProp.put("User Name", System.getProperty("user.name"));
		sysProp.put("OS", System.getProperty("os.name"));
		sysProp.put("Country", System.getProperty("user.country"));
		sysProp.put("Language", "Java");
		sysProp.put("Build Tool", "Maven");
		sysProp.put("Time", Calendar.getInstance().getTime().toString());
		PropertieFileUtils.setPropertiesFileForReport(sysProp, path);
	}
	
	

}
