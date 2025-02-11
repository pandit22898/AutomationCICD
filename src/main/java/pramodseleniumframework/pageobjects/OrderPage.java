package pramodseleniumframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodseleniumframework.abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver){
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> OrderProducts;
	
	public Boolean OrderedProductName(String coat) {
		
		Boolean AnyMatch = OrderProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(coat));
		
		return AnyMatch;
		
	}
	
	
	
}
