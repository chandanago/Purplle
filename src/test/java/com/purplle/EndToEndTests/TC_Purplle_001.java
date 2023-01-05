package com.purplle.EndToEndTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.purplle.genericLibrary.BaseClass;
import com.purplle.genericLibrary.ListenerImplementationClass;
import com.purplle.pomRepository.CartPage;
import com.purplle.pomRepository.HomePage;


@Listeners(ListenerImplementationClass.class)
/**
 * 
 * 
 * @author chandana R
 *
 */
public class TC_Purplle_001 extends BaseClass {
/**
 * @Description login to application and verify cart details after adding product to cart through shopping categories
 *  we can run the same script for multiple product
 * 
 */
	@Test(dataProvider = "Data")
	public void addProductToCart_Test(String category, String subCategory, String productType, String brand, String product)  {

		CartPage shoppingPage = new CartPage(driver);

		// navigating to add to cart page
		rUtil.productSearchWorkFlowThroughShopCategory(category, subCategory, productType, brand, product, driver);

		//verify shopping cart page is displayed
		Assert.assertEquals(driver.getCurrentUrl(), rUtil.readData().get("shoppingCartUrl"),"different url displayed for shopping cart page");
		ListenerImplementationClass.test.log(Status.INFO, "Shopping Cart page is displayed successfully");

		//Verify expected product is displayed
		String actualProduct = shoppingPage.getSelectProduct(product).getAttribute("alt");
		Assert.assertTrue(actualProduct.contains(product),"different product is displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Expected product is displayed successfully");

		//enter PinCode in pincode textField and verify pincode successful message
		shoppingPage.getEnterPinCode_TextField().sendKeys(rUtil.readData().get("enterPinCode"));
		shoppingPage.getCheckDelivery_link().click();
		Assert.assertTrue(shoppingPage.getPicodeSuccessfulMsg().getText().contains(rUtil.readData().get("PinCodeSuccessful_Msg")), "different message displayed for pincode");
		ListenerImplementationClass.test.log(Status.INFO, "Expected pincode successfull message is displayed successfully");

		//product price and Total order price calculation	
		double totalMrpPrice = rUtil.totalMrpPrice(driver);
		double SavingOnMrp_Price = rUtil.savingOnMrpPrice(driver);
		double SubTotal_Amt = rUtil.subTotalAmt(driver);
		double shippingCharge = rUtil.shippingCharge(driver);
		double orderTotalPrice = rUtil.orderTotalPrice(driver);

		//calculating SubtotalAmt
		double expectedSubTotalPrice = totalMrpPrice-SavingOnMrp_Price;

		// calculating OrderTotalAmt which includes shipping charge and subtotal price
		double expectedOrderTotal = shippingCharge + expectedSubTotalPrice;

		//verifying subtotal Amount
		Assert.assertEquals(expectedSubTotalPrice, SubTotal_Amt, "different subtotal price is displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Expected subtotal price is displayed successfully");

		//Verifying order total price
		Assert.assertEquals(expectedOrderTotal, orderTotalPrice, "different order total price displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Expected order total price is displayed successfully");
		
		//Resetting to intial stage
		webDriverUtil.clickDisabledElement(shoppingPage.getRemoveProductBtn(product), driver);
		
	}	

/**
 * 
 * By using DataProvider we can run the same script with different data
 * @return
 */
	@DataProvider(name = "Data") 
	public String[][] enterText() {
		String category = excelData.readDataFromExcel("addToCart", 1, 0);
		String subCategory = excelData.readDataFromExcel("addToCart", 1, 1);
		String productType = excelData.readDataFromExcel("addToCart", 1, 2);
		String brand = excelData.readDataFromExcel("addToCart", 1, 3);
		String brand1 = excelData.readDataFromExcel("addToCart", 2, 3);  //maybelline
		String product = excelData.readDataFromExcel("addToCart", 1, 4);
		String product1 = excelData.readDataFromExcel("addToCart", 2, 4);  //lipbalm
		return new String[][] { {category, subCategory, productType, brand, product}};
	}
}
