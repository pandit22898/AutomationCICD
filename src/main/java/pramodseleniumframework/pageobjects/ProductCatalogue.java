package pramodseleniumframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodseleniumframework.abstractcomponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="div.card-body")
	List<WebElement> products;
	
	@FindBy(css="div#toast-container")
	WebElement toast;
	
	@FindBy(css="div#toast-container")
	WebElement spinner;
	
	
	
	By ProductBy = By.cssSelector("div.card-body");
	By ProductN = By.cssSelector("button:last-of-type");
	
	
	
	public List<WebElement> getProductList() {
		
		waitForElementToBeVisible(ProductBy);
		return products;
	}

	public WebElement getProduct(String coat) {
		
	
		WebElement prod = getProductList().stream().filter
			(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(coat)).findFirst().orElse(null);
		
		return prod;
		
	}
	
	
	public void addProductToCart(String coat) {
		
		WebElement prod = getProduct(coat);
		prod.findElement(ProductN).click();	
		waitForElementToBeInvisible(toast);
		waitForElementToBeInvisible(spinner);
		waitForElementToBeClickable();
		
		
		
		
	}
	

}
