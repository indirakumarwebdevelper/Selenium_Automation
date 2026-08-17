package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Page.LoginPage;
import TestData.LoginData;



public class PayrunUiloadTest extends BaseTest {

    @BeforeMethod
    public void setupTest() {

        setup();
    }

    @Test(dataProvider = "loginData",
          dataProviderClass = LoginData.class)
    public void payrunTest(String username, String password) throws InterruptedException, AWTException {

        // Login using common login method
        login(username, password);

        // Now Payrun test starts
        System.out.println("Login completed");

        // Payrun code here
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@class='Id_1 Id__12 nav-link d-md-block']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    
     // division dropdown     
        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-light']")).click();
        List<WebElement> droplist = driver.findElements(By.xpath("(//ul[ @class='dropdown-menu inner show'])[1]//li"));
        
        for (WebElement webElement : droplist) {
			
        	System.out.println(webElement.getText());
        	
        	String dropdownlist = webElement.getText().trim();
        	
        	if(dropdownlist.contentEquals("SAN MEDIA PRIVATE LIMITED & Co")) {
        		
        		
        		webElement.click();
        		break;
        	}
    
        	}
        Thread.sleep(5000);

        
        // Month dropdown
       

        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle bs-placeholder btn-light']")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

       /* List<WebElement> monthList = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[contains(@class,'dropdown-menu') and contains(@class,'inner') and contains(@class,'show')]//li")
                )
        );*/

       driver.findElement(By.xpath("(//input[@class='form-control'])[2]")).sendKeys("August-2026");
       driver.findElement(By.xpath("//a[contains(@id,'bs-select-4')]")).click();
       
       driver.findElement(By.xpath("//button[text()='INITIATE PAYRUN']")).click();
       
       // 1st page
       
      // String Firstpagetext = driver.findElement(By.xpath("//li[@class='atten active fs-14  fw-5']")).getText();
       
       String Firstpagetext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='atten active fs-14  fw-5']"))).getText();
       
       if (Firstpagetext.equals("Attendance")) {
		
    	   // select all
         //  driver.findElement(By.xpath("//input[@id='selallchk']")).click();
    	   
    	//   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[1]"))).click();
    	   
    	// Wait for loader to disappear
    	   wait.until(ExpectedConditions.invisibilityOfElementLocated(
    	           By.id("globalLoader")
    	   ));

    	   // Wait for checkbox
    	   WebElement selectAll = wait.until(
    	           ExpectedConditions.visibilityOfElementLocated(
    	                   By.xpath("//input[@id='selallchk']")
    	           )
    	   );

    	   // Scroll to checkbox
    	   JavascriptExecutor js = (JavascriptExecutor) driver;

    	   js.executeScript(
    	           "arguments[0].scrollIntoView({block:'center', inline:'center'});",
    	           selectAll
    	   );

    	   // Wait for loader again
    	   wait.until(ExpectedConditions.invisibilityOfElementLocated(
    	           By.id("globalLoader")
    	   ));

    	   // Click checkbox using JavaScript
    	   js.executeScript("arguments[0].click();", selectAll);
    	   
    	   Actions actions = new Actions(driver);
    	   actions.scrollByAmount(0, 200).perform();
    	   
    	   
           
           //nextbutton
            driver.findElement(By.xpath("//button[@id='idnextbutton']")).click();
            
            
            //2nd page
            
            String secondndpagetext = driver.findElement(By.xpath("(//li[@class='atten active fs-14  fw-5'])[2]")).getText().trim();
            
            if (secondndpagetext.equals("Addition & Deduction")) {
    		
            	System.out.println("Enter into senond page of payrun");
            	
            	 actions.scrollByAmount(0, 400).perform();
            	 driver.findElement(By.xpath("//button[@id='nextToPre']")).click();
            	 
            	 // initiated job screen
            	 
            	//String initiatedjobtext = driver.findElement(By.xpath("//h1[contains(text(),'Pre-Calculation Job Initiated')]")).getText().trim();
            	
            	@NonNull
				WebElement initiatedjob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Pre-Calculation Job Initiated')]")));
            	String initiatedjobtext = initiatedjob.getText();
            	
            	
            	if (initiatedjobtext.equals("Pre-Calculation Job Initiated")) {
            		
            		System.out.println("Initiated job was run successfully");
            		
            		//viewstatusbtn
            		driver.findElement(By.xpath("(//button[@id='modalViewJobStatus'])[1]")).click();
            		
            		//Entered into pre-calculation stage
            		
            		String preCalculationText = driver.findElement(By.xpath("//h1[text()='Pre-Calculation Completed']")).getText();
            		
            		if (preCalculationText.equals("Pre-Calculation Completed")) {
						
            			System.out.println("Pre-calculation process completed....");
            			
            			// Proceed to Summary
            			wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[@onclick='proceedToSummary()']"))).click();
                        // Wait for confirmation popup
            			WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'swal2-confirm')]")));

            			confirmButton.click();

            			// Wait for SweetAlert popup to disappear
            			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".swal2-container")));

           			// Wait for application loader to disappear
            			wait.until(ExpectedConditions.invisibilityOfElementLocated( By.id("globalLoader")));
            			// Find View Summary
            			WebElement viewSummary = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='View Summary']")));

            			// Scroll directly to View Summary
            			actions.scrollToElement(viewSummary).perform();
            			// Wait until View Summary is clickable
            			wait.until( ExpectedConditions.elementToBeClickable(viewSummary)).click();
            		
            			// 4th  page
            			
            			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            			String fourthpagetext = driver.findElement(By.xpath("//li[@class='summary fs-14 fw-5 active']")).getText().trim();
            			
            			if (fourthpagetext.equals("Summary")) {
							
            				System.out.println("Enterd into summary screen....");
            				
            				WebElement processButton = wait.until(
            				        ExpectedConditions.visibilityOfElementLocated(
            				                By.id("nextToProcess")
            				        )
            				);

            				System.out.println("Enabled: " + processButton.isEnabled());
            				System.out.println("Displayed: " + processButton.isDisplayed());
            				System.out.println("Attribute disabled: " + processButton.getAttribute("disabled"));
            				System.out.println("Class: " + processButton.getAttribute("class"));

            				System.out.println("Process button enabled = " + processButton.isEnabled());

            				// Wait until button becomes enabled
            				wait.until(driver -> processButton.isEnabled());

            				// Scroll to Process button
            				JavascriptExecutor js1 = (JavascriptExecutor) driver;

            				js1.executeScript(
            				        "arguments[0].scrollIntoView({block:'center', inline:'center'});",
            				        processButton
            				);

            				// Wait until clickable
            				wait.until(ExpectedConditions.elementToBeClickable(processButton));

            				Thread.sleep(1000);

            				// Click
            				processButton.click();
            				
            					
            				
            				// chrome conformation popup
            				    driver.switchTo().alert().accept();
            				    
            				    // payrun process final conformation popup
            				//    String payrunfinaltext = driver.switchTo().alert().getText().trim();
            				    
            				    
            				    
            				    WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(30));

            				    Alert alert = alertWait.until(
            				            ExpectedConditions.alertIsPresent()
            				    );

            				    String payrunfinaltext = alert.getText().trim();

            				    System.out.println("Alert text: " + payrunfinaltext);

            		
            				                				          				    
            				    
            				    if (payrunfinaltext.equals("Payrun Processed Successfully.")) {
									
            				    	System.out.println("final conformation popup is shown for payrun complete");
            				    	//driver.switchTo().alert().accept();
            				    	
            				    	 alert.accept();
            				    	
            				    	// payrun completed process check;
            				    	
            				    	String FinalcheckText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='payrunprocess_tab']"))).getText();
            				    	
            				    	if (FinalcheckText.equals("Payrun Process")) {
										
            				    		System.out.println("Payrun completed successfully......");
									}
            				    	else {
            				    		
            				    		System.out.println("Payrun completed....but not comes for 1st page afer all the process");
            				    	}
            				   
								}
            				    else {
            				    	
            				    	System.out.println("Showing error in Final stage of process payrun");
            				    	
            				    }
						}
            			else {
            				
            				System.out.println("Showing error in 4th summary screen of the payrun process");
            			}
            			
					}
            		else {
            			
            			System.out.println("Showing error in pre-calculation process");
            		}
					
				}
            	
            	else {
            		
            		System.out.println("Showing error in Initiated job process");
            	}
            	 
    		}
            
            else {
            	
            	System.out.println("Showing error in senond page");
            }
            
           
	} // first page if condtions end's
       else {
    	   
       	System.out.println("Showing error in First page of the payrun process screen");
       } // first page else condtions end's
       
       
        
          
    }

    @AfterMethod
    public void closeBrowser() {
    	//closebrowser();
    }
}

