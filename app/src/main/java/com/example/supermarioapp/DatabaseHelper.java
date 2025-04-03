package com.example.supermarioapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "super-mario-db.db";
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
        values.put("description", "" +
                "Mario is an Italian-American human who's occupation is a plumber. " +
                "He wears his trademark bright red hat with the letter M on the front, " +
                "white gloves, red shirt, blue overalls with two gold buttons and brown " +
                "shoes. However, in his first appearance in Donkey Kong as well as his early " +
                "appearances prior to Super Mario Bros. 3 and in the three classics DIC Entertainment " +
                "cartoons, Mario wore a blue shirt and red overalls, which is the opposite of his " +
                "current outfit."
        );
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/3/3e/MPSS_Mario.png/revision/latest?cb=20211102010317&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Mario");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Luigi");
        values.put("role", "Sidekick");
        values.put("description", "Luigi is Mario's younger brother. " +
                "With that said, it's obvious that they would look the same. " +
                "Unlike Mario's red clothes, Luigi wears a green undershirt, and dark blue overalls." +
                " He is taller and thinner than Mario, has a longer neck, an ovoidal head instead of a " +
                "round head, and has a different style of mustache and sideburns. He also wears a green hat " +
                "that has a L on it. For some reason, in many games Luigi is referred to as names like \"the green" +
                " guy\" or \"the man in green\".");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/7/76/SMPJ_Luigi.png/revision/latest?cb=20240911024916&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Luigi");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Princess Peach");
        values.put("role", "Damsel");
        values.put("description", "" +
                "Peach is generally known for being sweet, graceful, calm, sophisticated, and well-mannered." +
                " Usually, she never shows aggressiveness when she fights or confronts her enemies." +
                " She is also willing to have Bowser team up with her and the Mario Bros. when a more dangerous" +
                " villain attacks the Mushroom Kingdom. She's also very friendly towards Luigi, despite not " +
                "interacting with him as much as Mario.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/d/db/Peach_%28Mario_Portal%29.png/revision/latest?cb=20230202133309&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Princess_Peach");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Bowser");
        values.put("role", "Villain");
        values.put("description", "Bowser is a huge, burl, green-shelled Koopa with spikes, horns, " +
                "and hair. His bushy eyebrows and mane are fittingly a bright, fiery red-orange and has" +
                " bright red eyes. He sports two small, horns, many spikes on his shell (similar to a Spiny)" +
                " with brown-orange spike-rings, as well as along his tail (minus the spike-rings). " +
                "He wears several spikes collars around his neck and arms. His neck collar in some games " +
                "such as Mario Kart Tour is also implied to be part of his shell.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/4/4b/Bowser_%28Mario_Portal%29.png/revision/latest?cb=20230107054523&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Bowser");
        db.insert(TABLE_NAME, null, values);

        values.clear();
        values.put("name", "Goomba");
        values.put("role", "Enemy");
        values.put("description", " Goombas resemble small brown mushrooms and are a fungus-based species like" +
                " Toads, Amanitas, Spooks, and Shroobs. Goombas are physically weak and are not much of a threat" +
                " to Mario or Luigi, since a single stomp usually defeats them, although a number of different" +
                " Goomba variants have emerged throughout the years; however, they do drop hints of being much" +
                " stronger and more competent than their appearances suggest, as in the case of Captain Goomba.");
        values.put("image", "https://static.wikia.nocookie.net/nintendo/images/d/d5/GoombaNSMB.png/revision/latest?cb=20110724131649&path-prefix=en");
        values.put("webUrl", "https://nintendo.fandom.com/wiki/Goomba");
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertCharacter(String name, String role, String description, String image, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLE_NAME + " WHERE name = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();

            Character existingCharacter = new Character(id, name, role, description, image, url);
            updateCharacter(existingCharacter);

            db.close();
            return;
        }

        cursor.close();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("role", role);
        values.put("description", description);
        values.put("image", image);
        values.put("webUrl", url);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", character.getName());
        values.put("role", character.getRole());
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
