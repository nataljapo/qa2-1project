package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {

    @Test
    public void firstTvNetTest() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();

        browser.get("http://tvnet.lv");

        By acceptButton = By.id("button");
        By emailInput = By.name("user{email}");
        By xpathButton = By.xpath(".//button[@mode = 'primary']");

        browser.findElement(xpathButton).click();
    }
}
