package homework.pageobject.delfi.model;

import org.openqa.selenium.WebElement;

public class Article {
    private String title;
    private int commentsCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setCommentsCount(WebElement commentsCount) {
        String commentsToParse = commentsCount.getText();
        commentsToParse = commentsToParse.substring(1, commentsToParse.length() - 1);
        this.commentsCount = Integer.parseInt(commentsToParse);
    }
}
