package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
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
import Page.PayrunLoadPage;
import TestData.LoginData;



/*public class PayrunUiloadTest extends BaseTest {

   @BeforeMethod
    public void setupTest() {

        setup();  
    }

    @Test(dataProvider = "loginData",
          dataProviderClass = LoginData.class)
    public void payrunTest(String username, String password, String divisionName, String month)throws InterruptedException, AWTException, IOException {

        // Login using common login method
        login(username, password);

        // Now Payrun test starts
        System.out.println("Login completed");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Payrun code here    
      //  driver.findElement(By.xpath("//a[@class='Id_1 Id__12 nav-link d-md-block']")).click();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='Id_1 Id__12 nav-link d-md-block']"))).click();
       
    
     // division dropdown 
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='btn dropdown-toggle btn-light']")
        )).click();
        
        List<WebElement> droplist = driver.findElements(By.xpath("(//ul[ @class='dropdown-menu inner show'])[1]//li"));
        
        for (WebElement webElement : droplist) {
			
        	System.out.println(webElement.getText());
        	
        	String dropdownlist = webElement.getText().trim();
        	
        	if(dropdownlist.contentEquals(divisionName)) {
        		
        		
        		webElement.click();
        		break;
        	}else {
        		
        		System.out.println("dropdown name doesn't match");
        	}
    
        	}
        Thread.sleep(2000);

        
        // Month dropdown
       

        driver.findElement(By.xpath("//button[@class='btn dropdown-toggle bs-placeholder btn-light']")).click();
        
       

    

       driver.findElement(By.xpath("(//input[@class='form-control'])[2]")).sendKeys(month);
       driver.findElement(By.xpath("//a[contains(@id,'bs-select-4')]")).click();
       
      // driver.findElement(By.xpath("//button[text()='INITIATE PAYRUN']")).click();
       
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='INITIATE PAYRUN']"))).click();
       
       // 1st page
       
      // String Firstpagetext = driver.findElement(By.xpath("//li[@class='atten active fs-14  fw-5']")).getText();
       
       String Firstpagetext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='atten active fs-14  fw-5']"))).getText();
       
       if (Firstpagetext.equals("Attendance")) {
    	   
    	   takeScreenshot("Payrun 1st page.....Test case Pass");

		
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
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            String secondndpagetext = driver.findElement(By.xpath("(//li[@class='atten active fs-14  fw-5'])[2]")).getText().trim();
            
            if (secondndpagetext.equals("Addition & Deduction")) {
    		
            	takeScreenshot("Payrun 2nd page.....Test case Pass");
            	
            	System.out.println("Enter into senond page of payrun");
            	
            	 actions.scrollByAmount(0, 400).perform();
            	 driver.findElement(By.xpath("//button[@id='nextToPre']")).click();
            	 
            	 // initiated job screen
            	 
            	//String initiatedjobtext = driver.findElement(By.xpath("//h1[contains(text(),'Pre-Calculation Job Initiated')]")).getText().trim();
            	
            	@NonNull
				WebElement initiatedjob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Pre-Calculation Job Initiated')]")));
            	String initiatedjobtext = initiatedjob.getText();
            	
            	
            	if (initiatedjobtext.equals("Pre-Calculation Job Initiated")) {
            		
            		takeScreenshot("Payrun 3rd page-pre-cal-job.....Test case Pass");
            		
            		System.out.println("Initiated job was run successfully");
            		
            		//viewstatusbtn
            		driver.findElement(By.xpath("(//button[@id='modalViewJobStatus'])[1]")).click();
            		
            		//Entered into pre-calculation stage
            		
            		String preCalculationText = driver.findElement(By.xpath("//h1[text()='Pre-Calculation Completed']")).getText();
            		
            		if (preCalculationText.equals("Pre-Calculation Completed")) {
						
            			takeScreenshot("Payrun 3rd page-pre-cal-job completed.....Test case Pass");
            			
            			System.out.println("Pre-calculation process completed....");
            			
            			// Proceed to Summary
            			wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[@onclick='proceedToSummary()']"))).click();
                        // Wait for confirmation popup
            			WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'swal2-confirm')]")));

            			confirmButton.click();

            			// Find View Summary
            			WebElement viewSummary = wait.until(
            			        ExpectedConditions.visibilityOfElementLocated(
            			                By.xpath("//a[normalize-space()='View Summary']")
            			        )
            			);

            			// Scroll directly to View Summary
            			actions.scrollToElement(viewSummary).perform();

            			// Wait until View Summary is clickable
            			wait.until(ExpectedConditions.elementToBeClickable(viewSummary)).click();
            		
            			// 4th  page
            			
            			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            			String fourthpagetext = driver.findElement(By.xpath("//li[@class='summary fs-14 fw-5 active']")).getText().trim();
            			
            			if (fourthpagetext.equals("Summary")) {
            				
            				Thread.sleep(2000);
                			takeScreenshot("Payrun 4rd page.....Test case Pass");

							
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
            				    	
                        			takeScreenshot("Payrun process compeleted.....Test case Pass");
                        			
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
            				    	
                        			takeScreenshot("Payrun process Failed.....Test case Failed");

            				    	System.out.println("Showing error in Final stage of process payrun");
            				    	
            				    }
						}
            			else {
            				
            				Thread.sleep(3000);
                			takeScreenshot("Payrun 4rd page.....Test case Failed");
            				System.out.println("Showing error in 4th summary screen of the payrun process");
            			}
            			
					}
            		else {
            			
            			takeScreenshot("Payrun 3rd page-pre-cal-job Failed.....Test case Failed");
            			
            			System.out.println("Showing error in pre-calculation process");
            		}
					
				}
            	
            	else {
            		
            		takeScreenshot("Payrun 3rd page-pre-cal-job.....Test case Failed");
            		
            		System.out.println("Showing error in Initiated job process");
            	}
            	 
    		}
            
            else {
            	
            	takeScreenshot("Payrun 2nd page.....Test case Failed");
            	
            	System.out.println("Showing error in senond page");
            }
            
           
	} // first page if condtions end's
       else {
    	   
    	   takeScreenshot("Payrun 1st page.....Test case Failed");
       	System.out.println("Showing error in First page of the payrun process screen");
       } // first page else condtions end's
       
       
        
          
    }

    @AfterMethod
    public void closeBrowser() {
    	closebrowser();
    }
}*/


public class PayrunUiloadTest extends BaseTest {

  /*  @BeforeMethod
    public void setupTest() {

        setup();
    }


    @Test(dataProvider = "loginData",
          dataProviderClass = LoginData.class)
    public void payrunTest( String username, String password, String divisionName,String month) throws InterruptedException, AWTException, IOException {

        login(username, password);

        System.out.println("Login completed");

        PayrunLoadPage payrun = new PayrunLoadPage(driver);


        // Open Payrun
  payrun.openPayrun();
                     
  
     // Select Division from Excel

        payrun.selectDivision(divisionName);
       
        // Select Month from Excel

        payrun.selectMonth(month);


        // Initiate Payrun

        payrun.initiatePayrun();


        // 1st Page of payrun

        if (payrun.verifyFirstPage()) {

        	System.out.println("Enterd into 1st page of payrun..Attendance screen");
            payrun.takeScreenshot("Payrun 1st page.....Test case Pass");

            payrun.selectAllEmployees();

            payrun.clickNext();


            // 2nd Page of payrun

            if (payrun.verifySecondPage()) {

            	System.out.println("Enterd into 2nd page of payrun..Attendance and deduction screen");
                payrun.takeScreenshot("Payrun 2nd page.....Test case Pass");

                payrun.clickNextToPre();


                // 3rd Page - pre-calculation Job Initiated

                if (payrun.verifyInitiatedJob()) {

                	System.out.println("Enter into pre-calculation initaited screen");
                    payrun.takeScreenshot("Payrun 3rd page-pre-cal-job.....Test case Pass");

                    payrun.clickViewJobStatus();


                    // 3rd Page - pre-cal job compeleted

                    if (payrun.verifyPreCalculationCompleted()) {

                    	System.out.println("Enter into pre-calculation initaited compelted screen");
                        payrun.takeScreenshot("Payrun 3rd page-pre-cal-job completed.....Test case Pass");

                        payrun.proceedToSummary();

                        payrun.clickViewSummary();


                        // 4th Page of page

                        if (payrun.verifySummaryPage()) {

                        	System.out.println("Enter into payrun 4rd screen....summary");
                        	
                        	payrun.takeScreenshot("Payrun 4rd page.....Test case Pass");

                        	payrun.clickProcess();

                        	// First confirmation popup
                        	String confirmationText = payrun.getPayrunConfirmationAlertText();

                        	if (confirmationText.equals("Do You Want To Process Payrun?")) {

                        	    System.out.println("Payrun confirmation accepted.");

                        	    // Wait for final processing alert
                        	    String alertText = payrun.getPayrunAlertText();

                        	    System.out.println("Final Alert text: " + alertText);

                        	    if (alertText.equals("Payrun Processed Successfully.")) {

                        	        // Accept final success alert FIRST
                        	        payrun.acceptAlert();

                        	        // Take screenshot after alert is closed
                        	        payrun.takeScreenshot("PayrunProcess completed...Test Case PASS");

                        	        if (payrun.verifyPayrunCompleted()) {

                        	            System.out.println(
                        	                    "Payrun completed successfully......");

                        	        } else {

                        	            payrun.takeScreenshot("PayrunProcess_Not completed.....Test case failed");

                        	            System.out.println(
                        	                    "Payrun completed but did not return to Payrun Process.");
                        	        }

                        	    } else {

                        	        // Accept error alert before taking screenshot
                        	        payrun.acceptAlert();

                        	        payrun.takeScreenshot("PayrunProcess not completed....Test case Failed");

                        	        System.out.println(
                        	                "Showing error in Final stage of process payrun");
                        	    }

                        	} else {

                        	    System.out.println(
                        	            "Unexpected confirmation alert: " + confirmationText);
                        	}


                        } else {

                            payrun.takeScreenshot("Payrun 4rd page.....Test case Failed");

                            System.out.println( "Showing error in 4th summary screen");
                        }

                    } else {

                        payrun.takeScreenshot("Payrun 3rd page-pre-cal-job completed.....Test case Failed");
                        System.out.println("Showing error in pre-calculation process");
                    }

                } else {

                    payrun.takeScreenshot("Payrun 3rd page-pre-cal-job.....Test case Failed");

                    System.out.println("Showing error in Initiated job process");
                }

            } else {

                payrun.takeScreenshot("Payrun 2nd page.....Test case Failed");

                System.out.println( "Showing error in second page");
            }

        } // 1st page if condition end's
        
        else {

            payrun.takeScreenshot("Payrun 1st page.....Test case Failed");

            System.out.println( "Showing error in First page");
        }
    }


    @AfterMethod
    public void closeBrowser() {

        closebrowser();
    }*/
	
	
/*	@Test(
	        dataProvider = "loginData",
	        dataProviderClass = LoginData.class
	)
	public void payrunTest(
	        int credentialId,
	        String username,
	        String password,
	        String divisionName,
	        String month,
	        String browser
	) throws InterruptedException, AWTException, IOException {

	    setup(browser);

	    String screenshotPrefix =
	            "Credential_" + credentialId
	            + "_" + browser
	            + "_" + username;

	    StringBuilder log = new StringBuilder();

	    log.append("\n");
	    log.append("==================================================\n");
	    log.append("STARTING CREDENTIAL ").append(credentialId).append("\n");
	    log.append("Browser  : ").append(browser).append("\n");
	    log.append("Username : ").append(username).append("\n");
	    log.append("Division : ").append(divisionName).append("\n");
	    log.append("Month    : ").append(month).append("\n");
	    log.append("==================================================\n\n");


        // Login
        login(username, password);

       // System.out.println("Login completed");
        log.append("Login completed\n");

        PayrunLoadPage payrun =
                new PayrunLoadPage(getDriver());

        // Open Payrun
        payrun.openPayrun();

        // Select Division from Excel
        payrun.selectDivision(divisionName);
        
       /* System.out.println(
                "Credential " + credentialId
                + " | Browser: " + browser
                + " | Division selected: " + divisionName
        );
        
        log.append("Division selected: ")
        .append(divisionName)
        .append("\n");

        // Select Month from Excel
        payrun.selectMonth(month);

       /* System.out.println(
                "Credential " + credentialId
                + " | Browser: " + browser
                + " | Month selected: " + month
        );
        
        log.append("Month selected: ")
        .append(month)
        .append("\n");
        
        // Initiate Payrun
        payrun.initiatePayrun();

        // 1st Page of Payrun
        if (payrun.verifyFirstPage()) {

        	payrun.takeScreenshot(
        	        screenshotPrefix + "_Payrun_1st_page_PASS"
        	);

            payrun.selectAllEmployees();

            payrun.clickNext();

            // 2nd Page of Payrun
            if (payrun.verifySecondPage()) {

            	payrun.takeScreenshot(
            	        screenshotPrefix + "_Payrun_2nd_page_PASS"
            	);

                payrun.clickNextToPre();

                // 3rd Page - Pre-calculation Job Initiated
                if (payrun.verifyInitiatedJob()) {

                	payrun.takeScreenshot(
                	        screenshotPrefix + "_Payrun_3rd_page_pre_cal_job_PASS"
                	);

                    payrun.clickViewJobStatus();

                    // 3rd Page - Pre-calculation Job Completed
                    if (payrun.verifyPreCalculationCompleted()) {

                    	payrun.takeScreenshot(
                    	        screenshotPrefix + "_Payrun_3rd_page_pre_cal_job_completed_PASS"
                    	);

                        payrun.proceedToSummary();

                        payrun.clickViewSummary();

                        // 4th Page - Summary
                        if (payrun.verifySummaryPage()) {

                        	payrun.takeScreenshot(
                        	        screenshotPrefix + "_Payrun_4th_page_PASS"
                        	);

                            payrun.clickProcess();

                         // First confirmation popup
                         String confirmationText =
                                 payrun.getPayrunConfirmationAlertText();

                        /* System.out.println(
                        	        "Credential " + credentialId
                        	        + " | Browser: " + browser
                        	        + " | Confirmation Alert: " + confirmationText
                        	);
                         log.append("Confirmation Alert: ")
                         .append(confirmationText)
                         .append("\n");

                         if (confirmationText.equals(
                                 "Do You Want To Process Payrun?"
                         )) {

                        	/* System.out.println(
                        		        "Credential " + credentialId
                        		        + " | Browser: " + browser
                        		        + " | Payrun confirmation accepted."
                        		);
                        	 
                        	 log.append("Payrun confirmation accepted.\n");

                             // Accept first confirmation alert
                             payrun.acceptAlert();

                             // Wait for final processing alert
                             String alertText =
                                     payrun.getPayrunAlertText();

                         /*    System.out.println(
                            	        "Credential " + credentialId
                            	        + " | Browser: " + browser
                            	        + " | Final Alert text: " + alertText
                            	);
                             
                             log.append("Final Alert: ")
                             .append(alertText)
                             .append("\n");

                             if (alertText.equals(
                                     "Payrun Processed Successfully."
                             )) {

                                 // IMPORTANT:
                                 // Accept alert before screenshot
                                 payrun.acceptAlert();

                                 payrun.takeScreenshot(
                                	        screenshotPrefix + "_Payrun_Process_COMPLETED_PASS"
                                	);                                

                                 if (payrun.verifyPayrunCompleted()) {

                                	/* System.out.println(
                                		        "Credential " + credentialId
                                		        + " | Browser: " + browser
                                		        + " | Payrun completed successfully......"
                                		);
                                	 log.append("Payrun completed successfully.\n");

                                 } else {

                                     payrun.takeScreenshot(
                                             "PayrunProcess_Not completed.....Test case failed"
                                     );

                                     System.out.println(
                                             "Payrun completed but did not return to Payrun Process."
                                     );
                                 }

                             } else {

                                 // Close final error alert first
                                 payrun.acceptAlert();

                                 payrun.takeScreenshot(
                                         "PayrunProcess not completed....Test case Failed"
                                 );

                                 System.out.println(
                                         "Showing error in Final stage of process payrun"
                                 );
                             }

                         } else {

                             System.out.println(
                                     "Unexpected confirmation alert: "
                                             + confirmationText
                             );

                             payrun.acceptAlert();
                         }

                        } else {

                            payrun.takeScreenshot(
                            		screenshotPrefix +   "_Payrun 4rd page.....Test case Failed"
                            );

                            System.out.println(
                                    "Showing error in 4th summary screen"
                            );
                        }

                    } else {

                        payrun.takeScreenshot(
                        		screenshotPrefix +  "_Payrun 3rd page-pre-cal-job completed.....Test case Failed"
                        );

                        System.out.println(
                                "Showing error in pre-calculation process"
                        );
                    }

                } else {

                    payrun.takeScreenshot(
                    		screenshotPrefix +  "_Payrun 3rd page-pre-cal-job.....Test case Failed"
                    );

                    System.out.println(
                            "Showing error in Initiated job process"
                    );
                }

            } else {

                payrun.takeScreenshot(
                		screenshotPrefix +  "_Payrun 2nd page.....Test case Failed"
                );

                System.out.println(
                        "Showing error in second page"
                );
            }

        } else {

            payrun.takeScreenshot(
            		screenshotPrefix +  "_Payrun 1st page.....Test case Failed"
            );

            System.out.println(
                    "Showing error in First page"
            );
        }
    }*/

	@Test(
	        dataProvider = "loginData",
	        dataProviderClass = LoginData.class
	)
	public void payrunTest(
	        int credentialId,
	        String username,
	        String password,
	        String divisionName,
	        String month,
	        String browser
	) throws InterruptedException, AWTException, IOException {

	    setup(browser);

	    StringBuilder log = new StringBuilder();

	    // ==============================
	    // START LOG
	    // ==============================

	    log.append("\n");
	    log.append("==================================================\n");
	    log.append("STARTING CREDENTIAL ").append(credentialId).append("\n");
	    log.append("Browser  : ").append(browser).append("\n");
	    log.append("Username : ").append(username).append("\n");
	    log.append("Division : ").append(divisionName).append("\n");
	    log.append("Month    : ").append(month).append("\n");
	    log.append("==================================================\n");

	    // Login
	    login(username, password);

	    log.append("Login completed\n");

	    PayrunLoadPage payrun =
	            new PayrunLoadPage(getDriver());

	    // Open Payrun
	    payrun.openPayrun();

	    // Division
	    payrun.selectDivision(divisionName);

	    log.append("Division selected: ")
	       .append(divisionName)
	       .append("\n");

	    // Month
	    payrun.selectMonth(month);

	    log.append("Month selected: ")
	       .append(month)
	       .append("\n");

	    // Initiate Payrun
	    payrun.initiatePayrun();

	    // 1st Page
	    if (payrun.verifyFirstPage()) {

	        log.append("1st Page: Attendance - PASS\n");

	        payrun.selectAllEmployees();
	        payrun.clickNext();

	        // 2nd Page
	        if (payrun.verifySecondPage()) {

	            log.append("2nd Page: Addition & Deduction - PASS\n");

	            payrun.clickNextToPre();

	            // 3rd Page
	            if (payrun.verifyInitiatedJob()) {

	                log.append(
	                    "3rd Page: Pre-Calculation Job Initiated - PASS\n"
	                );

	                payrun.clickViewJobStatus();

	                if (payrun.verifyPreCalculationCompleted()) {

	                    log.append(
	                        "Pre-Calculation Completed - PASS\n"
	                    );

	                    payrun.proceedToSummary();
	                    payrun.clickViewSummary();

	                    // 4th Page
	                    if (payrun.verifySummaryPage()) {

	                        log.append("4th Page: Summary - PASS\n");

	                        payrun.clickProcess();

	                        // First confirmation
	                        String confirmationText =
	                                payrun.getPayrunConfirmationAlertText();

	                        log.append("Confirmation Alert: ")
	                           .append(confirmationText)
	                           .append("\n");

	                        if (confirmationText.equals(
	                                "Do You Want To Process Payrun?"
	                        )) {

	                            log.append(
	                                "Payrun confirmation accepted.\n"
	                            );

	                            payrun.acceptAlert();

	                            // Final alert
	                            String alertText =
	                                    payrun.getPayrunAlertText();

	                            log.append("Final Alert: ")
	                               .append(alertText)
	                               .append("\n");

	                            if (alertText.equals(
	                                    "Payrun Processed Successfully."
	                            )) {

	                                payrun.acceptAlert();

	                                log.append(
	                                    "Payrun Processed Successfully - PASS\n"
	                                );

	                                if (payrun.verifyPayrunCompleted()) {

	                                    log.append(
	                                        "Payrun completed successfully.\n"
	                                    );

	                                } else {

	                                    log.append(
	                                        "Payrun completed but did not return "
	                                        + "to Payrun Process - FAIL\n"
	                                    );
	                                }

	                            } else {

	                                log.append(
	                                    "Final Payrun Processing - FAIL\n"
	                                );

	                                payrun.acceptAlert();
	                            }

	                        } else {

	                            log.append(
	                                "Unexpected confirmation alert - FAIL\n"
	                            );

	                            payrun.acceptAlert();
	                        }

	                    } else {

	                        log.append(
	                            "4th Page: Summary - FAIL\n"
	                        );
	                    }

	                } else {

	                    log.append(
	                        "Pre-Calculation Completed - FAIL\n"
	                    );
	                }

	            } else {

	                log.append(
	                    "Pre-Calculation Job Initiated - FAIL\n"
	                );
	            }

	        } else {

	            log.append(
	                "2nd Page: Addition & Deduction - FAIL\n"
	            );
	        }

	    } else {

	        log.append(
	            "1st Page: Attendance - FAIL\n"
	        );
	    }


	    // ==============================
	    // END LOG
	    // ==============================

	    log.append("\n");
	    log.append("==================================================\n");
	    log.append("COMPLETED CREDENTIAL ")
	       .append(credentialId)
	       .append("\n");
	    log.append("Browser: ")
	       .append(browser)
	       .append("\n");
	    log.append("==================================================\n");

	    System.out.println(log.toString());
	}
	

	
    @AfterMethod
    public void closeBrowser() {

        closebrowser();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
