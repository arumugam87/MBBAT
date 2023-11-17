package com.mb.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class test {

public static void main(String[] args) throws IOException {
	

	
	String sysprojectpath = System.getProperty("user.dir");
	System.out.println(sysprojectpath);
	
	File file = new File(sysprojectpath+"//resources//Baseproperty.xls");
	//	File file = new File("D:\\Automation\\MaxBlox_BAT\\BATMaxBlox\\src\\test\\java\\resources\\Baseproperty.xls");
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook w = new HSSFWorkbook(fis);
		Sheet sheet = w.getSheet("Login");
				
		Row row = sheet.getRow(1);
		Cell cell1 = row.getCell(0) ;
		String indexurl = cell1.getStringCellValue();
	
		System.out.println(indexurl);
}	
	
	
	
}
