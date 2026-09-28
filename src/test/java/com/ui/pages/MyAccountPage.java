package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class MyAccountPage extends BrowserUtility {
	
	private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public String getUserName() {
		logger.info("Getting username locator visible text");
		return getVisibleText(USER_NAME_LOCATOR);
	}

}
