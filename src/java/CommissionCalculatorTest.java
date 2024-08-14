package javaapplication77;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CommissionCalculatorTest {

    public static void main(String[] args) {
        // Set the path of the WebDriver executable
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
                String[][] testCases = {
            {"Salaried", "Standard", "Walk-in", "5000", "Commission: $0.0"},
            {"Non-salaried", "Bonus", "Regular", "5000", "Commission: $0.0"},
            {"Non-salaried", "Standard", "Walk-in", "5000", "Commission: $0.0"},
            {"Non-salaried", "Bonus", "Regular", "5000", "Commission: $0.0"},
            
            {"Non-salaried", "General", "Walk-in", "500", "Commission: $50.0"},
            {"Non-salaried", "General", "Walk-in", "15000", "Commission: $750.0"},
            
            {"Non-salaried", "Bonus", "Walk-in", "1000", "Commission: $100.0"},
            {"Non-salaried", "Bonus", "Walk-in", "1001", "Commission: $75"},
            
            {"Salaried", "Bonus", "Walk-in", "1000", "Commission: $50.0"},
            {"Salaried", "Bonus", "Walk-in", "1001", "Commission: $25"},
            
            {"Salaried", "Unknown", "Walk-in", "500", "Commission: $0.0"},
            {"Salaried", "Unknown", "Walk-in", "1500", "Commission: $0.0"}
        };

        // Loop through each test case
        for (String[] testCase : testCases) {
            runTest(driver, testCase[0], testCase[1], testCase[2], testCase[3], testCase[4]);
        }

        // Close the browser
        driver.quit();
    }

    public static void runTest(WebDriver driver, String employeeType, String itemType, String customerType, String itemPrice, String expectedCommission) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the JSP page before each test
        driver.get("http://localhost:8080/WebApplication1/newjsp.jsp");

        // Wait for the employeeType element to be present
        WebElement employeeTypeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("employeeType")));
        WebElement itemTypeDropdown = driver.findElement(By.name("itemType"));
        WebElement customerTypeDropdown = driver.findElement(By.name("customerType"));
        WebElement itemPriceField = driver.findElement(By.name("itemPrice"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        // Select values using Select class for dropdowns
        Select employeeTypeSelect = new Select(employeeTypeDropdown);
        employeeTypeSelect.selectByVisibleText(employeeType);

        Select itemTypeSelect = new Select(itemTypeDropdown);
        itemTypeSelect.selectByVisibleText(itemType);

        Select customerTypeSelect = new Select(customerTypeDropdown);
        customerTypeSelect.selectByVisibleText(customerType);

        itemPriceField.clear();
        itemPriceField.sendKeys(itemPrice);
        submitButton.click();

        // Wait for the result to be present
        WebElement result = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String actualCommission = result.getText();

        if (actualCommission.contains(expectedCommission)) {
            System.out.println("Test passed for " + employeeType + ", " + itemType + ", " + customerType + ", " + itemPrice);
        } else {
            System.out.println("Test failed for " + employeeType + ", " + itemType + ", " + customerType + ", " + itemPrice + ". Expected: " + expectedCommission + " but got: " + actualCommission);
        }
    }
}