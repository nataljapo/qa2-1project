package homework.pageobject.delfi.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pageobject.pages.BaseFunc;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@class, 'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-19')]");
    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitle() {
        return baseFunc.getText(TITLE).trim();
    }

    public int getCommentsCount() {
        if (baseFunc.findElements(COMMENTS).isEmpty()) {
            return 0;
        } else {
            String commentsToParse = baseFunc.getText(COMMENTS);
            commentsToParse = commentsToParse.substring(1, commentsToParse.length() - 1);
            return Integer.parseInt(commentsToParse);
        }
    }

    public void openCommentsPage() {
        Assertions.assertFalse(baseFunc.findElements(COMMENTS).isEmpty(), "There is no comments count");
            baseFunc.click(COMMENTS);
    }
  }