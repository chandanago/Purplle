package com.purplle.EndToEndTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.purplle.genericLibrary.BaseClass;
import com.purplle.genericLibrary.ExcelUtility;
import com.purplle.genericLibrary.ListenerImplementationClass;
import com.purplle.pomRepository.CartPage;

@Listeners(ListenerImplementationClass.class)
/**
 * 
 * 
 * @author chandana R
 *
 */
public class TC_Purplle_003 extends BaseClass {
	/**
	 * @Description login to application and verify cart details after adding product to cart through shopping categories
	 *  we can run the same script for multiple product
	 * 
	 */
	@Test(dataProvider = "ReadVariant1", dataProviderClass = ExcelUtility.class)
	public void addProductToCart_Test(String category, String subCategory, String brand, String productType, String product)  {
		CartPage shoppingPage = new CartPage(driver);
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
}	
