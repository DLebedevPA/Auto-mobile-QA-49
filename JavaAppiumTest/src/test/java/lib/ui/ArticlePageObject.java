package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_BY_NAME_TPL;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    @Step("Get article title")
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40)
            ;
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }

    }

    @Step("Adding the article to folder by folder name")
    public void addArticleToFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    @Step("Adding the article to My list")
    public void addArticleToMyList(String name_of_folder) throws Exception {
        this.waitForElementAndClick(
                OPTIONS_BUTTON, "Cannot find button to open article options", 15
        );

        Thread.sleep(1000);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find find option to add article to reading list", 5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder", 5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON, "Cannot press OK button", 5
        );
    }

    @Step("Adding the article to existing folder")
    public void addArticleToExistingFolder(String name_of_folder) throws Exception {
        this.waitForElementAndClick(
                OPTIONS_BUTTON, "Cannot find button to open article options", 15
        );

        Thread.sleep(1000);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find find option to add article to reading list", 5
        );

        this.addArticleToFolderByName(name_of_folder);
    }

    @Step("Adding article to my saved articles")
    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    @Step("Removing thr article from saved if it has been added")
    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }

    @Step("Closing the article")
    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }
}

