package Payroll_demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Logindmeo {

    public static void main(String[] args) throws InterruptedException {

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
    	   	
        // launch the browser:
        
      WebDriver driver = new ChromeDriver(options);
      
      driver.get("https://test2.2growhr.info/");
      driver.manage().window().maximize();
      
      driver.findElement(By.name("UserName")).sendKeys("san123");
      driver.findElement(By.name("Password")).sendKeys("san123");
      driver.findElement(By.xpath("//button[@type='submit']")).click();
      
      Thread.sleep(10);
            
      
    }
}