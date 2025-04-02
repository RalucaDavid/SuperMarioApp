package com.example.supermarioapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "super-mario.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "characters";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name TEXT, description TEXT, image TEXT, webUrl TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", character.getName());
        values.put("description", character.getDescription());
        values.put("image", character.getImage());
        values.put("webUrl", character.getWebUrl());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", character.getName());
        values.put("description", character.getDescription());
        values.put("image", character.getImage());
        values.put("webUrl", character.getWebUrl());
        db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(character.getId())});
        db.close();
    }

    public void deleteCharacter(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Character> getAllCharacters() {
        List<Character> characterList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Character character = new Character(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                characterList.add(character);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return characterList;
    }

}
