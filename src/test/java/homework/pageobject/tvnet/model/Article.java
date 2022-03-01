package homework.pageobject.tvnet.model;

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
    public void setTitle(WebElement webElement) {
        String titleToParse = webElement.getText();
        String[] title = titleToParse.split(" ");
        String lastWord = title[title.length - 1];
        titleToParse = titleToParse.substring(0, titleToParse.length() - lastWord.length()).trim();
        this.title = titleToParse;
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
