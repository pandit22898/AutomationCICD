package pramodseleniumframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodseleniumframework.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(className="infoWrap")
	List<WebElement> CartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public boolean getCartProducts(String coat) {
		
		boolean match = CartProducts.stream().anyMatch(CartP->CartP.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(coat));
		return match;
	
	}
	
	public CheckoutPage checkoutFromCart() {
		
		checkout.sendKeys(Keys.ENTER);
		
		return new CheckoutPage(driver);
		
		
	}
	
	
}
