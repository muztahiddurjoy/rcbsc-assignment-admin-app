package com.epizy.muztahiddurjoy.assignmentadminapp.Datasets;

public class EmailDataAlpha {
    String user_id;
    String service_id;
    String template_id;
    TemplateParams template_params;

    public EmailDataAlpha(String user_id, String service_id, String template_id, TemplateParams template_params) {
        this.user_id = user_id;
        this.service_id = service_id;
        this.template_id = template_id;
        this.template_params = template_params;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public TemplateParams getTemplate_params() {
        return template_params;
    }

    public void setTemplate_params(TemplateParams template_params) {
        this.template_params = template_params;
    }
}
