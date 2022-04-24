package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow rw;
	public static XSSFCell cl;
	
	public static int getRowCount(String filepath, String wSheet) throws IOException {
		fi = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(wSheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public static int getCellCount(String filepath, String wSheet, int rownum) throws IOException {
		fi = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(wSheet);
		rw = ws.getRow(rownum);
		int cellCount = rw.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	public static String getCellData(String filepath, String wSheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(wSheet);
		rw = ws.getRow(rownum);
		cl = rw.getCell(colnum);
		String data;
		DataFormatter formatter = new DataFormatter();
		String cellData = formatter.formatCellValue(cl);
		wb.close();
		fi.close();
		return cellData;
	}
	public static void setCellData(String filepath, String wSheet, int rownum, int colnum, String data) throws IOException {
		fi = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(wSheet);
		rw = ws.getRow(rownum);
		cl = rw.createCell(colnum);
		cl.setCellValue(data);
		fo = new FileOutputStream(filepath);
		wb.write(fo);
		wb.close();
		fi.close();
		
	}
	
	
	
	

}
