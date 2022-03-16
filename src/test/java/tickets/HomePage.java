package tickets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pageobject.pages.BaseFunc;

public class HomePage {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private final By AIRPORT_FROM = By.id("afrom");
    private final By AIRPORT_TO = By.id("bfrom");
    private final By GO_BTN = By.xpath(".//span[@class = 'gogogo']");
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectAirports(String from, String to) {
        LOGGER.info("Selecting airports");
        baseFunc.select(AIRPORT_FROM, from);
        baseFunc.select(AIRPORT_TO, to);
    }

    public void pressGoBtn() {
        LOGGER.info("Pressing GoGoGo button");
        baseFunc.click(GO_BTN);
    }
}
