package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public HomePage(Browser browserName) {
		super(browserName);
		goToWebsite(readProperties(QA, "URL"));
		/**
		 * If URL value is taken from JSON file then use below code
		 * 
		 * goToWebsite(JSONUtility.readJSON(Env.QA).getUrl());
		 * 
		 */
		maximizeWindow();
	}
	
	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		goToWebsite(readProperties(QA, "URL"));
		/**
		 * If URL value is taken from JSON file then use below code
		 * 
		 * goToWebsite(JSONUtility.readJSON(Env.QA).getUrl());
		 * 
		 */
		maximizeWindow();
	}

	public LoginPage goToLoginPage() {
		logger.info("Clicking on sign in for navigating to login page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
}
