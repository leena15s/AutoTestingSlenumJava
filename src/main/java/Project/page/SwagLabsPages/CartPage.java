package Project.page.SwagLabsPages;

import generator.actAide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;
    private actAide aide;

    private By checkoutButton = By.id("checkout");
    private By cartItemCount = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver, actAide aide) {
        this.driver = driver;
        this.aide = aide;
    }

    @Step("Go to cart page")
    public CartPage goToCart() {
        aide.click(By.className("shopping_cart_link"));
        return this;
    }

    @Step("Click Checkout")
    public pages.CheckoutPage checkout() {
        aide.click(checkoutButton);
        return new pages.CheckoutPage(driver, aide);
    }
}
// Proceed to checkout
//public pages.CheckoutPage checkout() {
//    aide.click(checkoutButton); // Click on the checkout button
//    return new pages.CheckoutPage(driver, aide);
//}
//    public String getCartItemCount() {
//        return aide.getText(cartItemCount);
//    }
//}
