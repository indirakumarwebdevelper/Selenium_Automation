package Tests;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Page.LoginPage;
import TestData.LoginData;

/*public class LoginTest extends BaseTest{

	LoginPage  loginpage;
	
	
	@BeforeMethod
	public void startbrowser() {
		
		setup();
		
		loginpage = new LoginPage(driver);
		
	}
	
	@Test(dataProvider = "loginData",dataProviderClass = LoginData.class)
	public void logincase(String Username, String Password) throws InterruptedException {
		
		loginpage.login(Username,Password);
		
		String verifyloginvalue = loginpage.verifylogin();
		
		Assert.assertEquals(verifyloginvalue, "Dashboard", "Login Failed......Case FAIL");
		
		//loginpage.logout();
		
	}
	
	@AfterMethod
	public void closebrowsers() {
		
		closebrowser();
	}
}*/


public class LoginTest extends BaseTest {

 /*   @BeforeMethod
    public void startBrowser() {
        setup();
    }

    @Test(dataProvider = "loginData",
          dataProviderClass = LoginData.class)
    public void loginCase(String username, String password) {

        login(username, password);
    }

    @AfterMethod
    public void closeBrowser() {
    	closebrowser();
    }*/
}


