package generator;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.atomic.AtomicReference;

public class actAide {

    private final Logger logger;

    private final WebDriver driver;
    private final Wait<WebDriver> wait ;
    //private final Logger logger;

    public actAide(WebDriver driver, Wait<WebDriver> wait, Logger logger){
        this.driver = driver;
        this.wait = wait;
        this.logger = logger;

    }


    @Step("Navigate to URL")
    public void navigate(String url) {
        logger.info("Navigating to: " + url);
        driver.get(url);
    }

    @Step("Type into element")
    public void type(By locator, CharSequence text) {
        logger.info("Typing: " + text + ", into: " + locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    @Step("Get text from element")
    public String getText(By locator) {
        logger.info("Reading text from: " + locator);
        return driver.findElement(locator).getText();
    }

    @Step("Click on element")
    public void click(By locator) {
        logger.info("Clicking: " + locator);
        try {
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException exception) {
            logger.info("Using JavaScript Click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
        }
    }

    @Step("Scroll to the element")
    public void scrollToElement(By locator) {
        logger.info("Scrolling to: " + locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

