package pramodseleniumframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodseleniumframework.abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement uEmail;
	
	@FindBy(id="userPassword")
	WebElement uPwd;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="*[class*='ng-trigger-flyInOut']")
	WebElement ErrorM;
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	public String ErrorLogin() {
		
		waitForElementToBeVisibleEle(ErrorM);
		return ErrorM.getText();
		
	}
	
	
	public ProductCatalogue LoginIn(String Email, String Pwd) {
		
		uEmail.sendKeys(Email);
		uPwd.sendKeys(Pwd);
		login.click();
		
		return new ProductCatalogue(driver);
	}
	
	
	
	

}
