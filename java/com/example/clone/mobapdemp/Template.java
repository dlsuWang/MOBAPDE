package com.example.clone.mobapdemp;

/**
 * Created by user1 on 3/14/2016.
 */
public class Template {

    private int id;
    private String template;
    public static final String TABLE_NAME = "template";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEMPLATE = "template";

    public Template() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
