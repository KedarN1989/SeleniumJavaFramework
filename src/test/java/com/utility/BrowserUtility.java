package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);;
	}
	
	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " + browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else {
			System.err.println("Invalid browser type... Please provide browser either Chrome or Edge");
		}
	}
	
	public BrowserUtility(Browser browserName) {
		logger.info("Launching browser for " + browserName);
		if(browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		}
	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);
		if(browserName == Browser.CHROME) {
			if(isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920, 1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		}
	}
	
	public void goToWebsite(String url) {
		logger.info("Navigating to this website: " + url);
		driver.get().get(url);
		logger.info("Navigated to this website: " + url);
	}
	
	public void maximizeWindow() {
		driver.get().manage().window().maximize();
		logger.info("Browser window is maximized");
	}
	
	public void clickOn(By locator) {
		logger.info("Clicking on element: " + locator);
		WebElement element = driver.get().findElement(locator);
		element.click();
		logger.info("Clicked on element: " + locator);
	}
	
	public void enterText(By locator, String textToEnter) {
		logger.info("Entering value" + textToEnter + " in edit text element: " + locator);
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(textToEnter);
		logger.info("Entered value" + textToEnter + " in edit text element: " + locator);
	}
	
	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		String value = element.getText();
		logger.info("Got this visible text " + value + " on this element " + locator);
		return value;
	}
	
	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screesnshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + "-" + timeStamp +"//.png";
		File screesnshotFile = new File(path);
		try {
			FileUtils.copyFile(screesnshotData, screesnshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public void quitWebDriverSession() {
		driver.get().quit();
	}

}
