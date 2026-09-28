package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class LoginPage extends BrowserUtility{
	private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
	private static final By PWD_TEXT_BOX_LOCATOR = By.name("passwd");
	private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");
	
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public MyAccountPage doLoginWith(String emailAddress, String password) {
		logger.info("Performing login operation");
		enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
		enterText(PWD_TEXT_BOX_LOCATOR, password);
		clickOn(SIGN_IN_BUTTON_LOCATOR);
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		logger.info("Login operation completed");
		return myAccountPage;
	}
}
