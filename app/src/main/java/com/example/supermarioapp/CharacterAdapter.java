package com.example.supermarioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private Context context;

    public CharacterAdapter(List<Character> characterList, Context context) {
        this.characterList = characterList;
        this.context = context;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());

        Glide.with(context)
                .load(character.getImage())
                .into(holder.characterImageView);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView characterImageView;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.character_name);
            characterImageView = itemView.findViewById(R.id.character_image);
        }
    }
}
