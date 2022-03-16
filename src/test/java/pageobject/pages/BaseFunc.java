package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseFunc {
    private WebDriver browser;
    private WebDriverWait wait;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public BaseFunc() {
        LOGGER.info("Starting Web Browser");
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {
        LOGGER.info("Open page by: " + url);
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        browser.get(url);
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by locator " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement webElement) {
        LOGGER.info("Clicking on web element");
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public List<WebElement> findElements(By locator) {
        LOGGER.info("Getting list of web elements by locator " + locator);
        return browser.findElements(locator);
    }

    public List<WebElement> findElements(WebElement parent, By child) {
        LOGGER.info("Getting list of child elements");
        return parent.findElements(child);
    }

    public String getText(WebElement parent, By child) {
        LOGGER.info("Getting text for child element");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child));
        return parent.findElement(child).getText();
    }

    public String getText(By locator) {
        LOGGER.info("Getting text for web element by locator " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return browser.findElement(locator).getText();
    }

    public void closeBrowser() {
        LOGGER.info("Closing browser window");
        browser.close();
    }

    public WebElement findElement(By locator) {
        LOGGER.info("Getting web element by locator " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public  WebElement findElement (WebElement parent, By child) {
        LOGGER.info("Getting child element");
        return parent.findElement(child);
    }

    public void select(By locator, String value) {
        LOGGER.info("Selecting " + value + " from dropdown by locator " + locator);
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    public void selectByVisibleText(By locator, String text) {
        LOGGER.info("Selecting " + text + " from dropdown by locator " + locator);
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }

    public void type(By locator, String text) {
        LOGGER.info("Typing " + text + " in " + locator);
        WebElement inputField = findElement(locator);
        inputField.clear();
        inputField.sendKeys(text);
    }

    public void type(By locator, int text) {
        LOGGER.info("Typing " + text + " in " + locator);
        type(locator, String.valueOf(text));
    }

    public void waitUntilElementsCountAtLeast(By locator, int count) {
        LOGGER.info("Waiting for the number of elements to be more than " + count);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, count));
    }
}
