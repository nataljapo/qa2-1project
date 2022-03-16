package tickets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pageobject.pages.BaseFunc;

public class SuccessfulBookingPage {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private final String SUCCESS_MESSAGE_TXT = "Thank You for flying with us!";
    private final By SUCCESS_MESSAGE =  By.xpath(".//div[@class = 'finalTxt']");
    private BaseFunc baseFunc;

    public SuccessfulBookingPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public boolean isSuccessMessagePresents() {
        LOGGER.info("Checking successful message text");
        return baseFunc.findElement(SUCCESS_MESSAGE).getText().equals(SUCCESS_MESSAGE_TXT);
    }
}
