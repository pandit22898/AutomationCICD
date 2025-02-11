package pramodseleniumframework.pageobjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pramodseleniumframework.abstractcomponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css="section.ta-results")
	WebElement CountryDpDn;
	
	@FindBy(css="section.ta-results button:last-of-type")
	WebElement SelectCountry;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement Submit;
	
	
	public void SelectCountry(String country) {
		
		Country.sendKeys(country);
		waitForElementToBeVisibleEle(CountryDpDn);
		SelectCountry.sendKeys(Keys.ENTER);
		
	}
	
	public ConfirmationPage PlaceOrder() throws InterruptedException, IOException {
		
		waitForWebElementToBeClickable(Submit);
		Actions a = new Actions(driver);
		a.moveToElement(Submit, -10, -15).click().build().perform();
		
		return new ConfirmationPage(driver);
		
		
	}
	
	
	
	
	
}
