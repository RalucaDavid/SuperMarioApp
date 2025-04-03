package com.example.supermarioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class CharacterAdapterCrud extends ArrayAdapter<Character> {

    private final List<Character> characterList;
    private final Context context;
    private final DatabaseHelper db;
    private final OnCharacterSelectedListener listener;

    public interface OnCharacterSelectedListener {
        void onCharacterSelected(Character character);
        void onCharacterDeleted(int id);
    }

    public CharacterAdapterCrud(Context context, List<Character> characters, DatabaseHelper db, OnCharacterSelectedListener listener) {
        super(context, 0, characters);
        this.context = context;
        this.characterList = characters;
        this.db = db;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Character character = characterList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.character_list_delete_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewCharacterName);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewName.setText(character.getName());

        // Click pe nume → se setează în inputuri
        textViewName.setOnClickListener(v -> listener.onCharacterSelected(character));

        // Click pe delete → se șterge din DB și se actualizează lista
        buttonDelete.setOnClickListener(v -> {
            db.deleteCharacter(character.getId());
            listener.onCharacterDeleted(character.getId());
        });

        return convertView;
    }
}