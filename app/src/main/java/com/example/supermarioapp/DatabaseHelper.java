package com.example.supermarioapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "supermario-db.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "characters";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, role TEXT, description TEXT, image TEXT, webUrl TEXT)";
        db.execSQL(createTable);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("name", "Mario");
        values.put("role", "Hero");
        values.put("description", "The main character of the Super Mario series.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/3/3e/MPSS_Mario.png/revision/latest?cb=20211102010317&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Mario");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Luigi");
        values.put("role", "Sidekick");
        values.put("description", "Mario's brother, often playing a supporting role.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/7/76/SMPJ_Luigi.png/revision/latest?cb=20240911024916&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Luigi");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Princess Peach");
        values.put("role", "Damsel");
        values.put("description", "The princess of the Mushroom Kingdom.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/d/db/Peach_%28Mario_Portal%29.png/revision/latest?cb=20230202133309&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Princess_Peach");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Bowser");
        values.put("role", "Villain");
        values.put("description", "The King of the Koopas, Mario's arch-nemesis.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/4/4b/Bowser_%28Mario_Portal%29.png/revision/latest?cb=20230107054523&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Bowser");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Goomba");
        values.put("role", "Enemy");
        values.put("description", "The Goomba is a common enemy in the Mario series, often encountered in levels.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/d/d5/GoombaNSMB.png/revision/latest?cb=20110724131649&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Goomba");
        db.insert(TABLE_NAME, null, values);
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
                        cursor.getString(4),
                        cursor.getString(5)
                );
                characterList.add(character);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return characterList;
    }

    public Character getCharacterById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Character character = new Character(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );

                cursor.close();
                db.close();
                return character;
            }
            cursor.close();
        }

        db.close();
        return null;
    }


}
