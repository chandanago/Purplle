package com.purplle.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


public class TakeSnapShot{
	
    public String takeScreenshot(WebDriver driver, String screenshotName)  {
    	
    	String localDateTime = LocalDateTime.now().toString().replace(":", "-");
    	
    	String filePath="./errorshots/"+screenshotName+localDateTime+".png";
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File source=ts.getScreenshotAs(OutputType.FILE);
    	File destination=new File(filePath);
    	try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	return destination.getAbsolutePath();
    }
	
}