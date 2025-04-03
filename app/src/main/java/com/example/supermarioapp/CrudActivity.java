package com.example.supermarioapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CrudActivity extends AppCompatActivity implements CharacterAdapterCrud.OnCharacterSelectedListener {

    private DatabaseHelper db;
    private EditText editTextName, editTextRole, editTextDescription, editTextImage, editTextUrl;
    private Button buttonAdd;
    private ListView listViewCharacters;
    private CharacterAdapterCrud adapter;
    private List<Character> characterList;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        db = new DatabaseHelper(this);
        editTextName = findViewById(R.id.editTextName);
        editTextRole = findViewById(R.id.editTextRole);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextImage = findViewById(R.id.editTextImage);
        editTextUrl = findViewById(R.id.editTextUrl);

        buttonAdd = findViewById(R.id.buttonAdd);
        listViewCharacters = findViewById(R.id.listViewCharacters);

        characterList = new ArrayList<>();
        adapter = new CharacterAdapterCrud(this, characterList, db, this);
        listViewCharacters.setAdapter(adapter);

        loadCharacters();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String role = editTextRole.getText().toString();
            String description = editTextDescription.getText().toString();
            String image = editTextImage.getText().toString();
            String url = editTextUrl.getText().toString();

            if (!name.isEmpty() && !role.isEmpty() && !description.isEmpty() && !image.isEmpty() && !url.isEmpty()) {
                db.insertCharacter(name, role, description, image, url);
                Toast.makeText(CrudActivity.this, "Changes Added!", Toast.LENGTH_SHORT).show();
                clearInputs();
                loadCharacters();
            }
        });
    }

    private void loadCharacters() {
        characterList.clear();
        characterList.addAll(db.getAllCharacters());
        adapter.notifyDataSetChanged();
    }

    private void clearInputs() {
        editTextName.setText("");
        editTextRole.setText("");
        editTextDescription.setText("");
        editTextImage.setText("");
        editTextUrl.setText("");
    }

    @Override
    public void onCharacterSelected(Character character) {
        editTextName.setText(character.getName());
        editTextRole.setText(character.getRole());
        editTextDescription.setText(character.getDescription());
        editTextImage.setText(character.getImage());
        editTextUrl.setText(character.getWebUrl());
    }

    @Override
    public void onCharacterDeleted(int id) {
        Toast.makeText(this, "Character Deleted!", Toast.LENGTH_SHORT).show();
        loadCharacters();
    }
}