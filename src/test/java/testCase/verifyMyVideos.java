package testCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.landingPage;


public class verifyMyVideos {
	
	static WebDriver driver;

	  @BeforeMethod
	  public void beforeMethod() {
		  // browser set up
		  WebDriverManager.firefoxdriver().setup();
	      		  
		  driver = new FirefoxDriver();
	 
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	      driver.get("https://www.discovery.com/");
	 
	      driver.manage().window().maximize();
	     	 
	  }
	
	  @Test
	  public void test() throws InterruptedException {
		  // page object refernce for Discovery landing page 
	       landingPage LandingPage = PageFactory.initElements(driver, landingPage.class);  
	      // select favorite video 
	       LandingPage.selectVideos();
	       // verify video in MyVideo section
	       LandingPage.VerifyMyVideos();
	 
	  }
	 
	  @AfterMethod
	  public void afterMethod() {
	      // killing browser after test completion 
		  if (driver != null) {
	            driver.quit();
	        }
	  }

}
