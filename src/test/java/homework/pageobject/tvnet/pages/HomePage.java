package homework.pageobject.tvnet.pages;

import homework.pageobject.tvnet.model.Article;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.pages.BaseFunc;

import java.util.List;

public class HomePage {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By COMMENTS_COUNT = By.xpath(".//span[contains(@class, 'article__comment')]");
    private final By ARTICLE_TITLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By ARTICLE = By.tagName("article");
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void acceptCookies() {
        baseFunc.click(ACCEPT_COOKIES_BTN);
    }

    public Article getArticleById(int articleNr) {
        List<WebElement> articlesElements = baseFunc.findElements(ARTICLE);
        Assertions.assertFalse(articlesElements.isEmpty(), "There are no articles!");
        WebElement currentArticle = articlesElements.get(articleNr - 1);
        return mapArticle(currentArticle);
    }

    private Article mapArticle(WebElement we) {
        Article article = new Article();
        List<WebElement> counters = baseFunc.findElements(we, COMMENTS_COUNT);
        Assertions.assertTrue(counters.size() < 2, "There is more than 1 counter!");
        if (counters.isEmpty()) {
            article.setCommentsCount(0);
            article.setTitle(baseFunc.getText(we, ARTICLE_TITLE));
        } else {
            article.setCommentsCount(counters.get(0));
            article.setTitle(baseFunc.findElement(we, ARTICLE_TITLE));
        }
        return article;
    }

    public void openArticle(int articleNr) {
        WebElement currentArticle = baseFunc.findElements(ARTICLE).get(articleNr - 1);
        baseFunc.click(currentArticle);
    }
}
