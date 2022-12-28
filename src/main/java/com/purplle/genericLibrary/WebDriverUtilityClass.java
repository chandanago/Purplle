package com.purplle.genericLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class contains WebDriver specific generic methods
 * @author 
 *
 */

public class WebDriverUtilityClass implements IAutoConstants {
	PropertiesClass property = new PropertiesClass();

	/**
	 * This method is used for obtaining Implicit wait functionality by passing WebDriver reference as parameter
	 * It will hold the control until a webElement is present in WebEleent DOM
	 */
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
	}
	/**
	 * This method is used for explicit wait functionality by providing WebDriver reference and WebElement reference
	 * @param driver
	 * @param element
	 */
	public void explicitlyWaitForElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public void explicitlyWait(WebDriver driver,WebElement element)
	{
		try {
			Thread.sleep(2000);
			WebDriverWait wait=new WebDriverWait(driver, ETO);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void explicitlyWaitForElementTextToPresent(WebDriver driver,WebElement element, String text)
	{
		WebDriverWait wait=new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}


	/**
	 * This method is used for explicit wait functionality by providing WebDriver reference and WebElement reference
	 * @param driver
	 * @param element
	 */
	public void explicitlyWaitForElementToClick(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}



	/**
	 * This method is used for explicit wait until a specified url is loaded
	 * @param driver
	 * @param partialUrl
	 */
	public void explicitlyWaitForUrl(WebDriver driver,String partialUrl)
	{
		WebDriverWait wait=new WebDriverWait(driver, ETO);	
		wait.until(ExpectedConditions.urlContains(partialUrl));
	}
	/**
	 * This method is used for explicit wait until a specified page is loaded
	 * @param driver
	 * @param partialUrl
	 */
	public void explicitlyWaitForPage(WebDriver driver,String title)
	{
		WebDriverWait wait=new WebDriverWait(driver, ETO);	
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * This method wait explicitly utill element presrent and perform click operation on element 
	 * @param driver
	 * @param element
	 * @throws Throwable 
	 */
	public void click(WebDriver driver, WebElement element)
	{
		explicitlyWaitForElement(driver,element );
		element.click();	
	}
	/**
	 * This method is used to select an option based on index from drop down  
	 * @param element
	 * @param i
	 */
	public void selectElementByIndex(WebElement element,int i)
	{
		Select sel=new Select(element);
		sel.selectByIndex(i);
	}
	/**
	 * This method is used to select an option based on value from drop down  
	 * @param element
	 * @param val
	 */
	public void selectElementByValue(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	/**
	 * This method is used to select an option based on Visible text from drop down  
	 * @param DropDown
	 * @param option
	 */
	public void selectElementByVisibleText(WebElement element,String option)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(option);
	}


	/**
	 * This method is used to select an option based on value from drop down  
	 * @param element
	 * @param val
	 */
	public void deselectElementByValue(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.deselectByValue(value);
	}




	/**
	 * This method is used to move to element using coordinates
	 * @param driver
	 * @param element
	 */
	public void moveToElementUsingCoordinates(WebDriver driver,int x, int y)
	{
		Actions act=new Actions(driver);
		act.moveByOffset(x, y).click().perform();
	}




	/**
	 * This method is used to perform mouse hover action to particular element using webElement
	 * @param WebDriver reference
	 * @param Target Element reference
	 */
	public void mouseHoverOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}


	/**
	 * This method is used to click on default area
	 * 
	 */
	public void clickOnDefaultArea(WebDriver driver,int x, int y)
	{
		Actions act=new Actions(driver);
		act.moveByOffset(x, y).click().perform();
	}



	/**
	 * this method is used to click on key down
	 * 
	 * @param driver
	 * @param interator
	 */

	public void aScrollDown(WebDriver driver, int interator) {

		for(int i=1; i<=interator;i++) {
			// Page Down
			Actions act=new Actions(driver);
			act.keyDown(Keys.CONTROL).sendKeys(Keys.DOWN).perform();

		}
	}


	/**
	 * this method is used to click on key Up
	 * 
	 * @param driver
	 * @param interator
	 */

	public void aScrollUp(WebDriver driver, int interator) {

		for(int i=1; i<=interator;i++) {
			// Page Down
			Actions act=new Actions(driver);
			act.keyDown(Keys.CONTROL).sendKeys(Keys.UP).perform();

		}
	}

	/**
	 * this method is used to click on key End
	 * 
	 * @param driver
	 * @param interator
	 */

	public void aScrollEnd(WebDriver driver, int interator) {

		for(int i=1; i<=interator;i++) {
			// Page Down
			Actions act=new Actions(driver);
			act.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

		}
	}


	/**
	 * This method is used to perform mouse hover action to particular element by using x and y coordinate with webElement 
	 * @param WebDriver reference
	 * @param Target Element reference
	 */


	public void mouseHoverOnElement(WebDriver driver,WebElement element,int x,int y)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element,x,y).perform();
	}


	public void mouseHoverOnElementByGetXY(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		act.moveToElement(element,x,y).perform();
	}


	/**
	 * This method is used to perform double click action on particular Web element  
	 * @param WebDriver reference
	 * @param Target Element reference	
	 */
	public void doubleClickElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to right click upon a particular Web Element 
	 * @param WebDriver reference
	 * @param Target Element reference	
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		act.contextClick(element).perform();
	}
	/**
	 * This method is used to perform drag and drop operation based on source web element to target web element locations
	 * @param driver
	 * @param sourceLocation
	 * @param destinationLocation
	 */
	public void dragAndDrop(WebDriver driver,WebElement sourceLocation,WebElement destinationLocation)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(sourceLocation, destinationLocation).perform();;
	}


	/**
	 * This method is used to perform press Enter key Action 
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	} 

	/**
	 * This method is used to switch to window based on url 
	 * @param driver
	 * @param partialUrl
	 */
	public void switchToWindowWithUrl(WebDriver driver,String partialUrl)
	{
		Set<String> wtabs = driver.getWindowHandles();
		Iterator<String> it=wtabs.iterator();
		while(it.hasNext())
		{
			String currTab=it.next();
			driver.switchTo().window(currTab);
			if(driver.getCurrentUrl().contains(partialUrl))
			{
				break;
			}
		}
	}
	/**
	 * This method is used to switch to window based on title 
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToWindowWithTitle(WebDriver driver,String partialTitle)
	{
		Set<String> wtabs = driver.getWindowHandles();
		Iterator<String> it=wtabs.iterator();
		while(it.hasNext())
		{
			String currTab=it.next();
			driver.switchTo().window(currTab);
			if(driver.getTitle().contains(partialTitle))
			{
				break;
			}
		}
	}


	/**
	 * This method is used to perform click action on disabled element
	 * @param WebElement
	 * @param driver
	 */
	public void clickDisabledElement(WebElement element, WebDriver driver)  {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].click();", element);
	}
	/**
	 * This method is used to send keys to a disabled text field
	 * @param WebElement
	 * @param keys to send
	 * @param  driver
	 */
	public void sendValueToDisabledTextField(WebElement element,String data, WebDriver driver)  {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].value='data';", element);
	}
	/**
	 * This method is used to scroll to view a particular web element
	 * @param WebElement
	 * @param boolean 
	 * @param  driver
	 */
	public void scrollIntoView(WebElement element,boolean trueorFalse, WebDriver driver)  {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].scrollIntoView("+trueorFalse+")", element);
	}
	/**
	 * This method is used to scroll to particular web element 
	 * @param WebElement
	 * @param x coordinate
	 * @param y coordinate
	 * @param  driver
	 */
	public void scrollToElement(WebElement element,int x, int y,WebDriver driver)  {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].scrollTo("+x+","+y+")",element);
	}

	/**
	 * This method is used to scroll till end of the page
	 * @param driver
	 */
	public void scrollToEndOfThePage(WebDriver driver)  {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	/**
	 * This method is used to scroll from current cursor position to particular coordinates
	 * @param x coordinate
	 * @param y coordinate
	 * @param driver
	 */
	public void scrollBy(int x,int y,WebDriver driver) {
		JavascriptExecutor javaScript=(JavascriptExecutor) driver;
		javaScript.executeScript("window.scrollBy("+x+","+y+")");
	}

	/**
	 * This method is used to scroll the till element present
	 * @param driver
	 * @param webElement
	 * @param x
	 * @param y
	 * @return
	 */

	public WebElement scrollTillElementPresent(WebDriver driver, WebElement webElement,int x, int y) {
		int count = 0;
		y=150;
		while(webElement.isDisplayed()==false && count<=5) {
			explicitlyWaitForElement(driver, webElement);

			scrollToElement(webElement, x, y, driver);
			count++;
			y=150+y;
		}
		return webElement;
	}

	/**
	 * Accept alert 
	 * @param driver
	 */

	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}

	/**
	 * Cancel Alert
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}


	/**
	 * This method is used to Switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}   
	/**
	 * This method is used to switch to frame based on web element
	 * @param driver
	 * @param element
	 * @throws AWTException 
	 */
	public void switchFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	} 
	/**
	 * This method is used to switch to frame based on id or name
	 * @param driver
	 * @param idOrName
	 */
	public void switchFrame(WebDriver driver,String idOrName) {
		driver.switchTo().frame(idOrName);
	} 

	/**
	 * This method is used to switch window 
	 * @param driver
	 * @param windowID
	 */

	public void switchWindow(WebDriver driver,String windowID)
	{
		driver.switchTo().window(windowID);
	}



	public void ToCloseOnlyChildWindow(WebDriver driver, String parentWind) {
		//String parentWind=driver.getWindowHandle();	
		Set<String> allwids=driver.getWindowHandles();
		allwids.remove(parentWind);
		for(String wid:allwids){
			driver.switchTo().window(wid);                        
			driver.close();
		}
	}
	public void explicitlyWaitForElementClick(WebDriver driver,WebElement element)
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebDriverWait wait=new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchNextWindow(WebDriver driver) {
		String parentWind=driver.getWindowHandle();	
		Set<String> allwids=driver.getWindowHandles();
		allwids.remove(parentWind);
		for(String wid:allwids){
			driver.switchTo().window(wid);                                                                                                                     
			

		}

	}

	public void scrollToWebElement(WebDriver driver, WebElement element) {
		Actions act;
		explicitlyWaitForElement(driver, element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		act = new Actions(driver);
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(" + x + "," + y + ")", element);
		while (!element.isDisplayed()) {
			if (!element.isDisplayed()) {
				act.moveToElement(element);
			}

		}
	}

}
