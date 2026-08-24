package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
	import java.io.File;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class PayrunLoadPage {

	    WebDriver driver;
	    WebDriverWait wait;
	    JavascriptExecutor js;
	    Actions actions;

	    public PayrunLoadPage(WebDriver driver) {

	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        js = (JavascriptExecutor) driver;
	        actions = new Actions(driver);
	    }

	    // Locators

	    private By payrunMenu =
	            By.xpath("//a[@class='Id_1 Id__12 nav-link d-md-block']");

	    private By divisionDropdown =
	            By.xpath("//button[@class='btn dropdown-toggle btn-light']");

	    private By divisionList =
	            By.xpath("(//ul[@class='dropdown-menu inner show'])[1]//li");

	    private By monthDropdown =
	            By.xpath("//button[@class='btn dropdown-toggle bs-placeholder btn-light']");

	    private By monthSearchInput =
	            By.xpath("(//input[@class='form-control'])[2]");

	    private By monthOption(String month) {
	        return By.xpath(
	                "//a[@role='option']//span[contains(@class,'text') and normalize-space()='" 
	                + month.trim() + "']/ancestor::a[1]"
	        );
	    }

	    
	    private By initiatePayrun =
	            By.xpath("//button[text()='INITIATE PAYRUN']");

	    private By firstPage =
	            By.xpath("//li[@class='atten active fs-14  fw-5']");

	    private By selectAll =
	            By.xpath("//input[@id='selallchk']");

	    private By nextButton =
	            By.xpath("//button[@id='idnextbutton']");

	    private By secondPage =
	            By.xpath("(//li[@class='atten active fs-14  fw-5'])[2]");

	    private By nextToPre =
	            By.xpath("//button[@id='nextToPre']");

	    private By initiatedJob =
	            By.xpath("//h1[contains(text(),'Pre-Calculation Job Initiated')]");

	    private By viewJobStatus =
	            By.xpath("(//button[@id='modalViewJobStatus'])[1]");

	    private By preCalculationCompleted =
	            By.xpath("//h1[text()='Pre-Calculation Completed']");

	    private By proceedToSummary =
	            By.xpath("//button[@onclick='proceedToSummary()']");

	    private By confirmButton =
	            By.xpath("//button[contains(@class,'swal2-confirm')]");

	    private By viewSummary =
	            By.xpath("//a[normalize-space()='View Summary']");

	    private By summaryPage =
	            By.xpath("//li[@class='summary fs-14 fw-5 active']");

	    private By processButton =
	            By.id("nextToProcess");

	    private By payrunProcessTab =
	            By.xpath("//a[@id='payrunprocess_tab']");


	    // Screenshot

	    public void takeScreenshot(String fileName) throws IOException {

	        TakesScreenshot screenshot = (TakesScreenshot) driver;

	        File source = screenshot.getScreenshotAs(OutputType.FILE);

	        File destination = new File(
	                "C:\\Users\\indira kumar p\\selenium_pom\\SeleniumAutomation\\src\\test\\resources\\Screenshots\\"
	                        + fileName + ".png"
	        );

	        if (!destination.getParentFile().exists()) {
	            destination.getParentFile().mkdirs();
	        }

	        java.nio.file.Files.copy(
	                source.toPath(),
	                destination.toPath(),
	                java.nio.file.StandardCopyOption.REPLACE_EXISTING
	        );

	        System.out.println("Screenshot saved: "
	                + destination.getAbsolutePath());
	    }


	    // Open Payrun

	    public void openPayrun() throws InterruptedException {

	        Thread.sleep(2000);

	        wait.until(ExpectedConditions.elementToBeClickable(payrunMenu)).click();
	    }


	  
	 // Select Division
	    public void selectDivision(String divisionName) throws InterruptedException {

	        WebElement dropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(divisionDropdown));

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                dropdown);

	        dropdown.click();

	        List<WebElement> dropList = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(divisionList));

	        System.out.println("---Select Your Division---");

	        for (WebElement element : dropList) {

	            String division = element.getText().trim();

	            if (!division.isEmpty()) {
	                System.out.println(division);
	            }

	            if (division.equalsIgnoreCase(divisionName)) {

	                WebElement option = element.findElement(By.xpath(".//a"));

	                js.executeScript(
	                        "arguments[0].scrollIntoView({block:'center'});",
	                        option);

	                js.executeScript(
	                        "arguments[0].click();",
	                        option);

	                // Wait until dropdown closes
	                wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                        By.xpath("(//ul[contains(@class,'dropdown-menu') and contains(@class,'show')])[1]")));

	                // Verify selected division
	                wait.until(driver -> {

	                    String selectedText = dropdown.getText().trim();

	                    return selectedText.equalsIgnoreCase(divisionName);
	                });

	                System.out.println("Division selected: " + divisionName);

	                return;
	            }
	        }

	        throw new RuntimeException(
	                "Division not found: " + divisionName);
	    }


	 // Select Month
	    public void selectMonth(String month) {

	        // Open Month dropdown
	        WebElement dropdown = wait.until(
	                ExpectedConditions.presenceOfElementLocated(monthDropdown));

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                dropdown);

	        js.executeScript(
	                "arguments[0].click();",
	                dropdown);

	        // Search month
	        wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        monthSearchInput));

	        WebElement searchInput =
	                driver.findElement(monthSearchInput);

	        searchInput.clear();
	        searchInput.sendKeys(month.trim());

	        System.out.println("Month entered: " + month);

	        // Select month based on visible text
	        WebElement option = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        monthOption(month)));

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                option);

	        js.executeScript(
	                "arguments[0].click();",
	                option);

	        System.out.println(
	                "Month selected successfully: " + month);
	    }
	    
	    
	    // Initiate Payrun

	    public void initiatePayrun() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                initiatePayrun)).click();
	    }


	    // First Page

	    public boolean verifyFirstPage() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(firstPage))
	                .getText()
	                .trim();

	        return text.equals("Attendance");
	    }


	    // Select All

	    public void selectAllEmployees() {

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.id("globalLoader")));

	        WebElement checkbox = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(selectAll));

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                checkbox);

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.id("globalLoader")));

	        js.executeScript(
	                "arguments[0].click();",
	                checkbox);
	    }


	    // Next

	    public void clickNext() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                nextButton)).click();
	    }


	    // Second Page

	    public boolean verifySecondPage() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(secondPage))
	                .getText()
	                .trim();

	        return text.equals("Addition & Deduction");
	    }


	    // Pre Calculation

	    public void clickNextToPre() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                nextToPre)).click();
	    }


	    public boolean verifyInitiatedJob() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        initiatedJob))
	                .getText()
	                .trim();

	        return text.equals("Pre-Calculation Job Initiated");
	    }


	    public void clickViewJobStatus() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                viewJobStatus)).click();
	    }


	    public boolean verifyPreCalculationCompleted() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        preCalculationCompleted))
	                .getText()
	                .trim();

	        return text.equals("Pre-Calculation Completed");
	    }


	    // Summary

	    public void proceedToSummary() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                proceedToSummary)).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                confirmButton)).click();
	    }


	    public void clickViewSummary() {

	        WebElement element = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        viewSummary));

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                element);

	        wait.until(ExpectedConditions.elementToBeClickable(
	                element)).click();
	    }


	    public boolean verifySummaryPage() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        summaryPage))
	                .getText()
	                .trim();

	        return text.equals("Summary");
	    }


	    // Process Payrun

	    public void clickProcess() throws InterruptedException {

	        WebElement button = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        processButton));

	        wait.until(driver -> button.isEnabled());

	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                button);

	        wait.until(ExpectedConditions.elementToBeClickable(
	                button));

	        Thread.sleep(1000);

	        button.click();
	    }


	    public String getPayrunAlertText() {

	        wait.until(ExpectedConditions.alertIsPresent());

	        Alert alert = driver.switchTo().alert();

	        return alert.getText().trim();
	    }


	    public void acceptAlert() {

	        driver.switchTo().alert().accept();
	    }


	    public boolean verifyPayrunCompleted() {

	        String text = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        payrunProcessTab))
	                .getText()
	                .trim();

	        return text.equals("Payrun Process");
	    }
	
}
