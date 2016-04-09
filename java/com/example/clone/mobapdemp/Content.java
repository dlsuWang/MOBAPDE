package com.example.clone.mobapdemp;

/**
 * Created by Clone on 2016. 3. 16..
 */
public class Content {

    private int id;
    private String title;
    private String combination;
    private String content;
    public static final String TABLE_NAME = "content";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMBINATION = "combination";
    public static final String COLUMN_CONTENT = "content";

    public Content(){}

    public Content(String title, String combination, String content){
        this.title = title;
        this.combination = combination;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
