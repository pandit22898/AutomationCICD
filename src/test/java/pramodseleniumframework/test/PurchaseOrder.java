package pramodseleniumframework.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pramodseleniumframework.pageobjects.CartPage;
import pramodseleniumframework.pageobjects.CheckoutPage;
import pramodseleniumframework.pageobjects.ConfirmationPage;
import pramodseleniumframework.pageobjects.LandingPage;
import pramodseleniumframework.pageobjects.OrderPage;
import pramodseleniumframework.pageobjects.ProductCatalogue;
import pramodseleniumframework.testComponent.BaseTest;

public class PurchaseOrder extends BaseTest {
	
	public String coat = "IPHONE 13 PRO";
	
	
	@Test(dataProvider="getData3",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue = landingPage.LoginIn(input.get("email"), input.get("pwd"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProduct(input.get("product"));
		productCatalogue.addProductToCart(input.get("product"));
		Thread.sleep(1000);
		CartPage cartPage = productCatalogue.clickOnCart();
		
		boolean match = cartPage.getCartProducts(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.checkoutFromCart();

		checkoutPage.SelectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.PlaceOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void verifyOrderProduct() {
		
		ProductCatalogue productCatalogue = landingPage.LoginIn("pandit22898@gmail.com", "Pramod@123");
		OrderPage orderPage = productCatalogue.clickOnOrderPage();
		Assert.assertTrue(orderPage.OrderedProductName(coat));
		
		
		
	}
	
	
	
	@DataProvider
	public Object[][] getData3() throws IOException {
		
	List<HashMap<String, String>> data = 
			getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\pramodseleniumframework\\data\\PurchaseOrder.json");
		
	return new Object[][] { {data.get(0)} , {data.get(1)} };
	
	
	}
	
	
	@DataProvider
	public Object[][] getData2() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "pandit22898@gmail.com");
		map.put("pwd", "Pramod@123");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "ppendeavour@gmail.com");
		map1.put("pwd", "Pramod@123");
		map1.put("product", "QWERTY");
		
		return new Object[][] {{map},{map1}};
		
	}
	
	

	@DataProvider
	public Object[][] getData1() {
		
		return new Object[][] {{"pandit22898@gmail.com", "Pramod@123","ADIDAS ORIGINAL"},{"ppendeavour@gmail.com", "Pramod@123","QWERTY"}};
		
	}
	
	
	

}
