package homework.pageobject.tvnet;

import homework.pageobject.tvnet.model.Article;
import homework.pageobject.tvnet.pages.ArticlePage;
import homework.pageobject.tvnet.pages.CommentsPage;
import homework.pageobject.tvnet.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.pages.BaseFunc;

public class ArticleTests {

    private BaseFunc baseFunc;
    private final String HOME_PAGE_URL = "tvnet.lv";

    @Test
    public void titleAndCommentsCountCheck() {
        baseFunc = new BaseFunc();

        baseFunc.openUrl(HOME_PAGE_URL);
        HomePage homePage = new HomePage(baseFunc);

        homePage.acceptCookies();

        Article article = homePage.getArticleById(19);

        homePage.openArticle(19);

        ArticlePage articlePage = new ArticlePage(baseFunc);
        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        Assertions.assertEquals(article.getTitle(), articlePageTitle, "Wrong title!");
        Assertions.assertEquals(article.getCommentsCount(), articlePageCommentsCount, "Wrong comments count!");

        articlePage.openCommentsPage();

        CommentsPage commentsPage = new CommentsPage(baseFunc);
        String commentsPageTitle = commentsPage.getTitle();
        int commentsPageCommentsCount = commentsPage.getCommentsCount();

        Assertions.assertEquals(articlePageTitle, commentsPageTitle, "Wrong title!");
        Assertions.assertEquals(articlePageCommentsCount, commentsPageCommentsCount, "Wrong comments count!");
    }
    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}
