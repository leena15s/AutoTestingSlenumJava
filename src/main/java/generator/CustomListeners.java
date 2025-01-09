package generator;

import com.google.common.annotations.VisibleForTesting;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class CustomListeners implements ITestListener, WebDriverListener {

    public void onTestFailure(ITestResult result) {
        System.out.println(result.getInstanceName() + "." + result.getName() + " FAILED");
    }
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getInstanceName() + "." + result.getName() + " PASSED");
    }
    @Step("Capturing screenshot evidence")
    public void afterGetText(WebElement element, String result) {
        try (InputStream is = Files.newInputStream(element.getScreenshotAs(OutputType.FILE).toPath())) {
            Allure.attachment("image.png", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//
//Sure! Let's break down your CustomListeners class step by step. The class is implementing two interfaces: ITestListener from TestNG and WebDriverListener from Selenium. This class is designed to hook into the lifecycle of your tests and take actions based on test execution (like capturing screenshots on failure).
//
//Class Overview:
//This class is a custom listener used to monitor and perform actions on test events. Specifically, it's used to:
//
//Capture screenshots when a test fails or after performing a particular WebDriver action.
//Log results when a test passes or fails.
//        1. Interfaces Implemented:
//The class implements two interfaces:
//
//ITestListener (from TestNG):
//
//This interface provides methods to listen for test execution events such as test start, success, failure, etc.
//Methods you are using:
//onTestFailure(ITestResult result): Triggered when a test fails.
//onTestSuccess(ITestResult result): Triggered when a test passes.
//WebDriverListener (from Selenium):
//
//This interface provides methods to hook into WebDriver actions (like clicking, getting text, etc.).
//The method you are using:
//afterGetText(WebElement element, String result): This method will be triggered after getting text from a WebElement. It's used to capture a screenshot after performing a specific action (e.g., getting text from an element).
//        2. Method by Method Breakdown:
//        2.1. onTestFailure(ITestResult result)
//This method is called whenever a test fails. Here's what it does:
//
//It prints a message to the console indicating which test failed. The message is constructed by accessing the test name and the instance name using result.getInstanceName() and result.getName().
//Example output: "TestClass.testMethod FAILED"
//        2.2. onTestSuccess(ITestResult result)
//This method is called whenever a test passes. It works similarly to onTestFailure, but it logs a success message instead of a failure message.
//
//Example output: "TestClass.testMethod PASSED"
//        2.3. afterGetText(WebElement element, String result)
//This is a custom step that is part of the Allure reporting functionality. The annotation @Step("Capturing screenshot evidence") marks this method as an Allure step, which is useful for reporting purposes.
//
//This method is called after getting text from a WebElement in Selenium. It’s triggered by the WebDriverListener's afterGetText() method.
//It tries to capture a screenshot of the WebElement passed to it.
//Screenshot capturing process:
//The element.getScreenshotAs(OutputType.FILE) method is used to get a screenshot of the WebElement. This will return a File object containing the screenshot.
//The code converts the File object to an InputStream using Files.newInputStream().
//Then it attaches the screenshot to the Allure report using Allure.attachment(). The screenshot is saved with the name "image.png".
//If there’s an issue during the screenshot capture, it throws a RuntimeException.
//        3. How It's Used in Practice:
//TestNG Integration:
//
//This CustomListeners class will work with TestNG’s @Listeners annotation. You would annotate your test class with @Listeners(CustomListeners.class) so that TestNG knows to use this listener and invoke the methods accordingly.
//Example of using it in a test class:
//
//java
//Copy code
//@Listeners(CustomListeners.class)
//public class TestExample {
//    @Test
//    public void testLogin() {
//        // Test code that might fail or succeed
//    }
//}
//Allure Integration:
//
//The @Step("Capturing screenshot evidence") annotation is used for integrating with Allure to log the captured screenshots as steps in the Allure report.
//When this method is executed, the screenshot is attached to the report, which is valuable for debugging when reviewing the test report.
//Test Execution Workflow:
//
//When the test runs, depending on the result (failure or success), TestNG invokes either onTestFailure or onTestSuccess based on the outcome of the test method.
//If the afterGetText() method is triggered after performing an action (such as getting text from an element), the screenshot of that action is captured and attached to the Allure report.
//4. Usage in Allure Reporting:
//When the test executes and the afterGetText() method is triggered (which happens after the getText() method is called on a WebElement), Allure will log that this method was executed. The screenshot of the element will be attached to the report under the step "Capturing screenshot evidence."
//
//In Allure reports, you will see the following:
//
//A step indicating that the text was retrieved from the element.
//An attachment containing the screenshot image (image.png).
//        5. Enhancing the Listener:
//You can extend this listener class to:
//
//Capture screenshots on test failures.
//Capture logs or console output.
//Attach additional information (e.g., HTML source, network requests) to the Allure report.
//Conclusion:
//This class allows you to integrate logging and screenshot capturing with your tests.
//It listens for TestNG test lifecycle events (success/failure) and WebDriver actions (e.g., retrieving text from an element).
//The captured information (like screenshots) is added to the Allure report, which is useful for debugging and analyzing the test results.
