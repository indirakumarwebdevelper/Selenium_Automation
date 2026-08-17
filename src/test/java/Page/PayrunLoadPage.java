package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PayrunLoadPage {

	WebDriver driver;
	
	By payrunicon = By.xpath("//a[@class='Id_1 Id__12 nav-link d-md-block']");
	By dropdown = By.xpath("//button[@class='btn dropdown-toggle btn-light']");
	By dropdownsearch = By.xpath("//input[@type='search']");
	
	
	public PayrunLoadPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void payruniconclick() {
		
		driver.findElement(payrunicon).click();
	}
	
	public void dropdownselect () {
		
		driver.findElement(dropdown).click();
	}
	
	public void dropsearch(String Searchvalue) {
		
		driver.findElement(dropdownsearch).sendKeys(Searchvalue);	
		driver.findElement(dropdownsearch).click();
		
	
	}
	
	
	
}
