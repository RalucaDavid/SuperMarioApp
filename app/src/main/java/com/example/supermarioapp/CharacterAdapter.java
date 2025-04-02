package com.example.supermarioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private Context context;
    private final CharacterClick listener;

    public CharacterAdapter(List<Character> characterList, Context context, CharacterClick listener) {
        this.characterList = characterList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.roleTextView.setText(character.getRole());

        Glide.with(context)
                .load(character.getImage())
                .into(holder.characterImageView);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCharacterClick(character.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView roleTextView;
        ImageView characterImageView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.character_name);
            roleTextView = itemView.findViewById(R.id.character_role);
            characterImageView = itemView.findViewById(R.id.character_image);
        }
    }
}
