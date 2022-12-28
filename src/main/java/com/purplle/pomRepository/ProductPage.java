package com.purplle.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	public WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//a[@class='banner_item active']")
	private WebElement selectFlavour;

	@FindBy(xpath="(//a[contains(@class,'mat-button-base ng-st')])[1]")
	private WebElement goTocartBtn;
	
	@FindBy(xpath="//div[@class='nudgesBxR t-left']/..//span[contains(@class,'dib fanB')]")
	private WebElement itemAddedToYourCart_Overlay;
	
	@FindBy(xpath="//div[@class='nudgesBxR t-left']/..//span[contains(text(),'View Cart')]")
	private WebElement viewCartBtn;
	
	/*@FindBy(xpath="(//span[contains(text(),'With Rose & Castor Oil')])[2]/following::tr[1]//td[1]")
	private WebElement productPrice; */
	
	
	//-----------------------------------------------------------------------------------
	
	
	
	public WebElement getProductPrice(String productName) {
		WebElement productPrice = driver.findElement(By.xpath("(//span[contains(text(),'"+productName+"')])[2]/following::tr[1]//td[1]"));
		return productPrice;
	}
	
	public WebElement getSelectProduct(String productName) {

		WebElement selectProduct = driver.findElement(By.xpath("//img[contains(@alt,'"+productName+"')]"));
		return selectProduct;
	}
	
	

	public WebElement getGoTocartBtn() {
		return goTocartBtn;
	}

	public WebElement getSelectFlavour() {
		return selectFlavour;
	}

	public WebElement getItemAddedToYourCart_Overlay() {
		return itemAddedToYourCart_Overlay;
	}

	public WebElement getViewCartBtn() {
		return viewCartBtn;
	}
	
	
	@FindBy(xpath="//span[contains(text(),'Babila Bling Hair Dryer 1100W')]")
	private WebElement selectHairDryerProduct1;
	
	public void getselectHairDryerProduct1() {
		selectHairDryerProduct1.click();
	}
	
	@FindBy(xpath="//span[contains(text(),'949')]")
	private WebElement selectHairDryerProduct1Price;

	public WebElement getSelectHairDryerProduct1Price() {
		return selectHairDryerProduct1Price;
	}
	//verify product img alternative text
}
