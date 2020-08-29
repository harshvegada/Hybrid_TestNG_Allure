package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertieFileUtils {

	public static Properties getProperties(String filePath) {
		File file = new File(filePath);
		Properties properties = new Properties();
		try {
			FileInputStream input = new FileInputStream(file);
			properties.load(input);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return properties;
	}

	public static void setPropertiesFileForReport(Map<String, String> propValue, String filePath) {
		Properties properties = new Properties();
		Set<Entry<String, String>> entry = propValue.entrySet();
		for (Entry<String, String> singleEntry : entry) {
			properties.setProperty(singleEntry.getKey(), singleEntry.getValue());
		}
		try {
			properties.store(new FileOutputStream(new File(filePath + File.separator + "environment.properties")),
					"Properties updated");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
