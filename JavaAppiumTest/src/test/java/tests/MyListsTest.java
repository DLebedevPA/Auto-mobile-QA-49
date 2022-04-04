package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "D.lebedev.test",
            password = "Test123.";
    @Test
    public void testSaveFirstArticleToMyList() throws Exception
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            if (ArticlePageObject.getArticleTitle().equals("Central user log in")){
                Auth.returnAfterAuthError();
            }

            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
        }

        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToListAndDeleteOneOfThem() throws Exception
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title1 = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            if (ArticlePageObject.getArticleTitle().equals("Central user log in")){
                Auth.returnAfterAuthError();
            }

            assertEquals("We are not on the same page after login",
                    article_title1,
                    ArticlePageObject.getArticleTitle()
            );
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("utomation for Apps");

        ArticlePageObject.waitForTitleElement();
        String article_title2 = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToExistingFolder(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title2);
    }
}
