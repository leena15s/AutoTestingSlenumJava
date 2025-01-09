package Project.SwagLab.pages;


import Project.page.SwagLabsPages.CartPage;
import Project.page.SwagLabsPages.Inventory;
import Project.page.SwagLabsPages.Login;
import Project.Tests;
import generator.CustomListeners;
import generator.actAide;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class DemoLoginTest  extends Tests {

    //TC1 -invaliduser,invalidpassword
    @Test(testName = "UnSuccessfully login", description = "using a uncorrect username and password to perform a usuccessful login")
    public void uncuccessfullyLoginTest() {
        JSONObject testCaseData = (JSONObject) testData.get("SwagLabsTestData");
        String userName = (String) testCaseData.get("invalidusername");
        String password = (String) testCaseData.get("invalidpassword");
        String actualText = new Login(driver, aide, wait).goTo().Unsuccessful(userName, password).getTheErrorMessage();

        Assert.assertEquals(actualText, "Epic sadface: Username and password do not match any user in this service");
    }

    //TC2 - Empty username + correct password
    @Test(testName = "Missing element", description = "Try logging in with a empty username.")
    public void EmptyusernameTest() {
        JSONObject testCaseData = (JSONObject) testData.get("SwagLabsTestData");
        String userName = (String) testCaseData.get("emptyuser");
        String password = (String) testCaseData.get("password");
        String actualText = new Login(driver, aide, wait).goTo().Emptyuser(password).getTheErrorMessage();

        Assert.assertEquals(actualText, "Epic sadface: Username is required");
    }

    //TC3    Empty password
    @Test(testName = "Missing element2", description = "Try logging in with a empty password.")
    public void EmptypasswordTest() {
        JSONObject testCaseData = (JSONObject) testData.get("SwagLabsTestData");
        String userName = (String) testCaseData.get("username");
        String password = (String) testCaseData.get("emptypassword");
        String actualText = new Login(driver, aide, wait).goTo().Emptypassword(password).getTheErrorMessage();

        Assert.assertEquals(actualText, "Epic sadface: Password is required");
    }

    //TC4 succes
    @Test(testName = "successfully login", description = "using a correct username and password to perform a successful login")
    public void successfulLoginTest() {
        JSONObject testCaseData = (JSONObject) testData.get("SwagLabsTestData");
        String userName = (String) testCaseData.get("username");
        String password = (String) testCaseData.get("password");

        String actualText = new Login(driver, aide, wait).goTo().successfulLogin(userName, password).readHeader();

        Assert.assertEquals(actualText, "Products");
    }

    // TC5+TC6
    @Test(testName = "Complete purchase flow", description = "Test the full purchase flow")
    public void completePurchaseTest() throws InterruptedException {
        JSONObject testCaseData = (JSONObject) testData.get("SwagLabsTestData");
        String userName = (String) testCaseData.get("username");
        String password = (String) testCaseData.get("password");
        String firstName = (String) testCaseData.get("firstName");
        String lastName = (String) testCaseData.get("lastName");
        String zipCode = (String) testCaseData.get("zipCode");

        // Step 1: Login
        Inventory inventoryPage = new Login(driver, aide, wait)
                .goTo()
                .successfulLogin(userName, password);

        // Step 2: Select items and go to cart
        inventoryPage.selectItems();
        CartPage cartPage = inventoryPage.goToCart();

        // Step 3: Proceed to checkout
        pages.CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillCheckoutInfo(firstName, lastName, zipCode);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Step 4: Scroll and finish purchase
        Thread.sleep(5000);
        checkoutPage.scrollToFinishAndClick();
        Thread.sleep(5000);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


        // Step 5: Verify success message
        String actualMessage = checkoutPage.ClickfinishButton().getSuccessMessage();
        String expectedMessage = "Thank you for your order!";

        // Debugging: Print both messages for verification
        System.out.println("Expected Message: " + expectedMessage);
        System.out.println("Actual Message: " + actualMessage);

        // Make comparison case-insensitive
        Assert.assertEquals(actualMessage.toLowerCase(), expectedMessage.toLowerCase());

    }


}

