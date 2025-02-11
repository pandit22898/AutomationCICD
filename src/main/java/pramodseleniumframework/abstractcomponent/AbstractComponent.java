package pramodseleniumframework.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pramodseleniumframework.pageobjects.CartPage;
import pramodseleniumframework.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")		
	WebElement Order;
			
	
	By cartBy = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	
	public void waitForElementToBeVisible(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToBeVisibleEle(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	public void waitForElementToBeInvisible(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
		
	}
	
	public void waitForElementToBeClickable() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
		wait.until(ExpectedConditions.elementToBeClickable(cartBy));
		
	}
	
		public void waitForWebElementToBeClickable(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	
		
		
	}
	
	public CartPage clickOnCart() {
		
		waitForElementToBeVisibleEle(cart);
		cart.click();
		return new CartPage(driver);
		
	}
	
	public OrderPage clickOnOrderPage() {
		
		Order.click();
		return new OrderPage(driver);
		
	}
	
}
