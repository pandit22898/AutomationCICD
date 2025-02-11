package pramodseleniumframework.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pramodseleniumframework.pageobjects.CartPage;
import pramodseleniumframework.pageobjects.CheckoutPage;
import pramodseleniumframework.pageobjects.ConfirmationPage;
import pramodseleniumframework.pageobjects.LandingPage;
import pramodseleniumframework.pageobjects.ProductCatalogue;
import pramodseleniumframework.testComponent.BaseTest;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on login page")
	public void Landed_On_Login_Page() throws IOException {
		
		landingPage =LaunchApplication();
		
	}
	
	
	@Given("^I Logged in with username (.+) and password (.+)$")
	public void Logged_In_With_User_Pwd(String Uname, String Pwd) {

		productCatalogue = landingPage.LoginIn(Uname, Pwd);
	
		
	}
	
	 
	@When("^I Added Product (.+) to Cart$")
	public void Added_Product_To_Cart(String ProductName) throws InterruptedException {
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProduct(ProductName);
		productCatalogue.addProductToCart(ProductName);
		Thread.sleep(1000);

		
	}
	
	 
	@And("^Checkout (.+) and Submit the order$")
	public void Checkout_And_Submit_Order(String ProductName) throws InterruptedException, IOException {
		
		cartPage = productCatalogue.clickOnCart();
		
		boolean match = cartPage.getCartProducts(ProductName);
		Assert.assertTrue(match);
		
		checkoutPage = cartPage.checkoutFromCart();

		checkoutPage.SelectCountry("india");
		confirmationPage = checkoutPage.PlaceOrder();

		
	}
	
	@Then("{string} message shoule be displayed")
	public void Confirmation_Message_Displayed(String string) {
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		
		
	}
	
	
	@Then("{string} message should be displayed")
	public void Login_Error_Message_Displayed(String string) {
		
		landingPage.ErrorLogin();
		Assert.assertEquals(string, landingPage.ErrorLogin());
		driver.close();
		
		
	}
	
	
}
