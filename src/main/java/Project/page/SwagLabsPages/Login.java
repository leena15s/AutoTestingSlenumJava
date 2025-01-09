package Project.page.SwagLabsPages;

import generator.Pages;
import generator.actAide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.atomic.AtomicReference;

public class Login extends Pages {
    private final Wait<WebDriver> wait ;


   private final String url = "https://www.saucedemo.com";

    private final By usernameTextArea = By.id("user-name");
    private final By passwordTextArea = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public Login(WebDriver driver, actAide aide, Wait<WebDriver> wait) {
        super(driver, aide);
        this.wait = wait;
    }


    @Step("Navigate to the SauceDemo login page")
    public Login goTo(){
        aide.navigate(url);
        return this;
    }

    private void login(String username, String password){
        aide.type(usernameTextArea, username);
        aide.type(passwordTextArea, password);
        aide.click(loginButton);
    }

  private void LoginEuser(String password){
        aide.type(passwordTextArea,password);
        aide.click(loginButton);
  }
  private void LoginEpassword(String username){
        aide.type(usernameTextArea,username);
        aide.click(loginButton);
  }
    @Step("Successful Login to SauceDemo")
    public Inventory successfulLogin(String username, String password){
        login(username, password);
        return new Inventory(driver,aide);
    }



    @Step("Unsuccessful Login to SauceDemo")
    public Login Unsuccessful(String username, String password){
        login(username, password);
        return this;
    }
    @Step("Empty User name ")
    public Login Emptyuser( String password){
        LoginEuser(password);
        return this;
    }


    @Step("Empty Password")
    public Login Emptypassword(String username){
        LoginEpassword(username);
        return this;
    }
    public String getTheErrorMessage(){
        AtomicReference<String> actualText = new AtomicReference<>("");
        wait.until(f -> {
            actualText.set(driver.findElement(errorMessage).getText());
            return true;
        });
        return driver.findElement(errorMessage).getText();
    }
}

