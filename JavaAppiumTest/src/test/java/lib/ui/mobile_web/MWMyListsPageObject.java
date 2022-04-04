package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'page-list')]//h3[text()='{TITLE}']";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'page-list')]//h3[text()='{TITLE}']/../../a[contains(@class,'watched')]";
        RESAVE_IN_SAVED_BUTTON = "xpath://a[contains(@title,'Add this page to your watchlist')]";
    }

    public MWMyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}