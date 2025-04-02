package com.example.supermarioapp;

import java.io.Serializable;

public class Character implements Serializable {
    private int id;
    private String name;
    private String role;
    private String description;
    private String image;
    private String webUrl;

    public Character(int id, String name, String role, String description, String image, String webUrl) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }
}
