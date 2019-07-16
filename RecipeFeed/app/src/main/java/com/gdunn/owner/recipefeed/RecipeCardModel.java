package com.gdunn.owner.recipefeed;

public class RecipeCardModel {
    private int id;
    private String title;
    private String imageUri;
    private String description;
    private String associatedURL;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssociatedURL() {
        return associatedURL;
    }

    public void setAssociatedURL(String associatedURL) {
        this.associatedURL = associatedURL;
    }
}
