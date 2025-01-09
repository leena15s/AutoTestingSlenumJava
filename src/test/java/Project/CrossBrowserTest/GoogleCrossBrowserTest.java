package Project.CrossBrowserTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import ru.yandex.qatools.allure.annotations.Parameter;


public class GoogleCrossBrowserTest {



    WebDriver driver;

    @Parameters("browser")
    @Test
    public void testGoogleWebsite(String browser) {
        // Set up the browser based on the parameter passed from testng.xml
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported");
        }

        try {
            // Maximize the window and navigate to Google
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            // Find the search bar (input element) and perform an action
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium WebDriver");

            // Verify the title of the page
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);
        } finally {
            // Close the browser after the test execution
            driver.quit();
        }
    }
}
