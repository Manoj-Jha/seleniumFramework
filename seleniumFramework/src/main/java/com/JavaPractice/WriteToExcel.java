package com.JavaPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {

	public static String filepath="D:\\Krypton Backup 17_10_2019\\seleniumFramework\\WriteToExcel_Demo.xlsx";
	public static void main(String[] args) {
		
		WriteToExcel readexcel=new WriteToExcel();
		readexcel.readExcel(filepath);
		//Create workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		//Create sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");
		//Create data set in map
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[]{"ID", "Name", "LastName"});
		data.put("2", new Object[]{1, "Amit", "Shukla"});
		data.put("3", new Object[]{2, "Sanjeev", "Sharma"});
		data.put("4", new Object[]{3, "Ram", "Madhav"});
		data.put("5", new Object[]{4, "Anil", "Sharma"});

		//Get key
		Set<String> keyset=data.keySet();
		int rowno =0;
		for(String key: keyset){
			XSSFRow row =sheet.createRow(rowno++);
			Object [] objArr =data.get(key);
			int cellno=0;
			for(Object obj:objArr) {
				XSSFCell cell=row.createCell(cellno++);
				if(obj instanceof String){
					cell.setCellValue((String)obj);
				}else if(obj instanceof Integer){
					cell.setCellValue((Integer)obj);
				}
			}
		}
		try{
			FileOutputStream out = new FileOutputStream(new File("WriteToExcel_Demo.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Data successfully written in excelsheet.");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void readExcel(String filepath) {
		XSSFWorkbook workbook=null;
		try {
			FileInputStream in = new FileInputStream(new File(filepath));
			 workbook = new XSSFWorkbook(in);
			XSSFSheet sheet1= workbook.getSheetAt(0);
			
			String data00 =sheet1.getRow(0).getCell(0).getStringCellValue();
			System.out.println(data00);
			String data01 =sheet1.getRow(0).getCell(1).getStringCellValue();
			System.out.println(data01);
			String data02 =sheet1.getRow(0).getCell(2).getStringCellValue();
			System.out.println(data02);
			String data11 =sheet1.getRow(1).getCell(1).getStringCellValue();
			System.out.println(data11);
			String data12 =sheet1.getRow(1).getCell(2).getStringCellValue();
			System.out.println(data12);
			//String data13 =sheet1.getRow(1).getCell(3).getStringCellValue();
			//System.out.println(data13);
			
			int rowCount = sheet1.getLastRowNum()-sheet1.getFirstRowNum();
			
			
			for(int i=0; i<=rowCount+1; i++){
				
				Row row=sheet1.getRow(i);
				
				for(int j=0; j<row.getLastCellNum(); j++){
					
					String data =row.getCell(j).getStringCellValue();
					System.out.println(data + "||");
					
				}
				
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
