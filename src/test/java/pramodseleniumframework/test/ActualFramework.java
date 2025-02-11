package pramodseleniumframework.test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class ActualFramework extends BaseTest {
	
	public String coat = "IPHONE 13 PRO";
	@Test
	public void submitOrder() throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue = landingPage.LoginIn("pandit22898@gmail.com", "Pramod@123");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProduct(coat);
		productCatalogue.addProductToCart(coat);
		Thread.sleep(1000);
		CartPage cartPage = productCatalogue.clickOnCart();
		
		boolean match = cartPage.getCartProducts(coat);
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
	
	
	
	
	

}
