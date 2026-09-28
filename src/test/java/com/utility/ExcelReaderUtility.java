package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	
	public static Iterator<User> readExcelFile(String fileName) {
		
		File xlsxFile = new File(System.getProperty("user.dir") + "\\testData\\" + fileName);
		
		XSSFWorkbook xssfWorkbook;
		List<User> userList = new ArrayList<User>();
		User user;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		Cell expectedUserNameCell;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			XSSFSheet xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			Iterator<Row> rowIterator = xssfSheet.iterator();
			rowIterator.next(); // Skipping column names
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				expectedUserNameCell = row.getCell(2);
				user = new User(emailAddressCell.toString(), passwordCell.toString(), expectedUserNameCell.toString());
				userList.add(user);
			}
			xssfWorkbook.close();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();
	}

}
