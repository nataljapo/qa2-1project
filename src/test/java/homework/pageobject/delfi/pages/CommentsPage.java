package homework.pageobject.delfi.pages;

import org.openqa.selenium.By;
import pageobject.pages.BaseFunc;

public class CommentsPage {
    private final By TITLE = By.xpath(".//h1[@class = 'article-title']");
    private BaseFunc baseFunc;

    public CommentsPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        return baseFunc.getText(TITLE).trim();
    }
}
