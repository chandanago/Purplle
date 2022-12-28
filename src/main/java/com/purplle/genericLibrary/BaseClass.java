package com.purplle.genericLibrary;

import java.awt.Robot;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import BusinessworkFlowLibrary.ReusableClass;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass implements IAutoConstants {

	public WebDriver driver=null;
	public ExtentReports report;
	public ExtentTest test;
	public static WebDriver sDriver ;
	public PropertiesClass property = new PropertiesClass();
	public WebDriverUtilityClass webDriverUtil = new WebDriverUtilityClass();
	public ExcelUtility excelData = new ExcelUtility();
	public JavaUtility javaUtil = new JavaUtility();
	public JavascriptExecutor javaScript=null;
    public ReusableClass rUtil = new ReusableClass();
	@BeforeSuite(alwaysRun=true)
	public void ProjectDocuments() {
		Reporter.log("Purplle web application",true);
	}

	@BeforeTest(alwaysRun=true)
	public void launchTheBrowser() {

		WebDriverManager.chromedriver().setup(); 
		Reporter.log("Browser Launched successfully",true);
	}

	@BeforeClass
	public void maximizeWindow() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		Reporter.log("Window is maximized successfully",true);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		sDriver=driver;
	}


	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		driver.get("https://www.purplle.com/");	
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() throws InterruptedException {
		driver.quit();
		Reporter.log("browser closed successfully",true);
	}

	@AfterSuite(alwaysRun=true)
	public void ReportGenerate() {
		Reporter.log("report generated",true);

	}

}
