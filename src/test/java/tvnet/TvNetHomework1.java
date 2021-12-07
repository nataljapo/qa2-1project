package tvnet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class TvNetHomework1 {
    private final String HOME_PAGE_URL = "http://tvnet.lv";
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE_TITLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By HOME_PAGE_ARTICLES = By.tagName("article");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//span[contains(class, 'list-article__comment')]");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[@itemprop = 'headline name']");
    private final By ARTICLE_PAGE_TITLE_COMMENTS = By.xpath(".//a[contains(@class, 'item--comments')]");
    private WebDriver browser;

    @Test
    public void titleCheck() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        browser = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        browser.manage().window().maximize();

        browser.get(HOME_PAGE_URL);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES_BTN));
        browser.findElement(ACCEPT_COOKIES_BTN).click();

        List<WebElement> homePageArticles = browser.findElements(HOME_PAGE_ARTICLES);
        WebElement homePageArticle = homePageArticles.get(27);
        String homePageTitleText = homePageArticle.findElement(ARTICLE_TITLE).getText();

        int homePageCommentsAmount = 0;

        if (!homePageArticle.findElements(HOME_PAGE_COMMENTS).isEmpty()) {
            String homePageComments = homePageArticle.findElement(HOME_PAGE_COMMENTS).getText();
            homePageComments = homePageComments.substring(1, homePageComments.length() - 1);
            homePageCommentsAmount = Integer.parseInt(homePageComments);
        }

        homePageArticle.click();
        WebElement articlePageTitle = browser.findElement(ARTICLE_PAGE_TITLE);
        String articlePageTitleText = articlePageTitle.getText();

        WebElement articlePageComments = browser.findElement(ARTICLE_PAGE_TITLE_COMMENTS);
        int articlePageCommentsAmount = 0;
        if (!articlePageComments.findElements(ARTICLE_PAGE_TITLE_COMMENTS).isEmpty()) {
            String articlePageCommentsText = articlePageComments.getText();
            articlePageCommentsAmount = Integer.parseInt(articlePageCommentsText);
        }

        Assertions.assertTrue(homePageTitleText.startsWith(articlePageTitleText), "Wrong title!");
        Assertions.assertEquals(homePageCommentsAmount, articlePageCommentsAmount, "Wrong amount!");

    }

    @AfterEach
    public void closeBrowser() {

        browser.close();
    }

}
