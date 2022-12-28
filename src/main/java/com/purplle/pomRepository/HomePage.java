package com.purplle.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	//--------------------------------------------------

	
	@FindBy(xpath="//span[contains(text(),'FACEBOOK')]")
	private WebElement facebookBtn;	
	
	@FindBy(xpath="//div[@class='pp-g df']/..//a[@class='pr']/../..//i[contains(@class,'p-basket')]")
	private WebElement addToCartIcon;	
	
	@FindBy(name="email")
	private WebElement emailAddressTxtField;	
	
	@FindBy(name="pass")
	private WebElement passwordTxtField;	
	
	@FindBy(name="login")
	private WebElement loginBtn;
	

	@FindBy(xpath="//i[contains(@class,'p-smiley-hamburger')]")
	private WebElement profileIcon;

	@FindBy(xpath="//a[contains(@class,'sign-in')]")
	private WebElement signInLink;
	
	@FindBy(xpath="//span[contains(text(),'Logout')]")
	private WebElement logoutLink;
	
	public WebElement getLogoutLink() {
		return logoutLink;
	}
	
	public WebElement getProfileIcon() {
		return profileIcon;
	}
	public WebElement getSignInLink() {
		return signInLink;
	}
	public WebElement getEmailAddressTxtField() {
		return emailAddressTxtField;
	}
	
	public WebElement getPasswordTxtField() {
		return passwordTxtField;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public WebElement getAddToCartIcon() {
		return addToCartIcon;
	}
	
	public WebElement getFacebookBtn() {
		return facebookBtn;
	}

	//----------------------------------------------------- v

	public WebElement getCategoryLink(String category) {
		WebElement categoryLink = driver.findElement(By.xpath("//a[contains(text(),'"+category+"')]"));
		return categoryLink;
	}

	public WebElement getSubCategoryLink(String subCategory) {
		WebElement subCategoryLink = driver.findElement(By.xpath("(//a[contains(text(),'"+subCategory+"')])[1]"));
		
		return subCategoryLink;
	}

	public WebElement getProductType(String productType) {
		WebElement productTypeLink = driver.findElement(By.xpath("//a[contains(@data-evlbl,'"+productType+"')]"));
		return productTypeLink;
	}

	public WebElement getProductBrand(String subCategory, String brand) {
		WebElement productTypeLink = driver.findElement(By.xpath("(//a[contains(text(), '"+subCategory+"')]) [2]/../../..//a[contains(@href,'"+brand+"')]"));
		return productTypeLink;
	}
	public WebElement getBrand_SubCatLink(String subCategory) {
		WebElement Brand_SubCatLink = driver.findElement(By.xpath("(//a[contains(text(),'"+subCategory+"')])[2]"));
		return Brand_SubCatLink;
	}
}
