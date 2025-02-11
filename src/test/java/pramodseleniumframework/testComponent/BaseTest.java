package pramodseleniumframework.testComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pramodseleniumframework.pageobjects.LandingPage;

public class BaseTest {
	
		public WebDriver driver;
		public LandingPage landingPage;
		
		
		//Initialize Driver
		public WebDriver Initialization() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\pramodseleniumframework\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") :  prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) 
			{
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
		
	}
		
		//Convert Json to Hashmap
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
			
			String jsonContent =
					FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<HashMap<String,String>> data=
					mapper.readValue( jsonContent, new TypeReference< List<HashMap<String,String>> >(){} );
			
			return data;
		}
		
		
		//Take Screenshot
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//Screen//"+ testCaseName + ".png");
			
			FileUtils.copyFile(src, file);
			
			return System.getProperty("user.dir")+"//Screen//"+ testCaseName + ".png";
			
		}
		
		
		
		//Launch URL
		@BeforeMethod(alwaysRun=true)
		public LandingPage LaunchApplication() throws IOException {
			
			Initialization();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
			
			
		}
	
		//close browser
		@AfterMethod(alwaysRun=true)
		public void closeBrowser() {
			
			driver.close();
			
		}
}
