package com.purplle.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductBrandPage {

	public WebDriver driver;

	public ProductBrandPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getSelectBrand(String brand) {

		WebElement selectBrand = driver.findElement(By.xpath("//button[contains(text(),'"+brand+"')]"));
		return selectBrand;
	}

	public WebElement getSelectProduct(String productName) {

		WebElement selectProduct = driver.findElement(By.xpath("(//img[contains(@alt,'"+productName+"')])[1]"));
		return selectProduct;
	}
	
	public WebElement getBrand_ProductType(String productType) {

		WebElement selectBrand_ProductType = driver.findElement(By.xpath("//button[contains(text(),'"+productType+"')]"));
		return selectBrand_ProductType;
	}
}
