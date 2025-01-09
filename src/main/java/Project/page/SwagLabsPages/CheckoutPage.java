package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import generator.actAide;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;
    private actAide aide;

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By successMessage = By.className("complete-header");


    public CheckoutPage(WebDriver driver, actAide aide) {
        this.driver = driver;
        this.aide = aide;
    }

    @Step("Fill in checkout information")
    public CheckoutPage fillCheckoutInfo(String firstName, String lastName, String zipCode) {
        aide.type(firstNameField, firstName);
        aide.type(lastNameField, lastName);
        aide.type(zipCodeField, zipCode);
        aide.click(continueButton);
        return this;
    }

    @Step("Scroll to finish button and click")
    public CheckoutPage scrollToFinishAndClick() {
        aide.scrollToElement(finishButton);
//        aide.click(finishButton);
        return this;
    }

    @Step("cliced btn ")
    public CheckoutPage ClickfinishButton(){
        aide.click(finishButton);
        return  this;
    }

    @Step("Get success message after completing checkout")
    public String getSuccessMessage() {
        return aide.getText(successMessage);
    }
// Fill out the checkout form
//public void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
//    aide.type(firstNameField, firstName);
//    aide.type(lastNameField, lastName);
//    aide.type(zipCodeField, zipCode);
//    aide.click(continueButton); // Click continue
//}
//
//    // Scroll to the Finish button and click it
//    public void scrollToFinishAndClick() {
//        aide.scrollToElement(finishButton); // Scroll to the Finish button
//        aide.click(finishButton); // Click Finish button
//    }
//
//    // Get success message
//    public String getSuccessMessage() {
//        return aide.getText(successMessage);
//    }
}
