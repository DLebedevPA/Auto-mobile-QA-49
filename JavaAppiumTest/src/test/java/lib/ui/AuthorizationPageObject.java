package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
        LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:button#wpLoginAttempt",
        RETURN_BUTTON = "xpath://a[text()='Return to the previous page.']";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Clicking authorization button")
    public void clickAuthButton() throws Exception
    {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        Thread.sleep(1000);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Entering login and password")
    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);
    }

    @Step("Clicking submit button")
    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }

    @Step("Clicking return button on 'Authorization error' screen")
    public void returnAfterAuthError()
    {
        this.waitForElementPresent(RETURN_BUTTON, "Cannot find 'Return to the previous page' button", 5);
        this.waitForElementAndClick(RETURN_BUTTON, "Cannot click 'Return to the previous page' button", 5);
    }
}
