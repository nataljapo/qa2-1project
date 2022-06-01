package pageobject;

import org.junit.jupiter.api.Test;
import pageobject.model.Article;
import pageobject.pages.BaseFunc;
import pageobject.pages.HomePage;

public class ArticleTests {
    private final String HOME_PAGE_URL = "tvnet.lv";

    @Test
    public void titleAndCommentsCountCheck() {
        BaseFunc baseFunc = new BaseFunc();

        baseFunc.openUrl(HOME_PAGE_URL);
        HomePage homePage = new HomePage(baseFunc);

        homePage.acceptCookies();

        Article article = homePage.getArticleById(1);
        System.out.println(article.getCommentsCount());
    }
}
