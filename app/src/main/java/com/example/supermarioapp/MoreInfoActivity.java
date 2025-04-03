package com.example.supermarioapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MoreInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Character character = (Character) getIntent().getSerializableExtra("character");
        if (character == null) return;

        TextView nameTextView = findViewById(R.id.character_name);
        TextView roleTextView = findViewById(R.id.character_role);
        TextView descriptionView = findViewById(R.id.character_description);

        nameTextView.setText(character.getName());
        roleTextView.setText("Role: " + character.getRole());
        descriptionView.setText(character.getDescription());
    }
}