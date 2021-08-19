package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class NotificationDatasetBeta {
    String to;
    NotificationDatasetAlpha data;


    public NotificationDatasetBeta(String to, NotificationDatasetAlpha data) {
        this.to = to;
        this.data = data;
    }

    public NotificationDatasetBeta() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public NotificationDatasetAlpha getData() {
        return data;
    }

    public void setData(NotificationDatasetAlpha data) {
        this.data = data;
    }
}
