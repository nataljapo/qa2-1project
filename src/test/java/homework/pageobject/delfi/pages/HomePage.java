package homework.pageobject.delfi.pages;

import homework.pageobject.delfi.model.Article;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.pages.BaseFunc;

import java.util.List;

public class HomePage {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE = By.tagName("article");
    private final By ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");

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
        } else {
            article.setCommentsCount(counters.get(0));
        }
        String homePageArticleTitle = baseFunc.getText(we, ARTICLE_TITLE).trim();
        article.setTitle(homePageArticleTitle);
        return article;
    }

    public void openArticle(int articleNr) {
        WebElement currentArticle = baseFunc.findElements(ARTICLE).get(articleNr - 1);
        baseFunc.click(currentArticle);
    }

    public void getAllTitles() {
        for(int i = 0; i < baseFunc.findElements(ARTICLE_TITLE).size(); i++) {
            if (!baseFunc.findElements(ARTICLE_TITLE).get(i).getText().isEmpty()) {
                System.out.println(i + 1 + " " + baseFunc.findElements(ARTICLE_TITLE).get(i).getText());
            }
        }
    }
}
