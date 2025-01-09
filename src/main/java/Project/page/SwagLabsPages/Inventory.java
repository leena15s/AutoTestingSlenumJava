package Project.page.SwagLabsPages;


import generator.Pages;
import generator.actAide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Inventory extends Pages {

    private final String url = "https://www.saucedemo.com";
    private final By productsLabel = By.xpath("//span[@class='title']");

    private By firstItemAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By secondItemAddButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By thirdItemAddButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");


    public Inventory(WebDriver driver, actAide aide) {
        super(driver, aide);
    }


    @Step("Navigate to the SauceDemo inventory page")
    public void goTo(){

        aide.navigate(url);
    }

    @Step("Reading inventory page header")
    public String readHeader(){
        return aide.getText(productsLabel);
    }
    @Step("Select 3 items")
    public Inventory selectItems() {
        aide.click(firstItemAddButton);
        aide.click(secondItemAddButton);
        aide.click(thirdItemAddButton);
        return this;
    }
//    // Select items (assuming 3 items should be selected)
//    public void selectItems() {
//        List<WebElement> buttons = driver.findElements(addToCartButtons);
//        for (int i = 0; i < 3; i++) {
//            buttons.get(i).click(); // Click on first 3 add-to-cart buttons
//        }
//    }
    @Step("Go to cart")
    public CartPage goToCart() {
        aide.click(By.className("shopping_cart_link"));
        return new CartPage(driver, aide);
    }
//        // Go to Cart page
//        public CartPage goToCart() {
//            aide.click(cartIcon); // Click on cart icon to navigate to CartPage
//            return new CartPage(driver, aide);
//        }
    }

//By using this pattern, your tests will be more maintainable, as changes to the
// page structure are only reflected in the Inventory class rather than in every individual test.