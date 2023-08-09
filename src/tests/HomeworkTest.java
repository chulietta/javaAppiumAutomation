package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class HomeworkTest extends CoreTestCase{

    @Test
    public void testCheckSearchInputPlaceholderText() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.assertSearchInputPlaceholder("Search Wikipedia");
    }

    /* Ex3 */
    @Test
    public void testSearchResultsAfterCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchListIsPresented();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForSearchListIsNotPresented();
    }

    @Test
    public void testSearchResultsHasSearchedWord() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchListIsPresented();
        searchPageObject.assertSearchResultContainsSearchRequest("1");
        searchPageObject.assertSearchResultContainsSearchRequest("2");
        searchPageObject.assertSearchResultContainsSearchRequest("3");
    }

    /* Ex6 */
    @Test
    public void testTitleOfArticleVisibleAfterOpen() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSearchInput();
        String search_line = "java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.assertTitlePresent();
    }

    /* Ex5 */
    @Test
    public void testCheckMyListAfterDeleteArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();

        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyList(name_of_folder);

        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement();

        articlePageObject.addArticleToExistingList(name_of_folder);
        navigationUI.clickMyList();

        String article_for_delete_title = "Java (programming language)";
        String article_titleNot_deleted = "Appium";

        myListsPageObject.swipeByArticleToDelete(article_for_delete_title);
        myListsPageObject.waitForArticleToAppearByTitle(article_titleNot_deleted);
        myListsPageObject.clickArticleTitleInMyList(article_titleNot_deleted);
        articlePageObject.waitForTitleElement();

        String current_article_title = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title!",
                article_titleNot_deleted,
                current_article_title
        );
    }
}
