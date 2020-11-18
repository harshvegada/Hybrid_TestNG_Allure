package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

import filePaths.Constants;

public class UserDataProvider {

	private static String firstName;
	private static String lastName;

	public static Object[][] dataSupplier() {
		Object[][] data = null;
		File file = new File(Constants.TEST_DATA);

		XSSFWorkbook wb;
		try {
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			wb.close();
			int lastRow = sheet.getLastRowNum();
			int lasCell = sheet.getRow(0).getLastCellNum();

			data = new Object[lastRow][lasCell];

			for (int i = 1; i <= lastRow; i++) {
				int firstCell = sheet.getRow(i).getFirstCellNum();
				int lastCell = sheet.getRow(i).getLastCellNum();
				Row row = sheet.getRow(i);
				for (int j = firstCell; j < lastCell; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {
					} else if (cell.getCellType() == CellType.STRING) {
						data[i - 1][j] = cell.getStringCellValue();
					} else if (cell.getCellType() == CellType.NUMERIC) {
						data[i - 1][j] = cell.getNumericCellValue();
					} else if (cell.getCellType() == CellType.BLANK) {
						data[i - 1][j] = "";
					} else if (cell.getCellType() == CellType.ERROR) {
						data[i - 1][j] = cell.getErrorCellValue();
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	public static Object[][] dataSupplierMap() {
		generateData();
		Object[][] obj = null;
		File file = new File(Constants.RANDOM_TEST_DATA);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			wb.close();
			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			obj = new Object[lastRowNum][1];

			for (int i = 0; i < lastRowNum; i++) {
				Map<Object, Object> datamap = new HashMap<Object, Object>();
				for (int j = 1; j < lastCellNum; j++) {
					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				}
				obj[i][0] = datamap;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	private static void generateData() {
		File file = new File("random.xlsx");
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Sheet1");
		Row header = sheet.createRow(0);
		for (int i = 1; i < 2; i++) {

			Row row = sheet.createRow(i);
			for (int j = 1; j <= 15; j++) {
				Cell cell = row.createCell(j);

				if (j == 1) {
					header.createCell(1).setCellValue("genderTitle");
					cell.setCellValue("Female");
				} else if (j == 2) {
					header.createCell(2).setCellValue("firstName");
					cell.setCellValue(getFirstName());
				} else if (j == 3) {
					header.createCell(3).setCellValue("lastName");
					cell.setCellValue(getLastName());
				} else if (j == 4) {
					header.createCell(4).setCellValue("email");
					cell.setCellValue(getEmail());
				} else if (j == 5) {
					header.createCell(5).setCellValue("password");
					cell.setCellValue(getPass());
				} else if (j == 6) {
					header.createCell(6).setCellValue("dob");
					cell.setCellValue("23/12/2020");
				} else if (j == 7) {
					header.createCell(7).setCellValue("userCity");
					cell.setCellValue("Pune");
				} else if (j == 8) {
					header.createCell(8).setCellValue("userAddress1");
					cell.setCellValue("Beverly Hills, Baner");
				} else if (j == 9) {
					header.createCell(9).setCellValue("zipCode");
					cell.setCellValue("411045");
				} else if (j == 10) {
					header.createCell(10).setCellValue("userCountry");
					cell.setCellValue("India");
				} else if (j == 11) {
					header.createCell(11).setCellValue("homePhone");
					cell.setCellValue(getMobile());
				} else if (j == 12) {
					header.createCell(12).setCellValue("mobileNumber");
					cell.setCellValue(getMobile());
				} else if (j == 13) {
					header.createCell(13).setCellValue("state");
					cell.setCellValue("Maharstra");
				} else if (j == 14) {
					header.createCell(14).setCellValue("info");
					cell.setCellValue("Info");
				} else if (j == 15) {
					header.createCell(15).setCellValue("productName");
					cell.setCellValue("T-shirts");
				}
			}
			try {
				wb.write(new FileOutputStream(file));
				wb.close();
			} catch (IOException e) {
			}
		}
	}

	private static String getEmail() {
		Faker faker = new Faker(new Locale("en-IND"));
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		return firstName + lastName + "@gmail.com";
	}

	private static String getFirstName() {
		Faker faker = new Faker(new Locale("en-IND"));
		lastName = faker.name().lastName();
		return lastName;
	}

	private static String getLastName() {
		Faker faker = new Faker(new Locale("en-IND"));
		firstName = faker.name().firstName();
		return firstName;
	}

	private static String getMobile() {
		String str = "78906";
		String mobile = "";
		for (int i = 0; i < str.length() - 1; i++) {
			mobile = mobile + new Random().nextInt(str.length());
		}
		return mobile;
	}

	private static String getPass() {
		String str = "ABCDEF6576658";
		String pwd = "";
		for (int i = 0; i < str.length() - 1; i++) {
			pwd = pwd + new Random().nextInt(str.length());
		}
		return pwd;
	}

}
