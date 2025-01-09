package Project;

import Project.page.SwagLabsPages.CartPage;
//import Project.SawgLab.SwagLabsPages.HomePage;
import generator.CustomListeners;
import generator.PropertiesReader;
import generator.actAide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class Tests {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected static Logger logger;

    protected actAide aide;
    protected static JSONObject testData;
    protected JSONObject testCaseData;

    private CartPage cartPage;
    private pages.CheckoutPage checkoutPage;

    @BeforeClass
    public static void beforeClass() throws IOException, ParseException {
        Configurator.initialize(null, "src/main/resources/properties/log4j2.properties"); //C
        logger = LogManager.getLogger(Tests.class.getName());
        testData =  (JSONObject) new JSONParser().parse( new FileReader("src/test/resources/testData/testdata.json", StandardCharsets.UTF_8) );
        PropertiesReader.readPropertyFile("src/main/resources/properties/configuration.properties");
    }

    @Parameters({"target-browser"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String targetBrowser){
        targetBrowser = PropertiesReader.props.getProperty("targetBrowser");
        logger.info("Test is starting...");
        switch (targetBrowser) {
            case "chrome" -> {
                logger.info("Opening Chrome Browser");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                logger.info("Opening FireFox Browser");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            case "edge" -> {
                logger.info("Opening Edge Browser");
                driver = new EdgeDriver();
                driver.manage().window().maximize();
            }

        }
        // Set Implicit Wait for 10 seconds (adjust as necessary)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver = new EventFiringDecorator(new CustomListeners()).decorate(driver);

        driver.manage().window().maximize();

        logger.info("Configuring 20 seconds explicit wait");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        aide = new actAide(driver, wait, logger);
    }

    @AfterMethod
    public void afterMethod(){
        //terminating the session
        logger.info("Quitting Browser");
        driver.quit();
    }
}

