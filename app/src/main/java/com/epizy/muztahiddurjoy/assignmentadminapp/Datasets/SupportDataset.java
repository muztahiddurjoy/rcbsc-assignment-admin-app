package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class SupportDataset {
    String email;
    String description;
    String imageurl;

    public SupportDataset(String email, String description, String imageurl) {
        this.email = email;
        this.description = description;
        this.imageurl = imageurl;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
