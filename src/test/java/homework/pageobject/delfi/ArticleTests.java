package homework.pageobject.delfi;

import homework.pageobject.delfi.model.Article;
import homework.pageobject.delfi.pages.ArticlePage;
import homework.pageobject.delfi.pages.CommentsPage;
import homework.pageobject.delfi.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.pages.BaseFunc;

public class ArticleTests {

    private BaseFunc baseFunc;
    private final String HOME_PAGE_URL = "delfi.lv";

    @Test
    public void titleCheck() {
        baseFunc = new BaseFunc();

        baseFunc.openUrl(HOME_PAGE_URL);
        HomePage homePage = new HomePage(baseFunc);

        homePage.acceptCookies();

        homePage.getAllTitles();

        Article article = homePage.getArticleById(7);
        System.out.println(article.getTitle());
        System.out.println(article.getCommentsCount());

        homePage.openArticle(7);

        ArticlePage articlePage = new ArticlePage(baseFunc);
        String articlePageTitle = articlePage.getTitle();
        System.out.println(articlePage.getTitle());
        int articlePageCommentsCount = articlePage.getCommentsCount();
        System.out.println(articlePageCommentsCount);

        Assertions.assertEquals(article.getTitle(), articlePageTitle, "Wrong title!");
        Assertions.assertEquals(article.getCommentsCount(), articlePageCommentsCount, "Wrong comments count!");

        articlePage.openCommentsPage();

        CommentsPage commentsPage = new CommentsPage(baseFunc);
        String commentsPageTitle = commentsPage.getTitle();
        System.out.println(commentsPageTitle);

        Assertions.assertEquals(articlePageTitle, commentsPageTitle, "Wrong title!");
    }
    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}
