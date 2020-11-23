package com.hzm.freestyle.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * Excel工具类（3.17或较新版本可直接使用，一些老版本直接把报错下面的 //注释放开即可）
 * 
 * @author Hezeming
 * @version 1.1 
 * @date 2018年1月10日
 */
public class ExcelUtil {

	public static void main(String[] args) throws Exception {


		Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\910228\\Desktop\\a.xlsx")));;
		Sheet sheet = workbook.getSheet("Sheet1");
		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.print(row.getCell(j).getStringCellValue() + " -> ");
			}
			System.out.println("");
		}
	}
}
