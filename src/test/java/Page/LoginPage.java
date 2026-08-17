package Page;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	
	By username = By.name("UserName");
	By password = By.name("Password");
	By loginbtn =  By.xpath("//button[@type='submit']");
	By verifyloigntext = By.xpath("//h4[text() ='Dashboard']");
	By logoutbtn = By.xpath("(//*[name()='svg' and @viewBox='0 0 24 24'])[7]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
   public void enterusername(String usernamevalue) {
	   
	   driver.findElement(username).sendKeys(usernamevalue);
   }
   
   
   public void enterpassword(String passwordvalue) {
	   
	   driver.findElement(password).sendKeys(passwordvalue);
   }
   
   
   public void loginbtnclick() {
	   
	   driver.findElement(loginbtn).click();
   }
   
   
   public String  verifylogin() {
	   
	  WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(30));
	  @NonNull
	WebElement text = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(verifyloigntext));
	return  text.getText();
   }
   
   public void logout() {
	   
	   driver.findElement(logoutbtn).click();
   }
   
   
   public void login(String usernamevalue,String passwordvalue) {
	   
	  enterusername(usernamevalue);
	  enterpassword(passwordvalue);
	  loginbtnclick();
   }
   
  
	
}
