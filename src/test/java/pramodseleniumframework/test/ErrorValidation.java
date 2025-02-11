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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pramodseleniumframework.pageobjects.CartPage;
import pramodseleniumframework.pageobjects.CheckoutPage;
import pramodseleniumframework.pageobjects.ConfirmationPage;
import pramodseleniumframework.pageobjects.LandingPage;
import pramodseleniumframework.pageobjects.ProductCatalogue;
import pramodseleniumframework.testComponent.BaseTest;
import pramodseleniumframework.testComponent.RetryTest;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=RetryTest.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue = landingPage.LoginIn("pandit22898qw@gmail.com", "Pmod@123");
		landingPage.ErrorLogin();
		Assert.assertEquals("Incorrect email or password.", landingPage.ErrorLogin());
		
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		

		String coat = "Banarsi Saree";
		
		ProductCatalogue productCatalogue = landingPage.LoginIn("ppendeavour@gmail.com", "Pramod@123");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProduct(coat);
		productCatalogue.addProductToCart(coat);
		Thread.sleep(1000);
		CartPage cartPage = productCatalogue.clickOnCart();
		
		boolean match = cartPage.getCartProducts("ZARA COAT");
		Assert.assertFalse(match);
		
		
		
	}
	
	
	
	
	
}
