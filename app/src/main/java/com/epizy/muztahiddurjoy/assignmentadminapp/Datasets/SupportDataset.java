package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class SupportDataset {
    String email;
    String description;

    public SupportDataset(String email, String description) {
        this.email = email;
        this.description = description;
    }

    public SupportDataset() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
