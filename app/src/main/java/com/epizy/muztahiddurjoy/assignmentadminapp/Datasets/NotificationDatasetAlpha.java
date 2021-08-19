package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class NotificationDatasetAlpha {
    private String title;
    private String content;

    public NotificationDatasetAlpha() {
    }

    public NotificationDatasetAlpha(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
