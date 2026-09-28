package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {
	
	@DataProvider(name = "LoginTestDataProvider")
	public Iterator<Object[]> loginTestDataProvider() {
		Gson gson = new Gson();
		File loginTestDataFile = new File(System.getProperty("user.dir") + "\\testData\\loginData.json");
		FileReader fileReader = null;
		List<Object[]> dataToReturn = null;
		try {
			fileReader = new FileReader(loginTestDataFile);
			TestData loginTestData = gson.fromJson(fileReader, TestData.class);
			dataToReturn = new ArrayList<Object[]>();
			for(User user: loginTestData.getData()) {
				dataToReturn.add(new Object[] {user});
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return dataToReturn.iterator();
	}
	
	@DataProvider(name= "LoginCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	
	@DataProvider(name= "LoginExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
	}
}
