package BusinessworkFlowLibrary;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;
import com.purplle.genericLibrary.BaseClass;
import com.purplle.genericLibrary.ExcelUtility;
import com.purplle.genericLibrary.ListenerImplementationClass;
import com.purplle.genericLibrary.PropertiesClass;
import com.purplle.genericLibrary.WebDriverUtilityClass;
import com.purplle.pomRepository.CartPage;
import com.purplle.pomRepository.HomePage;
import com.purplle.pomRepository.ProductBrandPage;
import com.purplle.pomRepository.ProductPage;

public class ReusableClass {

	public static HashMap<String, String> readData() {
		HashMap<String, String> map = new HashMap<String, String>();
		ExcelUtility excelData = new ExcelUtility();
		PropertiesClass property = new PropertiesClass();

		map.put("URL", property.readDataFromProperty("URL"));
		map.put("emailAddress", property.readDataFromProperty("emailAddress"));
		map.put("password", property.readDataFromProperty("password"));
		map.put("prodBrandPage", property.readDataFromProperty("ProductBrandPage"));
		map.put("itemAddedToCart_Overlay", excelData.readDataFromExcel("addToCart", 1, 6));
		map.put("enterPinCode", excelData.readDataFromExcel("addToCart", 2, 6));
		map.put("PinCodeSuccessful_Msg", excelData.readDataFromExcel("addToCart", 3, 6));
		map.put("shoppingCartUrl", property.readDataFromProperty("ShoppinCart_Url"));
		map.put("ProductPage", property.readDataFromProperty("ProductPage"));
	
		return map;
	}


	
	public void existUserLogin(WebDriver driver) {
		
		HomePage homePage = new HomePage(driver);
		WebDriverUtilityClass webDriverUtil = new WebDriverUtilityClass();
		String parentWnd = driver.getWindowHandle();
		//webDriverUtil.clickDisabledElement(homePage.getProfileIcon(), driver);
		homePage.getProfileIcon().click();
		homePage.getSignInLink().click();
		homePage.getFacebookBtn().click();
		
				webDriverUtil.switchToWindowWithTitle(driver, "Log in to Facebook | Facebook");
				homePage.getEmailAddressTxtField().sendKeys(readData().get("emailAddress"));
				homePage.getPasswordTxtField().sendKeys(readData().get("password"));
				homePage.getLoginBtn().click();
				
				driver.switchTo().window(parentWnd);
		
	}
	
	/**
	 * 
	 * This method is used to navigating through sub category page, select a product and navigate to cart page
	 * @param category
	 * @param subCategory
	 * @param productType
	 * @param brand
	 * @param product
	 * @param driver
	 */
	public void productSearchWorkFlowThroughShopCategory(String category, String subCategory, String productType, String brand, String product, WebDriver driver)  {

		HomePage homePage = new HomePage(driver);
		WebDriverUtilityClass webDriverUtil = new WebDriverUtilityClass();
		ReusableClass rUtil = new ReusableClass();

		ProductBrandPage brandPage = new ProductBrandPage(driver);
		ProductPage productPage = new ProductPage(driver);
		//verify home page is displayed
		webDriverUtil.explicitlyWaitForUrl(driver, readData().get("URL"));
		Assert.assertEquals(driver.getCurrentUrl(),readData().get("URL"),"given url is not entered in the address bar");
		ListenerImplementationClass.test.log(Status.INFO, "Home page is displayed successfully");

		existUserLogin(driver);
				
		//click on sunCategory link
	    webDriverUtil.explicitlyWaitForElementClick(driver, homePage.getCategoryLink(category));
		webDriverUtil.clickDisabledElement(homePage.getCategoryLink(category), driver);
	
		//click on skincare link
		webDriverUtil.clickDisabledElement(homePage.getSubCategoryLink(subCategory), driver);
		
		//click on lipbalm link
				webDriverUtil.clickDisabledElement(homePage.getProductType(productType), driver);
		
		//click on mamaearth brand
		webDriverUtil.explicitlyWaitForElement(driver, brandPage.getSelectBrand(brand));
	    brandPage.getSelectBrand(brand).click();      

		String selectedProductOnHomePage = null;
		try {
			selectedProductOnHomePage = brandPage.getSelectProduct(product).getAttribute("alt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//click on expected product "mamaearth lip-tint" and verify it navigating to product page 
		webDriverUtil.clickDisabledElement(productPage.getSelectProduct(product), driver);
		Assert.assertTrue(driver.getCurrentUrl().contains(readData().get("ProductPage")), "different product page url is displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Expected product page url is displayed successfully");

		//verify expected product is displayed
		//String productPrice = productPage.getProductPrice(product).getText();  
		String expectedProduct = productPage.getSelectProduct(product).getAttribute("alt");
		String[] selectedProductOnHomePage1 = selectedProductOnHomePage.split("-");
		Assert.assertTrue(expectedProduct.contains(selectedProductOnHomePage1[0]), "different product is displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Expected product is displayed successfully");

		//click on "addToCart"
		if(productPage.getGoTocartBtn().isDisplayed()==true) {
			productPage.getGoTocartBtn().click();
		}else {
		webDriverUtil.scrollBy(1356, 493, driver);
		productPage.getGoTocartBtn().click();}
		//verify item added to cart page overlay is displayed
		Assert.assertEquals(productPage.getItemAddedToYourCart_Overlay().getText(), readData().get("itemAddedToCart_Overlay"), "overlay is not displayed");
		ListenerImplementationClass.test.log(Status.INFO, "Overlay is displayed successfully");
		//click on viewcart button
		webDriverUtil.clickDisabledElement(productPage.getViewCartBtn(), driver);
		webDriverUtil.explicitlyWaitForUrl(driver, readData().get("shoppingCartUrl"));

	}

/**
 * fetching TotalMrp price and converting to double type
 * @param driver
 * @return
 */
	public double totalMrpPrice(WebDriver driver) {	
		CartPage shoppingPage = new CartPage(driver);
		String totalMrp_Price = shoppingPage.getTotalMrp_Price().getText();
		String totalMrpPrice1 = totalMrp_Price.substring(1, 4);
		double totalMrpPrice2 = Double.parseDouble(totalMrpPrice1);
		return totalMrpPrice2;
	}

	/**
	 * fetching Saving On Mrp price and converting to double type
	 * @param driver
	 * @return
	 */
	public double savingOnMrpPrice(WebDriver driver) {		
		CartPage shoppingPage = new CartPage(driver);
		String SavingOnMrp_Price = shoppingPage.getSavingOnMrp_price().getText();
		String SavingOnMrp_Price1 = SavingOnMrp_Price.substring(2, 4);
		double SavingOnMrp_Price2 = Double.parseDouble(SavingOnMrp_Price1);
		return SavingOnMrp_Price2;
	}

	/**
	 * fetching Sub Total amount price and converting to double type
	 * @param driver
	 * @return
	 */
	public double subTotalAmt(WebDriver driver) {
		CartPage shoppingPage = new CartPage(driver);
		String SubTotal_Amt = shoppingPage.getSubTotal_Amt().getText();
		String SubTotal_Amt1 = SubTotal_Amt.substring(1, 4);
		double SubTotal_Amt2 = Double.parseDouble(SubTotal_Amt1);
		return SubTotal_Amt2;
	}

	/**
	 * fetching Shipping charge and converting to double type
	 * @param driver
	 * @return
	 */
	public double shippingCharge(WebDriver driver) {
		CartPage shoppingPage = new CartPage(driver);
		String shipping_Charge = shoppingPage.getShippingCharge().getText();
		
		
		String shippingCharge1 = shipping_Charge.substring(1, 3);
		double shippingCharge2 = Double.parseDouble(shippingCharge1);
		return shippingCharge2;
	}

	/**
	 * fetching order total price and converting to double type
	 * @param driver
	 * @return
	 */
	public double orderTotalPrice(WebDriver driver) {
		CartPage shoppingPage = new CartPage(driver);
		String orderTotal_Price = shoppingPage.getOrderTotal_price().getText();
		String orderTotalPrice1 = orderTotal_Price.substring(1, 4);
		double orderTotalPrice2 = Double.parseDouble(orderTotalPrice1);
		return orderTotalPrice2;
	}

	/**
	 * 
	 * This method is used to navigating through Brand page, select a product and navigate to cart page
	 * @param category
	 * @param subCategory
	 * @param productType
	 * @param brand
	 * @param product
	 * @param driver
	 */

	public void productsearchworkFlowThroughBands(String category, String subCategory, String brand,String productType, String product, WebDriver driver) {

		PropertiesClass property = new PropertiesClass();
		WebDriverUtilityClass webDriverUtil = new WebDriverUtilityClass();
		ReusableClass rUtil = new ReusableClass();
		ExcelUtility excelData = new ExcelUtility();
		HomePage homePage = new HomePage(driver);

		String selectProdType = excelData.readDataFromExcel("removeFromCart", 1, 3);
		ProductBrandPage brandPage = new ProductBrandPage(driver);
		ProductPage productPage = new ProductPage(driver);
		//-------------------

		//verify home page is displayed
		webDriverUtil.explicitlyWaitForUrl(driver, rUtil.readData().get("URL"));
		Assert.assertEquals(driver.getCurrentUrl(), rUtil.readData().get("URL"),"given url is not entered in the address bar");
		ListenerImplementationClass.test.log(Status.INFO, "Home page is displayed successfully");
		//click on BRANDS link
		homePage.getCategoryLink(category).click();
		//click on featured link
		webDriverUtil.clickDisabledElement(homePage.getBrand_SubCatLink(subCategory), driver);
		//click on product brand link
		homePage.getProductBrand(subCategory, brand).click();
		webDriverUtil.clickDisabledElement(brandPage.getBrand_ProductType(productType), driver);

		try {
			Thread.sleep(2000);
			webDriverUtil.clickDisabledElement(brandPage.getSelectProduct(product), driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//click on "AddToCart" button
		webDriverUtil.clickDisabledElement(productPage.getGoTocartBtn(), driver);
		//navigate back and click on "AddToCart" icon
		driver.navigate().back();
		driver.navigate().back();
		webDriverUtil.clickDisabledElement(homePage.getAddToCartIcon(), driver);
	} 
}