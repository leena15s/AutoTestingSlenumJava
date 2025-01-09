package Project.SwagLab;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHooks {
    private static WebDriver driver;
//Cucumber##

    // Setup WebDriver before any tests run
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");  // Navigate to the website
        System.out.println("WebDriver initialized successfully");
    }

    // Tear down WebDriver after all tests are finished
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver quit successfully");
        }
    }

    // Getter for WebDriver if needed in step definitions
    public static WebDriver getDriver() {
        return driver;
    }

}

