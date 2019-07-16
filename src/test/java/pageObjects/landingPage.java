package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class landingPage {

	
	 final WebDriver driver;
	 
	 @FindBy(how = How.XPATH, using = "//div[contains(text(),'Recommended')]")
	 public WebElement recommended_label;
	 
	 @FindBy(how = How.XPATH, using = "//i[@class='icon-menu']")
	 public WebElement iconMenu;
	 
	 @FindBy(how = How.XPATH, using = "//span[contains(text(),'My Videos')]")
	 public WebElement myVideosLabel ;
	 
	 @FindBy(how = How.XPATH, using = "//section[5]//div[1]//div[1]//div[1]//div[1]//div[1]//section[1]//div[3]//a[1]//div[1]//div[2]//div[1]//span[1]//i[1]")
	 public WebElement recommended_video1_adf_button;
	 
	 @FindBy(how = How.XPATH, using = "//section[5]//div[1]//div[1]//div[1]//div[1]//div[2]//section[1]//div[3]//a[1]//div[1]//div[2]//div[1]//span[1]//i[1]")
	 public WebElement recommended_video2_adf_button ;
	 
	 @FindBy(how = How.XPATH, using = "//h2[@class='localStorageCarousel__heading']")
	 public WebElement fvSection ;
	 
	 @FindBy(how = How.XPATH, using = "//div[contains(text(),'Shark Week')]")
	 public WebElement fvVideo1Title ;
	 
	 @FindBy(how = How.XPATH, using = "	//h3[contains(@class,'showTileSquare__title')]//div[contains(text(),'Naked and Afraid XL')]")
	 public WebElement fvVideo2Title ;
	 
	 
	 	 
	public landingPage(WebDriver driver){ 
	 
	    this.driver = driver; 
	 
	    } 
	
	public void selectVideos() { 
		// scroll till recommended section
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommended_label);
		
		Actions action = new Actions(driver);

		// scroll to first available favorite video
		action.moveToElement(driver.findElement(By.xpath("//body[@class='eos-primaryImagesLoaded auto-play-video desktop']/div/div[contains(@class,'app-main app__main')]/div[contains(@class,'page-wrapper app__pageWrapper')]/section[5]/div[1]/div[1]/div[1]/div[1]/div[1]"))).build().perform();
		
		// select first favorite video
		recommended_video1_adf_button.click();

		// scroll to second available favorite video
		action.moveToElement(driver.findElement(By.xpath("//body[contains(@class,'eos-primaryImagesLoaded')]/div/div[contains(@class,'app__main')]/div[contains(@class,'app__pageWrapper')]/section[5]/div[1]/div[1]/div[1]/div[1]/div[1]/section[1]/div[2]"))).build().perform();

		// select second favorite video
		recommended_video2_adf_button.click();
		
	 
	    } 
	
	public void VerifyMyVideos(){ 

		// go to my video via grid 

		iconMenu.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOf(myVideosLabel));
		
		myVideosLabel.click();
		
		// Verify my favorite video section is present 
		if(fvSection.isDisplayed()){
			
			// scroll to my favorite video section
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fvSection);

			Actions action = new Actions(driver);
			
			// scroll to first available favorite video
			action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'myVideosLayout__localStorageCarousels')]//div[contains(@class,'content carousel__content showCarousel__content')]//div[1]//section[1]//div[3]"))).build().perform();
			
			// verify first video title 
			Assert.assertEquals("SHARK WEEK", fvVideo1Title.getText().toUpperCase());
			
			// scroll to second available favorite video
			action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'myVideosLayout__localStorageCarousels')]//div[2]//section[1]//div[3]"))).build().perform();

			// verify second video title 
			Assert.assertEquals("NAKED AND AFRAID XL", fvVideo2Title.getText().toUpperCase());
						
		}
			
	    } 
}