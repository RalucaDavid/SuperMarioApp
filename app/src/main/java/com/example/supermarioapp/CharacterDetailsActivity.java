package com.example.supermarioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CharacterDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        Character character = (Character) getIntent().getSerializableExtra("character");
        if (character == null) return;

        TextView nameTextView = findViewById(R.id.character_name);
        TextView roleTextView = findViewById(R.id.character_role);
        ImageView imageView = findViewById(R.id.character_image);

        nameTextView.setText(character.getName());
        roleTextView.setText(character.getRole());

        Glide.with(this)
                .load(character.getImage())
                .into(imageView);

        Button moreInfoButton = findViewById(R.id.button_more_info);
        Button webButton = findViewById(R.id.button_web_activity);

        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterDetailsActivity.this, MoreInfoActivity.class);
                intent.putExtra("character", character);
                startActivity(intent);
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterDetailsActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }
}