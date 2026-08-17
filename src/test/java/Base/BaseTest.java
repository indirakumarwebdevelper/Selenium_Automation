package Base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;   //test

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import Page.LoginPage;

 /*public class BaseTest {

	protected WebDriver driver;
	
	public void setup() {
		
		
		ChromeOptions options = new ChromeOptions();

        // Disable password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Hide "Chrome is being controlled by automated test software"
        options.setExperimentalOption(
                "excludeSwitches",
                Arrays.asList("enable-automation")
        );
    	   	
       driver = new ChromeDriver(options);
       driver.manage().window().maximize();
       driver.get("https://test2.2growhr.info/");
	   
       
	}
	
	public void closebrowser() {
		
		driver.quit();
	}
	
	
}*/



public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginpage;

    public void setup() {

        // ChromeOptions code

    	ChromeOptions options = new ChromeOptions();

        // Disable password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // Hide "Chrome is being controlled by automated test software"
        options.setExperimentalOption(
                "excludeSwitches",
                Arrays.asList("enable-automation")
        );
    	
    	
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://test2.2growhr.info/");

        loginpage = new LoginPage(driver);
    }

    public void login(String username, String password) {

        loginpage.login(username, password);

        String actualText = loginpage.verifylogin();

        Assert.assertEquals(
                actualText,
                "Dashboard",
                "Login Failed......Case FAIL"
        );
    }

    public void closebrowser() {
        driver.quit();
    }
}


