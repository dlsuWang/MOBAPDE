package com.example.clone.mobapdemp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user1 on 3/14/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{

    public static final String SCHEMA = "EmergencyApp";

    public DatabaseOpenHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        //create Template table
        String sql = "CREATE TABLE " + Template.TABLE_NAME + "(" +
                Template.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Template.COLUMN_TEMPLATE + " TEXT" +
                ")";

        db.execSQL(sql);

        //create Cembination table
        sql = "CREATE TABLE " + Combination.TABLE_NAME + "(" +
                Combination.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Combination.COLUMN_COMBINATION + " TEXT, " +
                Combination.COLUMN_TEMPLATE_ID + " INTEGER" +
                ")";

        db.execSQL(sql);
*/
        //create Contact table
        String sql = "CREATE TABLE " + Contact.TABLE_NAME + "(" +
                Contact.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contact.COLUMN_NAME + " TEXT, " +
                Contact.COLUMN_NUMBER + " TEXT" +
                ")";

        db.execSQL(sql);

        //create Content table
        sql = "CREATE TABLE " + Content.TABLE_NAME + "(" +
                Content.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Content.COLUMN_TITLE + " TEXT, " +
                Content.COLUMN_COMBINATION + " TEXT, " +
                Content.COLUMN_CONTENT + " TEXT" +
                ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST " + Template.TABLE_NAME;

        db.execSQL(sql);
        onCreate(db);

    }

    public long insertContent(Content c){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Content.COLUMN_TITLE, c.getTitle());
        cv.put(Content.COLUMN_COMBINATION, c.getCombination());
        cv.put(Content.COLUMN_CONTENT, c.getContent());

        long id = db.insert(Content.TABLE_NAME, null, cv);

        return id;
    }

    public Content queryContent(int id){
        Content content = new Content();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Content.TABLE_NAME, null, " " + Content.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {
            content.setTitle(c.getString(c.getColumnIndex(Content.COLUMN_TITLE)));
            content.setCombination(c.getString(c.getColumnIndex(Content.COLUMN_COMBINATION)));
            content.setContent(c.getString(c.getColumnIndex(Content.COLUMN_CONTENT)));
            content.setId(id);
        }
        else
        {
            content = null;
            Log.d("samletag", "HI");
        }

        return content;
    }

    public Cursor queryAllContent(){

        ArrayList<Content> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Content.TABLE_NAME, null, null, null, null, null, null);

        return c;
    }

    public int updateContent(Content updateContent){

        /*
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Contact.TABLE_NAME, null, " " + Contact.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(contact.getId())}, null, null, null);

        if(c.moveToFirst())
        {
            ContentValues cv = new ContentValues();
            cv.put(Contact.COLUMN_NAME, contact.getName());
            cv.put(Contact.COLUMN_NUMBER, contact.getNumber());

            int rowsAffected = db.update(Contact.TABLE_NAME, cv, " " + Contact.COLUMN_ID + " = ? ",
                    new String[]{String.valueOf(contact.getId())});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }*/

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Content.COLUMN_TITLE, updateContent.getTitle());
        cv.put(Content.COLUMN_COMBINATION, updateContent.getCombination());
        cv.put(Content.COLUMN_CONTENT, updateContent.getContent());


        int rows = db.update(Content.TABLE_NAME, cv,
                " " + Content.COLUMN_ID + "= ? ",
                new String[]{String.valueOf(updateContent.getId())});

        //db.close(); //optional

        return rows;

    }

    public int deleteContent(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Content.TABLE_NAME, null, " " + Content.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {

            int rowsAffected = db.delete(Content.TABLE_NAME," "
                    + Content.COLUMN_ID + " = ? ",new String[]{String.valueOf(id)});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }

    }

    public long insertContact(Contact c){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contact.COLUMN_NAME, c.getName());
        cv.put(Contact.COLUMN_NUMBER, c.getNumber());

        long id = db.insert(Contact.TABLE_NAME, null, cv);

        return id;
    }

    public Contact queryContact(int id){
        Contact contact = new Contact();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Contact.TABLE_NAME, null, " " + Contact.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {
            contact.setName(c.getString(c.getColumnIndex(Contact.COLUMN_NAME)));
            contact.setNumber(c.getString(c.getColumnIndex(Contact.COLUMN_NUMBER)));
            contact.setId(id);
        }
        else
        {
            contact = null;
        }

        return contact;
    }

    public Cursor queryAllContact(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Contact.TABLE_NAME, null, null, null, null, null, null);

        return c;
    }

    public ArrayList<Contact> queryAllContact_array(){

        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Contact.TABLE_NAME, null, null, null, null, null, null);

        if(c.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(c.getString(c.getColumnIndex(Contact.COLUMN_NAME)));
                contact.setNumber(c.getString(c.getColumnIndex(Contact.COLUMN_NUMBER)));
                contact.setId(c.getInt(c.getColumnIndex(Contact.COLUMN_ID)));
                contacts.add(contact);
            } while (c.moveToNext());
        }
        else{
             contacts = null;
            }
        return contacts;

    }

    public int updateContact(Contact updateContact){


        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Contact.TABLE_NAME, null, " " + Contact.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(updateContact.getId())}, null, null, null);

        if(c.moveToFirst())
        {
            ContentValues cv = new ContentValues();
            cv.put(Contact.COLUMN_NAME, updateContact.getName());
            cv.put(Contact.COLUMN_NUMBER, updateContact.getNumber());

            int rowsAffected = db.update(Contact.TABLE_NAME, cv, " " + Contact.COLUMN_ID + " = ? ",
                    new String[]{String.valueOf(updateContact.getId())});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }
/*
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contact.COLUMN_NAME, updateContact.getName());
        cv.put(Contact.COLUMN_NUMBER, updateContact.getNumber());


        int rows = db.update(Contact.TABLE_NAME, cv,
                " " + Contact.COLUMN_ID + "= ? ",
                new String[]{String.valueOf(updateContact.getId())});

        //db.close(); //optional

        return rows;
*/
    }

    public int deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Contact.TABLE_NAME, null, " " + Contact.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {

            int rowsAffected = db.delete(Contact.TABLE_NAME," "
                    + Contact.COLUMN_ID + " = ? ",new String[]{String.valueOf(id)});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }

    }
/*
    public long insertTemplate(Template template){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Template.COLUMN_TEMPLATE, template.getTemplate());

        long id = db.insert(Combination.TABLE_NAME, null, cv);

        return id;
    }

    public Template queryTemplate(int id){
        Template template = new Template();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Template.TABLE_NAME, null, " " + Template.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {
            template.setTemplate(c.getString(c.getColumnIndex(Template.COLUMN_TEMPLATE)));
            template.setId(id);
        }
        else
        {
            template= null;
        }

        return template;
    }

    public Cursor queryAllCombination(){

        ArrayList<Contact> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Combination.TABLE_NAME, null, null, null, null, null, null);

        return c;
    }

    public int updateCombination(Combination combination){

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Combination.TABLE_NAME, null, " " + Combination.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(combination.getId())}, null, null, null);

        if(c.moveToFirst())
        {
            ContentValues cv = new ContentValues();
            cv.put(Combination.COLUMN_COMBINATION, combination.getCombination());
            cv.put(Combination.COLUMN_TEMPLATE_ID, combination.getTemplate_id());

            int rowsAffected = db.update(Combination.TABLE_NAME, cv, " " + Combination.COLUMN_ID + " = ? ",
                    new String[]{String.valueOf(combination.getId())});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }
    }

    public int deleteCombination(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Combination.TABLE_NAME, null, " " + Combination.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {

            int rowsAffected = db.delete(Combination.TABLE_NAME," "
                    + Combination.COLUMN_ID + " = ? ",new String[]{String.valueOf(id)});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }

    }

    public long insertCombination(Combination combination){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Combination.COLUMN_COMBINATION, combination.getCombination());
        cv.put(Combination.COLUMN_TEMPLATE_ID, combination.getTemplate_id());

        long id = db.insert(Combination.TABLE_NAME, null, cv);

        return id;
    }

    public Combination queryCombination(int id){
        Combination combination = new Combination();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(Combination.TABLE_NAME, null, " " + Combination.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {
            combination.setCombination(c.getString(c.getColumnIndex(Combination.COLUMN_COMBINATION)));
            combination.setTemplate_id(Integer.parseInt(c.getString(c.getColumnIndex(Combination.COLUMN_TEMPLATE_ID))));
            combination.setId(id);
        }
        else
        {
            combination = null;
        }

        return combination;
    }

    public Cursor queryAllTemplate(){

        ArrayList<Contact> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Template.TABLE_NAME, null, null, null, null, null, null);

        return c;
    }

    public int updateTemplate(Template template){

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Template.TABLE_NAME, null, " " + Template.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(template.getId())}, null, null, null);

        if(c.moveToFirst())
        {
            ContentValues cv = new ContentValues();
            cv.put(Template.COLUMN_TEMPLATE, template.getTemplate());

            int rowsAffected = db.update(Combination.TABLE_NAME, cv, " " + Combination.COLUMN_ID + " = ? ",
                    new String[]{String.valueOf(template.getId())});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }
    }

    public int deleteTemplate(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(Template.TABLE_NAME, null, " " + Template.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null);

        if(c.moveToFirst())
        {

            int rowsAffected = db.delete(Template.TABLE_NAME," "
                    + Template.COLUMN_ID + " = ? ",new String[]{String.valueOf(id)});

            return rowsAffected;
        }
        else
        {
            return  -1;
        }

    }*/

}
