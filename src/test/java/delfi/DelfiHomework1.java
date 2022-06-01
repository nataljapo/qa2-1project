package delfi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DelfiHomework1 {
    private final String HOME_PAGE_URL = "http://delfi.lv";
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By HOME_PAGE_ARTICLES = By.tagName("article");
    private final By HOME_PAGE_ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By ARTICLE_PAGE_TITLE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-19')]");
    private final By COMMENTS_PAGE_TITLE = By.xpath(".//h1[@class = 'article-title']");
    private WebDriver browser;

    @Test
    public void titleCheck() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));

        browser.get(HOME_PAGE_URL);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));
        browser.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> homePageArticles = browser.findElements(HOME_PAGE_ARTICLES);
        WebElement homePageArticle = homePageArticles.get(9);
        String homePageArticleTitle = homePageArticle.findElement(HOME_PAGE_ARTICLE_TITLE).getText().trim();
        System.out.println(homePageArticleTitle);

        List<WebElement> homePageArticleTitles = browser.findElements(HOME_PAGE_ARTICLE_TITLE);
                for(int i = 0; i < homePageArticleTitles.size(); i++) {
            if (!homePageArticleTitles.get(i).getText().isEmpty()) {
                System.out.println(i + " " + homePageArticleTitles.get(i).getText());
            }
        }

        homePageArticle.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE_PAGE_TITLE));
        String articlePageTitle = browser.findElement(ARTICLE_PAGE_TITLE).getText().trim();
        System.out.println(articlePageTitle);

        if (!browser.findElements(ARTICLE_PAGE_TITLE_COMMENTS).isEmpty()) {
            browser.findElement(ARTICLE_PAGE_TITLE_COMMENTS).click();
        }

        if (!browser.findElements(COMMENTS_PAGE_TITLE).isEmpty()) {
            String commentsPageTitle = browser.findElement(COMMENTS_PAGE_TITLE).getText().trim();
            System.out.println(commentsPageTitle);
            Assertions.assertEquals(homePageArticleTitle, commentsPageTitle, "Wrong title!");
        }
        Assertions.assertEquals(homePageArticleTitle, articlePageTitle, "Wrong title!");
    }

    @AfterEach
    public void closeBrowser() {
        browser.close();
    }
}
