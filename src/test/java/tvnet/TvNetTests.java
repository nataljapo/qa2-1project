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

import java.time.Duration;
import java.util.List;

public class TvNetTests {
    private final String HOME_PAGE_URL = "http://tvnet.lv";
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE_TITLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[@itemprop = 'headline name']");
    private final String GIVEN_TITLE = "Katarā aizturētie Norvēģijas žurnālisti atgriezušies mājās";
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

        List<WebElement> titles = browser.findElements(ARTICLE_TITLE);
        for (WebElement title : titles) {
            if (title.getText().startsWith(GIVEN_TITLE)) {
                title.click();
                break;
            }
        }
        WebElement articlePageTitle = browser.findElement(ARTICLE_PAGE_TITLE);
        Assertions.assertEquals(GIVEN_TITLE, articlePageTitle.getText(), "Wrong title!");
    }

    @AfterEach
    public void closeBrowser() {
        browser.close();
    }
}
