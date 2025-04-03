package com.example.supermarioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CharacterClick {
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> characterList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_of_characters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        loadCharacters();

        Button editButton = findViewById(R.id.button_edit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrudActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCharacters();
    }

    private void loadCharacters() {
        characterList = dbHelper.getAllCharacters();

        characterAdapter  = new CharacterAdapter(characterList, this, this::onCharacterClick);
        recyclerView.setAdapter(characterAdapter);
    }

    @Override
    public void onCharacterClick(int id) {
        Character selectedCharacter = dbHelper.getCharacterById(id);
        Intent intent = new Intent(this, CharacterDetailsActivity.class);
        intent.putExtra("character", selectedCharacter);
        startActivity(intent);
    }
}