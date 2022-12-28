package com.purplle.EndToEndTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.purplle.genericLibrary.BaseClass;
import com.purplle.genericLibrary.ListenerImplementationClass;
import com.purplle.pomRepository.CartPage;

@Listeners(ListenerImplementationClass.class)
/**
 * 
 * 
 * @author chandana R
 *
 */

public class TC_Purplle_002 extends BaseClass {

/**
 * @Description verify the product removed from cart, by adding the product to cart through brands and navigating to cart through cart icon
 * @param category
 * @param subCategory
 * @param brand
 * @param productType
 * @param product
 */
	@Test(dataProvider = "EnterData")
	public void addProductToThecartAndVerifyTheCartThroghIcon(String category, String subCategory, String brand, String productType, String product) {
		CartPage shoppingPage = new CartPage(driver);
		//navigating to cart page
		rUtil.productsearchworkFlowThroughBands(category, subCategory, brand, productType, product, driver);

		//verify cart page is displayed
		Assert.assertEquals(driver.getCurrentUrl(),rUtil.readData().get("shoppingCartUrl") ,"different url displayed for shopping cart page");
		ListenerImplementationClass.test.log(Status.INFO, "Shopping Cart page is displayed successfully");
		String cartProduct = shoppingPage.getSelectProduct(product).getAttribute("alt");

		//verify expected product is displayed
		Assert.assertTrue(cartProduct.contains(product), "different product displayed");
		Assert.assertTrue(shoppingPage.getSelectProduct(product).isDisplayed(), "different product displayed");
		ListenerImplementationClass.test.log(Status.INFO, "expected product is displayed successfully");

		//verify product is removed from cart
		shoppingPage.getRemoveProductBtn(product).click();
		Assert.assertTrue(shoppingPage.getThereIsNoItemTxt().isDisplayed(), "product is not removed from cart");
		ListenerImplementationClass.test.log(Status.INFO, "expected product is removed successfully");
		
	} 

	/**
	 * 
	 * By using DataProvider we can run the same script with different data
	 * @return
	 */
	@DataProvider(name = "EnterData") 
	public String[][] enterdata() {
		String category = excelData.readDataFromExcel("removeFromCart", 1, 0);
		String subCategory = excelData.readDataFromExcel("removeFromCart", 1, 1);
		String brand = excelData.readDataFromExcel("removeFromCart", 1, 2);
		String productType = excelData.readDataFromExcel("removeFromCart", 1, 3);
		String product = excelData.readDataFromExcel("removeFromCart", 1, 4);
		//String product = excelData.readDataFromExcel("removeFromCart", 2, 4);

		return new String[][] { {category, subCategory, brand, productType, product} };                       
	}
}
