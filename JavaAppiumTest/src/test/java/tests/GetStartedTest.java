package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Owner("Denis Lebedev")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Feature(value = "Welcome screens")
    @DisplayName("Passing trough welcome screens")
    @Description("We clicking next button until last screen where we clicking get started button")
    @Step("Starting test testPassTroughWelcome")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPassTroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
