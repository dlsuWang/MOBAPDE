package com.example.clone.mobapdemp;

/**
 * Created by user1 on 3/14/2016.
 */
public class Combination {

    private int id;
    private String combination;
    private int template_id;
    public static final String TABLE_NAME = "combination";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMBINATION = "combination";
    public static final String COLUMN_TEMPLATE_ID = "template_id";

    public Combination() {
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
