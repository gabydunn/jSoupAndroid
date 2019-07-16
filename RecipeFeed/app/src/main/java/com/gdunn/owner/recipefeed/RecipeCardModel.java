package com.gdunn.owner.recipefeed;

public class RecipeCardModel {
    private int id;
    private String title;
    private String imageUri;
    private String description;
    private String associatedURL;
    private String authorName;
    private String authorImage;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

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
