package com.JavaPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String filepath="";
	
	public static void main(String[] args) {
		
    try {
		FileInputStream in = new FileInputStream(new File(filepath));
		
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	}

}
