package com.purplle.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.purplle.genericLibrary.WebDriverUtilityClass;

public class CartPage {
	public WebDriver driver;


	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[contains(@alt,'Castor Oil For 12 Hour Moisturization (2 g)')]/../../..//span[@class='pr t-1']/..")
	private WebElement product_price;

	@FindBy(xpath = "//input[@placeholder='Enter Pincode'] ")
	private WebElement enterPinCode_TextField;

	@FindBy(xpath = "//a[contains(text(),'Check Delivery')]")
	private WebElement checkDelivery_link;

	@FindBy(xpath = "//p[contains(text(),'Get delivery')]")
	private WebElement picodeSuccessfulMsg;
	
	@FindBy(xpath = "//div[contains(text(),' Total MRP: ')]/..//div[2]")
	private WebElement totalMrp_Price;

	@FindBy(xpath = "//div[contains(text(),'Saving on MRP')]/following-sibling::div")
	private WebElement savingOnMrp_price;

	@FindBy(xpath = "//div[contains(text(),' Subtotal')]/following::div[1]")
	private WebElement subTotal_Amt;

	//--------------------------------------------------------------------------------------
	@FindBy(xpath = "//div[contains(text(),'Shipping charges')]/following::div[1]")
	private WebElement shippingCharge;
	
	@FindBy(xpath = "//div[contains(text(),'Order total')]/following::div[1]")
	private WebElement orderTotal_price;

	@FindBy(xpath = "//a[contains(text(),'PLACE ORDER')]")
	private WebElement placeOrder_btn;

	@FindBy(xpath = "//p[contains(text(),'There are no items in this cart')]")
	private WebElement thereIsNoItemTxt;

	
	 
	
	//--------------------------------------------------------------
	


	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getPicodeSuccessfulMsg() {
		return picodeSuccessfulMsg;
	}
	
	public WebElement getThereIsNoItemTxt() {
		return thereIsNoItemTxt;
	}

	public WebElement getSelectProduct(String productName) {

		WebElement selectProduct = driver.findElement(By.xpath("//img[contains(@alt,'"+productName+"')]"));
		return selectProduct;
	}

	public WebElement getProduct_price(String productName) {
		
		WebElement productprice = driver.findElement(By.xpath("//img[contains(@alt,'"+productName+"')]/../../..//span[@class='pr t-1']/.."));
		return productprice;
	}

	public WebElement getRemoveProductBtn(String productName) {
		
		WebElement clickOnRmvBtn = driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]/..//a[contains(text(),'Remove')]"));
		return clickOnRmvBtn;
	}

	
	
	public WebElement getEnterPinCode_TextField() {
		return enterPinCode_TextField;
	}

	public WebElement getCheckDelivery_link() {
		return checkDelivery_link;
	}

	public WebElement getTotalMrp_Price() {
		return totalMrp_Price;
	}

	public WebElement getSavingOnMrp_price() {
		return savingOnMrp_price;
	}

	public WebElement getSubTotal_Amt() {
		return subTotal_Amt;
	}

	public WebElement getShippingCharge() {
		return shippingCharge;
	}

	public WebElement getOrderTotal_price() {
		return orderTotal_price;
	}

	public WebElement getPlaceOrder_btn() {
		return placeOrder_btn;
	}
}


/*
error message before adding miko3
text = "You must add Miko3 in order to proceed with checkout!"
*/