package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class TemplateParams {
    String student_name;
    String to_email;

    public TemplateParams(String student_name, String to_email) {
        this.student_name = student_name;
        this.to_email = to_email;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTo_email() {
        return to_email;
    }

    public void setTo_email(String to_email) {
        this.to_email = to_email;
    }
}
