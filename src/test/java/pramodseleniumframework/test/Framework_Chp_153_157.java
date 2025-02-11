package pramodseleniumframework.test;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Framework_Chp_153_157 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client/");
		String coat = "ZARA COAT 3";
		
		driver.findElement(By.id("userEmail")).sendKeys("pandit22898@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pramod@123");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.card-body")));
		
		List<WebElement> products = driver.findElements(By.cssSelector("div.card-body"));
		
		WebElement prod = products.stream().filter
			(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(coat)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> CartProducts = driver.findElements(By.className("infoWrap"));
		boolean match = CartProducts.stream().anyMatch(CartP->CartP.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(coat));
		Assert.assertTrue(match);

		
		driver.findElement(By.cssSelector(".totalRow button")).sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("section.ta-results"))));
		driver.findElement(By.cssSelector("section.ta-results button:last-of-type")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
	}

}
