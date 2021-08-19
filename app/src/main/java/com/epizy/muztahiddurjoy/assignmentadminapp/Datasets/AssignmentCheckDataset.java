package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class AssignmentCheckDataset {
    String name;
    String cleass;
    String roll;
    String section;
    String subject;
    String submitted_to;
    String title;
    String url;
    String time;

    public AssignmentCheckDataset() {
    }

    public AssignmentCheckDataset(String name, String cleass, String roll, String section, String subject, String submitted_to, String title, String url, String time) {
        this.name = name;
        this.cleass = cleass;
        this.roll = roll;
        this.section = section;
        this.subject = subject;
        this.submitted_to = submitted_to;
        this.title = title;
        this.url = url;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCleass() {
        return cleass;
    }

    public void setCleass(String cleass) {
        this.cleass = cleass;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubmitted_to() {
        return submitted_to;
    }

    public void setSubmitted_to(String submitted_to) {
        this.submitted_to = submitted_to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
