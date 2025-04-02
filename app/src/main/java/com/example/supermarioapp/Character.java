package com.example.supermarioapp;

public class Character {
    private int id;
    private String name;
    private String description;
    private String image;
    private String webUrl;

    public Character(int id, String name, String description, String image, String webUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.webUrl = webUrl;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
