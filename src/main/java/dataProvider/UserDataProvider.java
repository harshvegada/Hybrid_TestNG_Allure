package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import filePaths.Constants;

public class UserDataProvider {

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

	@DataProvider(name="dataSupplierMap")
	public static Object[][] dataSupplierMap() {
		Object[][] obj = null;
		File file = new File(Constants.TEST_DATA);
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
				for (int j = 0; j < lastCellNum; j++) {
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
}
