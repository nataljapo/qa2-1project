package homework.pageobject.tvnet.pages;

import org.openqa.selenium.By;
import pageobject.pages.BaseFunc;

public class CommentsPage {
    public final By TITLE = By.xpath(".//h1[@itemprop = 'headline name']");
    private final By COMMENTS = By.xpath(".//span[contains(@class, 'count')]");
    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        return baseFunc.getText(TITLE);
    }

    public int getCommentsCount() {
        if (baseFunc.findElements(COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsToParse = baseFunc.getText(COMMENTS);
            return Integer.parseInt(commentsToParse);
        }
    }
}

