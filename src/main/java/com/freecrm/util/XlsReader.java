package com.freecrm.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsReader {
	public FileInputStream f;
	private XSSFWorkbook w;
	private XSSFSheet s;
	private XSSFRow r;
	private XSSFCell c;

	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {
			f = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/TestData/Test_Data.xlsx");
			w = new XSSFWorkbook(f);
			s = w.getSheet(sheetName);
			r = s.getRow(rowNum);
			c = r.getCell(colNum);
			return c.getStringCellValue();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}